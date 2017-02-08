import java.util.Comparator;

/**
 * Created by Sydney on 27/11/2016.
 */
public class process implements Comparable<process>{
    int name;
    int burst;
    int editBurst;
    int priority;
    int turnAroundTime;
    int waitingTime;

    public process(int name, int burst, int priority) {
        this.name = name;
        this.burst = burst;
        this.priority = priority;
        turnAroundTime = burst;
        editBurst = burst;
    }

    @Override
    public int compareTo(process o) {
        return this.burst - o.burst;
    }

    @Override
    public String toString() {
        return "P" + name;
    }

    public int getEditBurst() {
        return editBurst;
    }

    public void setEditBurst(int x) {
       editBurst = x;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }
}
