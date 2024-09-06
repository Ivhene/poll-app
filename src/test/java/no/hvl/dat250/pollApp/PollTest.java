package no.hvl.dat250.pollApp;

import no.hvl.dat250.pollApp.entity.Poll;
import no.hvl.dat250.pollApp.entity.User;
import no.hvl.dat250.pollApp.entity.Vote;
import no.hvl.dat250.pollApp.entity.VoteOption;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PollTest {

    @Autowired
    private DomainManager domainManager;

    private User user1, user2;

    @BeforeEach
    void setUp() {
        domainManager = new DomainManager();
        user1 = new User("user1", "user1@gmail.com");
        user2 = new User("user2", "user2@gmail.com");
    }

    @Test
    void pollTest() {
        int originalSize = domainManager.getAllPolls().size();
        Poll poll = new Poll("question", Instant.MAX, user1);
        poll.setOptions( Arrays.asList(new VoteOption("alt 1", 1, poll), new VoteOption("alt 2", 2, poll),new VoteOption("alt 3", 3, poll)));

        poll = domainManager.createPoll(poll);

        assertEquals(poll, domainManager.getPollById(poll.getId()));
        assertEquals(originalSize + 1, domainManager.getAllPolls().size());
        assertEquals(3, poll.getOptions().size());

        int votesCount = domainManager.getVotesByPollId(poll.getId()).size();
        Vote vote1 = new Vote(user2, poll.getOptions().get(1));
        Vote vote2 = new Vote(user2, poll.getOptions().get(0));
        domainManager.castVote(poll.getOptions().get(1).getId(), vote1);
        domainManager.castVote(poll.getOptions().get(0).getId(), vote2);

        assertEquals(votesCount + 1, domainManager.getVotesByPollId(poll.getId()).size());
        assertTrue(domainManager.getVotesByPollId(poll.getId()).contains(vote2));

        domainManager.deletePoll(poll.getId());

        assertEquals(originalSize, domainManager.getAllPolls().size());
        assertEquals(0, domainManager.getVotesByPollId(poll.getId()).size());
    }
}
