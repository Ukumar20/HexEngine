# Hex Game

## Description
Hex Game is a strategy board game where two players (or a player and a bot, or two bots) compete to connect opposite sides of the hexagonal grid with their respective colors. This project includes a core engine (`HexEngine`) that enforces the game rules, a user interface (`HexBoard`) built with JavaFX, and support for various game modes: Player vs. Player (PVP), Player vs. Computer (PVC), and Computer vs. Computer (CVC).

## Rules of Hex
- The game is played on a hexagonal grid of varying sizes, typically 11x11, 13x13, or 19x19.
- Each player has a color (commonly red and blue) and tries to connect their respective sides of the board with an unbroken chain of their colored tiles.
- Players take turns placing a tile of their color on an empty cell.
- The game starts with an empty board.
- Any colour can start first
- The firstPlay player to form a connected path of their color linking their two opposing sides of the board wins the game.
- The game cannot end in a draw, as it is always possible for one player to create a winning path.

For more details and history of the game, see here [Hex](https://en.wikipedia.org/wiki/Hex_(board_game)).

## Features
- Basic game play based on rules of Hex
- Support for multiple game modes (i.e. PVP, PVC, CVC)

## Installation
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven 3.8.7 or higher

## Play
Simply build the maven project and run the App class - src/main/java/com/example/App.java

## Junit
Test cases - src/test/java/com/example/AppTest.java