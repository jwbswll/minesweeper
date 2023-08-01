# MineSweeper CLI Game

## Images
- [Difficulty screen and first render](./images/Screenshot%202023-08-01%20at%2015.34.48.png)
- [Player selects space that neighbours a mine](./images/Screenshot 2023-08-01 at 15.34.56.png)
- [Player select empty space which cascades into other empty spaces](./images/Screenshot 2023-08-01 at 15.58.15.png)
- [You win!](./images/Screenshot 2023-08-01 at 15.55.00.png)
- [You lose!](./images/Screenshot 2023-08-01 at 15.52.38.png)

## MVP
Recreate a simplified version of the game Minesweeper to be played in the java console The game should be able to randomly generate 10 mines in a 10x10 grid The user will be able to enter a command that represents a coordinate to check a location for a mine The application will display a number from 0-8 depending on how many mines surround that location If the user selects a mine, the game will respond "boom!" and the game will be lost If every non-mine square has been revealed, the game is won Render the grid to the console after every user command

** Bonuses (optional) **

Allow for the user to configure number of mines and grid size via a configuration. (Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares

---

## Build Steps

- With JRE installed, open gitbash/terminal at folder with cloned repo
- run:

```bash
java -jar MineSweeper.jar
```

---


## Features

- Set difficulty level which sets the number of mines on the field
- Select the row and column number where you want to check for mines
- Recursive method that reveals all adjacent empty squares until it reaches a square that neighbours a mine
- printLegend method that prints minefield with numbers and mines for debugging

---

## Known issues

- Method that reveals adjacent empty squares to user's coordinates doesn't work all the time. It will reveal most of the empty squares, but sometimes stops a few short. If you try it out and find the bug please let me know!

---

## Future Goals

- Fix method that reveal adjacent empty squares when user selects an empty square
- Add option at the beginning to set grid-size


---
