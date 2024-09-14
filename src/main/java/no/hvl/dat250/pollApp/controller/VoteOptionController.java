package no.hvl.dat250.pollApp.controller;

import no.hvl.dat250.pollApp.entity.VoteOption;
import no.hvl.dat250.pollApp.repo.DomainManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/polls/{pollId}/voteoptions")
public class VoteOptionController {

    @Autowired
    private DomainManager domainManager;

    @GetMapping
    public ResponseEntity<List<VoteOption>> getVoteOptions(@PathVariable String pollId) {
        List<VoteOption> options = domainManager.getVoteOptionsByPollId(pollId).stream().toList();

        return ResponseEntity.ok(options);
    }

    @GetMapping("/{voteoptionId}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable String voteoptionId) {
        VoteOption voteOption = domainManager.getVoteOptionById(voteoptionId);

        return ResponseEntity.ok(voteOption);
    }
}
