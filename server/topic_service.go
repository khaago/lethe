package server

import (
	"context"

	pb "github.com/khaago/lethe/broker"
)

func (s *brokerServer) ListTopic(ctx context.Context, opts *pb.ListTopicOptions) (*pb.ListTopicResults, error) {
	return nil, nil
}

func (s *brokerServer) CreateTopic(ctx context.Context, opts *pb.CreateTopicOptions) (*pb.CreateTopicResults, error) {
	// return store.CreateTopic(*opts)
	return nil, nil
}

func (s *brokerServer) DeleteTopic(ctx context.Context, opts *pb.DeleteTopicOptions) (*pb.DeleteTopicResult, error) {
	// return store.DeleteTopic(*opts)
	return nil, nil
}
