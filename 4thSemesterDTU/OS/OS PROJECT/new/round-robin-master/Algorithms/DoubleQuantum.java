package roundRobin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DoubleQuantum {
    public static void main(String[] args) throws IOException {

        int q; //time quantum
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Time Quantum:");
        q = scan.nextInt();
        int q0 = q;//q0 is the first time quantum used.

        //String filename = "src/dataLarge.txt"; //The data for 100 processes
        String filename = "src/test_q5.txt"; //The data for 5 processes
        Scanner sc = new Scanner(new File(filename));

        int totalWaiting = 0; //total waiting time
        int totalTurnAround = 0; //total turn around time
        int totalResponse = 0; //total response time
        int numSwitch = 0; //number of context switch
        int sum = 0; //sum of remaining burst time
        int last=0; //the index of last process at the end of hashmap loop
        int first=0; //the index of first process of non-zero burst time back to the beginning of for loop

        List<Integer> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(Integer.parseInt(sc.nextLine()));
        }
        int n = lines.size();
        int[] bt = new int[n]; //burst time
        int[] wt = new int[n]; //waiting time
        int[] tat = new int[n]; //turn around time
        int[] origBurst = new int[n]; //original burst time
        int[] rt = new int[n]; //response time
        boolean hasOthers = false;

        for (int i = 0; i < n; i++) {
            bt[i] = lines.get(i);
            origBurst[i] = bt[i];
            sum += bt[i];
        }

        for (int i = 0; i < n; i++) {
            wt[i] = 0;
        }

        int timer = 0; //a timer to record total time has elapsed

        while (sum != 0) {

            if(q>q0) {
                for (int i = 0; i < n; i++) {
                    if(bt[i]>0){
                        first = i+1;
                        break; //find the index of first non-zero process
                    }
                }

                if (hasOthers && first == last){
                    numSwitch -= 1;//if there are more than one process remaining and
                    //the last process at previous loop equals to the fist process at
                    //the beginning of the new loop, the context switch should not count in
                }
            }

            for (int i = 0; i < n; i++) {
                hasOthers = false;
                if (bt[i] > q) {
                    bt[i] -= q;
                    sum -= q;
                    timer += q;
                    if ((origBurst[i] - bt[i]) <= q) {
                        rt[i] = timer - q;
                    }
                    for (int j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0)) {
                            wt[j] += q;
                            hasOthers = true;
                        }
                    }
                } else if (bt[i] > 0 && bt[i] <= q) {
                    timer += bt[i];
                    int sub = timer - bt[i];
                    for (int j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0)) {
                            wt[j] += bt[i];
                            hasOthers = true;
                        }
                    }
                    sum -= bt[i];
                    bt[i] = 0;
                    if ((origBurst[i] - bt[i]) <= q) {
                        rt[i] = sub;
                    }
                } else {
                    //do nothing
                }
                if (hasOthers) {
                    numSwitch++;
                }
            }

            q = 2*q;

            HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

            for(int i = 0; i < n; i++) {
                if (bt[i] > 0) {
                    hmap.put(i, bt[i]);
                }
            }

            Map<Integer, Integer> map = sortByValues(hmap);

            for (Map.Entry<Integer, Integer> me : map.entrySet()) {

                Integer i_sorted = me.getKey();
                Integer bt_sorted = me.getValue();
                hasOthers = false;

                if (bt_sorted > q) {
                    bt[i_sorted] = bt_sorted-q;
                    sum -= q;
                    last = i_sorted+1;
                    for (int j = 0; j < n; j++) {
                        if ((j != i_sorted) && (bt[j] != 0)) {
                            wt[j] += q;
                            hasOthers = true;
                        }
                    }
                } else if (bt_sorted > 0 && bt_sorted <= q) {
                    for (int j = 0; j < n; j++) {
                        if ((j != i_sorted) && (bt[j] != 0)) {
                            wt[j] += bt[i_sorted];
                            hasOthers = true;
                        }
                    }
                    sum -= bt_sorted;
                    bt[i_sorted] = 0;
                } else {
                    //do nothing
                }
                if (hasOthers) {
                    numSwitch++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + origBurst[i];
        }
        System.out.println("Process\t\tBT\tWT\tTAT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("Process" + (i + 1) + "\t" + origBurst[i] + "\t" + wt[i] + "\t" + tat[i] + "\t" + rt[i]);
        }

        for (int i = 0; i < n; i++) {
            totalWaiting += wt[i];
        }
        for (int i = 0; i < n; i++) {
            totalTurnAround += tat[i];
        }
        for (int i = 0; i < n; i++) {
            totalResponse += rt[i];
            //to check each process' response time
            //System.out.println("RT(P" + (i + 1) + ") = " + rt[i]);
        }
        double avgWaiting = (double) totalWaiting / n;
        double avgTurnAround = (double) totalTurnAround / n;
        double avgResponse = (double) totalResponse / n;
        System.out.printf("Average Waiting Time = %.3f ms\n", avgWaiting);
        System.out.printf("Average Turnaround Time = %.3f ms\n", avgTurnAround);
        System.out.printf("Average Response Time = %.3f ms\n", avgResponse);
        System.out.println("Number of Context Switch = " + numSwitch);
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
