# Voting-System

## Features Implemented:


### Enter Candidate:
            - Endpoint: /entercandidate?name={name}
            - Method: POST
            - Adds a new candidate with an initial vote count of 0.
            - If the candidate already exists, it returns an appropriate message.
### Cast Vote:
            - Endpoint: /castvote?name={name}
            - Method: POST
            - Increments the vote count for an existing candidate.
            - If the candidate doesn't exist, it returns an error message.
### Count Votes:
            - Endpoint: /countvote?name={name}
            - Method: GET
            - Returns the current vote count for a specified candidate.
            - If the candidate doesn't exist, it returns an error message.
### List Votes:
            - Endpoint: /listvote
            - Method: GET
            - Returns a list of all candidates and their respective vote counts in JSON format.
### Get Winner:
            - Endpoint: /getwinner
            - Method: GET
            - Returns the candidate with the highest number of votes.
            - In case of a tie, returns the list of candidates with the highest vote count.

