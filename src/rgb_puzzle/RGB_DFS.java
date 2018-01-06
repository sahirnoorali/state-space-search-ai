/*
 * #Author: Sahir Noor Ali 
 * #Code: Applying DFS to solve RGB Puzzle
 */

package rgb_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
//*************************************************************************************************
public class RGB_DFS {
        
    private Stack<Node> DFS_Stack;
    private String goalBoard;
    private String operator[];
    private int nodesGenerated;
    private int nodesExplored;    
//*************************************************************************************************    
    public RGB_DFS(String filepath) throws FileNotFoundException{
    
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
		
        //Initializing Stack with the board:
        DFS_Stack = new Stack<Node>();
        DFS_Stack.push(new Node(board));
    
    }//close ctor
//*************************************************************************************************
    public void setGoalBoard(String board){
        goalBoard = board;
    }//close setGoalBoard
//*************************************************************************************************
    public boolean DFS(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
        
	//Peak the top node
        Node current = DFS_Stack.peek();
	//Goal Test
        if(Util.goalTest(current.getBoard(),goalBoard) == true){
            Util.solutionPath(current,filepath);
            return true;
        }//close if
        else{
        
            //Init Hashmap to store unique entries
            HashMap explored = new HashMap();    
            
            //DFS Loop
            while(true){
			
                if(DFS_Stack.isEmpty() ==  true)
                    return false;
                
		//Remove the node
                current = DFS_Stack.pop();
                //Mark it explored
                explored.put(current.geID(), current.getBoard());

		//Increment the explored counter
                nodesExplored++;
                
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
                        //And the operator
                        child.setOperator(operator[i]);
                        //Increment the nodes generated counter
                        nodesGenerated++;

                        //If not explored
                        if(explored.containsValue(child.getBoard()) != true){
                            //If goal state then print solution
                            if(Util.goalTest(child.getBoard(),goalBoard) == true){
                                System.out.println("Nodes Generated: "+nodesGenerated);
                                System.out.println("Nodes Explored: "+ nodesExplored);
                                Util.solutionPath(child,filepath);
                                Util.printBoard(child.getBoard());
                                return true;
                            }//close if
                            
                            //Otherwise push the node in the stack
                            DFS_Stack.push(child);
                        }//close if
                    }//close if
                }//close for
            }//close while
        }//close else
    }//close runBFS
//*************************************************************************************************    
    
}//close class
