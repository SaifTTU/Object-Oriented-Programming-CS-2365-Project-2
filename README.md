# Object-Oriented-Programming-CS-2365-Project-2
# UNO Workout!
This is a joint project of Tucker Hortman and Saif Chowdhury.
We're recreating a game called Uno workout in Java. 

Note: I just added a boolean actionHand = false; in the card class. The program can now tell when the objects are action cards or not. 
Hopefully now we can use this to begin working on the action cards.
Also Note: for now the showHand() method also adds the values of the hand, (it skips action cards) but later we should make a separate method that adds the values in one's hand.

We also added total variables to the player class that calculates the total amount of workouts that player had to do during the course of the game. These include stuff like int totalPushup; and int totalSquat; 

The method that adds values in the players hand was modified to contribute to this running total, to be displayed at the end of the game. 

The html producing method has been completed. 
We now just have to implement some javafx.
