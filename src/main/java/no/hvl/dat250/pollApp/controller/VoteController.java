package no.hvl.dat250.pollApp.controller;

import no.hvl.dat250.pollApp.entity.Vote;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("polls/{pollId}/voteoptions/{voteoptionId}/votes")
public class VoteController {

    @Autowired
    private DomainManager domainManager;

    @GetMapping
    public ResponseEntity<List<Vote>> getVotes(@PathVariable String voteoptionId) {
        List<Vote> votes = new ArrayList<>(domainManager.getVotesByPollId(voteoptionId));

        return ResponseEntity.ok(votes);
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        Vote submittedVote = domainManager.castVote(vote);

        return ResponseEntity.ok(submittedVote);
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> updateVote(@RequestBody Vote vote) {
        Vote updatedVote = domainManager.castVote(vote);

        return ResponseEntity.ok(updatedVote);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Vote> deleteVote(@PathVariable String voteId) {
        domainManager.deleteVote(voteId);

        return ResponseEntity.noContent().build();
    }
}
