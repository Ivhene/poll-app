package no.hvl.dat250.pollApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonManagedReference("user-polls") // Unique reference name
    @JsonProperty("polls")
    private List<Poll> polls = new ArrayList<>();

    @JsonManagedReference("user-votes") // Unique reference name
    @JsonProperty("votes")
    private List<Vote> votes = new ArrayList<>();

    public User() {
        this.username = "";
        this.email = "";
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("polls")
    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    @JsonProperty("votes")
    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
