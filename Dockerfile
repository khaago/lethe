FROM golang:1-alpine

RUN apk update
RUN apk add protobuf

LABEL maintainer="khaago@protonmail.com"

WORKDIR /app

COPY go.mod go.sum dinstall ./

RUN chmod +x dinstall

RUN ./dinstall

COPY . .

RUN protoc -I broker/ broker/broker.proto --go_out=plugins=grpc:broker

RUN ls -ltr broker/

RUN go install

EXPOSE ${port}

ENTRYPOINT lethe -port=${port}

