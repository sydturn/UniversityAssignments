/**
 * Created by Sydney on 7/22/2016.
 */
public class StackPostfix {
    private class Node {
    double item;
    Node next;

    public Node(double item, Node next) {
        this.item = item;
        this.next = next;
    }
}

    Node head;

    public StackPostfix() {

    }

    public boolean isEmpty() {
        return(head==null);
    }

    public void push(double item) {
        head = new Node(item, head);
    }

    public double pop(){
        if(isEmpty()) {
            throw new StackException("Cannot pop: there is nothing in the stack.");
        }
        double r = head.item;
        head = head.next;
        return r;
    }

    public double peek(){
        if(isEmpty()) {
            throw new StackException("Cannot peek: there is nothing in the stack.");
        }
        return head.item;
    }
}
