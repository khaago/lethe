package server

import (
	"context"

	pb "github.com/khaago/lethe/broker"
)

func (s *brokerServer) Register(ctx context.Context, request *pb.RegistrationRequest) (*pb.RegistrationResponse, error) {
	return nil, nil
}

func (s *brokerServer) Subscribe(stream pb.Broker_SubscribeServer) error {
	return nil
}
