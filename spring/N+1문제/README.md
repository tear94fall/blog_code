# JPA N+1 문제 해결하기

## 1. N+1 문제란 무엇인가?

N+1 문제란? 연관관계에서 발생하는 문제로 연관관계가 설정된 엔티티를 조회하는 경우  
조회된 데이터의 갯수(N)만큼 연관관계의 조회 쿼리가 추가로 발생하는 문제이다.  

N+1 문제가 무엇인지 좀 더 쉽게 섦영하기 위해서 두개의 테이블과 코드를 가지고 설명을 하도록 하겠다.  
설명에 사용할 테이블은 사용자 테이블과 팀 테이블 2개이다.   

사용자(User) 테이블

| User ||
|:----:|:----:|
| id   | Long   |
| name | String | 
| team | Team   |

팀(Team) 테이블

| Team ||
|:----:|:----:|
| id   | Long   |
| name | String | 
| users | List\<User\> |

사용자는 한개의 팀에만 소속되어야 하고, 팀은 여러명의 팀원(사용자)를 가질 수 있다.  

| 팀 이름 | 팀원 |
|:-----:|:-----:|
| 백엔드 개발팀 | 팀원1, 팀원2, 팀원3, 팀원4, ... |

사용자는 팀과 `@ManyToOne` 연관관계를 가진다.   
팀과 사용자는 `@OneToMany` 연관관게를 가진다.  

테이블을 클래스로 나타내면 다음과 같다.  

```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
}
```

```java
@Entity
public class Team {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(fetch = FetchType.EAGER) // 즉시 로딩
    private List<User> users = new ArrayList<>();
}
```

연관관계의 fetch 타입을 지연 로딩으로 설정 해준다.  
지연 로딩/즉시 로딩의 차이는, 즉시 로딩의 경우  
데이터를 조회할때 연관된 데이터까지 한번에 불러오는 방법이며,  
지연 로딩은 데이터 조회시 연관된 데이터에 프록시 객체(임시객체)를 생성하고,  
이 연관된 데이터를 참조하는 시점에 객체를 가져오는 방법이다.   
예제에서는 보다 빠르게 N+1을 발생시켜야 하므로 즉시로딩을 사용하도록 한다.  

전체 팀을 조회해보기 전에, N+1 문제를 눈으로 확인하기 위해 설정 하나를 추가해주도록 하자.  
실제 구동되는 쿼리를 눈으로 보기 위해서 `application.yaml` 파일에 아래 내용을 추가해준다.  

```yaml
spring:
  jpa:
    properties:
      hibernate:
        show_sql: true    # 수행되는 SQL문을 보여준다.
        format_sql: true  # 수행되는 SQL을 보기 쉽게 출력해준다.
```

이제 전체 팀을 조회해보자.  

```java
teamRepository.findAll();
```

이제 실제 수행된 쿼리문을 확인해보자.  

```sql

```

우리는 여기서 무언가 이상함을 눈치챌 수 있다.   
실제로 팀을 조회하는 쿼리 1개만 나갈것으로 예상했지만, 다수의 쿼리가 나간것을 확인할수 있다.  
각각의 팀은 팀원을 가지고있고, 팀을 조회하는 경우 팀에 소속된 팀원의 정보를 가져와야 한다.  
팀이 10개면 각각의 팀의 팀원을 조회하는 쿼리가 1개씩 총 10번의 쿼리가 발생하게된다.  
N+1 문제는 N과 1이 의미하는 바는 바로 다음과 같다.   
N (팀에 소속된 팀원을 조회하는 쿼리) +(추가로 발생) 1 (팀전체를 조회하는 쿼리)

```java
@OneToMany(fetch = FetchType.EAGER)
```

그럼 무엇이 문제였을까? 즉시 로딩이 문제였을까?
한번 즉시 로딩을 지연 로딩으로 변경해보자

```java
@OneToMany(fetch = FetchType.LAZY)
```

또다시 전체 팀원을 조회해보자.  

```java
List<Team> teams = teamRepository.findAll();
```

언뜻 보면 크게 문제가 없어보인다.  
이것이 더 큰 문제이다. 왜 더 큰문제인지는 생각을 좀 더 해보자.   
우리는 지연로딩을 사용했다. 지연로딩은 연관된 데이터(소속된 팀원)를 참조하기 전까지는 쿼리가 수행되지 않는다.  
위의 조회에서는 연관된 데이터를 참조하지 않았다.   
즉, 우리는 로딩되는 시점의 코드를 사용하지 않아서, 언뜻보면 N+1 문제가 해결된 것처럼 보이는 것이다.  
실제 N+1 문제가 해결되었는지 확인하기 위해서 연관된 데이터 사용하는 코드를 추가해보자.   

```java
team.getUsers.forEach(user -> {
    System.out.Println(user.getTeam.getName() + " : " + user->getName());
});
```

추가할 코드는 조회한 팀의 소속된 팀원의 이름을 전부 출력하는 코드이다.  
코드의 실행 결과는 팀별로 팀원의 이름을 출력하므로 따로 추가하지 않겠다.  
그럼 제일 중요한 실제 수행되는 쿼리문을 한번 보자.

```sql
```

역시 N+1 문제가 발생하였다.  

