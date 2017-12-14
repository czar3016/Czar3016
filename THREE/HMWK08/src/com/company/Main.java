/*Zachary Czarnecki - HMWK08 Binary Trees
* Create 3 binary trees using a generic function: one Integer, one Double, and one Characters.
* Read out the number of inserted variables to make sure everything works. Use PrintWriter to display the Trees ordered
* and unordered two ways: pre - root,left branches,right branches and post - left branches, right branches, and ending
* with the root. These ordering functions work with recursive calling to helper functions; inorder, preorder, and
* postorder, respectivly.*/
package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
	    PrintWriter outpt;
        outpt=new PrintWriter(new File("HMWK08.txt"));
        //Integer Tree
        BinaryTreeMgr<Integer>TreeInt=new BinaryTreeMgr<Integer>();
        TreeInt.insertnode(78);//Root
        TreeInt.insertnode(39);
        TreeInt.insertnode(33);
        TreeInt.insertnode(93);
        TreeInt.insertnode(28);
        TreeInt.insertnode(-23);
        TreeInt.insertnode(40);
        TreeInt.insertnode(5);
        TreeInt.insertnode(33);
        TreeInt.insertnode(14);
        TreeInt.insertnode(19);
        System.out.println("This is the Binary Tree for Integers.\n There are "+TreeInt.getnumber());
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeInt.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeInt.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeInt.postorder(outpt);

        //Doubles
        BinaryTreeMgr<Double>TreeDoub=new BinaryTreeMgr<Double>();
        TreeDoub.insertnode(78.3); //Root
        TreeDoub.insertnode(15.7);
        TreeDoub.insertnode(105.2);
        TreeDoub.insertnode(55.8);
        TreeDoub.insertnode(18.7);
        TreeDoub.insertnode(42.7);
        TreeDoub.insertnode(16.9);
        TreeDoub.insertnode(26.7);
        TreeDoub.insertnode(42.7);
        TreeDoub.insertnode(88.4);
        TreeDoub.insertnode(95.8);
        System.out.println("\nThis is the Binary Tree for Doubles.\n There are "+TreeDoub.getnumber());
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeDoub.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeDoub.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeDoub.postorder(outpt);

        //Char
        BinaryTreeMgr<Character>TreeChar=new BinaryTreeMgr<Character>();
        TreeChar.insertnode('D'); //Root
        TreeChar.insertnode('Z');
        TreeChar.insertnode('A');
        TreeChar.insertnode('Q');
        TreeChar.insertnode('M');
        TreeChar.insertnode('$');
        TreeChar.insertnode('3');
        TreeChar.insertnode('U');
        TreeChar.insertnode('A');
        TreeChar.insertnode('&');
        TreeChar.insertnode('(');
        System.out.println("\nThis is the Binary Tree for Chars.\n There are "+TreeChar.getnumber());
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeChar.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeChar.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeChar.postorder(outpt);
        outpt.close();
    }
}
class BinaryTreeMgr<T extends Comparable>{
    protected TreeNode<T> root;//this is root tree
    protected int number;// number of nodes in the tree
    public BinaryTreeMgr(){
        root=null;
        int number=0;
    }//end constructor
    public int getnumber(){return number;}
    private static class TreeNode<T extends Comparable>{
        //class constructing the nodes
        protected T nodevalue;
        protected TreeNode<T> left;//left branch
        protected TreeNode<T> right;//right branch
        public TreeNode(T x){
            nodevalue=x;
            left=null;//pointer for the next left node
            right=null;//right pointer
        }//end of Node constructor
    }//end class TreeNode
    protected TreeNode<T> CreateNode(T x){
        //creates new node
        return new TreeNode<>(x);
    }//end CreateNode
    public int insertnode(T x){
        if(root==null){
            //set the root at start
            root=CreateNode(x);
            number=1;
            return number;
        }else{
            TreeNode<T> parent=null;
            //set root for ordering purposes
            TreeNode<T> current=root;
            while (current!=null)//find last leaf
            if(x.compareTo(current.nodevalue)<0){
                parent=current;
                current=current.left; //move left
            }else if (x.compareTo(current.nodevalue)>0){
                parent=current;
                current=current.right; //move right
            }
            else return -99; //don't return duplicates
            if (x.compareTo(parent.nodevalue)<0)
                parent.left=CreateNode(x);
            else parent.right=CreateNode(x);
        }//node inserted
        number++; //NEXT
        return number;
    }//end of insertnode
    public void preorder(PrintWriter outpt){
        preorder(root, outpt);
    }//end helper
    public void inorder(PrintWriter outpt){
        //traverses the tree in order and prints the nodes
        inorder(root, outpt);
    }//end helper
    public void postorder(PrintWriter outpt){
        postorder(root, outpt);
    }//end helpers

    protected void preorder(TreeNode<T> root, PrintWriter outpt){
        //recursive function to print the nodes inorder(left-root-right
        if (root==null) return;
        System.out.print(root.nodevalue+" ");
        outpt.print(root.nodevalue+" ");
        preorder(root.left, outpt);
        preorder(root.right, outpt); //as far left as we can print
    }//end preorder
    protected void inorder(TreeNode<T> root, PrintWriter outpt){
        //recursive function to print the nodes inorder(left-root-right
        if (root==null) return;
        inorder(root.left, outpt); //as far left as we can print
        System.out.print(root.nodevalue+" ");
        outpt.print(root.nodevalue+" ");
        inorder(root.right, outpt);
    }//end inorder
    protected void postorder(TreeNode<T> root, PrintWriter outpt){
        //recursive function to print the nodes inorder(left-root-right
        if (root==null) return;
        postorder(root.left, outpt); //as far left as we can print
        postorder(root.right, outpt);
        System.out.print(root.nodevalue+" ");
        outpt.print(root.nodevalue+" ");
    }//end postorder
}
