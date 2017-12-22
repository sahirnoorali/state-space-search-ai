/*
 * #Author: Sahir Noor Ali
 * #Code: Node Class
 */

package rgb_puzzle;
import java.util.Objects;
//*************************************************************************************************
public class Node {
    
    private final String RGB_Board;
    private Node parent;
    private String operator;
    private Node next;
    private static int id;
    private int hValue; //used only for both the informed searches
    private int cost; //used only for A* search 
//*************************************************************************************************
    public Node(String board){
    
        operator = null;
        next = null;
        parent = null;
        id++;
        cost = 0;
        RGB_Board = board;
    }//close Node
//*************************************************************************************************    
    public Node(Node temp){
    
       RGB_Board = temp.getBoard();
       operator = temp.getOperator();
       next = temp.getNext();
       parent = temp.getParent();
       hValue = temp.getHeuristicValue();
       cost = temp.getCost();
       id++;
    }//close copy ctor
//*************************************************************************************************
    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        
        Node temp = (Node)o;
        return(temp.getBoard().equals(RGB_Board));
    }//close equals   
//*************************************************************************************************
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.RGB_Board);
        return hash;
    }//close hashCode
//*************************************************************************************************
    public void setParent(Node n){parent = n;} 
    public void setNext(Node n){next = n;}
    public void setOperator(String optr){operator = optr;}
    public void setHeuristicValue(int value){hValue=value;}
    public void setCost(int cst){cost= cst;}
//*************************************************************************************************    
    public Node getNext(){return next;}
    public Node getParent(){return parent;}
    public String getOperator(){return operator;}
    public String getBoard(){return RGB_Board;}
    public int geID(){return id;}
    public int getHeuristicValue(){return hValue;}
    public int getCost(){return cost;}
//************************************************************************************************* 
}//close Node
