import java.io.*;
import java.util.Arrays;

/**
 * Created by Sydney on 8/9/2016.
 * This program tests the generic tree class by making three trees(character, short, float) and testing its functions
 */
public class AVLTest {
    public static void main (String[] args) throws IOException {
    	AVLTree tree = new AVLTree();
    	tree.insert(22);
    	tree.insert(8);
    	tree.insert(17);
    	tree.insert(14);
    	tree.insert(16);
    	tree.insert(30);
    	tree.insert(34);
    	tree.insert(38);	
    	tree.printTreePre();
    	tree.delete(30);
    	tree.printTreePre();
    }
}
