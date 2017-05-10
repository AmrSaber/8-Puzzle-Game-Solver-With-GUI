# 8 Puzzle Game

## Overview
Simple 8 Puzzle game implemented in java with simple GUI implemented in Java SWING as shown

![Game preview](http://i.imgur.com/vA3ClxQ.png)

The game can be solved and manipulated with the player and can be auto-solved anytime with the 2 solving buttons (See notes below)

## General Note
* The game is very simple and solution is implemented with **A\* Search** as well as **DFS Search**
of course A* is for the optimal solution with the least moves in the minimum time 
and the DFS is totally horrible it's just there for comparison  
* When using the **DFS** Solution I strongly advice to set the game speed to *Fast* (with the last button) as the solution
is usually very long
* You can stop the solution anytime by pressing the *Reset* button, the solution will stop and the game will reset
(no thing else will work while the game is being solved by the Algorithms anyway :P)

### Game Features
* After a solving method is pressed, solving data will be displayed on the top right indicating how long did the search take, 
number of moves it requires and number of expanded nodes needed to solve the situation (See image below)
![status Image](http://i.imgur.com/sFg1m8e.png)
* When the game is solved absolutly nothing happens, I am depending on the players to celebrate thierselves in the way they see fit :"D
