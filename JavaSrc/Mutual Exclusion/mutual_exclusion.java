import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Sydney on 10/17/2016.
 */

public class mutual_exclusion {
    static int content = 0;
    static Queue<Integer> products = new LinkedList<>();
    static boolean available = true;

    public static void main (String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            //array empty, produce.
            while (true) {
                if (available && products.isEmpty()) {
                    available = false;
                    while (products.size() < 10) {
                        products.add(content);
                        System.out.println("One of " + products.size() + " products produced.");
                        content++;
                    }
                    System.out.println("Queue full! Buy, buy, buy!");
                    available = true;
                    try {
                        //hold up man.
                        currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            int eventualStop = 0;
            //array full, consume
            while(true) {
                if (available && products.size() == 10) {
                    available = false;
                    while (products.size() != 0) {
                        products.remove();
                        System.out.println("One of " + content + " products consumed");
                        content--;
                    }
                    System.out.println("Queue empty, waiting for more!");
                    available = true;
                    try {
                        currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                eventualStop++;
            }
        }
    }
}

