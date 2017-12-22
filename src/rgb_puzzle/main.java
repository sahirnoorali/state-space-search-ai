/*
 * #Author: Sahir Noor Ali
 * #Code: Solving RGB Puzzle
 */

package rgb_puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
//********************************************************************************************************
public class main {

    public static String initGoalBoard(){
       String goalboard = "RRRRR"
                        + "RGGGR"
                        + "RGBGR"
                        + "RGGGR"
                        + "RRRRR";
       return goalboard;
    }//close initGoalBoard              
//********************************************************************************************************
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
   
        int probNo = 1;
        
        while(probNo != 0){
        
          System.out.println("Select the Problem Number to Solve (1-5): ");
          System.out.println("Press 0 to Exit.");
          Scanner sc = new Scanner(System.in);
          probNo = sc.nextInt();
                    
            
            if(probNo == 1){

                RGB_BFS Problem1 = new RGB_BFS("P1-in.txt");
                Problem1.setGoalBoard(initGoalBoard());
                Problem1.BFS("P1-out.txt");
            }//close Problem 1
            
            if(probNo == 2){
            
                RGB_DFS Problem2 = new RGB_DFS("P2-in.txt");
                Problem2.setGoalBoard(initGoalBoard());
                Problem2.DFS("P2-out.txt");
            }//close Problem 2
            
            if(probNo == 3){
            
                RGB_IDS Problem3 = new RGB_IDS("P3-in.txt");
                Problem3.setGoalBoard(initGoalBoard());
                Problem3.IDS("P3-out.txt");  
            }//close Problem 3
            
            if(probNo == 4){
            
                RGB_GBFS Problem4 = new RGB_GBFS("P4-in.txt",initGoalBoard());
                Problem4.GBFS("P4-out.txt");
            }//close Problem 4
            
            if(probNo == 5){
            
                RGB_AStar Problem5 = new RGB_AStar("P5-in.txt",initGoalBoard());
                Problem5.AStar("P5-out.txt");
            }//close Problem 5
        
            if(probNo != 0)
                System.out.println("Problem "+probNo+" Solved\n");
        }//close while
    }//close main
}//close class
