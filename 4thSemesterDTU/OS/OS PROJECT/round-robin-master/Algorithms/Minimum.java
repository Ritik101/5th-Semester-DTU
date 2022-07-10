package roundRobin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Minimum {

    public static void main(String[] args) throws IOException {
        int q; //time quantum

        //String filename = "src/dataLarge.txt"; //The data for 100 processes
        String filename = "src/test_q5.txt"; //The data for 5 processes
        Scanner sc = new Scanner(new File(filename));

        int totalWaiting = 0; //total waiting time
        int totalTurnAround = 0; //total turn around time
        int totalResponse = 0; //total response time
        int numSwitch = 0; //number of context switch
        int sum = 0; //sum of remaining burst time

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

        for (int i = 0; i < n; i++) {
            bt[i] = lines.get(i);
            origBurst[i] = bt[i];
            sum += bt[i];
        }

        for (int i = 0; i < n; i++) {
            wt[i] = 0;
        }

        boolean firstloop = true;
        boolean hasOthers = false;
        int timer = 0; //a timer to record total time has elapsed
        int sub=0;

        while (sum != 0) {

            HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

            for(int i = 0; i < n; i++) {
                if (bt[i] > 0) {
                    hmap.put(i, bt[i]);
                }
            }

            Map<Integer, Integer> map = sortByValues(hmap);
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            Map.Entry<Integer, Integer> minq = (Map.Entry)iterator.next();
            q = minq.getValue();
            System.out.println("Time quantum = " + q);

            for (Map.Entry<Integer, Integer> me : map.entrySet()) {

                Integer i_sorted = me.getKey();
                Integer bt_sorted = me.getValue();
                hasOthers = false;

                if (bt_sorted > q) {
                    bt[i_sorted] = bt_sorted-q;
                    sum -= q;
                    if(firstloop){
                        timer += q;
                        if ((origBurst[i_sorted] - bt[i_sorted]) == q) {
                            rt[i_sorted] = timer - q;
                        }
                    }
                    for (int j = 0; j < n; j++) {
                        if ((j != i_sorted) && (bt[j] != 0)) {
                            wt[j] += q;
                            hasOthers = true;
                        }
                    }
                } else if (bt_sorted > 0 && bt_sorted <= q) {
                    if(firstloop){
                        timer += bt[i_sorted];
                        sub = timer - bt[i_sorted];
                    }

                    for (int j = 0; j < n; j++) {
                        if ((j != i_sorted) && (bt[j] != 0)) {
                            wt[j] += bt[i_sorted];
                            hasOthers = true;
                        }
                    }
                    sum -= bt_sorted;
                    bt[i_sorted] = 0;
                    if(firstloop && (origBurst[i_sorted] - bt[i_sorted]) <= q) {
                        rt[i_sorted] = sub;
                    }
                } else {
                    //do nothing
                }
                if (hasOthers) {
                    numSwitch++;
                }
            }
            firstloop = false;
        }

        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + origBurst[i];
        }
        System.out.println("Process\t\tBT\tWT\tTAT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("Process" + (i + 1) + "\t" + origBurst[i] + "\t" + wt[i] + "\t" + tat[i]+ "\t" + rt[i]);
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
