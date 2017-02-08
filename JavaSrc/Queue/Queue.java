/**
 * Created by Sydney on 7/22/2016.
 * Code from textbook (as requested).
 */
public class Queue {
    private class Node {
        String item;
        Node next;

        public Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }
        public Node(String item) {
            this.item = item;
        }
    }

    Node tail;

    public Queue() {

    }

    public boolean isEmpty() {
        return(tail==null);
    }

    public void enqueue(String newItem) throws QueueException {
        Node newNode = new Node(newItem);
        if(isEmpty()) {
            newNode.next = newNode;
        }
        else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        tail = newNode;
        //add item to back of queue
    }

    public String dequeue() throws QueueException {
        if(!isEmpty()) {
            Node head = tail.next;
            if(head == tail) {//last node in queue special case
                tail=null;
            }
            else {
                tail.next = head.next;
            }
            return head.item;
        }
        else {
            throw new QueueException("Cannot remove or get: Queue is empty.");
        }
        //retrieves and removes the front of a queue(the head)
        //throws exception if queue is empty

    }

    public void dequeueAll() {
        tail = null;
        //removes all items of a queue
    }

    public Object peek() throws QueueException {
        if(!isEmpty()) {
            Node head = tail.next;
            return head.item;
        }
        else {
            throw new QueueException("Cannot peek: Queue is empty.");
        }
        //retrieves the item at the front of a queue
        //throws exception if queue is empty
    }
}
