# Docker에서 명령어 실행하기

![intro](./images/docker-logo.png)

## 1. Docker

우선 도커가 설치되어있어야 합니다.  
도커 설치는 기존 포스팅을 참고 해주시기 바랍니다!  

도커 컨테이너 환경을 사용하면 때로는 명령어를 수행 하고 싶을때가 있습니다.  
컨테이너 내부에 파일이 정상 존재하는지 확인 해야되는 경우도 있고,  
볼륨이 제대로 마운트 되었는지 확인 해야되는지 확인이 하고 싶을때도 있고,  
서버스간 정상 적으로 통신을 하는지 등을 확인이 필요한 경우도 있습니다.  

이때 아래와 같이 도커 컨테이너 내부에 직접 접속해서 명령어를 확인 하는 방법도 있습니다.
```bash
docker exec -it alpine-container bash
```
하지만 직접 접속하지 않아도 될정도로 간단한 작업인 경우도 있습니다.  
또한 반복적으로 작업을 수행해야 하는 스크립트를 작성하는 경우 사용할 수있는 방법이 있습니다.

과연 무엇일까요? 우선 위의 명령어를 자세히 봅시다.  
위의 명령어가 무슨 명령어 였는지 확인 해봅시다.  
위의 명령어가 컨테이너 내부에 접속하는 명령어 였나요?  

아닙니다! 위의 명령어는 도커 컨테이너에 명령을 수행하는 명령어 였습니다.  
다만 i와 t옵션을 통해 표준 입출력을 tty (터미널)로 받겠다는 것이며,  
쉘의 종류는 bash를 사용하겠다는 의미입니다.  
Docker 컨테이너에 명령어를 수행하는 방법 또한 이와 다르지 않습니다.  

## 2. Docker 명령어 실행하기

도커 컨테이너에 명령어를 수행하는 방법은 위에서 본 명령어와 크게 다르지 않습니다.  
명령어는 다음과 같습니다.  
```bash
docker exec [container id] [command]
```
간단하죠?  
그럼 예제를 실행해 보겠습니다.  
알파인 리눅스 컨테이너의 파일 목록을 확인 하는 명령어를 수행해보겠습니다.  

```bash
$ docker exec alpine-container ls -al
total 64
drwxr-xr-x    1 root     root          4096 Mar 29 01:48 .
drwxr-xr-x    1 root     root          4096 Mar 29 01:48 ..
-rwxr-xr-x    1 root     root             0 Mar 29 01:48 .dockerenv
drwxr-xr-x    2 root     root          4096 Feb 10 16:45 bin
drwxr-xr-x    5 root     root           360 Mar 31 12:41 dev
drwxr-xr-x    1 root     root          4096 Mar 29 01:48 etc
...
```

다음과 같이 용량을 확인 하는 명령어도 생각을 해볼수 있을것같습니다.  

```bash
$ docker exec alpine-container df -Th
Filesystem           Type            Size      Used Available Use% Mounted on
overlay              overlay        58.4G      6.9G     48.5G  12% /
tmpfs                tmpfs          64.0M         0     64.0M   0% /dev
shm                  tmpfs          64.0M         0     64.0M   0% /dev/shm
/dev/vda1            ext4           58.4G      6.9G     48.5G  12% /etc/resolv.conf
/dev/vda1            ext4           58.4G      6.9G     48.5G  12% /etc/hostname
...
```

마지막으로 다음과 같이 네트워크 설정을 확인 할수 도 있습니다.  

```bash
docker exec alp
ine-container ifconfig
eth0      Link encap:Ethernet  HWaddr 02:42:AC:11:00:02
          inet addr:172.17.0.2  Bcast:172.17.255.255  Mask:255.255.0.0
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          ...
          RX bytes:2366 (2.3 KiB)  TX bytes:540 (540.0 B)

lo        Link encap:Local Loopback
          inet addr:127.0.0.1  Mask:255.0.0.0
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          ...
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)
```