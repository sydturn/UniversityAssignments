/**
 * Created by Sydney on 27/11/2016.
 */
public class unrunnableException extends Exception{
    public unrunnableException() {
        super("There is not enough resources to run this process at this time.");
    }
}
