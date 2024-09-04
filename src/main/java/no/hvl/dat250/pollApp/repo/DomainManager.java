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

    public void deleteUser(String username) {
        users.remove(username);
    }

    // Poll Management

    public Poll createPoll(Poll poll) {
        // Ensure options list is initialized and has at least 2 options
        if (poll.getOptions() == null && poll.getOptions().size() > 1) {
            return null;
        }

        // Update the user
        // Allows for user to be null
        User user = poll.getCreatedUser();
        if (user != null) {
            user.getPolls().add(poll);
            users.put(user.getUsername(), user);
        }

        // Add poll to storage
        polls.put(poll.getId(), poll);

        for (VoteOption option : poll.getOptions()) {
            option.setPoll(poll); // Set the poll reference for each option
            voteOptions.put(option.getId(), option);
        }

        return poll;
    }


    public Poll getPollById(String pollId) {
        return polls.get(pollId);
    }

    public Collection<Poll> getAllPolls() {
        return polls.values();
    }

    public void deletePoll(String pollId) {
        // removes the poll
        Poll poll = polls.remove(pollId);

        // removes the votes
        for(Vote vote : votes.values()) {
            if (vote.getOption().getPoll().equals(poll)) {
                votes.remove(vote.getId());
            }
        }

        // remove all options
        for(VoteOption voteOption : poll.getOptions()) {
            voteOptions.remove(voteOption.getId());
        }

        poll.getCreatedUser().getPolls().remove(poll); // remove the poll from the user
    }

    // VoteOption Management

    public VoteOption getVoteOptionById(String voteOptionId) {
        return voteOptions.get(voteOptionId);
    }

    public Collection<VoteOption> getVoteOptionsByPollId(String pollId) {
        Poll poll = polls.get(pollId);

        return poll.getOptions();
    }

    // Vote Management

    public Vote castVote(Vote vote) {
        // Delete existing vote for the same user and option
        User user = vote.getUser();
        VoteOption option = vote.getOption();
        Poll poll = option != null ? option.getPoll() : null;

        if (user != null && poll != null) {
            // Remove existing votes
            votes.values().removeIf(v -> v.getUser().equals(user) && v.getOption().getPoll().equals(poll));
        }

        // Add new vote
        votes.put(vote.getId(), vote);

        // Update the User's votes
        if (user != null) {
            // Ensure the user is in the map
            if (!users.containsKey(user.getUsername())) {
                users.put(user.getUsername(), user);
            }
            // Update the user's votes list
            User existingUser = users.get(user.getUsername());
            existingUser.getVotes().add(vote);
        }

        // Update the VoteOption's votes
        if (option != null) {
            // Ensure the vote option is in the map
            if (!voteOptions.containsKey(option.getId())) {
                voteOptions.put(option.getId(), option);
            }
            // Update the vote option's votes list
            VoteOption existingOption = voteOptions.get(option.getId());
            existingOption.getVotes().add(vote);
        }

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

    public void deleteVote(String voteId) {
        votes.remove(voteId);
    }
}
