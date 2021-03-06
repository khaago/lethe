package store

import (
	"log"

	pb "github.com/khaago/lethe/broker"
)

// Init starts the store
func Init() {
	// data = make(map[string][]*pb.Event)
	// topics = make(map[string]*pb.Topic)
	// acks = make([]*pb.Ack, 0, 10)
	log.Printf("Store started")
}

// SaveEvent saves an event and returns the serial number
func (store *Store) SaveEvent(event *pb.Event, topicName string) (int64, error) {
	return 0,nil
}

// GetEvent returns an event at a given serial number
func (store *Store) GetEvent(serialNumber int64, topicName string) (*pb.Event, error) {
	return nil, nil
}

// GetEventsSince returns all events with a serial number greater than provided
func GetEventsSince(serialNumber int64, topicName string) ([]*pb.Event, error) {
	return nil, nil
}


