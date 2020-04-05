package store

import (
	"log"

	pb "github.com/khaago/lethe/broker"
)

// var data map[string][]*pb.Event
// var topics map[string]*pb.Topic
// var acks []*pb.Ack

// Init starts the store
func Init() {
	// data = make(map[string][]*pb.Event)
	// topics = make(map[string]*pb.Topic)
	// acks = make([]*pb.Ack, 0, 10)
	log.Printf("Store started")
}

// Save saves an event
func Save(event *pb.Event) error {
	return nil
}

// GetEvents gets events
func GetEvents(opts *pb.ListenOptions) ([]*pb.Event, error) {
	return nil, nil
}
