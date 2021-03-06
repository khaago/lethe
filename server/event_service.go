package server

import (
	"context"

	pb "github.com/khaago/lethe/broker"
	"github.com/khaago/lethe/store"
)

func (s *brokerServer) Dispatch(ctx context.Context, opts *pb.EventPacket) (*pb.DispatchAck, error) {
	storeService := &store.Store{}
	ser, err := storeService.SaveEvent(opts.GetEvent(), opts.GetTopic());
	ack := &pb.DispatchAck{
		Ack: &pb.Ack{StatusCode: 0, Message: ""},
		SerialNumber: ser,
	}
	return ack, err
}

func (s *brokerServer) DispatchStream(stream pb.Broker_DispatchStreamServer) error {
	return nil
}

func (s *brokerServer) Listen(opts *pb.ListenOptions, stream pb.Broker_ListenServer) error {
	return nil
}
