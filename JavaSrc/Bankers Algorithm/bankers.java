import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Sydney on 27/11/2016.
 * Question 7.3
 */
public class bankers {
    static int[] available = new int[4];
    static process[] holder = new process[5];
    static Queue<process> pN = new LinkedList<process>();
    public static void main(String[] args) {
        File file = new File("input3.txt");

        try {
            Scanner reader = new Scanner(file);
            for (int i = 0; i < available.length; i++) {
                available[i] = reader.nextInt();
            }
            for (int processes = 0; processes < 5; processes++) {
                int[] allocate = new int[4];
                for (int i = 0; i < allocate.length; i++) {
                    allocate[i] = reader.nextInt();
                }
                int[] max = new int[4];
                for (int i = 0; i < max.length; i++) {
                    max[i] = reader.nextInt();
                }
                process p = new process(processes, allocate, max);
                holder[processes] = p;
                pN.add(p);
            }
            testBankers();

            while(reader.hasNextLine()) {
                int x = reader.nextInt();
                System.out.println("Process P" + x + " is requesting more resources!");
                for(int i = 0; i < holder.length; i++) {
                    if(holder[i].getActualName() == x) {
                        int[] request = new int[4];
                        for(int newRequest = 0; newRequest < 4; newRequest++) {
                            request[newRequest] = reader.nextInt();
                            available[newRequest] -= request[newRequest];
                        }
                        holder[i].additionalRequest(request);
                    }
                    pN.add(holder[i]);
                }
                testBankers();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testBankers() {
        int[] availableTemp = available;

        System.out.println("Need Matrix");
        for(int i = 0; i < pN.size(); i++) {
            process temp = pN.remove();
            temp.printNeed();
            pN.add(temp);
        }

        while(!pN.isEmpty()) {
            boolean hesitate = false;
            //if we get to the
            process temp = pN.remove();
            try {
                if(temp.triedOnce()) {
                    hesitate = true;
                }
                availableTemp = temp.run(availableTemp);
            }
            catch(unrunnableException e) {
                if(hesitate) {
                    System.out.println("Would result in an unsafe state, do not grant request.\n");
                    break;
                }
                pN.add(temp);
            }
        }
        if(pN.isEmpty()) {
            System.out.println("We are in a safe state.\n");
        }
        pN = new LinkedList<process>();
    }
}
