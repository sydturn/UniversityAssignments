/**
 * Created by Sydney on 7/19/2016.
 */

public class Node {

    private String item;
    private Node next;
    private Node prev;

    public Node(){
        prev = this;
        next = this;
    }
    public Node(String item, Node next){
        this.item = item;
        this.next = next;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public Node getPrev() {
        return prev;
    }
}
