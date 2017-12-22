/*
 * #Author: Sahir Noor Ali
 * #Code: A* Search to solve RGB Puzzle
 */

package rgb_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
//*************************************************************************************************
public class RGB_AStar {
    
    private PriorityQueue<Node> A_Queue;
    private String goalBoard;
    private int nodesExplored;
    private int nodesGenerated;
    private String[] operator; 
    
//*************************************************************************************************	
    public RGB_AStar(String filepath, String gBrd) throws FileNotFoundException{
    	
        //Open the file and set up the Scanner obj
        File file = new File(filepath);
        Scanner sc = new Scanner(file);
        
        String board = new String();
        operator = Util.setOperator();
        
        String gBoard="";
        //Getting Goal State:
        for(int i=0;i<5;i++){
            String line = sc.nextLine();
            String arr[] = line.split(" ");
            for(int j=0;j<5;j++){
                gBoard = arr[j].charAt(0) + gBoard;
            }//close inner for
        }//close outer for    
        
        //Set up the board correctly
        board =  new StringBuilder(gBoard).reverse().toString();
        
        //Close the file
        sc.close();
		
        //Initializing Priority Queue:
        A_Queue = new PriorityQueue<Node>(new Comparator<Node>(){
        
            //Defining comparision on the basis of heursitic value
            @Override
            public int compare(Node a, Node b){
                if((a.getHeuristicValue()+ a.getCost()) > 
                   (b.getHeuristicValue()+ b.getCost()))
                    return 1;
                else if((a.getHeuristicValue() + a.getCost()) < 
                        (b.getHeuristicValue() + b.getCost()))
                    return -1;
                else 
                    return 0;
           
            }//close compare
        });
        
        //Set the goal board
        goalBoard = gBrd;
        
        //Create  a new node with input board
        Node temp = new Node(board);
        //Set the Heuristic value
        temp.setHeuristicValue(greedyHeuristic(board));
        //Add it into the Priority Queue
        A_Queue.add(temp);
    
    }//close ctor
//*************************************************************************************************   
      public String toString(char board[][]){
        
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                builder.append(board[i][j]);
            }
        }//close for    
      return builder.toString();
    }//close toString
//*************************************************************************************************
    public int greedyHeuristic(String board){
    
        int hValue = 0;
        for(int i=0;i<25;i++){
            if(board.charAt(i) != 'B')
                if(board.charAt(i) != goalBoard.charAt(i))
                    hValue++;
        }//close if
        
      return hValue;
    }//close greedyHeuristic     
//*************************************************************************************************   
   public Node extractNode(Node temp){
  
       Object array[] = A_Queue.toArray();
       for(int i=0;i<array.length;i++){
      
           if(((Node)array[i]).equals(temp))
               return (Node)array[i];
       }//close for
	   
       return null;
   }//close extractNode   
//*************************************************************************************************
    public boolean AStar(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
    
        //Peek the top node
        Node current = A_Queue.peek();
        
        //Goal test
        if(Util.goalTest(current.getBoard(),goalBoard) == true){
            Util.solutionPath(current,filepath);
            return true;
        }//close if
        
        else{
        
            //Init Hashmap to store unique entries
            HashMap explored = new HashMap();    
            int count = 0;
            
            //A* loop
            while(true){
                
                if(A_Queue.isEmpty() ==  true)
                    return false;
                
                //Remove the top node
                current = A_Queue.poll();
                //Mark it explored
                explored.put(current.geID(), current.getBoard());
                //Increment the explored counter
                nodesExplored++;
                
                //Check if Goal:
                if(Util.goalTest(current.getBoard(),goalBoard) == true){
                    Util.solutionPath(current,filepath);
                    Util.printBoard(current.getBoard());
                    System.out.println("Nodes Generated: "+nodesGenerated);
                    System.out.println("Nodes Explored: "+nodesExplored);
                    return true;
                }//close if

                //Operator Times:
                for(int i=0;i<4;i++){
                   
                    if(Util.validate(current.getBoard(),operator[i]) == true){

                        String board;
                        //Apply operator
                        board = Util.updateBoard(current.getBoard(),operator[i]);
			
                        //Create a new node with applied operator
                        Node child = new Node(board);
                        //Set its parent
                        child.setParent(current);
                        //And operator
                        child.setOperator(operator[i]);
                        
                        //Get the heuristic value
                        int h = greedyHeuristic(board);
                        //Set the h
                        child.setHeuristicValue(h);
                        
                        //Get the cost
                        int cost = current.getCost()+1;
                        //Set the node's cost
                        child.setCost(cost);
                        
                        //Increment the generated counter
                        nodesGenerated++;

                        //If not explored
                        if(explored.containsValue(child.getBoard()) != true){
                           
                            //& if not added in the PQ then add it
                            if(!A_Queue.contains(child))
                                A_Queue.add(child);  
                            
                           else{
                               
                              //Get the existing node from the frontier  
                              Node temp = new Node(extractNode(child));
                              
                              //If its cost is greater then apply the following:      
                              if(cost < temp.getCost()){
                                  temp.setCost(cost);
                                  temp.setParent(current);
                                  temp.setHeuristicValue(h);
                                  temp.setOperator(operator[i]);
                                  
                                   A_Queue.remove(child);
                                   A_Queue.add(temp);
                                  
                                  System.out.println(count);
                                  count++;
                              }//close if
                           }//close else
                        }//close if
                    }//close if
                }//close for
            }//close while
        }//close else
    }//close AStar
//*************************************************************************************************   
}//close class
