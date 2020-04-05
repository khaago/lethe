package server

import (
	"context"
	"fmt"
	"io"
	"log"

	pb "github.com/khaago/lethe/broker"
	"github.com/khaago/lethe/store"
)

func (s *brokerServer) CreateTopic(ctx context.Context, opts *pb.TopicOptions) (*pb.Topic, error) {
	return store.CreateTopic(*opts)
}

func (s *brokerServer) DeleteTopic(ctx context.Context, opts *pb.TopicOptions) (*pb.DeleteResult, error) {
	return store.DeleteTopic(*opts)
}

// Dispatch sends a message that was received as an Event.
func (s *brokerServer) Dispatch(ctx context.Context, event *pb.Event) (*pb.Ack, error) {
	log.Println("Received event to dispatch", *event)
	ack, err := store.Save(*event)
	if err == nil {
		log.Println("Saved event", *event)
	}
	return ack, err
}

// Listen listens to incoming message based on the ListenOptions
func (s *brokerServer) Listen(opts *pb.ListenOptions, stream pb.Broker_ListenServer) error {
	events, err := store.GetEvents(opts)
	if err != nil {
		return err
	}
	for _, event := range events {
		if err = stream.Send(event); err != nil {
			return err
		}
	}
	return nil
}

// Dispatch a stream of events
func (s *brokerServer) DispatchStream(stream pb.Broker_DispatchStreamServer) error {
	for {
		in, err := stream.Recv()
		if err == io.EOF {
			return nil
		}
		if err != nil {
			return fmt.Errorf("Failed to receive event from stream: %v", err)
		}
		log.Println("Message received", in)
		ack, serr := store.Save(*in)
		if serr != nil {
			return fmt.Errorf("Failed to save message: %v", err)
		}
		if err := stream.Send(ack); err != nil {
			return fmt.Errorf("Failed to send ack: %v", err)
		}
	}
}
