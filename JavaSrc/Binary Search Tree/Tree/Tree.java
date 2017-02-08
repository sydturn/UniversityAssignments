import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sydney on 8/9/2016.
 */
public class Tree <T extends Comparable<T>> {

    private class Node<T extends Comparable<T>>{
        T item;
        Node<T> left, right;

        public Node(T i, Node<T> left, Node<T> right) {
            this.item = i;
            this.left = left;
            this.right = right;
        }

        public int compareTo(T o) {
           return item.compareTo(o);
        }
    }

    private Node root;

    public Tree() {
        root = null;
    }

    public Tree(T i){
        root = new Node<T>(i, null, null);
    }

    public boolean contains(T i) {
        return contains(i, root);
    }

    private boolean contains(T i, Node r) {
        if (r == null) return false;
        if (r.item == i) return true;
        if (r.item.compareTo(i) > 0) return contains(i, r.left);
        return contains(i, r.right);
    }

    private Node find(T i, Node r) {
        if (r == null) return null;
        if (r.item == i) return r;
        if (r.item.compareTo(i) > 0) return find(i, r.left);
        return find(i, r.right);
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void removeAll() {
        root = null;
    }

    public void insert(T i) {
        root = insert(i, root);
    }

    private Node insert(T i, Node r) throws BSTException{
        if (r == null) {
            return new Node(i, null, null);
        }
        if (r.item == i) {
            throw new BSTException("Duplicate item, please try again.");
        }
        if (r.item.compareTo(i) > 0) {
            r.left = insert(i, r.left);
        }
        else {
            r.right = insert(i, r.right);
        }
        return r;
    }

    public void delete(T i) {
        root = delete(i, root);
    }

    private Node delete(T i, Node r) throws BSTException{
        if (r == null) {
            throw new BSTException("Item does not exist");
        }
        if(r.item == i) {
            return deleteNode(r);
        }
        if (r.item.compareTo(i) > 0) {
            r.left = delete(i, r.left);
        }
        r.right = delete(i, r.right);

        return r;
    }

    private Node findSuccessor(Node r) {
        while (r.left != null) {
            r = r.left;
        }
        return r;
    }

    private Node deleteNode(Node r) {
        if (r.left == null && r.right == null){
            return null;
        }
        if (r.left == null || r.right == null){
            return r.right == null ? r.left : r.right;
        }
        if (r.left != null && r.right != null){
            Node successor = findSuccessor(r.right);
            r.right = delete((T)successor.item, r.right);
            r.item = successor.item;
        }
        return r;
    }

    private String pre(Node r) {
        if(r == null) {
            return "";
        }
        String s = "";
        s += r.item +  ", " + pre(r.left) + pre(r.right);
        return s;
    }

    private String post(Node r) {
        if(r == null) {
            return "";
        }
        String s = "";
        s += post(r.left) + post(r.right) + r.item +  ", ";
        return s;
    }

    private String intra(Node r) {
        if(r == null) {
            return "";
        }

        String s = "";
        s += intra(r.left) + r.item + ", " + intra(r.right);
        return s;
    }

    public void printTreeIn() {
        System.out.print("In-traversal: ");
        System.out.println(intra(root));
    }

    public void printTreePost() {
        System.out.print("Post-Traversal: ");
        System.out.println(post(root));
    }

    public void printTreePre() {
        System.out.print("Pre-Traversal: ");
        System.out.println(pre(root));
    }

    public void saveTree() throws IOException{
        PrintWriter writer = new PrintWriter("tree.txt");
        writer.println("Infix: " + intra(root));
        writer.println("Postfix: " + post(root));
        writer.println("Prefix: " + pre(root));
        writer.close();
    }


    public int count() {
       return count(root);
    }
    private int count(Node r) {
        if(r == null) {
            return 0;
        }
        return 1 + count(r.left) + count(r.right);
    }

    public int maxHeight() {
        return height(root);
    }
    private int height(Node r) {
        if(r == null) {
            return 0;
        }
        return Math.max(height(r.right), height(r.left))+1;
    }

    public int minHeight() {
        return minHeight(root);
    }
    private int minHeight(Node r) {
        if(r == null) {
            return 0;
        }
        return Math.min(height(r.right), height(r.left))+1;
    }

    public boolean isLeaf(T i) {
        return isLeaf(find(i, root));
    }
    private boolean isLeaf(Node r) {
        return (r.right == null && r.left == null);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node r) {
        if (r == null) {
            return true;
        }
        return (isBalanced(r.left) && isBalanced(r.right)
                && Math.abs(height(r.left) - height(r.right)) <= 1);
    }

    public boolean isFull() {
        return isFull(root);
    }
    private boolean isFull(Node r) {
        if(r == null) {
            return true;
        }
        if(height(r) > 0) {
            if((isFull(r.left) && height(r.left) == height(r)-1) && (isFull(r.right) && height(r.right) == height(r)-1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isComplete() {
/*
        A binary tree T of height h is complete if
        All nodes at level h – 2 and above have two children each, and
        When a node at level h – 1 has children, all nodes to its left at the same level have two children each, and
        When a node at level h – 1 has one child, it is a left child
*/
        return false;
    }
}
