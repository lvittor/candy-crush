# Objective

The aim of this work is to implement new functionalities in a very reduced version of the game **_Candy Crush_** developed in Java.

## Functional description

The game consists of a vertically oriented grid of different colored candies. The mechanics of the game lie in choosing a pair of adjacent candies to swap their positions. This exchange is valid only if a valid figure is formed with candies of the same color.

The figures are horizontal or vertical lines of 3, 4 or 5 candies, or a T (of three candies in a line and two perpendicular in the middle) in any orientation, or an L in any orientation.

If the exchange is valid (because one or two figures are formed due to it) the candies that compose them "explode" and disappear. Then the candies that were on top of them fall instead (this is a recursive process).

When certain figures explode they leave special candies in their place, which when they explode generate cascading explosions of different types. Each exploding candy scores the user.

## Levels

### Level 1:   
The objective of this level is to reach the desired score before a certain number of moves.

![Level1](https://media.giphy.com/media/UohbAYIuTuiztcbKpK/giphy.gif)

### Level 2: GoldenBoard
The goal of this level is for the entire board to turn to gold.

![Level2](https://media.giphy.com/media/VFgd2b8XDXeHDzqNar/giphy.gif)
 
For this it is necessary to make exchanges:
- Valid horizontal exchanges transform the row in question to gold.
- Valid vertical exchanges transform the column in question to gold.

If a row or column is already gold and valid trades are made on it, it does nothing. The number of squares on the board remaining to turn gold is displayed on the bottom panel, along with the score and remaining moves.

### Level 5: Fruit (Cherry & Hazelnut)

The objective of the level is to bring a certain amount of fruit to the last row of the board in less than a certain amount of moves.

![Level5](https://media.giphy.com/media/c5e2L8WZyDTrwJKfld/giphy.gif)

These fruits cannot generate combos, no special candy deletes them and they only disappear when they reach the last row of the board.

The number of remaining fruits and the number of remaining moves are displayed in the lower panel, along with the score.
