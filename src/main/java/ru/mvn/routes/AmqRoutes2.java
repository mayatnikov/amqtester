package ru.mvn.routes;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mvn.controller.MyConf;
import ru.mvn.controller.Registrator;
import ru.mvn.data.Client;
import ru.mvn.data.Operator;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.annotation.PostConstruct;

@Component
public class AmqRoutes2 extends RouteBuilder {
    private static Log logger = LogFactory.getLog(AmqRoutes2.class);

    @Autowired
    MyConf myConf;

    @Autowired
    Registrator registrator;

//    @PropertyInject("period")
//    private String period ="200";


    @PostConstruct
    public void init() {
    }

    @Override
    public void configure() throws Exception {


        PodamFactory factory = new PodamFactoryImpl();


        from("timer:operator-mq?fixedRate=true&period={{msg-period}}")
                .process((exchange) -> {
                    exchange.getOut().setBody(factory.manufacturePojo(Operator.class));
                })
                .marshal().json()
                .to("log:OPERATOR?showHeaders=true&showBody=true")
                    .to("{{client-mq}}");


        from("timer:client-mq?fixedRate=true&period={{msg-period}}")
                .process((exchange) -> {
                    exchange.getOut().setBody(factory.manufacturePojo(Client.class));
                })
                .marshal().json()
                .to("log:CLIENT?showHeaders=true&showBody=true")
                    .to("{{operator-mq}}");


    }

}