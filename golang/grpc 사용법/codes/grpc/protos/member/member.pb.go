// Code generated by protoc-gen-go. DO NOT EDIT.
// versions:
// 	protoc-gen-go v1.31.0
// 	protoc        v4.25.1
// source: protos/member/member.proto

package __

import (
	protoreflect "google.golang.org/protobuf/reflect/protoreflect"
	protoimpl "google.golang.org/protobuf/runtime/protoimpl"
	reflect "reflect"
	sync "sync"
)

const (
	// Verify that this generated code is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(20 - protoimpl.MinVersion)
	// Verify that runtime/protoimpl is sufficiently up-to-date.
	_ = protoimpl.EnforceVersion(protoimpl.MaxVersion - 20)
)

type MemberSignupRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	UserId   string `protobuf:"bytes,1,opt,name=user_id,json=userId,proto3" json:"user_id,omitempty"`
	Password string `protobuf:"bytes,2,opt,name=password,proto3" json:"password,omitempty"`
	Email    string `protobuf:"bytes,3,opt,name=email,proto3" json:"email,omitempty"`
	Username string `protobuf:"bytes,4,opt,name=username,proto3" json:"username,omitempty"`
	Age      int32  `protobuf:"varint,5,opt,name=age,proto3" json:"age,omitempty"`
}

func (x *MemberSignupRequest) Reset() {
	*x = MemberSignupRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_protos_member_member_proto_msgTypes[0]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MemberSignupRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MemberSignupRequest) ProtoMessage() {}

func (x *MemberSignupRequest) ProtoReflect() protoreflect.Message {
	mi := &file_protos_member_member_proto_msgTypes[0]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MemberSignupRequest.ProtoReflect.Descriptor instead.
func (*MemberSignupRequest) Descriptor() ([]byte, []int) {
	return file_protos_member_member_proto_rawDescGZIP(), []int{0}
}

func (x *MemberSignupRequest) GetUserId() string {
	if x != nil {
		return x.UserId
	}
	return ""
}

func (x *MemberSignupRequest) GetPassword() string {
	if x != nil {
		return x.Password
	}
	return ""
}

func (x *MemberSignupRequest) GetEmail() string {
	if x != nil {
		return x.Email
	}
	return ""
}

func (x *MemberSignupRequest) GetUsername() string {
	if x != nil {
		return x.Username
	}
	return ""
}

func (x *MemberSignupRequest) GetAge() int32 {
	if x != nil {
		return x.Age
	}
	return 0
}

type MemberSignResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Result string `protobuf:"bytes,1,opt,name=result,proto3" json:"result,omitempty"`
}

func (x *MemberSignResponse) Reset() {
	*x = MemberSignResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_protos_member_member_proto_msgTypes[1]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MemberSignResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MemberSignResponse) ProtoMessage() {}

func (x *MemberSignResponse) ProtoReflect() protoreflect.Message {
	mi := &file_protos_member_member_proto_msgTypes[1]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MemberSignResponse.ProtoReflect.Descriptor instead.
func (*MemberSignResponse) Descriptor() ([]byte, []int) {
	return file_protos_member_member_proto_rawDescGZIP(), []int{1}
}

func (x *MemberSignResponse) GetResult() string {
	if x != nil {
		return x.Result
	}
	return ""
}

type MemberLoginRequest struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	UserId   string `protobuf:"bytes,1,opt,name=user_id,json=userId,proto3" json:"user_id,omitempty"`
	Password string `protobuf:"bytes,2,opt,name=password,proto3" json:"password,omitempty"`
}

func (x *MemberLoginRequest) Reset() {
	*x = MemberLoginRequest{}
	if protoimpl.UnsafeEnabled {
		mi := &file_protos_member_member_proto_msgTypes[2]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MemberLoginRequest) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MemberLoginRequest) ProtoMessage() {}

func (x *MemberLoginRequest) ProtoReflect() protoreflect.Message {
	mi := &file_protos_member_member_proto_msgTypes[2]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MemberLoginRequest.ProtoReflect.Descriptor instead.
func (*MemberLoginRequest) Descriptor() ([]byte, []int) {
	return file_protos_member_member_proto_rawDescGZIP(), []int{2}
}

func (x *MemberLoginRequest) GetUserId() string {
	if x != nil {
		return x.UserId
	}
	return ""
}

func (x *MemberLoginRequest) GetPassword() string {
	if x != nil {
		return x.Password
	}
	return ""
}

type MemberLoginResponse struct {
	state         protoimpl.MessageState
	sizeCache     protoimpl.SizeCache
	unknownFields protoimpl.UnknownFields

	Result string `protobuf:"bytes,1,opt,name=result,proto3" json:"result,omitempty"`
}

func (x *MemberLoginResponse) Reset() {
	*x = MemberLoginResponse{}
	if protoimpl.UnsafeEnabled {
		mi := &file_protos_member_member_proto_msgTypes[3]
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		ms.StoreMessageInfo(mi)
	}
}

func (x *MemberLoginResponse) String() string {
	return protoimpl.X.MessageStringOf(x)
}

func (*MemberLoginResponse) ProtoMessage() {}

func (x *MemberLoginResponse) ProtoReflect() protoreflect.Message {
	mi := &file_protos_member_member_proto_msgTypes[3]
	if protoimpl.UnsafeEnabled && x != nil {
		ms := protoimpl.X.MessageStateOf(protoimpl.Pointer(x))
		if ms.LoadMessageInfo() == nil {
			ms.StoreMessageInfo(mi)
		}
		return ms
	}
	return mi.MessageOf(x)
}

// Deprecated: Use MemberLoginResponse.ProtoReflect.Descriptor instead.
func (*MemberLoginResponse) Descriptor() ([]byte, []int) {
	return file_protos_member_member_proto_rawDescGZIP(), []int{3}
}

func (x *MemberLoginResponse) GetResult() string {
	if x != nil {
		return x.Result
	}
	return ""
}

var File_protos_member_member_proto protoreflect.FileDescriptor

var file_protos_member_member_proto_rawDesc = []byte{
	0x0a, 0x1a, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x73, 0x2f, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x2f,
	0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x2e, 0x70, 0x72, 0x6f, 0x74, 0x6f, 0x12, 0x06, 0x6d, 0x65,
	0x6d, 0x62, 0x65, 0x72, 0x22, 0x8e, 0x01, 0x0a, 0x13, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x53,
	0x69, 0x67, 0x6e, 0x75, 0x70, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x17, 0x0a, 0x07,
	0x75, 0x73, 0x65, 0x72, 0x5f, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x75,
	0x73, 0x65, 0x72, 0x49, 0x64, 0x12, 0x1a, 0x0a, 0x08, 0x70, 0x61, 0x73, 0x73, 0x77, 0x6f, 0x72,
	0x64, 0x18, 0x02, 0x20, 0x01, 0x28, 0x09, 0x52, 0x08, 0x70, 0x61, 0x73, 0x73, 0x77, 0x6f, 0x72,
	0x64, 0x12, 0x14, 0x0a, 0x05, 0x65, 0x6d, 0x61, 0x69, 0x6c, 0x18, 0x03, 0x20, 0x01, 0x28, 0x09,
	0x52, 0x05, 0x65, 0x6d, 0x61, 0x69, 0x6c, 0x12, 0x1a, 0x0a, 0x08, 0x75, 0x73, 0x65, 0x72, 0x6e,
	0x61, 0x6d, 0x65, 0x18, 0x04, 0x20, 0x01, 0x28, 0x09, 0x52, 0x08, 0x75, 0x73, 0x65, 0x72, 0x6e,
	0x61, 0x6d, 0x65, 0x12, 0x10, 0x0a, 0x03, 0x61, 0x67, 0x65, 0x18, 0x05, 0x20, 0x01, 0x28, 0x05,
	0x52, 0x03, 0x61, 0x67, 0x65, 0x22, 0x2c, 0x0a, 0x12, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x53,
	0x69, 0x67, 0x6e, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x16, 0x0a, 0x06, 0x72,
	0x65, 0x73, 0x75, 0x6c, 0x74, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x72, 0x65, 0x73,
	0x75, 0x6c, 0x74, 0x22, 0x49, 0x0a, 0x12, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x4c, 0x6f, 0x67,
	0x69, 0x6e, 0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x12, 0x17, 0x0a, 0x07, 0x75, 0x73, 0x65,
	0x72, 0x5f, 0x69, 0x64, 0x18, 0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x75, 0x73, 0x65, 0x72,
	0x49, 0x64, 0x12, 0x1a, 0x0a, 0x08, 0x70, 0x61, 0x73, 0x73, 0x77, 0x6f, 0x72, 0x64, 0x18, 0x02,
	0x20, 0x01, 0x28, 0x09, 0x52, 0x08, 0x70, 0x61, 0x73, 0x73, 0x77, 0x6f, 0x72, 0x64, 0x22, 0x2d,
	0x0a, 0x13, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x4c, 0x6f, 0x67, 0x69, 0x6e, 0x52, 0x65, 0x73,
	0x70, 0x6f, 0x6e, 0x73, 0x65, 0x12, 0x16, 0x0a, 0x06, 0x72, 0x65, 0x73, 0x75, 0x6c, 0x74, 0x18,
	0x01, 0x20, 0x01, 0x28, 0x09, 0x52, 0x06, 0x72, 0x65, 0x73, 0x75, 0x6c, 0x74, 0x32, 0x94, 0x01,
	0x0a, 0x0d, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x53, 0x65, 0x72, 0x76, 0x69, 0x63, 0x65, 0x12,
	0x41, 0x0a, 0x06, 0x53, 0x69, 0x67, 0x6e, 0x75, 0x70, 0x12, 0x1b, 0x2e, 0x6d, 0x65, 0x6d, 0x62,
	0x65, 0x72, 0x2e, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x53, 0x69, 0x67, 0x6e, 0x75, 0x70, 0x52,
	0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x1a, 0x1a, 0x2e, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x2e,
	0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x53, 0x69, 0x67, 0x6e, 0x52, 0x65, 0x73, 0x70, 0x6f, 0x6e,
	0x73, 0x65, 0x12, 0x40, 0x0a, 0x05, 0x4c, 0x6f, 0x67, 0x69, 0x6e, 0x12, 0x1a, 0x2e, 0x6d, 0x65,
	0x6d, 0x62, 0x65, 0x72, 0x2e, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x4c, 0x6f, 0x67, 0x69, 0x6e,
	0x52, 0x65, 0x71, 0x75, 0x65, 0x73, 0x74, 0x1a, 0x1b, 0x2e, 0x6d, 0x65, 0x6d, 0x62, 0x65, 0x72,
	0x2e, 0x4d, 0x65, 0x6d, 0x62, 0x65, 0x72, 0x4c, 0x6f, 0x67, 0x69, 0x6e, 0x52, 0x65, 0x73, 0x70,
	0x6f, 0x6e, 0x73, 0x65, 0x42, 0x04, 0x5a, 0x02, 0x2e, 0x2f, 0x62, 0x06, 0x70, 0x72, 0x6f, 0x74,
	0x6f, 0x33,
}

var (
	file_protos_member_member_proto_rawDescOnce sync.Once
	file_protos_member_member_proto_rawDescData = file_protos_member_member_proto_rawDesc
)

func file_protos_member_member_proto_rawDescGZIP() []byte {
	file_protos_member_member_proto_rawDescOnce.Do(func() {
		file_protos_member_member_proto_rawDescData = protoimpl.X.CompressGZIP(file_protos_member_member_proto_rawDescData)
	})
	return file_protos_member_member_proto_rawDescData
}

var file_protos_member_member_proto_msgTypes = make([]protoimpl.MessageInfo, 4)
var file_protos_member_member_proto_goTypes = []interface{}{
	(*MemberSignupRequest)(nil), // 0: member.MemberSignupRequest
	(*MemberSignResponse)(nil),  // 1: member.MemberSignResponse
	(*MemberLoginRequest)(nil),  // 2: member.MemberLoginRequest
	(*MemberLoginResponse)(nil), // 3: member.MemberLoginResponse
}
var file_protos_member_member_proto_depIdxs = []int32{
	0, // 0: member.MemberService.Signup:input_type -> member.MemberSignupRequest
	2, // 1: member.MemberService.Login:input_type -> member.MemberLoginRequest
	1, // 2: member.MemberService.Signup:output_type -> member.MemberSignResponse
	3, // 3: member.MemberService.Login:output_type -> member.MemberLoginResponse
	2, // [2:4] is the sub-list for method output_type
	0, // [0:2] is the sub-list for method input_type
	0, // [0:0] is the sub-list for extension type_name
	0, // [0:0] is the sub-list for extension extendee
	0, // [0:0] is the sub-list for field type_name
}

func init() { file_protos_member_member_proto_init() }
func file_protos_member_member_proto_init() {
	if File_protos_member_member_proto != nil {
		return
	}
	if !protoimpl.UnsafeEnabled {
		file_protos_member_member_proto_msgTypes[0].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MemberSignupRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_protos_member_member_proto_msgTypes[1].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MemberSignResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_protos_member_member_proto_msgTypes[2].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MemberLoginRequest); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
		file_protos_member_member_proto_msgTypes[3].Exporter = func(v interface{}, i int) interface{} {
			switch v := v.(*MemberLoginResponse); i {
			case 0:
				return &v.state
			case 1:
				return &v.sizeCache
			case 2:
				return &v.unknownFields
			default:
				return nil
			}
		}
	}
	type x struct{}
	out := protoimpl.TypeBuilder{
		File: protoimpl.DescBuilder{
			GoPackagePath: reflect.TypeOf(x{}).PkgPath(),
			RawDescriptor: file_protos_member_member_proto_rawDesc,
			NumEnums:      0,
			NumMessages:   4,
			NumExtensions: 0,
			NumServices:   1,
		},
		GoTypes:           file_protos_member_member_proto_goTypes,
		DependencyIndexes: file_protos_member_member_proto_depIdxs,
		MessageInfos:      file_protos_member_member_proto_msgTypes,
	}.Build()
	File_protos_member_member_proto = out.File
	file_protos_member_member_proto_rawDesc = nil
	file_protos_member_member_proto_goTypes = nil
	file_protos_member_member_proto_depIdxs = nil
}
