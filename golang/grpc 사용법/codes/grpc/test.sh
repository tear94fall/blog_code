#!/bin/bash

function list()
{
	# List
	grpcurl --plaintext localhost:8088 list
}

function signup()
{
	# Signup
	echo "[Signup]"
	grpcurl --plaintext -d "{ \
		\"user_id\" : \"jslim\", \
		\"password\" : \"1234\", \
		\"email\" : \"joonsub.lim@gmail.com\", \
		\"username\" : \"joonsub.lim\", \
		\"age\" : 30 \
		}" localhost:8088 member.MemberService/Signup
}

function login()
{
	# Login
	echo "[Login]"
	grpcurl --plaintext -d "{ \
		\"user_id\" : \"jslim\", \
		\"password\" : \"1234\" \
		}" localhost:8088 member.MemberService/Login
}

function build()
{
	# Build
	protoc -I=. \
		    --go_out . --go_opt paths=source_relative \
		    --go-grpc_out . --go-grpc_opt paths=source_relative \
		    protos/member/member.proto
}

function main()
{
	command=$1

    case $command in 
    build)
        build
        ;;
    list)
        list
        ;;
    signup)
        signup
        ;;
    login)
        login
        ;;
    *)
        echo "invalid command";
        echo "$0 [build|list|signup|login]";
        ;;
    esac
}

main $1