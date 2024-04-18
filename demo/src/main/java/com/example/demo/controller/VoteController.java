package com.example.demo.controller;

import com.example.demo.model.Vote;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/")
    public ResponseEntity<String> castVote(@RequestParam Long productionId, @RequestParam Long voterId, @RequestParam int score) {
        try {
           voteService.castVote(productionId, voterId, score);
            return ResponseEntity.ok("Vote was successfull!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
