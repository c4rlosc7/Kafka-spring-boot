# Kafka-spring-boot

knowledge about Kafka using spring boot 

## Apache Kafka

Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.


[Kafka Official](https://kafka.apache.org)


### Broker 



## Docker Commands 

Here wirte docker commands to shared.

### docker hello-world

```docker
# Docker version
> docker version

# Pull from docker hub 'hello-world' image
> docker pull hello-world

# Look local docker images
> docker images 

# Run image
> docker run image-name

# Container is running 
> docker ps

```

## Docker Compose

docker-compose.yml

```docker
version: '3.8'

services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    ports:
      - 2181:2181

  kafka:
    image: confluentinc/cp-kafka:latest
    container_name: kafka
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
    ports:
      - "9092:9092"

```

### Simple example of Apache Kafka

```docker
# Create a new topic
docker exec -it kafka bash
kafka-topics --bootstrap-server kafka:9092 --create --topic topic-test-00

# Create a new producer
docker exec -it kafka bash
kafka-console-producer --bootstrap-server kafka:9092 --topic topic-test-00

# Create a new consumer
docker exec -it kafka bash
kafka-console-consumer --bootstrap-server kafka:9092 --topic topic-test-00 --from-beginning

# Run docker-compose file
docker-compose up -d

# Down execute docker-compose file
docker-compose down

```

[Doccker Hub](https://hub.docker.com/_/hello-world)

