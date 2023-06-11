# EnableJpaAuditing 사용해보기

## 1. JPA Auditing 이란 무엇인가?

데이터 베이스에 존재하는 테이블들에는 공통적으로 존재하는 데이터들이 있습니다.  
바로 생성 날짜, 마지막 수정 날짜와 같은 컬럼 입니다.   
ORM을 사용하는 경우는 이런 공통 데이터들이 엔티티 마다 중복된 코드들이 존재하게 됩니다.  
만일 공통된 부분의 수정이 있는 경우 관련된 코드를 전부 변경해줘야 합니다.  

그래서 보통은 이런 공통 데이터만을 갖고 있는 엔티티를 별도로 만들고, 이 클래스를 상속 받아 사용합니다.  
다음과 같이 말이죠.

```java
public abstract class BaseEntity {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

public class Member extends BaseTimeEntity {
    ...
}
```

이제 공통된 부분에 수정이 필요한 경우 부모 객체만 수정을 하면 되겠네요.  
우선 중복 코드의 문제는 사라졌습니다. 그런데 한가지 문제가 있습니다.  
뭐가 문제일까요? 바로 값을 입력하는 시점에 대한 고민을 해봐야 합니다.  
생성되는 시점, 또는 수정되는 시점에 데이터를 직접 설정해줘야 합니다.  
코드로 확인해볼까요?  

```java
public class Member extends BaseTimeEntity {
    ...

    public void modify(...) {
        this.updatedAt = Datetime.Now();
    }
}
```

수정을 하게되는 경우 (modify 함수 호출)에 날짜를 채워 주도록 합니다.  
하지만 이렇게 수정되는 곳곳마다 코드를 채워줘야하는것은 매우 번거로운 일입니다.  
그래서 JPA 에서는 엔티티 객체가 수정되거나 변경되었을때 자동으로 값을 채워주는 기능이 있습니다.  
이러한 기능을 JPA Auditing 이라고 하며 @EnableJpaAuditing을 통해 사용가능 합니다.  

## 2. @EnableJpaAuditing 사용하기

