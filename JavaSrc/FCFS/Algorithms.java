import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Sydney on 27/11/2016.
 * Question 6.16
 */
public class Algorithms {
     static Queue<process> processes = new LinkedList<>();
     static Queue<process> prioratized = new LinkedList<>();
     static process[] processesArray;
     public static void main(String[] args) {
         File file = new File("input1.txt");

         try {
             Scanner reader = new Scanner(file);
             String x = reader.nextLine();
             String[] temp = x.split(" ");
             processesArray = new process[temp.length];
             int counter = 0;
             while(reader.hasNextLine()) {
                 process p = new process(Integer.parseInt(temp[counter]), reader.nextInt(), reader.nextInt());
                 processes.add(p);
                 processesArray[counter] = p;
                 counter++;
             }
             FCFS();
             SJF();
             priority();
             roundRobin();
         }
         catch(Exception e) {
             e.printStackTrace();
         }
     }
     public static void FCFS() {
         System.out.println("FIRST COME FIRST SERVE");
         System.out.println("----------------------");
         int counter = 0;
         double averageWaitingTime = 0;
         while(!processes.isEmpty()) {
             process x = processes.remove();
             x.setWaitingTime(counter);
             System.out.println("P" + x.getName() + "\n Turnaround time = " + (x.getBurst() + x.getWaitingTime()) +
                     "\n Waiting Time = " + x.getWaitingTime());
             counter += x.getBurst();
             averageWaitingTime += x.getWaitingTime();
         }
         System.out.println("\nAverage Wait Time (FCFS): " + averageWaitingTime/processesArray.length);
         reset();
     }

     public static void SJF() {
         System.out.println("\n  SHORTEST JOB FIRST");
         System.out.println("----------------------");
         int waitTimer = 0;
         int averageWaitingTime =  0;
         Arrays.sort(processesArray);

         for(int i = 0; i < processesArray.length; i++) {
             processesArray[i].setWaitingTime(waitTimer);
             System.out.println(processesArray[i].toString() + "\n Turnaround time = " + (processesArray[i].getBurst()
                     + processesArray[i].getWaitingTime()) + "\n Waiting Time = " + processesArray[i].getWaitingTime());
             waitTimer += processesArray[i].getBurst();
             averageWaitingTime += processesArray[i].getWaitingTime();
         }

         System.out.println("\nAverage Wait Time (SJF): " + averageWaitingTime/processesArray.length);
         reset();
     }

     public static void priority() {
         System.out.println("\n      PRIORITY");
         System.out.println("----------------------");
         process[] temp = processesArray;
         int waitTimer = 0;
         int averageWaitingTime =  0;

         //I am so, so sorry.
         for(int i = 0; i < temp.length; i++) {
             int swap = temp[i].getBurst();
             temp[i].setBurst(temp[i].getPriority());
             temp[i].setPriority(swap);
         }

         Arrays.sort(temp);
         for(int x = 0; x < temp.length; x++) {
             prioratized.add(temp[x]);
         }

         for(int i = 0; i < temp.length; i++) {
             temp[i].setWaitingTime(waitTimer);
             System.out.println(temp[i].toString() + "\n Turnaround time = " + (temp[i].getBurst()
                     + temp[i].getWaitingTime()) + "\n Waiting Time = " + temp[i].getWaitingTime());
             waitTimer += temp[i].getBurst();
             averageWaitingTime += temp[i].getWaitingTime();
         }

         System.out.println("\nAverage Wait Time (Priority): " + averageWaitingTime/temp.length);
     }

     public static void roundRobin() {
         int quantum = 2;
         int waiting = 0;
         int averageWaitingTime = 0;

         System.out.println("\n     ROUND ROBIN");
         System.out.println("----------------------");

         while(!prioratized.isEmpty()) {
             process hold = prioratized.remove();
             hold.setWaitingTime(hold.getWaitingTime()+waiting);
             hold.setEditBurst(hold.getEditBurst()-quantum);
             if(hold.getEditBurst() > 0) {
                 prioratized.add(hold);
             }
             else {
                 //just print wait time for turnaround time because we have been adding its burt time in
                 System.out.println(hold.toString() + "\n Turnaround time = " + (hold.getWaitingTime() + 2) +
                         "\n Waiting Time = " + (((hold.getWaitingTime() + 2) - hold.getBurst()) + hold.getEditBurst()));
                 averageWaitingTime += ((hold.getWaitingTime() + 2) - hold.getBurst()) + hold.getEditBurst();
             }
         }
         System.out.println("\nAverage Wait Time (Round Robin): " + averageWaitingTime/processesArray.length);
     }

     public static void reset() {
         processes = new LinkedList<>();
         for(int i = 0; i < processesArray.length; i++) {
             processes.add(processesArray[i]);
         }
     }
}
