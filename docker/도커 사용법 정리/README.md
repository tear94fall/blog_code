# Docker를 사용하여 Mysql 컨테이너 생성하고 접속하기

![intro](./images/docker-logo.png)

## 1. Docker

우선 도커가 설치되어있어야 합니다.  
도커 설치는 기존 포스팅을 참고 해주시기 바랍니다!

```bash
$ docker -v
Docker version 20.10.10, build b485636
```

도커 버전을 확인합니다. 도커가 정상 설치되었으므로 본격적으로 Mysql 설치를 진행해보겠습니다.  

## 2. Docker 이미지 가져오기

도커로 Mysql을 실행하려면 우선 Docker 저장소에서 Mysql 이미지를 가져와야 합니다.  
`docker pull mysql` 명령어를 통해 이미지를 가져올 수 있습니다.  
`docker pull mysql:<version>` 다음과 특정 버전을 가져오는것 또한 가능합니다.  

```bash
$ docker pull mysql
Using default tag: latest
latest: Pulling from library/mysql
a10c77af2613: Pull complete 
b76a7eb51ffd: Pull complete 
258223f927e4: Pull complete 
2d2c75386df9: Pull complete 
63e92e4046c9: Pull complete 
f5845c731544: Pull complete 
bd0401123a9b: Pull complete 
3ef07ec35f1a: Pull complete 
c93a31315089: Pull complete 
3349ed800d44: Pull complete 
6d01857ca4c1: Pull complete 
4cc13890eda8: Pull complete 
Digest: sha256:aeecae58035f3868bf4f00e5fc623630d8b438db9d05f4d8c6538deb14d4c31b
Status: Downloaded newer image for mysql:latest
docker.io/library/mysql:latest
```

## 2. Docker 이미지 리스트 보기

도커로 이미지를 잘 가져왔는지 확인합니다.  
`docker images` 명령어를 통해 가져온 이미지들의 리스트를 볼 수 있습니다.  

```bash
$ docker images
REPOSITORY      TAG         IMAGE ID       CREATED         SIZE
mysql           latest      b05128b000dd   13 days ago     516MB
```

## 3. Docker Mysql 컨테이너 생성 및 실행

도커 컨테이너를 실행시키는 명령어는 `docker run` 입니다.  
우리가 다운받은 mysql 이미지를 실행하는 명령어는 다음과 같습니다.  
`docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest`  

```bash
$ docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest
3df967b762c36a1ce2cc00d24f86c0ba7f42c2ab940f1533bcb2b81f78ed5331
```

정상 실행이 되면 컨테이터의 id가 표시됩니다.  

`--name mysql-container` 통해 컨테이너의 이름을 mysql-container로 실행합니다.  
도커는 컨테이너를 고유 id를 통해서 식별합니다. id는 hash 값입니다.  
몇개의 컨테이너만 있을때는 쉽게 구별이 가능하나 다수의 컨테이너가 동작중인 경우 구별하기 어려우므로  
이름을 통해 컨테이너를 구별할수 있도록 합니다.  
`-d` 옵션은 백그라운드에서 실행이 가능하도록 합니다.  
`-e` 옵션은 환경 변수를 설정하기 위해 사용합니다. MYSQL_ROOT_PASSWORD의 환경 별수를 설정합니다.  
`-p` 옵션은 포트를 설정하는 옵션입니다. <호스트 포트>:<컨테이너 포트> 입니다.  

## 4. Docker 실행중인 컨테이너 보기

실행중인 도커 컨테이너의 리스트를 보는 명령어는 `docker ps` 입니다.  
실행중인 컨테이너의 상태와 생성된 시간 포트 정보등을 확인 할 수 있습니다.  
`docker ps -a`와 같이 `-a` 옵션을 추가하면 모든 컨테이너를 확인 할 수 있습니다.  

```bash
$ docker ps
CONTAINER ID   IMAGE            COMMAND                  CREATED          STATUS          PORTS                               NAMES
3df967b762c3   mysql:latest     "docker-entrypoint.s…"   45 seconds ago   Up 44 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql-container
```

## 5. 컨테이너 시작/멈춤/재시작

각각의 명령어를 입력한뒤 `docker ps` 명령어를 통해 STATUS 가 변경 되는 것을 확인 합니다.  

시작
```bash
$ docker start mysql-container
mysql-container
```

멈춤
```bash
$ docker stop mysql-container
mysql-container
```

재시작
```bash
$ docker restart mysql-container
mysql-container
```

## 6. 컨테이너 접속

컨테이너의 접속하는 명령어는 다음과 같습니다.  
`docker exec -it mysql-container bash`  

`-it` 옵션은 `i` 옵션과 `t`옵션을 동시에 쓰는 것입니다.   
`i` 옵션은 컨테이너의 표준 입력을 열겠다는 뜻입니다.  
`t` 옵션은 터미널을 통해 접속하겠다는 의미입니다.  
`bash`는 bash 쉘을 연결하겠다는 의미입니다.  

위 명령어를 입력하면 bash 쉘이 실행되는데 Mysql에 접속합니다.  
`mysql -u root -p`를 통해 Mysql에 접속합니다.  
그리고 아까 docker로 Mysql을 실행할때 입력했던 패스워드를 입력합니다.  
이제 Docker로 Mysql 구동 시켰고 Mysql을 사용할수 있습니다.

```bash
$ docker exec -it mysql-container bash
root@3df967b762c3:/# mysql -u root -p 
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.27 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> 
```

## 7. 컨테이너 삭제

컨테이너를 삭제하는 명령어는 다음과 같습니다.  
`docker rm <container id>` 또는 `docker rm <container name>`  

```bash
$ docker rm mysql-container
mysql-container
```

정상적으로 삭제되었는지 확인하기 위해 `docker ps` 명령어를 통해 확인합니다.  

## 8. Docker 이미지 삭제

만일 컨테이너가 아닌 이미지를 삭제하려는 경우 다음과 같은 명령어로 삭제가 가능합니다.  
`docker rmi <image id>` 또는 `docker rmi <image name>`  

```bash
$ docker rmi alpine
Untagged: alpine:latest
Untagged: alpine@sha256:21a3deaa0d32a8057914f36584b5288d2e5ecc984380bc0118285c70fa8c9300
Deleted: sha256:c059bfaa849c4d8e4aecaeb3a10c2d9b3d85f5165c66ad3a4d937758128c4d18
```

정상적으로 삭제되었는지 확인하기 위해 앞서 매운 `docker images`로 확인합니다.  

## 9. 마무리

이상으로 Docker 를 사용해 Mysql을 설치하면서 Docker의 기본적인 명령어에 대해서 학습했습니다.  
위의 명령어들은 도커를 사용하면서 정말 많이 사용하므로 꼭 숙지해두시기 바랍니다.  
