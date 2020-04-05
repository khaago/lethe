package server

import (
	"flag"
	"fmt"
	"log"
	"net"

	pb "github.com/khaago/lethe/broker"
	"github.com/khaago/lethe/store"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials"
)

var (
	tls      = flag.Bool("tls", false, "Connection uses TLS if true, else plain TCP")
	certFile = flag.String("cert_file", "", "The TLS cert file")
	keyFile  = flag.String("key_file", "", "The TLS key file")
	port     = flag.Int("port", 0, "The server port")
)

type brokerServer struct {
	pb.UnimplementedBrokerServer
}

func newServer() *brokerServer {
	s := &brokerServer{}
	return s
}

// Run starts a broker
func Run() {
	flag.Parse()
	lis, err := net.Listen("tcp", fmt.Sprintf("0.0.0.0:%d", *port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	var opts []grpc.ServerOption
	if *tls {
		// if *certFile == "" {
		// 	*certFile = testdata.Path("server1.pem")
		// }
		// if *keyFile == "" {
		// 	*keyFile = testdata.Path("server1.key")
		// }
		creds, err := credentials.NewServerTLSFromFile(*certFile, *keyFile)
		if err != nil {
			log.Fatalf("Failed to generate credentials %v", err)
		}
		opts = []grpc.ServerOption{grpc.Creds(creds)}
	}
	grpcServer := grpc.NewServer(opts...)
	pb.RegisterBrokerServer(grpcServer, newServer())
	log.Println("BrokerServer is registered...Starting store")
	store.Init()
	grpcServer.Serve(lis)
}
