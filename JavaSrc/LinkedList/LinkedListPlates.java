import sun.awt.image.ImageWatched;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sydney on 7/19/2016.
 */
public class LinkedListPlates {

    private Node head = null;

    public LinkedListPlates(){
        head = new Node();
    }

    public boolean isEmpty() {
        //if the head is null then the list is null
        return head==head.getNext();
    }

    public int size() {
        int counter = 0;
        Node curr = head;
        while(curr.getNext()!=head) {
            counter++;
            curr = curr.getNext();
        }
        return counter;
    }

    private Node find(String s){
        Node curr = head.getNext();
        while(curr!=head && s.compareTo((String)curr.getItem()) > 0){
            curr = curr.getNext();
        }

        return curr.getPrev();
    }

    //no longer checks for duplicates but changes array size instead. This is the only change.
    public void add(String item) throws LinkedPlateException {
        if(!validPlate(item)) {
            throw new LinkedPlateException("Illegal plate.");
        }
        Node curr = find(item);
        if(curr.getNext().equals(item)){
            throw new LinkedPlateException("Duplicale plate.");
        }
        Node newNode = new Node(item, curr.getNext());
        curr.setNext(newNode);
        newNode.setPrev(curr);
    }

    private boolean validPlate(String plate) {
        String pattern = "[A-Z]{2}[0-9]{2} [A-Z]{3}";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher match = r.matcher(plate);

        if (match.find()) {
            return true;
        } else {
            return false;
        }
    }

    public void remove(String item) {
        Node curr = find(item);
        curr.setNext(curr.getNext().getNext());
        curr.getNext().setPrev(curr);
    }

    public void remove(int index) {
        Node curr = head.getNext();
        while (curr!=head && index > 0) {
            index -= 1;
            curr = curr.getNext();
        }
        if(curr== head) {
            throw new LinkedPlateException("Index out of bounds");
        }
        curr.getPrev().setNext(curr.getNext());
    }

    //remove with recursion
    public void removeR(String item) {
        removeRHelper(item, head.getNext());
    }
    private void removeRHelper(String item, Node pos)throws LinkedPlateException{
        if(pos == head) {
            throw new LinkedPlateException(item + " does not exist.");
        }
        if(item.equals(pos.getItem())){
            pos.getPrev().setNext(pos.getNext());
            return;
        }
        removeRHelper(item, pos.getNext());
    }

    //add with recursion
    public void addR(String item) {
        addRHelper(item, head.getNext());
    }
    private void addRHelper(String item, Node pos) {
        if(pos == head) {
            pos.getPrev().setNext(new Node(item,head));
        }
        else if(pos.getItem().compareTo(item) < 0) {
            addRHelper(item, pos.getNext());
        }
        else {
            Node newNode = new Node(item, pos.getPrev());
            pos.setNext(newNode);
            newNode.setPrev(pos);
        }
    }

    //get with recursion
    public String getR(int index) {
        getRHelper(index, head.getNext());
    }
    private String getRHelper(int item, Node pos) {

    }

    //reverse with recursion
    public void reverseR() {

    }

    //reverse with iteration
    public void reverse() {

    }

    public String get(int index) throws LinkedPlateException{
        Node curr = head.getNext();
        while (curr!=head && index > 0) {
            index -= 1;
            curr = curr.getNext();
        }
        if(curr== head) {
            throw new LinkedPlateException("Index out of bounds");
        }
        return curr.getItem();
    }

    public int locateIndex(String item) {
        int counter = 0;
        Node curr = head.getNext();
        while (curr!=head) {
            if(curr.getItem().equals(item)) {
                return counter+1;
            }
        }
        return -1;
    }

    public void getContents() {
        Node curr = head.getNext();
        System.out.print("[");
        while (curr!=head) {
            System.out.print(curr.getItem() + ", ");
        }
        System.out.print("]");
    }

    public void removeAll() {
        head.setNext(head);
        head.setPrev(head);
    }
}

