/*Zachary Czarnecki - HMWK09 Binary Tree Deletion
* Use HMWK08 to create a binary tree, and print it out with recursive calling to helper functions; inorder, preorder,
* and postorder, respectivly. Once that works, delete three nodes; 28, 105, and 110 using a deleteNode and helper
* function. Re-print the list in each order after each deletion.
* Deletion works via boolean call; checking math and moving the structure based on "visual" information, rather than
* mathmatically. Basically, depending on where the node for deletion is, the needed replacement will always be the same,
* relativly speaking, depending on how many children a node has and where they are. */
package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        PrintWriter outpt;
        outpt = new PrintWriter(new File("HMWK09.txt"));
        //Integer Tree
        BinaryTreeMgr<Integer> TreeInt = new BinaryTreeMgr<Integer>();
        TreeInt.insertnode(78);//Root
        TreeInt.insertnode(39);
        TreeInt.insertnode(52);
        TreeInt.insertnode(28);
        TreeInt.insertnode(33);
        TreeInt.insertnode(14);
        TreeInt.insertnode(16);
        TreeInt.insertnode(4);
        TreeInt.insertnode(35);
        TreeInt.insertnode(105);
        TreeInt.insertnode(85);
        TreeInt.insertnode(92);
        TreeInt.insertnode(80);
        TreeInt.insertnode(97);
        TreeInt.insertnode(110);
        TreeInt.insertnode(99);
        System.out.println("This is the complete Binary Tree.\n There are " + TreeInt.getnumber());
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeInt.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeInt.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeInt.postorder(outpt);
        //now to start deleteing
        TreeInt.deleteNode(28);
        System.out.println("\n\nDeleting node 28.");
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeInt.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeInt.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeInt.postorder(outpt);

        TreeInt.deleteNode(105);
        System.out.println("\n\nDeleting node 105.");
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeInt.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeInt.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeInt.postorder(outpt);

        TreeInt.deleteNode(110);
        System.out.println("\n\nDeleting node 110.");
        System.out.println("Printing Pre Order(Root-Left-Right)");
        TreeInt.preorder(outpt);
        System.out.println("\nPrinting In Order(Left-Root-Right)");
        TreeInt.inorder(outpt);
        System.out.println("\nPrinting Post Order(Left-Right-Root)");
        TreeInt.postorder(outpt);
    }
}

class BinaryTreeMgr<T extends Comparable> {
    protected TreeNode<T> root;//this is root tree
    protected int number;// number of nodes in the tree

    public BinaryTreeMgr() {
        root = null;
        int number = 0;
    }//end constructor

    public int getnumber() {
        return number;
    }

    private static class TreeNode<T extends Comparable> {
        //class constructing the nodes
        protected T nodevalue;
        protected TreeNode<T> left;//left branch
        protected TreeNode<T> right;//right branch

        public TreeNode(T x) {
            nodevalue = x;
            left = null;//pointer for the next left node
            right = null;//right pointer
        }//end of Node constructor
    }//end class TreeNode

    protected TreeNode<T> CreateNode(T x) {
        //creates new node
        return new TreeNode<>(x);
    }//end CreateNode

    public int insertnode(T x) {
        if (root == null) {
            //set the root at start
            root = CreateNode(x);
            number = 1;
            return number;
        } else {
            TreeNode<T> parent = null;
            //set root for ordering purposes
            TreeNode<T> current = root;
            while (current != null)//find last leaf
                if (x.compareTo(current.nodevalue) < 0) {
                    parent = current;
                    current = current.left; //move left
                } else if (x.compareTo(current.nodevalue) > 0) {
                    parent = current;
                    current = current.right; //move right
                } else return -99; //don't return duplicates
            if (x.compareTo(parent.nodevalue) < 0)
                parent.left = CreateNode(x);
            else parent.right = CreateNode(x);
        }//node inserted
        number++; //NEXT
        return number;
    }//end of insertnode

    public void preorder(PrintWriter outpt) {
        preorder(root, outpt);
    }//end helper

    public void inorder(PrintWriter outpt) {
        //traverses the tree in order and prints the nodes
        inorder(root, outpt);
    }//end helper

    public void postorder(PrintWriter outpt) {
        postorder(root, outpt);
    }//end helpers

    protected void preorder(TreeNode<T> root, PrintWriter outpt) {
        //recursive function to print the nodes inorder(left-root-right
        if (root == null) return;
        System.out.print(root.nodevalue + " ");
        outpt.print(root.nodevalue + " ");
        preorder(root.left, outpt);
        preorder(root.right, outpt); //as far left as we can print
    }//end preorder

    protected void inorder(TreeNode<T> root, PrintWriter outpt) {
        //recursive function to print the nodes inorder(left-root-right
        if (root == null) return;
        inorder(root.left, outpt); //as far left as we can print
        System.out.print(root.nodevalue + " ");
        outpt.print(root.nodevalue + " ");
        inorder(root.right, outpt);
    }//end inorder

    protected void postorder(TreeNode<T> root, PrintWriter outpt) {
        //recursive function to print the nodes inorder(left-root-right
        if (root == null) return;
        postorder(root.left, outpt); //as far left as we can print
        postorder(root.right, outpt);
        System.out.print(root.nodevalue + " ");
        outpt.print(root.nodevalue + " ");
    }//end postorder

    protected boolean deleteNode(T x) {
        TreeNode<T> rootptr = root;
        TreeNode<T> value = root;
        boolean isItLeftChild = true;
        //node not found
        while (rootptr.nodevalue!=x){
            value=rootptr;
            //loo left
            if (rootptr.nodevalue.compareTo(x)>0){// <
                isItLeftChild=true;
                //shift focus to left child node
                rootptr=rootptr.left;
            }else{
                isItLeftChild=false;
                rootptr=rootptr.right;
            }
            if (rootptr==null){
                System.out.println("Node not found.");
                return false;
            }
        }//end while
        //if no children
        if(rootptr.left==null && rootptr.right==null){
            if(rootptr==root) root=null;
            //delete parent relation
            else if (isItLeftChild) value.left=null;
            else value.right=null;
        }
        //no right children
        else if (rootptr.right==null){
            if(rootptr==root) root=rootptr.left;
                //delete parent relation
            else if (isItLeftChild) value.left=rootptr.left;
            else value.right=rootptr.left;
        }
        //no left children
        else if (rootptr.left==null){
            if(rootptr==root) root=rootptr.right;
                //delete parent relation
            else if (isItLeftChild) value.left=rootptr.right;
            else value.right=rootptr.right;
        }else {//two children
            TreeNode<T> replacement = getReplacementNode(rootptr);
            //if ptr is root replace root
            if(rootptr==root) root=replacement;
            //if deleted node had a child, that child is the replacement
            else if(isItLeftChild) value.left=replacement;
            else value.right=replacement;
            replacement.left=rootptr.left;
        }//end else
        return true;
    }//end deleteNode

    public TreeNode<T> getReplacementNode(TreeNode<T> replaceNode){
        TreeNode<T> replacementParent=replaceNode;
        TreeNode<T> replacement=replaceNode;
        TreeNode<T> focusNode=replaceNode.right;
        while (focusNode != null){
            //no children on left
            replacementParent=replacement;
            replacement=focusNode;
            focusNode=focusNode.left;
        }
        //if the replacement isn't the right child move the replacement into the parent's left slot and move the
        //replaced nodes right child into the replacement's right
        if(replacement != replaceNode.right){
            replacementParent.left=replacement.right;
            replacement.right=replaceNode.right;
        }
        return replacement;
    }
}
