package ru.mvn.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 09/07/17
 */

@Component
@ConfigurationProperties(prefix="my")
public class MyConf implements ApplicationListener<EmbeddedServletContainerInitializedEvent>, ApplicationContextAware
{
    String host_ip;
    int port;
    int bufSize;
    int loop;
    List<String> clients;
    List<String> operators;

    List<String> mobiles;

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

    public List<String> getClients() {
        return clients;
    }

    public void setClients(List<String> clients) {
        this.clients = clients;
    }

    public List<String> getOperators() {
        return operators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    public List<String> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<String> mobiles) {
        this.mobiles = mobiles;
    }

    @PostConstruct
public void viewConfig() {
        L.info("Operators:");
        Arrays.stream(operators.toArray()).forEach(s -> L.info(s));
        L.info("Clients:");
        Arrays.stream(clients.toArray()).forEach(s -> L.info(s));


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

    static ApplicationContext appContext;
    static MyConf myConf;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
        myConf  = this;
    }

    public static ApplicationContext getContext(){
        return appContext;
    }

    public static MyConf getMyConf(){
        return myConf;
    }
}
