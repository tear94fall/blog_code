# Go 설치하기

![intro](./images/golang_icon.jpeg)

## 1. 설치 하기

포스팅에서는 맥에서 Go를 설치하는 방법을 소개하려고 한다.  
윈도우나 리눅스 환경에서도 크게 다르지 않다.  
윈도우에서는 다음과 같이 설치파일 다운로드 후 설치를 진행하면 된다.
리눅스는 베포판의 패키지 매니저를 통해 설치한다.

`https://go.dev/`
Go 공식 사이트에서 설치파일을 다운로드 한다.  
다운로드가 완료되면 실행하여 설치 해준다.
크게 어려울것이 없고 순서대로 버튼만 잘 클릭하면 된다.

![intro](./images/gui_go_install_pkg.png)
![intro](./images/gui_go_install_1.png)
![intro](./images/gui_go_install_2.png)
![intro](./images/gui_go_install_3.png)

### 1-2. Go 설치하기
### 1-3. cmd로 Go 설치하기

![intro](./images/cmd_install_go.png)
![intro](./images/cmd_installing_go.png)

리눅스 설치방법
우분투 설치
`apt-get install go`
`yum install go`
를 통해 다운로드 및 설치를 진행한다.

### 1-4. 버전확인

설치가 완료되었다면, 정상적으로 설치 되어있는지 확인 한다.

![intro](./images/cmd_go_version_check.png)

버전을 확인하면 정상적으로 설치되었음을 확인 할수 있다.

## 2. 개발 환경 구축

ide는 go-land와 vscode를 많이 사용하는 것 같다.  
go-land의 경우 유료 이기 때문에, 무료로 사용할 수 있는 vscode를 설치하도록 한다.
vscode 설치의 경우 어렵지 않기 때문에 생략한다.

go 관련 extension으로 구글에서 만든 go를 설치해준다.

![intro](./images/go_extension.png)

문법 관련 오류나 formatting과 같은 부분을 자동으로 해주기 때문에 매우 편리하다.

## 3.프로젝트 생성

go 프로젝트 생성을 위해 디렉토리를 하나 만들어준다.

![intro](./images/go_project_directory.png)

그리고 파일을 하나 만들어 준다. (main.go)

![intro](./images/go_make_file.png)

```golang
package main

import fmt

func main(){
    fmt.Println("Hello world")

    return
}
```

그리고 간단한 예제 코드를 입력해준다.

이제 실행을 해야하는데 실행 방법은 다음과 같다.

go run main.go 처음 실행하면 오류가 발생하면서 실행되지 않을 것이다.

![intro](./images/go_init.png)

그럴경우 `go mod init <패키지 이름>`을 실행해준다.
그러면 go mod tidy를 입력하라고 한다.
역시 바로 입력해준다.

![intro](./images/go_mod.png)

두 명령을 실행하고 나면 파일이 생성될 것이다.
현재는 fmt 패키지만을 사용해서 go.mod만 생성되는데
패키지를 설치하고 사용하면 `go.sum`파일도 같이 생성된다.
이는 아래에 패키지 설치에서 확인 할수 있다.

![intro](./images/go_init_file.png)

입력 한뒤 실행 해준다.

![intro](./images/go_run.png)

`Hello world`가 보이면 정상적으로 실행이 된것이다.
Go는 실행 뿐만 아니라, 실행파일을 컴파일 할수 도 있다.

![intro](./images/go_build_result.png)

`go build main.go`를 실행한 후 생성되는 파일을 보자
`main`이라는 실행파일이 생성되어다.

![intro](./images/go_build_result_run.png)

이것을 실행하면 방금전에 `go run main.go`와 같은 실핼결과를 얻을 수 있다.

## 4. 패키지 사용하기

패키지를 사용하는 간단한 코드를 입력해준다.

```golang
package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()
	
	r.GET("/hello", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})

	r.Run(":8080")
}
```

![intro](./images/go_import_error.png)
코드를 입력해주면 패키지를 입력하는 부분에서 오류가 발생할 것이다.
이는 패키지가 설치되지 않아서 발생하는 것으로
go 에서는 `go get <패키지>`로 설치가 가능하다. `-u` 옵션을 추가하면 종속성 패키지까지 함께 업데이트 해준다.

![intro](./images/go_get_example.png)

`go get -u github.com/gin-gonic/gin`를 입력해준다.

![intro](./images/go_install_gin.png)

`-u` 옵션 때문에 종속성 패키지까지 같이 업데이트가 된다.

![intro](./images/go_import.png)

설치가 완료되면 패키지 import 부분의 에러는 사라진다.

`go run main.go`를 통해 정상적으로 작동하는지 확인 한다.

![intro](./images/go_start_gin.png)

웹 브라우저로 접속해 확인한다.

![intro](./images/go_start_gin_running.png)

![intro](./images/go_gin_browser_test.png)
