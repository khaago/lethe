package server

import (
	"context"
	"fmt"
	"io"
	"log"

	pb "github.com/khaago/lethe/broker"
	"github.com/khaago/lethe/store"
)

func (s *brokerServer) Dispatch(ctx context.Context, opts *pb.EventPacket) (*pb.DispatchAck, error) {
	log.Println("Received event to dispatch", *opts.GetEvent())
	err := store.Save(opts.GetEvent())
	if err == nil {
		log.Println("Saved event", *opts.GetEvent())
	}
	return nil, err
}

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
		_ = store.Save(in.GetEvent())
	}
}

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
