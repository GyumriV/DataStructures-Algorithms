
//Vardan Vardanyan
//CSC 130, Assignment 2
import java.util.Random;
import java.util.Scanner;

//Creating a node structure for our AVL tree
class Node {
	int data, height;
	Node left, right;
	
	Node(int d){
		data = d;
		height = 1;
	}
}

public class Main {
	
	Node root;

	//method to find height of tree
	int height(Node n) {
		if(n==null) {
			return 0;
		}
		return n.height;
	}
	
	//method to find max between two integers
	int max(int a, int b) {
		if(a>b)
			return a;
		else
			return b;
	}
	
	//Right rotation for AVL tree
	Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		
		x.right = y;
		y.left = T2;
		
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;
		
		return x;
	}
	
	//Left rotation for AVL tree
	Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		
		y.left = x;
		x.right = T2;
		
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;
		
		return y;
	}
	
	// Getting balance of the tree
	int getBalance(Node n) {
		if(n == null) {
			return 0;
		}
		return height(n.left) - height(n.right);
	}

	// Inserting node into tree
	Node insert(Node node, int data) {
		if(node == null) {
			return (new Node(data));
		}
		
		if(data < node.data) {
			node.left = insert(node.left, data);
		}else if(data > node.data) {
			node.right = insert(node.right, data);
		}else {
			return node;
		}
		
		node.height = 1 + max(height(node.left), height(node.right));
		int balance = getBalance(node);
		
		if (balance > 1 && data < node.left.data) {
			return rightRotate(node);
		}
		if(balance < -1 && data > node.right.data) {
			return leftRotate(node);
		}
		if(balance > 1 && data > node.left.data) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance < -1 && data < node.right.data) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}


	// Finding min given a node's subtree
	Node min(Node n) {
		Node current = n;
		
		while(current.left != null) {
			current = current.left;
		}
		return current;
	}	
	

	//Removing a node from tree
	Node remove(Node root, int data) {  
      if (root == null) return root;  

      if (data > root.data)  
          root.right = remove(root.right, data);  

      else if (data < root.data)  
          root.left = remove(root.left, data);  

      else {  

          if ((root.right == null) || (root.left == null)){  
              Node n = null;  
              
              if (n == root.left) n = root.right;  
              else n = root.left;  

              if (n == null) {  
                  n = root;  
                  root = null;  
              }  
              else
                  root = n; 
          }  
          else {  
              Node n = min(root.right);  
              root.data = n.data;     
              root.right = remove(root.right, n.data);  
          }  
      }  

      if (root == null) return root;  

      root.height = max(height(root.left), height(root.right)) + 1;  

      int bal = getBalance(root);  
      if (bal > 1 && getBalance(root.left) < 0) {  
          root.left = leftRotate(root.left);  
          return rightRotate(root);  
      }  
      if (bal > 1 && getBalance(root.left) >= 0) return rightRotate(root);  

      if (bal < -1 && getBalance(root.right) > 0) {  
          root.right = rightRotate(root.right);  
          return leftRotate(root);  
      } 
      if (bal < -1 && getBalance(root.right) <= 0) return leftRotate(root);  
      
      return root;  
  } 
	
	
	public static void main(String[] args) {
		//Creating two trees
		Main tree = new Main();
		Main tree2 = new Main();
		String input = "";
		Scanner scan = new Scanner(System.in);
		double time = 0;	//timer for elapsed time
		
		//Creating array of ints with values 0 - 4999
		int arr[] = new int[5000];
		for(int i = 0;i<5000;i++) {
			arr[i] = i;
		}
		//Randomize array
		Random rand = new Random();
		for(int i = 0; i < 5000; i++) {
			int randIndex = rand.nextInt(500);
			int temp = arr[randIndex];
			arr[randIndex] = arr[i];
			arr[i] = temp;
		}
		//Insert array into first tree
		for(int i = 0; i < arr.length; i++) {
			tree.root = tree.insert(tree.root, arr[i]);
		}
		
		do {
			int minVal = 0;
			long startTime = System.nanoTime();	//start time
			if(tree.root != null) {
				tree2.root = tree2.insert(tree2.root, tree.min(tree.root).data);
				minVal = tree.min(tree.root).data;
			}
			System.out.printf("\nThe process with a priority of %d is now scheduled to run!", minVal);
			if (tree.root != null){
				tree.root = tree.remove(tree.root, tree.min(tree.root).data);
			}
			System.out.printf("\nThe process with a priority of %d has run out of its timeslice!", minVal);
			long endTime = System.nanoTime();
			if(tree.root == null) {
				long passedTime = endTime - startTime;
				time = (double)passedTime / 1000000;
				System.out.println("\nEvery process has got a chance to run. Please press \"Enter\" to start the next round!\n");
				System.out.println("Time passed in second: " + time + "\n");
				time = 0;
				input = scan.nextLine();
				if(input.equals("")) {
					tree = tree2;
					tree2 = new Main();
				}
			}
		}while(input.equals(""));
	}		
}


