# Instructions to run
First, make sure you have Java 8 installed and Gradle 2.12 or higher.

# Running
After cloning the repo, go to the root folder and execute `./gradlew bootRun`. To run the tests use `./gradlew test`.

# Testing the API

## To create a new game:

Request: 
```
POST http://localhost:8080/new_game

{ 
    "name": "Player name"
}
```

Response:
```
{
  "gameId": "35d03fa5-be6e-471e-bec4-b4a9a235641d",
  "availableCodePegs": [
    "RED",
    "BLUE",
    "GREEN",
    "YELLOW",
    "ORANGE",
    "PURPLE",
    "CYAN",
    "MAGENTA"
  ],
  "codeLength": 8,
  "solved": false,
  "playerResource": {
    "name": "Player name",
    "results": []
  }
}
```

## To make a guess for a previously created game:

Request:
```
POST http://localhost:8080/guess

{ 
    "gameId": "b2813487-a282-4cf6-8884-53d2295f2504",
    "password" : ["RED", "RED", "RED", "RED", "RED", "RED", "RED", "RED"]
}
```

Response:
```
{
  "gameId": "35d03fa5-be6e-471e-bec4-b4a9a235641d",
  "availableCodePegs": [
    "RED",
    "BLUE",
    "GREEN",
    "YELLOW",
    "ORANGE",
    "PURPLE",
    "CYAN",
    "MAGENTA"
  ],
  "codeLength": 8,
  "solved": false,
  "playerResource": {
    "name": "Your name",
    "results": [
      {
        "guessedPassword": [
          "RED",
          "RED",
          "RED",
          "RED",
          "RED",
          "RED",
          "RED",
          "RED"
        ],
        "numberOfWhiteKeyPegs": 0,
        "numberOfBlackKeyPegs": 1
      }
    ]
  }
}
```
