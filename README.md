# lethe
lethe remembers so your soul can ride in oblivion

Build your own stubs
~~~~
go get -u github.com/golang/protobuf/protoc-gen-go
export PATH=$PATH:$GOPATH/bin
protoc -I broker/ broker/broker.proto --go_out=plugins=grpc:broker
~~~~

Use already generated code as follows
~~~~
docker build -t lethe .
docker run -d -p 8080:8080 lethe
~~~~