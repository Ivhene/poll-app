package no.hvl.dat250.pollApp.repo;

import no.hvl.dat250.pollApp.entity.Poll;
import no.hvl.dat250.pollApp.entity.User;
import no.hvl.dat250.pollApp.entity.Vote;
import no.hvl.dat250.pollApp.entity.VoteOption;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class DomainManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Poll> polls = new HashMap<>();
    private final Map<String, VoteOption> voteOptions = new HashMap<>();
    private final Map<String, Vote> votes = new HashMap<>();

    // User Management

    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public User getUserById(String username) {
        return users.get(username);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    // Poll Management

    public Poll createPoll(String question, String createdByUsername, List<String> optionTexts, Instant validUntil) {
        Poll poll = new Poll(question, validUntil, Instant.now(), users.get(createdByUsername));
        polls.put(poll.getId(), poll);

        List<VoteOption> options = new ArrayList<>();

        for (int i = 0; i < optionTexts.size(); i++) {
            VoteOption option = new VoteOption(optionTexts.get(i), i, poll);
            voteOptions.put(option.getId(), option);
            options.add(option);
        }

        poll.setOptions(options);

        return poll;
    }

    public Poll getPollById(String pollId) {
        return polls.get(pollId);
    }

    public Collection<Poll> getAllPolls() {
        return polls.values();
    }

    // VoteOption Management

    public VoteOption getVoteOptionById(String voteOptionId) {
        return voteOptions.get(voteOptionId);
    }

    public Collection<VoteOption> getVoteOptionsByPollId(String pollId) {
        List<VoteOption> options = new ArrayList<>();
        for (VoteOption option : voteOptions.values()) {
            if (option.getPoll().getId().equals(pollId)) {
                options.add(option);
            }
        }
        return options;
    }

    // Vote Management

    public Vote castVote(String username, String pollId, String voteOptionId) {
        // Optionally, add validation to ensure user and poll exist, and the option belongs to the poll
        Vote vote = new Vote(Instant.now(), users.get(username), voteOptions.get(voteOptionId));
        votes.put(vote.getId(), vote);
        return vote;
    }

    public Collection<Vote> getVotesByPollId(String pollId) {
        List<Vote> pollVotes = new ArrayList<>();
        for (Vote vote : votes.values()) {
            if (vote.getOption().getPoll().getId().equals(pollId)) {
                pollVotes.add(vote);
            }
        }
        return pollVotes;
    }

    public Collection<Vote> getVotesByUserId(String userId) {
        List<Vote> userVotes = new ArrayList<>();
        for (Vote vote : votes.values()) {
            if (vote.getUser().getUsername().equals(userId)) {
                userVotes.add(vote);
            }
        }
        return userVotes;
    }
}
