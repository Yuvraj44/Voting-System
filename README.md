# Voting-System

Features Implemented:


1.	Enter Candidate:
            o	Endpoint: /entercandidate?name={name}
            o	Method: POST
            o	Adds a new candidate with an initial vote count of 0.
            o	If the candidate already exists, it returns an appropriate message.
2.	Cast Vote:
            o	Endpoint: /castvote?name={name}
            o	Method: POST
            o	Increments the vote count for an existing candidate.
            o	If the candidate doesn't exist, it returns an error message.
3.	Count Votes:
            o	Endpoint: /countvote?name={name}
            o	Method: GET
            o	Returns the current vote count for a specified candidate.
            o	If the candidate doesn't exist, it returns an error message.
4.	List Votes:
            o	Endpoint: /listvote
            o	Method: GET
            o	Returns a list of all candidates and their respective vote counts in JSON format.
5.	Get Winner:
            o	Endpoint: /getwinner
            o	Method: GET
            o	Returns the candidate with the highest number of votes.
            o	In case of a tie, returns the list of candidates with the highest vote count.

