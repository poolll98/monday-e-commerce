package com.ecommerce.backend.payload.response;

public class AddElementMessage extends MessageResponse {

    private Long id;

    public AddElementMessage(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
