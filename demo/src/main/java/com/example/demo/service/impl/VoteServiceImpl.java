package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.Production;

import com.example.demo.model.Vote;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProductionRepository;

import com.example.demo.repository.VoteRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final ProductionRepository productionRepository;

    private  final EmployeeService employeeService;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, ProductionRepository productionRepository,
                           EmployeeService employeeService) {
        this.voteRepository = voteRepository;
        this.productionRepository = productionRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Vote castVote(Long productionId, Long voterId, int score) throws Exception {

        Employee employee = employeeService.getEmployeeById(voterId);

        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new Exception("Production not found"));

        if (voteRepository.existsByProductionIdAndVoterId(productionId, voterId)) {
            throw new Exception("Voter has already voted for this production");
        }

        Vote vote = new Vote();
        vote.setProduction(production);
        vote.setVoter(employee);
        vote.setScore(score);
        return voteRepository.save(vote);
    }
}
