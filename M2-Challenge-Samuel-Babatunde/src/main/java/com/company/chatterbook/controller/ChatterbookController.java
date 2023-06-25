package com.company.chatterbook.controller;

import com.company.chatterbook.models.user.User;
import com.company.chatterbook.models.chat.ChatterPost;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class ChatterbookController {

    private List<User> userList = new ArrayList<>();



    public ChatterbookController() {
        User luis = new User("Luis");
        User sue = new User("Sue");
        User timothy = new User("Timothy");
        User george = new User("George");
        User arturo = new User("Arturo");
        User mariella = new User("Mariella");
        User paolo = new User("Paolo");
        User tri = new User("Tri");
        User jane = new User("Jane");
        User carol = new User("Carol");
        User carl = new User("Carl");

        luis.setChatterPost(Arrays.asList(new ChatterPost("Hey! This is my first post!"), new ChatterPost("Anybody want to be friends?")));
        sue.setChatterPost(Arrays.asList(new ChatterPost("I'm bored"), new ChatterPost("Who wants to hang?")));
        timothy.setChatterPost(Arrays.asList(new ChatterPost("My life is awesome!"), new ChatterPost("Click here to buy my vegan shakes!")));
        george.setChatterPost(Arrays.asList(new ChatterPost("I like pigs."), new ChatterPost("I love lamp.")));
        arturo.setChatterPost(Arrays.asList(new ChatterPost("My cat is so cute"), new ChatterPost("My kitten is purr-fect.")));
        mariella.setChatterPost(Arrays.asList(new ChatterPost("I'll never post again")));
        paolo.setChatterPost(Arrays.asList(new ChatterPost("Have you ever heard of the band Nirvana?"), new ChatterPost("Pearl Jam 4 Life")));
        tri.setChatterPost(Arrays.asList(new ChatterPost("You definitely grew up in the 90s if you get these 10 references."), new ChatterPost("I don't get this generation? Everyone expects a participation trophy.")));
        jane.setChatterPost(Arrays.asList(new ChatterPost("I just wrecked my dad's car. He's going to kill me!"), new ChatterPost("Grounded.... for 5 months :( ")));
        carol.setChatterPost(Arrays.asList(new ChatterPost("Does anyone have some imodium?")));
        carl.setChatterPost(Arrays.asList(new ChatterPost("My roommate is awful!!!!"), new ChatterPost("Anyone know a room for rent in Hyde Park?")));

        userList = Arrays.asList(luis, sue, timothy, george, arturo, mariella, paolo, tri, jane, carol, carl);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUserList() {
        return userList;
    }

    @RequestMapping(value = "user/{name}", method = RequestMethod.GET)
    public User getUser(@PathVariable("name") String username){
        username = sanitize(username.strip());
        User user = null;
        if(username.length() > 0) {
            for (User _user : userList) {
                if (_user.getName().toLowerCase().equals(username.toLowerCase())) {
                    user = _user;
                }
            }
        }
        return user;
    }

    @RequestMapping(value = "chat/{user}", method = RequestMethod.GET)
    public List<ChatterPost> getUserChatterPosts(@PathVariable("user") String username){
        User user = null;
        List<ChatterPost> userPosts = new ArrayList<>();
//        Sanitize user input using allowed characters
        username = sanitize(username.strip());
        if(username.length() > 0) {
            for (User _user : userList) {
                if (_user.getName().toLowerCase().equals(username.toLowerCase())) {
                    user = _user;
                }
            }
        }
        if (user != null) {
            userPosts = user.getChatterPosts();
        }
        return userPosts;
    }


    private String sanitize(String rawinput){
        return rawinput.replaceAll("[^A-Za-z0-9_]", "");
    }

}
