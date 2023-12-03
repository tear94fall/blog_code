package main

import (
	"context"
	"log"
	memberpb "main/protos/member"
	"main/user"
	"net"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

type Server struct {
	memberpb.UnimplementedMemberServiceServer
}

func (s *Server) Signup(ctx context.Context, in *memberpb.MemberSignupRequest) (*memberpb.MemberSignResponse, error) {
	user := user.User{}

	user.UserId = in.GetUserId()
	user.Passwd = in.GetPassword()
	user.Username = in.GetUsername()
	user.Email = in.GetEmail()
	user.Age = int64(in.GetAge())

	log.Printf("Received user info: %v\n", user)

	return &memberpb.MemberSignResponse{Result: "success"}, nil
}

func (s *Server) Login(ctx context.Context, in *memberpb.MemberLoginRequest) (*memberpb.MemberLoginResponse, error) {
	log.Printf("Received userid: %s, password %s\n", in.GetUserId(), in.GetPassword())

	return &memberpb.MemberLoginResponse{Result: "success"}, nil
}

func main() {
	lis, err := net.Listen("tcp", ":8088")
	if err != nil {
		log.Println(err.Error())
	}

	s := grpc.NewServer()
	memberpb.RegisterMemberServiceServer(s, &Server{})
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Println(err.Error())
	}
}
