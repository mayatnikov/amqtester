package ru.mvn.data;

import ru.mvn.controller.MyConf;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Random;

public class PhoneNumber implements AttributeStrategy<String> {

    private static final Random random = new Random();
    private static final List<String> values = MyConf.getMyConf().getMobiles();
    private static final int size = values.size() - 1;

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return values.get(random.nextInt(size)).toString();
    }
}
