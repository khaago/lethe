package store

import (
	pb "github.com/khaago/lethe/broker"
)

// EventStore defines event related methods
type EventStore interface {
	SaveEvent(event *pb.Event, topicName string) error
	GetEvent(serialNumber int64, topicName string) (*pb.Event, error)
	GetEventsSince(serialNumber int64, topicName string) ([]*pb.Event, error)
}

// TopicStore defines topic related methods
type TopicStore interface {
	CreateTopic(name string) error
	DeleteTopic(name string) error
	ListTopics(pattern string) ([]string, error)
}

// Store holds database implementations
type Store struct {
	Events map[string][]*pb.Event
}

// Init starts an empty store
func (store *Store) Init() {
	if len(store.Events) == 0 {
		store.Events = make(map[string][]*pb.Event)
	}
}

// Sync syncs the recent commit log
func (store *Store) Sync() {
	if len(store.Events) == 0 {
		store.Events = make(map[string][]*pb.Event)
	}
}
