/**
 * Created by Sydney on 7/22/2016.
 */
public class Language {

    Character[] stack;
    int size = 0;

    public Language() {
        stack = new Character[5];
    }

    public boolean isEmpty() {
        return(size==0);
    }

    public void push(Character item) throws StackException {
        if(size == stack.length) {
            throw new StackException("There is no more room in the stack.");
        }
        stack[size] = item;
        size++;
    }

    public Character pop(){
        if(size == 0) {
            throw new StackException("There is nothing left in the stack.");
        }
        Character r = stack[size-1];
        stack[size-1] = null;
        size--;
        return r;
    }

    public Character peek(){
        return stack[size];
    }
}
