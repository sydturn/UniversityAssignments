/**
 * Created by Sydney on 7/12/2016.
 */
public class LicensePlate {
    int contents = 0;
    String[] array;

    public void createSortedList() {
        //Creates an empty sorted list.
        array = new String[20];
    }

    public boolean sortedIsEmpty() {
        //determines whether a sorted list is empty.
        return contents == 0;
    }

    public int sortedSize() {
        //returns the number of items that are in a sorted list.
        return contents;
    }

    public void sortedAdd(String item) {
        //Inserts item into its proper sorted position in a
        //sorted list. Throws an exception if the item
        //cannot be placed on the list (list is full)
    }

    public void sortedRemove(String item) {
        //Deletes item from a sorted list.
        //Throws exception if the item is not found.
    }

    public int sortedGet(int index) {
        //Returns the item at position index of a
        //sorted list, if 0 <= index < sortedSize().
        //The list is left unchanged by this operation.
        //Throws an exception if the index is out of range.
    }

    public int locateIndex(String item) {
        //returns the position where item belongs or
        //exists in a sorted list; item and the list are
        //unchanged
    }
}
