package server

import (
	"context"
	"log"

	pb "github.com/khaago/lethe/broker"
)

// Dispatch sends a message that was received as an Event.
func (s *brokerServer) Dispatch(ctx context.Context, event *pb.Event) (*pb.Ack, error) {
	// TODO impl
	// Return an ack
	return &pb.Ack{AckId: "0"}, nil
}

// Listen listens to incoming message based on the topic params
func (s *brokerServer) Listen(topic *pb.Topic, stream pb.Broker_ListenServer) error {
	events := []*pb.Event{
		{MsgId: "0", Part: 0, Topic: &pb.Topic{Name: "test0", DestPatterns: nil}, Msg: "Hello0!", Time: 1020, Chunksize: 16},
		{MsgId: "1", Part: 0, Topic: &pb.Topic{Name: "test1", DestPatterns: nil}, Msg: "Hello1!", Time: 1021, Chunksize: 16},
	}

	for _, event := range events {
		if err := stream.Send(event); err != nil {
			log.Fatalf("Failed to send event: %v", err)
		}
	}
	return nil
}

// Dispatch a stream of events
func (s *brokerServer) DispatchStream(stream pb.Broker_DispatchStreamServer) error {
	acks := []*pb.Ack{
		{AckId: "0", MsgId: "0", Part: 0},
		{AckId: "1", MsgId: "1", Part: 0},
	}

	for _, ack := range acks {
		if err := stream.Send(ack); err != nil {
			log.Fatalf("Failed to send ack: %v", err)
		}
	}
	return nil
}
