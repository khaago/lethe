package com.khaago.lethe.grpc;

import com.khaago.lethe.BrokerGrpc;
import com.khaago.lethe.CreateTopicOptions;
import com.khaago.lethe.Topic;
import com.khaago.lethe.dto.TopicDto;
import com.khaago.lethe.exception.ExceptionService;
import com.khaago.lethe.exception.InvalidInputException;
import com.khaago.lethe.repo.TopicRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.apache.ignite.IgniteAtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TopicService extends BrokerGrpc.BrokerImplBase {

    private ConversionService conversionService;
    private TopicRepository topicRepository;
    private IgniteAtomicLong topicIdGen;
    private ExceptionService exceptionService;

    @Autowired
    public TopicService(ConversionService conversionService, TopicRepository topicRepository, IgniteAtomicLong topicIdGen, ExceptionService exceptionService) {
        this.conversionService = conversionService;
        this.topicRepository = topicRepository;
        this.topicIdGen = topicIdGen;
        this.exceptionService = exceptionService;
    }

    @Override
    public void createTopic(CreateTopicOptions request, StreamObserver<Topic> responseObserver) {
        Topic topic = Topic.newBuilder(getDefaultTopic())
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setRetention(request.getRetention())
                .putAllProperties(request.getPropertiesMap())
                .build();
        TopicDto dto = conversionService.convert(topic, TopicDto.class);
        if (dto == null) {
            exceptionService.generateOnError(responseObserver, new InvalidInputException(), Status.INVALID_ARGUMENT);
        } else {
            topicRepository.save(dto);
        }
        responseObserver.onNext(topic);
        responseObserver.onCompleted();
    }

    private Topic getDefaultTopic() {
        return Topic.newBuilder()
                .setId(topicIdGen.getAndIncrement())
                .setCreated(LocalDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .build();
    }
}