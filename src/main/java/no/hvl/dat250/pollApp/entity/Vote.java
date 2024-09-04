package no.hvl.dat250.pollApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class Vote {
    @JsonProperty("id")
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("publishedAt")
    private Instant publishedAt;

    @JsonBackReference("option-votes") // Unique reference name
    @JsonProperty("option")
    private VoteOption option;

    @JsonBackReference("user-votes") // Unique reference name
    @JsonProperty("user")
    private User user;

    public Vote() {
    }

    public Vote(User user, VoteOption option) {
        this.id = UUID.randomUUID().toString();
        this.publishedAt = Instant.now();
        this.user = user;
        this.option = option;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("publishedAt")
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("option")
    public VoteOption getOption() {
        return option;
    }

    public void setOption(VoteOption option) {
        this.option = option;
    }
}
