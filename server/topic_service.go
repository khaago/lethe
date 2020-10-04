package server

import (
	"context"

	pb "github.com/khaago/lethe/broker"
)

func (s *brokerServer) ListTopics(ctx context.Context, opts *pb.TopicInfo) (*pb.TopicResults, error) {
	// TODO
	return nil, nil
}

func (s *brokerServer) CreateTopic(ctx context.Context, opts *pb.TopicInfo) (*pb.Ack, error) {
	// return store.CreateTopic(*opts)
	return nil, nil
}

func (s *brokerServer) DeleteTopic(ctx context.Context, opts *pb.TopicInfo) (*pb.Ack, error) {
	// return store.DeleteTopic(*opts)
	return nil, nil
}
