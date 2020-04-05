package store

import (
	"fmt"
	"log"
	"time"

	pb "github.com/khaago/lethe/broker"
)

var data map[string][]*pb.Event
var topics map[string]*pb.Topic
var acks []*pb.Ack

// Init starts the store
func Init() {
	data = make(map[string][]*pb.Event)
	topics = make(map[string]*pb.Topic)
	acks = make([]*pb.Ack, 0, 10)
	log.Printf("Store started")
}

// CreateTopic creates a new topic
func CreateTopic(opts pb.TopicOptions) (*pb.Topic, error) {
	if topics[opts.GetTopicName()] != nil {
		return nil, fmt.Errorf("topic with name %s already exists", opts.GetTopicName())
	}
	topic := &pb.Topic{Name: opts.GetTopicName(), Offset: 0}
	topics[opts.GetTopicName()] = topic
	log.Printf("topic %s created", opts.GetTopicName())
	return topic, nil
}

// DeleteTopic removes a topic
func DeleteTopic(opts pb.TopicOptions) (*pb.DeleteResult, error) {
	if err := validateTopic(opts.GetTopicName()); err != nil {
		return nil, err
	}
	delete(topics, opts.GetTopicName())
	log.Printf("topic %s deleted", opts.GetTopicName())
	return &pb.DeleteResult{TopicName: opts.GetTopicName(), Success: true}, nil
}

// Save stores the event under a new key
func Save(event pb.Event) (*pb.Ack, error) {
	if err := validateTopic(event.GetTopicName()); err != nil {
		return nil, err
	}
	data[event.GetTopicName()] = append(data[event.GetTopicName()], &event)
	ack := &pb.Ack{
		AckId:     event.GetMsgId(),
		MsgId:     event.GetMsgId(),
		TopicName: event.GetTopicName(),
		Time:      time.Now().UnixNano(),
	}
	acks = append(acks, ack)
	return ack, nil
}

// GetEvent gets an event from the datastore based on the GetOptions
func GetEvent(opts *pb.ListenOptions) (*pb.Event, error) {
	if err := validateTopic(opts.GetTopicName()); err != nil {
		return nil, err
	}
	// if the topic is empty just ignore the offset and return nil
	if isEmpty(opts.GetTopicName()) {
		return nil, nil
	}
	index := opts.GetOffset()
	// if a negative index is supplied treat it as the highest available index
	if index < 0 {
		index = (int32)(len(data[opts.GetTopicName()]) - 1)
	}
	if err := data[opts.GetTopicName()][index]; err != nil {
		return nil, fmt.Errorf("Failed to retrieve event for topic %s offset %d due to \n%v", opts.GetTopicName(), opts.GetOffset(), err)
	}
	return data[opts.GetTopicName()][index], nil
}

func validateTopic(topicName string) error {
	if len(topicName) == 0 {
		return fmt.Errorf("topicName is empty")
	}
	if data[topicName] == nil {
		return fmt.Errorf("topic %s not found", topicName)
	}
	return nil
}

func isEmpty(topicName string) bool {
	return len(data[topicName]) == 0
}
