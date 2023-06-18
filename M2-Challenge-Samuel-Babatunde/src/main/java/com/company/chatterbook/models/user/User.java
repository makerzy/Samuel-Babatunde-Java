package com.company.chatterbook.models.user;

import com.company.chatterbook.models.chat.ChatterPost;

import java.util.ArrayList;
import java.util.List;

public class User {
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private final String name;



    public List<ChatterPost> getChatterPosts() {
        return chatterPosts;
    }

    public void setChatterPost(List<ChatterPost> chatterPosts) {
        this.chatterPosts = chatterPosts;
    }

    private List<ChatterPost> chatterPosts = new ArrayList<>();



}
