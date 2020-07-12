PORT ?= 50051

build:
	# cp src/main/proto/lethe.proto src/test/proto/lethe.proto
	/usr/local/bin/mvn clean install

run:
	/usr/local/bin/mvn spring-boot:run \
					-Dspring-boot.run.arguments=--spring.main.banner-mode=off,--grpc.port=${PORT}

debug:
	/usr/local/bin/mvn spring-boot:run \
					-Dspring-boot.run.arguments=--spring.main.banner-mode=off,--grpc.port=${PORT} \
					-Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

go:
	make build
	make run