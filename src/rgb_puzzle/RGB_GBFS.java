/*
 * #Author: Sahir Noor Ali
 * #Code: Applying Greedy Best First Search to solve RGB Puzzle
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
public class RGB_GBFS {
    
    private PriorityQueue<Node> GBFS_Queue;
    private String goalBoard;
    private int nodesExplored;
    private int nodesGenerated;
    private String[] operator; 
 //*************************************************************************************************   
    public RGB_GBFS(String filepath, String gBrd) throws FileNotFoundException{
    
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
        GBFS_Queue = new PriorityQueue<Node>(new Comparator<Node>(){
        
            @Override
            public int compare(Node a, Node b){
                if(a.getHeuristicValue() > b.getHeuristicValue())
                    return 1;
                else if(a.getHeuristicValue() < b.getHeuristicValue())
                    return -1;
                else 
                    return 0;
           
            }//close compare
        });
        
        //Set the goal board
        goalBoard = gBrd;
      
        //Create a new node with board
        Node temp = new Node(board);
        //Set its heuristic value
        temp.setHeuristicValue(greedyHeuristic(board));
        //Initializing Queue with the node:
        GBFS_Queue.add(temp);
    
    }//close ctor
 /************************************************************************************************* 
 /* Calculate heuristic value based on whether they are at the same position as in the goal state
 /************************************************************************************************/ 
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
    public boolean GBFS(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
    
        //Peak the top node
        Node current = GBFS_Queue.peek();
        
        //Goal Test
        if(Util.goalTest(current.getBoard(),goalBoard) == true){
            Util.solutionPath(current,filepath);
            return true;
        }//close if
        
        else{
        
            //Init Hashmap to store unique entries
            HashMap explored = new HashMap();    
            
            //GBFS Loop
            while(true){
                
                if(GBFS_Queue.isEmpty() ==  true)
                    return false;
                
                //Remove the node
                current = GBFS_Queue.poll();
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
		
                        //Create a new node with applied operator board
                        Node child = new Node(board);
                        //Set its parent
                        child.setParent(current);
                        //And the operator
                        child.setOperator(operator[i]);
                        //As well as the heuristic value
                        child.setHeuristicValue(greedyHeuristic(board));
                        //Increment the generated counter
                        nodesGenerated++;
						
                        //If not explored, add in the Priority Queue
                        if(explored.containsValue(child.getBoard()) != true){
                            GBFS_Queue.add(child);
                        }//close if
                    }//close if
                }//close for
            }//close while
        }//close else
    }//close GBFS
//*************************************************************************************************   
}//close class
