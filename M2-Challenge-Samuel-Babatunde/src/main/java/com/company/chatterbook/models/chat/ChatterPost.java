package com.company.chatterbook.models.chat;

public class ChatterPost {
    public ChatterPost(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    private final String text;

}
