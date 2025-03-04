# Kafka-spring-boot

knowledge about Kafka using spring boot 

## Apache Kafka

Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

### Broker 

A broker in Apache Kafka is a server that stores, manages, and delivers messages. Brokers are the core components of the Kafka ecosystem. 

### What do brokers do? 

- Receive messages: Brokers receive messages from producers
- Store messages: Brokers store messages in topic partitions, which are log files
- Deliver messages: Brokers deliver messages to consumers
- Manage partitions: Brokers manage partitions, which are divisions of topics
- Replicate partitions: Brokers replicate partitions between each other to provide redundancy
- Handle requests: Brokers handle requests to write and read events

### Topics

In Apache Kafka, a topic is a logical category for organizing events or messages. Topics are similar to folders in a filesystem, where events are the files in that folder. 

### Characteristics of topics

- Append-only: New messages are added to the end of the log 
- Immutable: Events cannot be modified after they are written 
- Durable: Logs are stored on disk 
- Scalable: Topics can be configured to expire data after a certain age or size 
- Multi-producer and multi-subscriber: A topic can have multiple producers and consumers 

### How topics are used

- Developers use topics to hold different types of events 
- Developers use topics to hold filtered and transformed versions of the same type of event 
- Topics are key to publish-subscribe systems 
- Topics act as message queues where producers publish data and consumers retrieve it 

### How to create a topic

```docker
# Create a new topic
> docker exec -it kafka bash
> kafka-topics --bootstrap-server kafka:9092 --create --topic topic-test-00 --partitions 5 --replication-factor 1

# Topics list
> docker exec -it kafka bash
> kafka-topics --list --bootstrap-server kafka:9092

# Topics describe
> docker exec -it kafka bash
> kafka-topics --describe --topic topic-test-00 --bootstrap-server kafka:9092
```

- --bootstrap-server **kafka:9092** "kafka server"
- --create --topic **topic-test-00** "name of topic to create"
- --partitions **5** "partitions number"
- --replication-factor **1** "replication number of broker"

### Diagram

![ComponentsKafka](material/DIAGRAM1.png)

### Throughput

Throughput in Apache Kafka is the number of messages processed in a given time period. It is a key performance metric that measures how efficient data streaming operations are. 

### Why is throughput important?

High throughput is important for applications that process large amounts of data quickly. 
Throughput is often defined in terms of records per second or megabytes (MB) per second. 

### How to improve throughput? 

- Optimize Kafka configuration parameters
- Increase the number of producers, partitions, and consumers
- Tune producer configurations

### Other Kafka performance metrics 

- Latency: The amount of time it takes to process each message

### Kafka architecture

Kafka's architecture is designed to handle high-throughput, low-latency event streaming. It uses a distributed, partitioned, and replicated log service provided by its brokers. 

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
> docker exec -it kafka bash
> kafka-topics --bootstrap-server kafka:9092 --create --topic topic-test-00

# Create a new producer
> docker exec -it kafka bash
> kafka-console-producer --bootstrap-server kafka:9092 --topic topic-test-00

# Create a new consumer
> docker exec -it kafka bash
> kafka-console-consumer --bootstrap-server kafka:9092 --topic topic-test-00 --from-beginning

# Run docker-compose file
> docker-compose up -d

# Down execute docker-compose file
> docker-compose down

# Topics list
> docker exec -it kafka bash
> kafka-topics --list --bootstrap-server kafka:9092

# Topics describe
> docker exec -it kafka bash
> kafka-topics --describe --topic topic-test-00 --bootstrap-server kafka:9092
```

[Doccker Hub](https://hub.docker.com/_/hello-world)
