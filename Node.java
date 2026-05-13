import java.util.ArrayList;


/**
* Clase Node
* Models a node in an 8 way linked list
* @author Cecilia M. Curlango Rosas
* @version 01 2026
*/
public class Node implements Comparable<Node> {
   private int number; // INFO part
   private Node up, down,
           left, right,
           downLeft, downRight,
           upLeft, upRight;


   public Node(int number) {
       this.number = number;
       up = null;
       down = null;
       left = null;
       right = null;
       downLeft = null;
       downRight = null;
       upLeft = null;
       upRight = null;
   }

    @Override
    public int compareTo(Node otro) {
        if (otro == null || this.esVacio || otro.esVacio) return -1;

        if (this.number == otro.number || (this.number + otro.number == 10)) {
            return 0; 
        }
        return -1;
    }


   /**
    * Updates all links
    * to neighboring nodes so that
    * nothing points to it anymore.
    */
   public void delete() {
     
   }
   /**
    * Returns whether input node is next to
    * node.
    * @return true if nodes are next to each other
    */
    public boolean isNeighbor(Node input) {
        if (input == null) return false;

        return (input == up || input == down ||
                input == left || input == right ||
                input == upLeft || input == upRight ||
                input == downLeft || input == downRight);
    }
   /**
    * Returns whether input node contains same value or
    * adds up to 10.
    * @return true if nodes contain the same value or add up to 10
    */
    public boolean isMatchValue(Node input) {
        return this.compareTo(input) == 0;
    }


   public Node getDown() {
       return down;
   }


   public void setDown(Node down) {
       this.down = down;
   }


   public Node getLeft() {
       return left;
   }


   public void setLeft(Node left) {
       this.left = left;
   }


   public Node getRight() {
       return right;
   }


   public void setRight(Node right) {
       this.right = right;
   }


   public Node getDownLeft() {
       return downLeft;
   }


   public void setDownLeft(Node downLeft) {
       this.downLeft = downLeft;
   }


   public Node getDownRight() {
       return downRight;
   }


   public void setDownRight(Node downRight) {
       this.downRight = downRight;
   }


   public Node getUpLeft() {
       return upLeft;
   }


   public void setUpLeft(Node upLeft) {
       this.upLeft = upLeft;
   }


   public Node getUpRight() {
       return upRight;
   }


   public void setUpRight(Node upRight) {
       this.upRight = upRight;
   }


   public Node getUp() {
       return up;
   }


   public void setUp(Node up) {
       this.up = up;
   }


   public int getNumber() {
       return number;
   }
   public void setNumber(int number) {
       this.number = number;
   }


   @Override
   public String toString() {
       return number + "";
   }


  /**
    * Returns an ArrayList containing all non-empty neighboring nodes
    * @return ArrayList<Node> with neighboring non-empty nodes
    */


   public ArrayList<Node> getNeighbors() {
       
   }
}
