package ru.mvn.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import ru.mvn.Application;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Registrator {
    private static Log logger = LogFactory.getLog(Registrator.class);

    int consumerCounter;
    int producerCounter;


    @PostConstruct
    public void init() {
        consumerCounter=0;
        producerCounter=0;
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("Producer count="+ producerCounter);
        logger.info("Consumer count="+ consumerCounter);
    }

    public int plusConsumer() {
       return consumerCounter++;
    }
    public int plusProducer() {
       return  producerCounter++;
    }

    public int getConsumerCounter() {
        return consumerCounter;
    }

    public void setConsumerCounter(int consumerCounter) {
        this.consumerCounter = consumerCounter;
    }

    public int getProducerCounter() {
        return producerCounter;
    }

    public void setProducerCounter(int producerCounter) {
        this.producerCounter = producerCounter;
    }
}
