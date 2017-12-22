/*
 * #Author: Sahir Noor Ali
 * #Code: Applying BFS to solve RGB Puzzle
 */

package rgb_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//*************************************************************************************************  
public class RGB_BFS {
    
    private Queue<Node> BFS_Queue;
    private String goalBoard;
    private String operator[];
    private int nodesGenerated;
    private int nodesExplored;
    
//*************************************************************************************************    
    public RGB_BFS(String filepath) throws FileNotFoundException{
    
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
        
	//Initializing Queue with the board:
        BFS_Queue = new LinkedList<Node>();
        BFS_Queue.add(new Node(board));
        
    }//close ctor
//*************************************************************************************************  
    public void setGoalBoard(String board){
        goalBoard = board;
    }//close setGoalBoard
//*************************************************************************************************  
    public boolean BFS(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
        
	//Peak the top node
        Node current = BFS_Queue.peek();
        
	//Goal Test
        if(Util.goalTest(current.getBoard(),goalBoard) == true){
            Util.solutionPath(current,filepath);
            return true;
        }//close if
        
        else{
        
            //Init Hashmap to store unique entries
            HashMap explored = new HashMap();    
            
    	    //BFS Loop
            while(true){
                
                if(BFS_Queue.isEmpty() ==  true)
                    return false;
                
                //Remove the node
                current = BFS_Queue.remove();
                //Mark it explored
                explored.put(current.geID(),current.getBoard());
                //Increment the explored counter
                nodesExplored++;
                
                //Operator Times:
                for(int i=0;i<4;i++){
                    
                    if(Util.validate(current.getBoard(),operator[i]) == true){
						
                        String board;
                        //Apply Operator
			board = Util.updateBoard(current.getBoard(),operator[i]);

			//Create a new node with applied operator
                        Node child = new Node(board);
			//Set its parent
                        child.setParent(current);
			//And the operator
                        child.setOperator(operator[i]);
			//Increment the nodes generated counter
                        nodesGenerated++;
                        
			//If not explored 
                        if(explored.containsValue(child.getBoard()) != true){
							
                            //If goal state then print solution
                            if(Util.goalTest(child.getBoard(),goalBoard) == true){
                                Util.solutionPath(child,filepath);
                                Util.printBoard(child.getBoard());
                                System.out.println("Nodes Generated: "+nodesGenerated);
                                System.out.println("Nodes Explored: "+ nodesExplored);
                                return true;
                            }//close if
							
                            //otherwise, add to the Queue
                            BFS_Queue.add(child);
                        
    			  }//close if
                    }//close if
                }//close for
            }//close while
        }//close else
    }//close BFS
//*************************************************************************************************  
}//close class
