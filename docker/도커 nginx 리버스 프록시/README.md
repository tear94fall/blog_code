# Docker에서 Nginx 리버스 프록시 사용법

![intro](./images/docker-logo.png)

## 1. Nginx의 리버스 프록시

## 2. Docker Nginx 컨테이너 생성

## 3. Golang 서버 컨테이너 생성

gin을 이용해 8081 포트와 8082 포트를 사용하는 서버의 컨테이너를 생성한다.

```golang
package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()
	
	r.GET("/hello", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "hello",
		})
	})

	r.Run(":8081")
}
```

서버의 포트와 url 를 절절하게 변경하여 8081, 8082 포트를 사용하는 서버 코드를 작성한다.  
8081 포트는 hello url을 사용하도록 하고,  
8082 포트는 world url을 사용하도록 설정한다.  

## 4. 마무리
