package no.hvl.dat250.pollApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Poll {
    @JsonProperty("id")
    private String id;

    @JsonProperty("question")
    private String question;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("publishedAt")
    private Instant publishedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("validUntil")
    private Instant validUntil;

    @JsonBackReference("user-polls") // Unique reference name
    @JsonProperty("createdUser")
    private User createdUser;

    @JsonManagedReference("poll-options") // Unique reference name
    @JsonProperty("options")
    private List<VoteOption> options = new ArrayList<>();

    public Poll() {
    }

    public Poll(String question, Instant validUntil, User createdUser) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
        this.publishedAt = Instant.now();
        this.validUntil = validUntil;
        this.createdUser = createdUser;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonProperty("publishedAt")
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("validUntil")
    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    @JsonProperty("createdUser")
    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    @JsonProperty("options")
    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }
}
