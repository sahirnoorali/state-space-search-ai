/*
 * #Author: Sahir Noor Ali
 * #ID: K132047 
 * #Code: AI Assignment Problem 4 (RGB Puzzle: Apply Iterative Deepening Search to solve RGB)
 */

package rgb_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;
//*************************************************************************************************
public class RGB_IDS {
    
    private String goalBoard;
    private String initBoard;
    private int nodesExplored;
    private int nodesGenerated;
    private String operator[];
//*************************************************************************************************    
    public RGB_IDS(String filepath) throws FileNotFoundException{
    
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
        //Set the init board
        initBoard = board;
        //Close the file
        sc.close();
    }//close ctor
//*************************************************************************************************
    public void setGoalBoard(String board){
        goalBoard = board;
    }//close setGoalBoard
//*************************************************************************************************
    public Node DLS(int depthLimit){
        return Recursive_DLS(new Node(initBoard), depthLimit);
    }//close DLS
//*************************************************************************************************
    public Node Recursive_DLS(Node node, int depthLimit){
        
       if(depthLimit == 0)
            return node;
       
       //Node will now get explored 
       nodesExplored++;
       
       //Goal test
       if(Util.goalTest(node.getBoard(),goalBoard))
            return node;
 
       else{
            
            //Operator Times
            for(int i=0;i<4;i++){
                  
                if(Util.validate(node.getBoard(),operator[i]) == true){

                    String board;
                    //Apply validated operator
                    board = Util.updateBoard(node.getBoard(),operator[i]);
					
                    //Create a new node
                    Node child = new Node(board);
                    //Set its parent
                    child.setParent(node);
                    //And the operator
                    child.setOperator(operator[i]);
                    //Increment the nodes generated counter
                    nodesGenerated++;
                    
                    //Go a level deeper with this new node
                    Node result = Recursive_DLS(child, depthLimit-1);
					
                    //Goal Test
                    if(Util.goalTest(result.getBoard(), goalBoard))
                        return result;
                }//close if
            }//close for
          return node;
        }//close else
    }//close Recursive_DLS
//*************************************************************************************************     
    public boolean IDS(String filepath) throws FileNotFoundException, UnsupportedEncodingException{
    
       //100 As an example for infinity 
       //(according to the scope of this problem)
       for(int i=1;i<=100;i++){

          Node result = new Node(DLS(i));   
          
          System.out.println("Pass No: "+i);
          Util.printBoard(result.getBoard());
          
          if(Util.goalTest(result.getBoard(), goalBoard)){ 
              Util.solutionPath(result, filepath);
              Util.printBoard(result.getBoard());
              System.out.println("Nodes Generated: "+nodesGenerated);
              System.out.println("Nodes Explored: "+nodesExplored);  
              return true;
          }//close if
       }//close for
     return false;  
    }//close IDS
//*************************************************************************************************     
}//close class
