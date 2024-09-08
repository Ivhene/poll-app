package no.hvl.dat250.pollApp;

import no.hvl.dat250.pollApp.entity.Poll;
import no.hvl.dat250.pollApp.entity.User;
import no.hvl.dat250.pollApp.entity.Vote;
import no.hvl.dat250.pollApp.entity.VoteOption;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PollAppApplicationTests {

	private User user;
	private DomainManager domainManager;


	@BeforeEach
	void setup() {
		domainManager = new DomainManager();
		user = new User("user1", "user1@gmail.com");
	}

	@Test
	void testCase1() {
		domainManager.addUser(user);
		assertEquals(1, domainManager.getAllUsers().size());
		assertTrue(domainManager.getAllUsers().contains(user));

		User user2 = new User("user2", "user2@gmail.com");
		domainManager.addUser(user2);
		assertEquals(2, domainManager.getAllUsers().size());
		assertTrue(domainManager.getAllUsers().contains(user2));

		Poll poll = new Poll("Question", Instant.MAX, user);
		poll.setOptions( Arrays.asList(new VoteOption("alt 1", 1, poll), new VoteOption("alt 2", 2, poll),new VoteOption("alt 3", 3, poll)));
		poll = domainManager.createPoll(poll);
		assertEquals(1, domainManager.getAllPolls().size());

		Vote vote1 = new Vote(user2, poll.getOptions().get(1));
		Vote vote2 = new Vote(user2, poll.getOptions().get(0));
		domainManager.castVote(poll.getOptions().get(1).getId(), vote1);
		domainManager.castVote(poll.getOptions().get(0).getId(), vote2);

		assertEquals(1, domainManager.getVotesByPollId(poll.getId()).size());
		assertTrue(domainManager.getVotesByPollId(poll.getId()).contains(vote2));
		assertFalse(domainManager.getVotesByPollId(poll.getId()).contains(vote1));

		domainManager.deletePoll(poll.getId());

		assertEquals(0, domainManager.getVotesByPollId(poll.getId()).size());
	}

	@Test
	void testCase2() {
		domainManager.addUser(user);

		Poll poll = new Poll("Question", Instant.MAX, user);
		poll.setOptions( Arrays.asList(new VoteOption("alt 1", 1, poll), new VoteOption("alt 2", 2, poll),new VoteOption("alt 3", 3, poll)));
		domainManager.createPoll(poll);


		poll.setQuestion("New Question");
		poll = domainManager.createPoll(poll);

		assertEquals(poll, domainManager.getPollById(poll.getId()));
		assertEquals(3, poll.getOptions().size());
		assertEquals("New Question", poll.getQuestion());

		domainManager.deletePoll(poll.getId());

		assertEquals(0, domainManager.getAllPolls().size());
		assertEquals(0, domainManager.getUserById(user.getUsername()).getPolls().size());
		assertEquals(0, domainManager.getVoteOptionsByPollId(poll.getId()).size());
	}

	@Test
	void testCase3() {
		domainManager.addUser(user);
		assertEquals(user, domainManager.getUserById(user.getUsername()));

		user.setEmail("newmail@gmail.com");
		domainManager.updateUser(user.getUsername(), user);

		String oldUsername = user.getUsername();
		user.setUsername("new");
		domainManager.updateUser(oldUsername, user);

		assertEquals(1, domainManager.getAllUsers().size());
		assertTrue(domainManager.getAllUsers().contains(user));
		assertEquals("new", user.getUsername());
	}

	@Test
	void testCase4() {
		domainManager.addUser(user);

		User user2 = new User("user2", "user2@gmail.com");
		domainManager.addUser(user2);

		Poll poll = new Poll("Question", Instant.MAX, user);
		poll.setOptions( Arrays.asList(new VoteOption("alt 1", 1, poll), new VoteOption("alt 2", 2, poll),new VoteOption("alt 3", 3, poll)));
		domainManager.createPoll(poll);

		Vote vote = new Vote(user2, poll.getOptions().get(1));
		domainManager.castVote(poll.getOptions().get(1).getId(), vote);

		assertEquals(1, domainManager.getVotesByPollId(poll.getId()).size());
		assertTrue(domainManager.getVotesByPollId(poll.getId()).contains(vote));

		domainManager.deleteVote(vote.getId());
		assertEquals(0, domainManager.getVotesByPollId(poll.getId()).size());

		poll = domainManager.getPollById(poll.getId());
		assertEquals(0, poll.getOptions().get(1).getVotes().size());

		user = domainManager.getUserById(user.getUsername());
		assertEquals(0, user.getVotes().size());
	}
}
