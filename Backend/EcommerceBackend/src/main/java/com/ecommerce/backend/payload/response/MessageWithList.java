package com.ecommerce.backend.payload.response;

import java.util.List;

public class MessageWithList {
    private String message;

    private List<String> list;

    public MessageWithList(String message, List<String> list) {

        this.message = message;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
