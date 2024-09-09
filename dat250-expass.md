# DAT250 Assignment 2 - Poll App

## Introduction
This repository contains my solution to the poll app api assignment in DAT250 at HVL. You can read about the assignment itself [here](https://github.com/selabhvl/dat250public/blob/master/expassignments/expass2.md). In this document I will go over what has been done, the problems I encountered while making the solution and what is left to do in order to make the solution complete.

## What has been done
As part of this assignment, I have made my solution for an api used in a poll app. The solution was made with spring boot, and I have divided the classes into 3 main packages
 - **Controller** - Contains all of the Controller classes. Decided to have 1 controller for each resource. [View package](https://github.com/Ivhene/poll-app/tree/main/src/main/java/no/hvl/dat250/pollApp/controller)
 - **Entity** - Contains all the entities (resource) classes. These are the objects as defined in the domain model (see task description). [View package](https://github.com/Ivhene/poll-app/tree/main/src/main/java/no/hvl/dat250/pollApp/entity)
 - **Repo** - Contains the class used to store resources in memory. This could have also been defined as a service package, but decided to have it as a repo. [View package](https://github.com/Ivhene/poll-app/tree/main/src/main/java/no/hvl/dat250/pollApp/repo)

### Test cases
There were a couple of test cases defined that the API had to cover in order to be completed. In some test cases I have decided to skip some of the checks on if something was created. I only do this if I feel like another test case already tests this, to avoid doubling up on some checks.
#### Case 1
- Create a new user
- List all users and check if the newly added user exist
- Create another user
- List all users again and check if both created users exist
- The first user creates a new poll
- List all polls and check if the new poll exist
- The second user votes on the poll
- The second user changes their vote on the poll
- List all votes and verify that only the most recent vote exist
- Delete the poll
- Check that all votes are deleted

#### Case 2
 - Create a new user
 - Create a new poll
 - Edit the poll
 - Get the poll by its ID and check if it matches the edited poll
 - List all voteoptions and check if they exist
 - Delete the poll
 - List all polls and check if it is empty
 - Check if the polls assosiated with the user is an empty list
 - Check that the voteoptions were deleted

#### Case 3
 - Create a new user
 - Get the user by username and check that it matches the created user
 - Edit the users email
 - Get the user by username and check that it matches the edited user
 - Edit the users username
 - List all users and check that it only contains the user with a new username

#### Case 4
- Create a new user
- Create another user
- The first user creates a new poll
- The second user votes on the poll
- List all votes and verify that the vote exist
- Delete the vote
- List all votes and verify that the vote is deleted
- Check that the voteoption on the poll no longer has a vote
- Check that the user do not currently have a vote registered

#### Cases as code
```java
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
```


## Problems
The only real problem I encountered was when figuring out how to define the JSON objects without having infinite cycles of objects. I ended up solving this by using the ```@JsonManagedReference``` and ```@JsonBackReference```. There were still some struggles with this, as initially I got some errors from Spring being unable to find the reference pairings. I eventually figured out that this was due to all the references being unnamed, as Spring saw multiple references that shared the same identifier. This was solved by putting identifiers on all the references.

Example from User and Vote
```java
    // In Vote
    @JsonBackReference("user-votes") // Unique reference name
    @JsonProperty("user")
    private User user;

    // In User
    @JsonManagedReference("user-votes") // Unique reference name
    @JsonProperty("votes")
    private List<Vote> votes = new ArrayList<>();
```

## Remaining work
To start off, I did not do the optional API Documentation step before submitting. If it is done when you look at this, then I decided to do it for myself at a later point. Other than that, the only things I would try to get done with more time is to test more, as I do believe that there are still some places where I have not correctly defined adding or removing the linked resources (A Vote might not always have the updated voteoption, if i edit the voteoptions, what happens to their votes, etc). I think there is more test cases like these that I could make in order to make the solution "complete". 
