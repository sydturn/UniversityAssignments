import java.util.Arrays;

/**
 * Created by Sydney on 27/11/2016.
 * Question 7.23
 */
public class process {
    int name;
    int[] allocation = new int[4];
    int[] max = new int[4];
    int[] need =  new int[4];
    boolean tested = false;

    process (int name, int[] allocation, int max[]) {
        this.name = name;
        this.allocation = allocation;
        this.max = max;

        need();
    }
    private void need() {
        for(int i = 0; i < need.length; i++) {
            need[i] = max[i] - allocation[i];
        }
    }
    public void printNeed() {
        System.out.println(Arrays.toString(need));
    }
    public boolean triedOnce() {
        return tested;
    }
    public int[] run(int[] available)throws unrunnableException {
        int[] work = new int[available.length];
        for(int i = 0; i < available.length; i++) {
            if(need[i] > available[i]) {
                tested = true;
                throw new unrunnableException();
            }
            else {
                work[i] = available[i] + allocation[i];
            }
        }
        tested = true;
        return work;
    }
    public boolean getDone() {
        return tested;
    }
    public int getActualName() {
        return name;
    }
    public void additionalRequest(int[] request) {
        for(int i = 0; i < allocation.length; i++) {
            allocation[i] +=  request[i];
        }
        need();
    }
}
