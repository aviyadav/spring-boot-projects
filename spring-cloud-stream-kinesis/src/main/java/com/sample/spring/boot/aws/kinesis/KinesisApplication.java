package com.sample.spring.boot.aws.kinesis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableBinding(Processor.class)
public class KinesisApplication {

    @Autowired
    private Processor processor;

    public static void main(String... args) {
        SpringApplication.run(KinesisApplication.class, args);
    }

    public void produce(String val) {
        processor.output().send(MessageBuilder.withPayload(val).build());
    }
    
    @StreamListener(Processor.INPUT)
    public void consume(String val) {
        System.out.println("VAL == " + val);
    }
}
