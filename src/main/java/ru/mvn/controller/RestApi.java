package ru.mvn.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static ru.mvn.Application.thisApp;

/**
 * Created with IntelliJ IDEA.
 * User: vitaly
 * Date: 11/06/16
 * Time: 20:27
 */



@RestController
public class RestApi {
    private static Log L = LogFactory.getLog(RestApi.class);

    @Autowired
    MyConf conf;

    int tik=0;
// Это только для демонстрации возможностей и в тестах MQ не используется
    @RequestMapping( value={"/inf"}, method = RequestMethod.POST )
    public @ResponseBody String getStat() {
        tik++;
        String answer = "OK. tik="+tik+" "+conf.getHost_ip()+":"+conf.getPort();
        L.debug(answer);
        return answer;
    }
}
