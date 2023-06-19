# Spring profile별 환경 분리 하기

## 1. 환경을 분리해야 하는 이유?

현업에서 개발할때 실제 운영 환경에 테스트를 할수는 없습니다.  
테스트간 별다른 큰 문제가 발생하지 않으면 좋겠지만, 그렇지 않을 확률이 높으니까요.  
그래서 테스트시에는 별도로 분리된 환경에서 테스트하는것이 좋습니다.  
예를들면 테스트간 사용하는 DB는 개발용도로만 사용하는 DB이면 좋을것입니다.  

실제 운영환경에서 사용하는 DB는 mysql이고, 테스트시에는 로컬 환경의 h2를 써야하는 경우  
우리는 서로 다른 환경을 구분하기 위한 코드를 짜거나, 다른 설정을 해야 할것입니다.  
다행히 스프링에서는 profile를 통해 서로 다른 설정을 사용하도록 하는 기능을 제공하고 있습니다.  
그럼 어떻게 환경을 구분 할 수 있는지 알아보겠습니다.  

## 2. application.yml를 통한 profile 환경 분리

우리가 보통 사용하는 application.yml 파일 입니다.  

```yml
server:
  port: 8080

spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:tcp://localhost:1521/test
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        show_sql: true
        format_sql: true
    open-in-view: true

logging:
  level:
    org.hibernate.type.descriptor.sql: trace
```

그럼 어떻게 application.yml 파일을 통해 환경을 분리 할 수 있을까요?  
다행히 스프링에서는 이런한 기능을 옵션을 통해 제공하고 있습니다.  

```yml
spring:
  config:
    activate:
      on-profile: create
```

위의 설정은 profile을 create라는 이름으로 사용하겠다는 뜻입니다.  

```yml
spring:
  profiles:
    default: dev
```

위의 설정은 만일 해당 설정에 해당되는 값이 없을때, 기본 프로필로 무엇을 사용할것인가를 설정합니다.  