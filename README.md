
# Chess

>A chess game is a family of strategic board games played by two players on a square
chessboard of 64 squares, using a set of pawns and pieces.

## Overview

A simple console chess game, made as one of the projects, assigned during the first-year of IT studies.

## Project Requirements

Implement a fully functional chess game using the object-oriented programming concepts presented during the classes. 
In the logical part, implement all the issues related to the actors on the board (pawns and pieces), 
while in the visual part, implement a text representation of the board with pawns and pieces.
<br> <br>Make sure that there is the possibility to save and load the game board at any point
of the game, stored as a binary file according to the specified format.
<br>Specification of the format starting from the least significant bit (bit at index 0):
* The first 3 bits determine the type of piece or pawn (1 - king, 2 - queen, 3 - rook,
4 - bishop, 5 - knight, 0 - pawn);
* The next 4 bits specify the horizontal position;
* The next 4 bits specify the vertical position;
* The last bit specifies the color of the piece.
Note that position 0, 0 is reserved for a piece that is not on the board





