package main

import (
	"context"
	"log"
	"time"

	memberpb "main/protos/member"
	"main/user"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

func SingupUser(c memberpb.MemberServiceClient, ctx context.Context, user user.User) {
	res, err := c.Signup(ctx, &memberpb.MemberSignupRequest{
		UserId:   user.UserId,
		Password: user.Passwd,
		Email:    user.Email,
		Username: user.Username,
		Age:      int32(user.Age),
	})

	if err != nil {
		log.Println(err.Error())
	}

	log.Printf("Signup: %v", res)
}

func LoginUser(c memberpb.MemberServiceClient, ctx context.Context, userid string, password string) {
	res, err := c.Login(ctx, &memberpb.MemberLoginRequest{
		UserId:   userid,
		Password: password,
	})

	if err != nil {
		log.Println(err.Error())
	}

	log.Printf("Login: %v", res)
}

func main() {
	conn, err := grpc.Dial("localhost:8088", grpc.WithTransportCredentials(insecure.NewCredentials()), grpc.WithBlock())
	if err != nil {
		log.Println(err.Error())
	}
	defer conn.Close()
	c := memberpb.NewMemberServiceClient(conn)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second)
	defer cancel()

	user := user.User{}
	user.Username = "admin"
	user.UserId = "administrator"
	user.Email = "administrator@test.com"
	user.Passwd = "admin1234"
	user.Age = 28

	SingupUser(c, ctx, user)
	LoginUser(c, ctx, user.UserId, user.Passwd)
}
