package no.hvl.dat250.pollApp.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String email;
    private List<Poll> polls;
    private List<Vote> votes;

    public User() {
        this.username = "";
        this.email = "";
        polls = new ArrayList<Poll>();
        votes = new ArrayList<Vote>();
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
