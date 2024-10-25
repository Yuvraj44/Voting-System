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
    
    @GetMapping("/listvote")
    public Map<String, Integer> listVotes() 
    {
        return candidates; 
    }
    
    @GetMapping("/getwinner")
    public String getWinner() 
    {
        lock.lock();
        try 
        {
            int maxVotes = candidates.values().stream()
                .max(Integer::compareTo)
                .orElse(0);

        
            List<String> winners = candidates.entrySet().stream()
                .filter(entry -> entry.getValue() == maxVotes)
                .map(Map.Entry::getKey)
                .toList();

            if (winners.isEmpty()) 
                return "No candidates available.";
            else if (winners.size() == 1) 
                return "The winner is " + winners.get(0);
            else 
                return "It's a tie! The winners are " + String.join(", ", winners);
        
        } 
        finally 
        {
            lock.unlock();
        }
    }


}
