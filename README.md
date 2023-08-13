
# Matching Cards Game

### What will the application do?

In the game **Matching Card Game**, players are given a set of cards that are face-down,
and they must locate matching pairings by flipping them over. <br>
The player should match all the pairs in the smallest amount of time to have the greatest score

### Who will use it?

The game is suitable for any users who enjoy puzzles or memory games. 
It can be played individually or in groups for some friendly competition. <br>
Designed to provide entertainment and mental stimulation, the game can be enjoyed by both casual players and those seeking a challenge.

### Why is this project of interest to me?

This project interests me for several reasons:

-	Developing the game gives me a chance to improve my programming abilities while learning new techniques: Aspects such as designing the user interface, implementing the game logic, and producing an engaging experience all demand creativity and critical thinking.
-	I have had a strong interest for games since young, and there is no better way to start my developer journey than with the topic I am passionate about.
-	By creating a game like this, I can learn more about the field of game development. Creating a fun experience for this game will require me to comprehend game mechanics at its fundamental level, optimize its performance to avoid delays, and balance the difficulty levels to make players feel rewarded for being challenged at the right level.



### User stories

- As a player, I want to be able to change the game's difficulty to match my skill level by choosing a more complex pack of cards.
- As a player, when the card is flipped, its image should be revealed. This gives the player the ability to pair cards with the same image and the opportunity to eliminate them.
- As a player, when the second card flipped has the same image as the first one flipped, the cards are eliminated and the player can advance towards winning the game, which involves clearing the board.
- As a player, when a pair of cards is eliminated, points are added to the game's score.
- As a player, when the game is finished, the player can add that score along with a name chosen to the leaderboard.
- As a player, he should be able to save his leaderboard to file (if he so chooses).
- As a player, he should be able to load his leaderboard from file (if he so chooses).

# Instructions for Grader

- You can generate the first required event related to adding points to the game's score by eliminating a pair of cards
- You can generate the second required event related to adding scores with a name chosen to the leaderboard by finishing a game and pressing Yes after being prompted "Do you want to record the score to the leaderboard?" and submitting a name
- You can locate my visual component by playing one of the modes and seeing the cards' front and back
- You can save the state of my application by going to the Leaderboard tab at the bottom of the frame and selecting the "Save leaderboard to file" option
- You can reload the state of my application by going to the Leaderboard tab at the bottom of the frame and selecting the "Load leaderboard from file" option


## Phase 4: Task 2
Mon Aug 07 17:29:57 PDT 2023
Subtracted 5 pts to the current score


Mon Aug 07 17:30:01 PDT 2023
Added 10 pts to the current score


Mon Aug 07 17:30:04 PDT 2023
Added 10 pts to the current score


Mon Aug 07 17:30:13 PDT 2023
Added score to the leaderboard -> David: 15

## Phase 4: Task 3
After looking at the UML diagram I have made, I have found the relationship of the leaderboard class to be a bit awkward as it has to be updated after a game, but I also want the functionalities (like saving and loading) to be in one class.
This has led me to have two instance of leaderboard (one in LeaderboardTab and one in MatchingCardGameUI). \
A way to fix this could be using the singleton pattern, where there can only be one instance of the Leaderboard class, and so, a change can be seen from both classes instead of updating each other's leaderboard everytime.



