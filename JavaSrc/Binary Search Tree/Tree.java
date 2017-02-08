public class Tree {

    private class TNode {
        Integer i;
        TNode left, right;

        public TNode(Integer i, TNode left, TNode right) {
            this.i = i;
            this.left = left;
            this.right = right;
        }
    }

    TNode root;

    public Tree() {
        root = null;
    }

    public Tree(Integer i) {
        root = new TNode(i, null, null);
    }

    public boolean contains(Integer i) {
        return contains(i, root);
    }

    private boolean contains(Integer i, TNode r) {
        if (r == null) return false;
        if (r.i == i) return true;
        if (r.i > i) return contains(i, r.left);
        return contains(i, r.right);
    }

    private TNode find(Integer i, TNode r) {
        if (r == null) return null;
        if (r.i == i) return r;
        if (r.i > i) return find(i, r.left);
        return find(i, r.right);
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void removeAll() {
        root = null;
    }

    public void insert(Integer i) {
        root = insert(i, root);
    }

    private TNode insert(Integer i, TNode r) throws BSTException{
        if (r.i == i) {
            throw new BSTException("Duplicate item, please try again.");
        }
        if (r == null) {
            return new TNode(i, null, null);
        }
        if (r.i > i) {
            r.left = insert(i, r.left);
        }
        r.right = insert(i, r.right);
        return r;
    }

    public void delete(Integer i) {
        root = delete(i, root);
    }

    private TNode delete(Integer i, TNode r) throws BSTException{
        if (r == null) {
            throw new BSTException("Item does not exist");
        }
        if(r.i == i) {
            return deleteNode(r);
        }
        if (r.i > i) {
            r.left = delete(i, r.left);
        }
        r.right = delete(i, r.right);

        return r;
    }

    private TNode findSuccessor(TNode r) {
        while (r.left != null) {
            r = r.left;
        }
        return r;
    }

    private TNode deleteNode(TNode r) {
        if (r.left == null && r.right == null){
            return null;
        }
        if (r.left == null || r.right == null){
            return r.right == null? r.left: r.right;
        }
        if (r.left != null && r.right != null){
            TNode Successor = findSuccessor(r.right);
            r.right = delete(Successor.i, r.right);
            r.i = Successor.i;
        }
        return r;
    }
}
