# Solving RGB Puzzle via State Space Search Algortihms

## Introduction
State Space Search is a type of search, in Artificial Intelligence, where we apply available set of actions to reach from initial state to the goal state.

There are two main types of State Space Search:

- **Uninformed Search**
- **Informed Search**

## Uninformed Search
In uninformed search, there is no information regarding the location of goal state. 
This project applies the following uninformed search algorithms:

  - **Breadth First Search**
  - **Depth First Search**
  - **Iterative Deepening Search**
 
## Informed Search
Informed search, on the other hand, consider some information about the goal state via heuristic function.
This project applies the following informed search algorithms:

  - **Greedy Best First Search**
  - **A\* Search**

## RGB Puzzle - Problem
Red Green Blank (RGB) Problem is similar (not the same) to 15 Puzzle. (https://en.wikipedia.org/wiki/15_puzzle) 

### Input
Input for this problem is a 5x5 grid where each cell is a character and the there can only be following characters:

  - **R:** Red
  - **G:** Green
  - **B:** Blank
  
An example input configuration is given below:

```
R R R R R
R G G G R
R G G R R
R G G R G
R R R B R
```
### Goal State
The goal state/grid to achieve is:
```
R R R R R  
R G G G R
R G B G R
R G G G R
R R R R R
```
### Allowed Actions/Operators 
One important thing to know is that 'B' can only be moved/swapped with any of the four (max) neighbors around 'B' as the allowed operators are:

- **Up**
- **Down**
- **Left**
- **Right**

### Output
The code writes the operators, required in succession, in a file which are needed to solve the given input state and outputs the nodes
generated and explored on the console. The output file for the above input will look like:
```
U
R
U
L
L
```
### Note
Each search is regarded as a problem in the code and thus, Problem 1 corresponds to BFS similarly Problem 5 corresponds to A* respectively. The input file format is 'PX-in.txt' where X can be 1-5 and the output file format
is 'PX-out.txt' where X can be 1-5 again.
