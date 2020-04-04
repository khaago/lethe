package server

import (
	"context"
	"io"
	"log"

	pb "github.com/khaago/lethe/broker"
)

// Dispatch sends a message that was received as an Event.
func (s *brokerServer) Dispatch(ctx context.Context, event *pb.Event) (*pb.Ack, error) {
	log.Println("Received event to dispatch", *event)
	// TODO impl
	// Return an ack
	return &pb.Ack{AckId: event.GetMsgId()}, nil
}

// Listen listens to incoming message based on the topic params
func (s *brokerServer) Listen(topic *pb.Topic, stream pb.Broker_ListenServer) error {
	// TODO replace mock with actual event source (local synchronized file or DB)
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
	for {
		in, err := stream.Recv()
		if err == io.EOF {
			return nil
		}
		if err != nil {
			log.Fatalf("Failed to receive event from stream: %v", err)
		}
		log.Println("Message received", in)
		// TODO save the message or whatever
		msg := &pb.Ack{AckId: in.GetMsgId(), MsgId: in.GetMsgId(), Part: in.GetPart()}
		if err := stream.Send(msg); err != nil {
			log.Fatalf("Failed to send ack: %v", err)
		}
	}
}
