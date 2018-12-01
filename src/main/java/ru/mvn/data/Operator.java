package ru.mvn.data;

import org.springframework.stereotype.Component;
import uk.co.jemos.podam.common.PodamStrategyValue;

import java.time.LocalDateTime;

public class Operator {
// [{"client_id":"9161100177","message":"1234","date":20181115164358,
// "operator_id":"VSK\\Kovchenkov","operator_name":"Ковченков,
// Алексей Владимирович","direction":"out"}]

    Long client_id;
    Long message_id;
    LocalDateTime localDateTime;
    Long operator_id;

    @PodamStrategyValue(OperatorName.class)
    String operator_name;

    String direction;

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(Long operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = "in";
    }
}

