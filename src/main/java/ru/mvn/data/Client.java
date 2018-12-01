package ru.mvn.data;


import java.time.LocalDateTime;

public class Client {

    // [{"client_id":"9161100177","message":"1234","date":20181115164358,"direction":"in"}]
    Long client_id;
    Long message_id;
    LocalDateTime date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = "out";
    }

}
