package no.hvl.dat250.pollApp.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VoteOption {
    private String id;
    private String caption;
    private int presentationOrder;
    private Poll poll;
    private List<Vote> votes;

    public VoteOption() {
    }

    public VoteOption(String caption, int presentationOrder, Poll poll) {
        this.id = UUID.randomUUID().toString();
        this.caption = caption;
        this.presentationOrder = presentationOrder;
        this.poll = poll;
        votes = new ArrayList<Vote>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }
}
