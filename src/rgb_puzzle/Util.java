/*
 * #Author: Sahir Noor Ali
 * #Code: Utility or Helper Class (Re-Usable Code)
 */

package rgb_puzzle;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//*************************************************************************************************
public class Util {
        
//*************************************************************************************************    
   public static void printBoard(String board){
       for(int i=0;i<25;i++){
           System.out.print(board.charAt(i)+" ");
           if((i+1)%5 == 0)
              System.out.println("");   
        }//close for
       System.out.println("");
   }//close printBoard
//*************************************************************************************************
   public static String[] setOperator(){
   
       String optr[] = new String[4];
       
        optr[0] = "UP";     optr[1] = "DOWN";
        optr[2] = "LEFT";   optr[3] = "RIGHT";

       return optr;
   }//close setOperator   
//*************************************************************************************************    
   public static boolean goalTest(String board, String goalBoard){
        return (board.equals(goalBoard));
   }//close goalTest
 //*************************************************************************************************    
    public static void solutionPath(Node temp,String filepath) throws FileNotFoundException, UnsupportedEncodingException{
        
        if(temp.getParent() == null || temp.geID() == 1)
            System.out.println("Initial = Goal");
        
        else{
            
            String optr="";
            int index = 0;
            Node current  = temp;
           
            while(current.getParent() != null){
                optr = optr+current.getOperator().charAt(0);
                current = current.getParent();
            }//close while
        
            String output = new StringBuilder(optr).reverse().toString();

            //Writing Solution Path to the File:
            PrintWriter writer = new PrintWriter(filepath, "UTF-8");
            int n = output.length();
            
            for(int i=0;i<n;i++){
                writer.println(output.charAt(i));
            }//close for
            
            writer.close();
        }//close else    
    }//close solutionPath
//*************************************************************************************************    
    public static String updateBoard(String board,String optr){
    
        StringBuilder childBoard = new StringBuilder(board);
        
        int xy[] = searchBlank(board);    
        
        if(optr.equals("UP")){
            //Swap:
            char temp = childBoard.charAt((xy[0]*5)+xy[1]);
            childBoard.setCharAt((xy[0]*5)+xy[1], board.charAt((xy[0]*5)+xy[1]-5));
            childBoard.setCharAt((xy[0]*5)+xy[1]-5,temp);
        }//close UP
        
        else if(optr.equals("DOWN")){
            //Swap:
            char temp = childBoard.charAt((xy[0]*5)+xy[1]);
            childBoard.setCharAt((xy[0]*5)+xy[1], board.charAt((xy[0]*5)+xy[1]+5));
            childBoard.setCharAt((xy[0]*5)+xy[1]+5,temp);
        }//close DOWN
        
        else if(optr.equals("LEFT")){
            //Swap:
            char temp = childBoard.charAt((xy[0]*5)+xy[1]);
            childBoard.setCharAt((xy[0]*5)+xy[1], board.charAt((xy[0]*5)+xy[1]-1));
            childBoard.setCharAt((xy[0]*5)+xy[1]-1,temp);
        }//close LEFT
        
        else{
            //Swap:
            char temp = childBoard.charAt((xy[0]*5)+xy[1]);
            childBoard.setCharAt((xy[0]*5)+xy[1], board.charAt((xy[0]*5)+xy[1]+1));
            childBoard.setCharAt((xy[0]*5)+xy[1]+1,temp);
        }//close RIGHT
        
        
      return childBoard.toString();
    }//close updateBoard
//*************************************************************************************************    
    public static int[] searchBlank(String board){
    
        int [] xy = new int[2];
        
        int row = 0; int col = 0;
        for(int i=0;i<25;i++){
             
            col = i%5;
            if(board.charAt(i) == 'B'){
                    xy[0] = row;
                    xy[1] = col;   
                    break;
             }//close if                    
        
            if((i+1)%5 == 0)
                row++;
           
        }//close for
               
      return xy; 
    }//close searchBlank
//*************************************************************************************************   
    public static boolean validate(String board, String operator){
    
         int xy[] = new int[2];
         xy = searchBlank(board);
         
         if(operator.equals("UP")){
            if(xy[0] == 0)
                return false;
            else 
                return true;
         }//close UP
         
         else if(operator.equals("DOWN")){
            if(xy[0] == 4)
                return false;
            else 
                return true;         
         }//close DOWN
         
         else if(operator.equals("LEFT")){
            if(xy[1] == 0)
               return false;
            else 
               return true;
         }//close LEFT
         
         else{
            if(xy[1] == 4)
                return false;
            else 
                return true;
         }//close RIGHT
    }//close validate 
//*************************************************************************************************
}//close Util
