package ru.mvn.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mvn.controller.MyConf;
import ru.mvn.controller.Registrator;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class AmqRoutes extends RouteBuilder {
    private static Log logger = LogFactory.getLog(AmqRoutes.class);

    @Autowired
    MyConf myConf;
    @Autowired
    Registrator registrator;

    public static String buffer;

    @PostConstruct
    public void init() {
        Integer len = myConf.getBufSize();
        if (len == 0) len = 80;
        String symbols = "1234567890abcdefghijklmnopqrstuvwxyzQAZWSXEDCRFVTGBYHNUJMIKOLP";
        buffer = new Random().ints(len, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    @Override
    public void configure() throws Exception {

        if (myConf.isProducer()) {

            from("timer:hello?fixedRate=true&period=2000")
                    .process((exchange) -> {
                        exchange.getOut().setHeader("JMSCorrelationID", "test-111-222-333");
                        exchange.getOut().setHeader("IPAddress", "111.222.333.444");
                        exchange.getOut().setBody(buffer + ":" + (new Date()) + "");
                    })
                    .log("log:PRODUCER?showHeaders=true")
                    .loop(myConf.getLoop())
                    .to("seda:tmp1");

            from("seda:tmp1?concurrentConsumers=100")
                    .process( exchange ->  registrator.plusProducer() )
                    .to(myConf.getProducerMQ());
        }
        if (myConf.isConsumer()) {
            from(myConf.getConsumerMQ())
            .process( exchange -> registrator.plusConsumer() )
            .log("Receive message size=${body.length}")
            ;
        }
    }
}