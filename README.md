# lethe
lethe remembers so your soul can ride in oblivion

~~~~
docker network create -d bridge lethe-bridge
docker build -t lethe .
docker run --name lethe --rm --net lethe-bridge -d -p 50051:50051 -e port=50051 lethe
~~~~
