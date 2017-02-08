import java.io.IOException;

/**
 * Created by Sydney on 8/9/2016.
 */
public class TestTree {
    public static void main (String[] args) throws IOException {
        Tree<Character> charTree = new Tree();
        Tree<Short> shortTree = new Tree();
        Tree<Float> floatTree = new Tree();

        for(int i = 0; i < 15; i++) {
            char c = (char)((int)(Math.random() * 26) + 'A');
            try {
                charTree.insert(c);
            }
            catch(Exception e) {
                i--;
            }
        }

        for(int i = 0; i < 30; i++) {
            try {
                floatTree.insert((float) (Math.random() * Float.MAX_VALUE));
            }
            catch(Exception e) {
                i--;
            }
        }

        for(int i = 0; i < 23; i++) {
            short s = (short) (Math.random() * Short.MAX_VALUE);
            try {
                shortTree.insert(s);
            }
            catch(Exception e) {
                i--;
            }
        }

        System.out.printf("Information: \n(Character Tree) Nodes = %d, Min Height = %d, Max Height = %d, Tree full " +
                "(%b), Tree complete(%b), Tree balanced(%b) \n(Float Tree) Nodes = %d, Min Height = %d, " +
                "Max Height = %d, Tree full(%b), Tree complete(%b), Tree balanced(%b) \n(Short Tree) Nodes = %d, " +
                "Min Height = %d, Max Height = %d, Tree full(%b), Tree complete(%b), Tree balanced(%b)\n",
                charTree.count(), charTree.minHeight(),charTree.maxHeight(), charTree.isFull(),charTree.isComplete(),
                charTree.isBalanced(),
                floatTree.count(), floatTree.minHeight(), floatTree.maxHeight(), floatTree.isFull(),
                floatTree.isComplete(), floatTree.isBalanced(),
                shortTree.count(), shortTree.minHeight(), shortTree.maxHeight(), shortTree.isFull(),
                shortTree.isComplete(), shortTree.isBalanced());

        System.out.println();
        charTree.printTreeIn();
        charTree.printTreePost();
        charTree.printTreePre();

        System.out.println();
        floatTree.printTreeIn();
        floatTree.printTreePost();
        floatTree.printTreePre();

        System.out.println();
        shortTree.printTreeIn();
        shortTree.printTreePost();
        shortTree.printTreePre();


/*      d) Retrieve and report two examples of a leaf node and two examples of a non-leaf node from
        each tree – use your isLeaf() method to report if it is a leaf or not.
        e) Delete two internal nodes and two leaf nodes from each tree.
        f) Report the number of nodes, min/max height of the tree, and if the tree is full, complete
        and balanced.
        g) Print the trees using the three print methods.*/

    }
}
