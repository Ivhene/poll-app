package no.hvl.dat250.pollApp.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Poll {
    private String id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private User createdUser;
    private List<VoteOption> options;

    public Poll() {
    }

    public Poll(String question, Instant publishedAt, Instant validUntil, User createdUser) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.createdUser = createdUser;
        this.options = new ArrayList<VoteOption>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }
}
