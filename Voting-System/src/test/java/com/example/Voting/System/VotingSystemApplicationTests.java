package com.example.Voting.System;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VotingSystemApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testVotingSystem() {
        
        ResponseEntity<String> response = restTemplate.postForEntity("/entercandidate?name=ajay", null, String.class);
        assertEquals("Saved", response.getBody());

        
        ResponseEntity<String> duplicateResponse = restTemplate.postForEntity("/entercandidate?name=ajay", null, String.class);
        assertEquals("Candidate already exists.", duplicateResponse.getBody());

        
        

        
        
        ResponseEntity<String> castVoteResponse = restTemplate.postForEntity("/castvote?name=ajay", null, String.class);
        assertEquals("Vote casted", castVoteResponse.getBody());

        
        ResponseEntity<String> invalidCandidateResponse = restTemplate.postForEntity("/castvote?name=neha", null, String.class);
        assertEquals("Candidate does not exist.", invalidCandidateResponse.getBody());

        
        
        
        
        
        
        ResponseEntity<String> countVoteResponse = restTemplate.getForEntity("/countvote?name=ajay", String.class);
        assertEquals("Candidate ajay has 1 votes.", countVoteResponse.getBody());

       
        ResponseEntity<String> invalidCountVoteResponse = restTemplate.getForEntity("/countvote?name=neha", String.class);
        assertEquals("Candidate does not exist.", invalidCountVoteResponse.getBody());

        
        
        
        
        
        
        
        
        restTemplate.postForEntity("/castvote?name=ajay", null, String.class);
        restTemplate.postForEntity("/entercandidate?name=neha", null, String.class);
        restTemplate.postForEntity("/castvote?name=neha", null, String.class);
        restTemplate.postForEntity("/castvote?name=neha", null, String.class);
        restTemplate.postForEntity("/castvote?name=neha", null, String.class);

        
        ResponseEntity<Map> listVoteResponse = restTemplate.getForEntity("/listvote", Map.class);
        Map<String, Integer> votes = listVoteResponse.getBody();

        assertTrue(votes.containsKey("ajay"));
        assertTrue(votes.containsKey("neha"));
        assertFalse(votes.containsKey("Yuvraj"));
        assertEquals(2, votes.get("ajay"));
        assertEquals(3, votes.get("neha"));

        
        
        
        
        
        ResponseEntity<String> getWinnerResponse = restTemplate.getForEntity("/getwinner", String.class);
        assertEquals("The winner is neha", getWinnerResponse.getBody());

        
        restTemplate.postForEntity("/castvote?name=ajay", null, String.class);
        ResponseEntity<String> tieResponse = restTemplate.getForEntity("/getwinner", String.class);
        assertEquals("It's a tie! The winners are neha, ajay", tieResponse.getBody());
    }
}

