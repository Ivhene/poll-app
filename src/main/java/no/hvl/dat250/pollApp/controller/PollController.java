package no.hvl.dat250.pollApp.controller;

import no.hvl.dat250.pollApp.entity.Poll;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private DomainManager domainManager;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Poll>> getPolls() {
        List<Poll> polls = domainManager.getAllPolls().stream().toList();
        return ResponseEntity.ok(polls);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = domainManager.createPoll(poll);
        return ResponseEntity.status(201).body(createdPoll);
    }

    @GetMapping(value = "/{pollId}", produces = "application/json")
    public ResponseEntity<Poll> getPollById(@PathVariable String pollId) {
        Poll poll = domainManager.getPollById(pollId);
        return ResponseEntity.ok(poll);
    }

    @PutMapping(value = "/{pollId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Poll> updatePoll(@PathVariable String pollId, @RequestBody Poll poll) {
        Poll updatedPoll = domainManager.createPoll(poll);
        return ResponseEntity.ok(updatedPoll);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<Void> deletePoll(@PathVariable String pollId) {
        domainManager.deletePoll(pollId);
        return ResponseEntity.noContent().build();
    }
}
