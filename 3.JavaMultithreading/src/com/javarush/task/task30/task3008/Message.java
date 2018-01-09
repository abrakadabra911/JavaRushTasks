package com.javarush.task.task30.task3008;

import java.io.Serializable;

/**
 * Created by leha on 2017-06-18.
 */
public class Message implements Serializable {
    public Message(MessageType type) {
        this.type = type;
        this.data = null;
    }

    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    private final MessageType type;
    private final String data;

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }


}
