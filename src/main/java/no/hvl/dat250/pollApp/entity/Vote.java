package no.hvl.dat250.pollApp.entity;

import java.time.Instant;
import java.util.UUID;

public class Vote {
    private String id;
    private Instant publishedAt;
    private User user;
    private VoteOption option;

    public Vote() {
    }

    public Vote(Instant publishedAt, User user, VoteOption option) {
        this.id = UUID.randomUUID().toString();
        this.publishedAt = publishedAt;
        this.user = user;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoteOption getOption() {
        return option;
    }

    public void setOption(VoteOption option) {
        this.option = option;
    }
}
