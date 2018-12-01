package ru.mvn.data;

import org.springframework.stereotype.Component;
import ru.mvn.controller.MyConf;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;


@Component
public class OperatorName implements AttributeStrategy<String> {

    private static final Random random = new Random();
    private static final List<String> values = MyConf.getMyConf().getOperators();
    private static final int size = values.size() - 1;

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return values.get(random.nextInt(size)).toString();
    }
}
