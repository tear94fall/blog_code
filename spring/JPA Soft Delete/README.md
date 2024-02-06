# [JPA] Soft Delete 기능 사용하기

![intro](./images/spring-data-jpa.png)

## 1. Soft Delete 기능 사용하기

```java
@Getter
@Entity
@SQLDelete(sql = "UPDATE member SET deleted = true where member_id = ?")
@SQLRestriction("deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String email;
    private int age;

    private boolean deleted = Boolean.FALSE;

    ...
}
```

스프링 부트3를 기준으로`@Where`이 Deprecated 됨에 따라 `@SQLRestriction` 를 대신 사용하도록 한다.   
`@Where` 어노테이션과 마찬가지로 where절에 설정한 쿼리가 추가로 나가도록 하는 기능을 제공한다.   

```java
@Test
@Transactional
@DisplayName("조회")
public void searchMemberTest() {
    //given
    Member member = memberRepository.save(createMember("asdf", "asdf@test.com", 25));

    //when
    em.flush();
    em.clear();

    Member findMember = memberRepository.findById(member.getId())
            .orElseThrow(IllegalArgumentException::new);

    //then
    assertEquals(member.getUsername(), findMember.getUsername());
}
```

![intro](./images/deleted-where-query.png)

```java
@Test
@Transactional
@DisplayName("soft delete 테스트")
public void softDeleteTest() {
    //given
    Member member = memberRepository.save(createMember("asdf", "asdf@test.com", 25));

    //when
    memberRepository.delete(member);
    em.flush();
    em.clear();

    //then
    assertThrows(IllegalArgumentException.class, () -> memberRepository.findById(member.getId())
            .orElseThrow(IllegalArgumentException::new));
}
```

![intro](./images/soft-delete-query.png)

## 2. 상속관계 에서 Soft Delete 기능 사용하기

```java
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE item SET deleted = true where item_id = ?")
@SQLRestriction("deleted = false")
@DiscriminatorColumn
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private int price;

    private boolean deleted = Boolean.FALSE;
}
```

```java
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("ALBUM")
public class Book extends Item {

    private String author;
    private String isbn;

    ...
}
```

![intro](./images/super-class-mapping-delete-query.png)


```java
@OnDelete(action = OnDeleteAction.CASCADE)
public class Book extends Item {
    ...
}
```

상속 관게에서 Soft delete 기능을 사용 하기 위해서는 `@OnDelete` 어노테이션을 통해서 자식 클래스에서도 실제 삭제가 일어나지 않도록 한다.   
만일 상속 관계에서 위의 코드를 추가 하지 않으면, 실제 데이터가 삭제되므로 주의 하도록 한다.   

![intro](./images/super-class-mapping-update-query.png)