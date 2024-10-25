package com.example.Voting.System;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class VotingController {
	
	private final Map<String, Integer> candidates = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();
    
    @PostMapping("/entercandidate")
    public String enterCandidate(@RequestParam String name) 
    {
        lock.lock();
        try 
        {
            if (candidates.containsKey(name)) 
                return "Candidate already exists.";
             else
            {
                candidates.put(name, 0);
                return "Saved";
            }
        } 
        finally
        {
            lock.unlock();
        }
    }
    
    @PostMapping("/castvote")
    public String castVote(@RequestParam String name) 
    {
        lock.lock();
        try 
        {
            if (candidates.containsKey(name)) {
                candidates.put(name, candidates.get(name) + 1);
                return "Vote casted";
            }
            else 
                return "Candidate does not exist.";
            
        }
         finally 
        {
            lock.unlock();
        }
    }
    
    @GetMapping("/countvote")
    public String countVote(@RequestParam String name) 
    {
        lock.lock();
        try 
        {
            if (candidates.containsKey(name)) 
                return "Candidate " + name + " has " + candidates.get(name) + " votes.";
            else 
                return "Candidate does not exist.";
            
        } 
        finally 
        {
            lock.unlock();
        }
    }

}
