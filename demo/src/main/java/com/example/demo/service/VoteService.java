package com.example.demo.service;

import com.example.demo.model.Production;
import com.example.demo.model.Employee;
import com.example.demo.model.Vote;

public interface VoteService {
    Vote castVote(Long productionId, Long voterId, int score) throws Exception;
}
