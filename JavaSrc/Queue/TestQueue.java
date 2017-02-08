/**
 * Created by Sydney on 7/22/2016.
 */
public class TestQueue {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue("Sally");
        queue.enqueue("Jim");
        queue.enqueue("Kevin");
        queue.enqueue("Alex");
        queue.enqueue("Karen");
        queue.enqueue("Ralph");
        queue.enqueue("Susan");
        queue.enqueue("Bob");
        queue.enqueue("Terry");
        queue.enqueue("Tim");
        queue.enqueue("Alice");
        queue.enqueue("Caitlynn");
        queue.enqueue("Melanie");
        queue.enqueue("Lindsay");
        queue.enqueue("Erin");

        displayQueue(queue);
        System.out.println("\nThere are " + queueCount(queue) + " people in the queue.");

    }
    private static void displayQueue(Queue queue) {
        String marker = queue.dequeue();
        System.out.print(marker + ", ");
        queue.enqueue(marker);
        while(!queue.peek().equals(marker)) {
            String curr = queue.dequeue();
            System.out.print(curr + (queue.peek()!=marker ? ", ": " "));
            queue.enqueue(curr);
        }
    }

    private static int queueCount(Queue queue) {
        int counter = 1;
        String marker = queue.dequeue();
        queue.enqueue(marker);
        while(!queue.peek().equals(marker)) {
            String curr = queue.dequeue();
            queue.enqueue(curr);
            counter++;
        }
        return counter;
    }
}
