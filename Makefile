PORT ?= 50051

build:
	cp src/main/proto/lethe.proto src/test/proto/lethe.proto
	/usr/local/bin/mvn clean install

run:
	/usr/local/bin/mvn spring-boot:run -Dspring-boot.run.arguments=--spring.main.banner-mode=off,--grpc.port=${PORT}

go:
	make build
	make run