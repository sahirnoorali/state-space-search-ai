# Solving RGB Puzzle via State Space Searches

This project applies the following informed and uninformed searches:

- Uninformed Searches:
  1. Breadth First Search
  2. Depth First Search
  3. Iterative Deepening Search
- Informed Searches:
  1. Greedy Best First Search
  2. A* Search

to solve the Red Green Blank (RGB) Problem which is similar (not the same) to 15 Puzzle. (https://en.wikipedia.org/wiki/15_puzzle) 

Input for this problem is a 5x5 grid where each cell is a character and the there can only be following characters:

  - R: Red
  - G: Green
  - B: Blank
  
And the goal state/grid to achieve is:

  R R R R R
  
  R G G G R
  
  R G B G R
  
  R G G G R
  
  R R R R R

Main thing to note is that only 'B' can be moved/swapped with any of the four (max) neighbors around 'B' as the allowed operators are:

- Up
- Down
- Left
- Right

Each search is regarded as a problem in the code and thus, Problem 1 corresponds to BFS similarly Problem 5 corresponds to A* respectively.
The code writes the operators, required in succession, in a file which are needed to solve the given input state and outputs the nodes
generated and explored, while searching, on the console. 

The input file format is 'PX-in.txt' where X can be 1-5 and the output file format
is 'PX-out.txt' where X can be 1-5 again.
