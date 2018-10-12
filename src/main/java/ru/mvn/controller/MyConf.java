package ru.mvn.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 09/07/17
 */

@Component
@ConfigurationProperties(prefix="my")
public class MyConf implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {
    String host_ip;
    int port;
    int bufSize;
    int loop;

    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public void setBufSize(int bufSize) {
        this.bufSize = bufSize;
    }

    public int getBufSize() {
        L.debug ("App1 started with bufSize:"+bufSize);
        return bufSize;
    }

    private static Log L = LogFactory.getLog(MyConf.class);
    boolean producer;
    boolean consumer;
    String producerMQ; //="activemq:hello";
    String consumerMQ;  // ="activemq:hello?concurrentConsumers=100";


    public String getProducerMQ() {
        return producerMQ;
    }

    public void setProducerMQ(String producerMQ) {
        this.producerMQ = producerMQ;
    }

    public String getConsumerMQ() {
        return consumerMQ;
    }

    public void setConsumerMQ(String consumerMQ) {
        this.consumerMQ = consumerMQ;
    }

    public boolean isProducer() {
        return producer;
    }

    public void setProducer(boolean producer) {
        this.producer = producer;
    }

    public boolean isConsumer() {
        return consumer;
    }

    public void setConsumer(boolean consumer) {
        this.consumer = consumer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost_ip() {
        return host_ip;
    }

    public void setHost_ip(String host_ip) {
        this.host_ip = host_ip;
    }


    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
        InetAddress ip;
        String hostname;
        try {
            host_ip  = InetAddress.getLocalHost().toString();
            L.debug ("App1 started on host:"+host_ip);
        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
    }

}
