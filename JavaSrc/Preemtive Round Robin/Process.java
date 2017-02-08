import java.util.Comparator;

/**
 * Created by Sydney on 27/11/2016.
 */
public class Process implements Comparable<Process> {
    boolean interupted = false;
    @Override
    public int compareTo(Process o) {
        if(o.interupted) {
            return -1;
        }
        return 0;
    }
}
