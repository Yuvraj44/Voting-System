package com.example.Voting.System;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class VotingController {
	
	private final Map<String, Integer> candidates = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();
}
