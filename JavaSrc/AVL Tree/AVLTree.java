import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sydney on 8/9/2016.
 * Tree class with generics (usable by multiple types)
 */
public class AVLTree <T extends Comparable<T>> {
    private class Node<T extends Comparable<T>>{
        T item;
        int height;
        Node<T> left, right;

        public Node(T i, Node<T> left, Node<T> right) {
            this.item = i;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public int compareTo(T o) {
            return item.compareTo(o);
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(T i){
        root = new Node<T>(i, null, null);
    }


    public boolean contains(T i) {
        return contains(i, root);
    }

    //checks for user given node in tree and returns true if found
    private boolean contains(T i, Node r) {
        if (r == null) return false;
        if (r.item == i) return true;
        if (r.item.compareTo(i) > 0) return contains(i, r.left);
        return contains(i, r.right);
    }

    //private method returns node which contains specified item
    private Node find(T i, Node r) {
        if (r == null) return null;
        if (r.item.compareTo(i) == 0) return r;
        if (r.item.compareTo(i) > 0) return find(i, r.left);
        return find(i, r.right);
    }

    //checks if tree is empty
    public boolean isEmpty() {
        return (root == null);
    }

    //empties tree
    public void removeAll() {
        root = null;
    }

    //inserts node into tree (smallest on the left, largest on the right)
    public void insert(T i) {
        root = insert(i, root);
    }

    //throws exception if item already exists
    private Node insert(T i, Node r) throws AVLException{
        if (r == null) {
            return new Node(i, null, null);
        }
        if (r.item == i) {
            throw new AVLException("Duplicate item, please try again.");
        }
        if (r.item.compareTo(i) > 0) {
            r.left = insert(i, r.left);
        }
        else {
            r.right = insert(i, r.right);
        }
        if(Math.abs(balanceValue(r))>1) 	//imbalanced
        	if((r.left != null ? r.left.height: 0) < (r.right != null ? r.right.height : 0)) {  //rotate left
        		r = rotateLeft(r);
        		 if(Math.abs(balanceValue(r))>1) {
        			r = rotateRight(r); //undo
        			r.right = rotateRight(r.right);
        			//rotate longest child 
        			r = rotateLeft(r); //redo
        		}
        	}
        	else {
        		r = rotateRight(r);
				if(Math.abs(balanceValue(r))>1) {
					r = rotateLeft(r); //undo
					r.left = rotateLeft(r.left);
					//rotate longest child 
					r = rotateRight(r); //redo
        		}
        	}
			r.height = 1 + Math.max((r.left != null ? r.left.height: 0), (r.right != null ? r.right.height : 0));
        	//ready to return currRoot
    		//continued on next slide

        return r;
    }
    
    private int balanceValue(Node r) {
    	return ((r.left != null ? r.left.height: 0) - (r.right != null ? r.right.height : 0));
    }
    
    private Node rotateLeft (Node currRoot) {
		Node newRoot = currRoot.right;
		Node temp = newRoot.left;
		newRoot.left = currRoot;
		currRoot.right = temp;
		currRoot.height = 1 + Math.max((currRoot.left != null ? currRoot.left.height: 0), (currRoot.right != null ? currRoot.right.height : 0));
		newRoot.height = 1 + Math.max((newRoot.left != null ? newRoot.left.height: 0), (newRoot.right != null ? newRoot.right.height : 0));
		return newRoot;
    }
    
    private Node rotateRight (Node currRoot) {
		Node newRoot = currRoot.left;
		Node temp = newRoot.right;
		newRoot.right = currRoot;
		currRoot.left = temp;
		currRoot.height = 1 + Math.max((currRoot.left != null ? currRoot.left.height: 0), (currRoot.right != null ? currRoot.right.height : 0));
		newRoot.height = 1 + Math.max((newRoot.left != null ? newRoot.left.height: 0), (newRoot.right != null ? newRoot.right.height : 0));
		return newRoot;
    }

    //removes a specified node from the tree by first finding the node to remove (first of 4 methods)
    public void delete(T i) {
        root = delete(i, root);
    }

    //throws exception if item does not exist/isn't found
    private Node delete(T i, Node r) throws AVLException{
        if (r == null) {
            throw new AVLException("Item does not exist");
        }
        if(r.item.equals(i)) {
            return deleteNode(r);
        }
        if (r.item.compareTo(i) > 0) {
            r.left = delete(i, r.left);
        }
        else {
            r.right = delete(i, r.right);
        }
        return r;
    }

    //finds child node, used for removing nodes
    private Node findSuccessor(Node r) {
        while (r.left != null) {
            r = r.left;
        }
        return r;
    }

    //method that removes the found node (final method of 4)
    private Node deleteNode(Node r) {
        if (r.left == null && r.right == null){
            return null;
        }
        if (r.left == null || r.right == null) {
            return r.right == null ? r.left : r.right;
        }
        Node successor = findSuccessor(r.right);
        r.right = delete((T)successor.item, r.right);
        r.item = successor.item;
        
        if(Math.abs(balanceValue(r))>1) 	//imbalanced
        	if(r.left.height < r.right.height) {  //rotate left
        		r = rotateLeft(r);
        		 if(Math.abs(balanceValue(r))>1) {
        			r = rotateRight(r); //undo
        			r.right = rotateRight(r.right);
        			//rotate longest child 
        			r = rotateLeft(r); //redo
        		}
        	}
        	else {
        		r = rotateRight(r);
				if(Math.abs(balanceValue(r))>1) {
					r = rotateLeft(r); //undo
					r.left = rotateLeft(r.left);
					//rotate longest child 
					r = rotateRight(r); //redo
        		}
        	}

        return r;
    }

    //turns tree into pretraversal string
    private String pre(Node r) {
        if(r == null) {
            return "";
        }
        return r.item +  "," + pre(r.left) + pre(r.right);
    }

    //turns tree into post traversal string
    private String post(Node r) {
        if(r == null) {
            return "";
        }
        return post(r.left) + post(r.right) + r.item +  ",";
    }

    //turns tree into into intraversal string
    private String intra(Node r) {
        if(r == null) {
            return "";
        }

        return intra(r.left) + r.item + "," + intra(r.right);
    }

    //displays intraversal tree by calling intra
    public void printTreeIn() {
        System.out.print("In-traversal: ");
        System.out.println(intra(root));
    }

    //displayes posttraversal string by calling post
    public void printTreePost() {
        System.out.print("Post-Traversal: ");
        System.out.println(post(root));
    }

    //displays pretraversal string by calling pre
    public void printTreePre() {
        System.out.print("Pre-Traversal: ");
        System.out.println(pre(root));
    }

    //saves intraversal tree to a .txt file
    public void saveTreeIn() throws IOException{
        PrintWriter writer = new PrintWriter("intree.txt");
        writer.println(intra(root));
        writer.close();
    }

    //saves posttraversal string to a .txt file
    public void saveTreePost() throws IOException {
        PrintWriter writer = new PrintWriter("posttree.txt");
        writer.println(post(root));
        writer.close();
    }

    //saves pretraversal string to a .txt file
    public void saveTreePre() throws IOException {
        PrintWriter writer = new PrintWriter("pretree.txt");
        writer.println(pre(root));
        writer.close();
    }

    //counts the number of nodes in the tree
    public int count() {
        return count(root);
    }
    private int count(Node r) {
        if(r == null) {
            return 0;
        }
        return 1 + count(r.left) + count(r.right);
    }

    //returns the height of the longest branch
    public int maxHeight() {
        return height(root);
    }
    private int height(Node r) {
        if(r == null) {
            return 0;
        }
        return Math.max(height(r.right), height(r.left))+1;
    }

    //returns the height of the shortest branch
    public int minHeight() {
        return minHeight(root);
    }
    private int minHeight(Node r) {
        if(r == null) {
            return 0;
        }
        return Math.min(height(r.right), height(r.left))+1;
    }

    //checks if node has children nodes (if not, it is a leaf)
    public boolean isLeaf(T i) {
        return isLeaf(find(i, root));
    }
    private boolean isLeaf(Node r) {
        if(r == null) {
            return false;
        }
        return (r.right == null && r.left == null);
    }

    //checks if the tree is balanced (branches within one node of eachother)
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node r) {
        if (r == null) {
            return true;
        }
        return (Math.abs(height(r.left) - height(r.right)) <= 1 && isBalanced(r.left) && isBalanced(r.right));
    }

    //checks if tree is full(all leaves but the last leaves have two children)
    public boolean isFull() {
        return isFull(root);
    }
    private boolean isFull(Node r) {
        if(r == null) {
            return true;
        }
        if(height(r) > 0) {
            if((isFull(r.right) && height(r.right) == height(r)-1) && (isFull(r.left) && height(r.left) == height(r)-1)) {
                return true;
            }
        }
        return false;
    }

    //checks if the tree is complete
    public boolean isComplete() {
        return isComplete(root);
    }
    private boolean isComplete(Node r) {
        if(isFull(r)) {
             return true;
        }
        if(height(r.left) == height(r.right)) {
            return isFull(r.left) && isComplete(r.right);
        }
        if(height(r.left) - height(r.right)==1) {
            return isFull(r.right) && isComplete(r.left);
        }
        return false;
    }

    //turns a sorted array into a balanced tree
    public void treeBuildFromSorted(T[] items) {
        removeAll();
        treeBuildFromSorted(items, 0, items.length);
    }
    private void treeBuildFromSorted(T[] items, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        insert(items[mid]);
        treeBuildFromSorted(items, start, mid);
        treeBuildFromSorted(items, mid + 1, end);
    }
}
