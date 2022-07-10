import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Mean {
	String filename;
	int q,sum,n,numSwitch,totalWaiting,totalTurnAround,totalResponseTime;
	int[] bt,wt,tat,origBurst,rt;
	HashMap<Integer,Integer> hash;
	
	public Mean(String filename) throws IOException{
		this.filename = filename;
        Scanner sc = new Scanner(new File(filename));
        totalWaiting = 0; //total waiting time
        totalTurnAround = 0; //total turn around time
        numSwitch = 0; //number of context switch
        sum = 0; //sum of remaining burst time
        q=0;
        List<Integer> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(Integer.parseInt(sc.nextLine()));
        }
        n = lines.size();
        bt = new int[n]; //burst time
        wt = new int[n]; //waiting time
        tat = new int[n]; //turn around time
        origBurst = new int[n]; //original burst time
        rt = new int[n]; //response time
        hash = new HashMap<Integer,Integer>();

        for (int i = 0; i < n; i++) {
            bt[i] = lines.get(i);
            origBurst[i] = bt[i];
            sum += bt[i];
            hash.put(i, bt[i]);
        }

        for (int i = 0; i < n; i++) {
            wt[i] = 0;
        }

		
	}
	
	public void calculateQ(){
		int total = 0;
		int num = 0;
		for (int key:hash.keySet()){
			if(hash.get(key)!=0){
				total += hash.get(key);
				num += 1;
			}
		}
		q = (int)total/num;
		System.out.println(q);
	}
	
	public void remove0(){
		HashMap<Integer,Integer> newHash = new HashMap<Integer,Integer>();
		for (int key:hash.keySet()){
			if(hash.get(key)!=0){
				newHash.put(key, hash.get(key));
			}
		}
		hash = newHash;
	}
	
	
	public void calculate(){
        int timer = 0;
        while (sum != 0) {
        	remove0();
        	calculateQ();
            for (int i:hash.keySet()) {           
                boolean hasOthers = false;
                if (hash.get(i) > q) {
                    bt[i] -= q;
                    hash.put(i, hash.get(i)-q);
                    sum -= q;
                    timer += q;
                    if ((origBurst[i]-bt[i])<=q && rt[i]==0){
                    	rt[i] = timer-q;
                    }  
                    //System.out.println(timer);
                    for (int j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0)) {
                            wt[j] += q;
                            hasOthers = true;                          
                        }
                    }
                } else if (hash.get(i) > 0 && hash.get(i) <= q) {
                	timer += bt[i];
                    //System.out.println(timer);
                    for (int j = 0; j < n; j++) {
                        if ((j != i) && (bt[j] != 0)) {
                            wt[j] += bt[i];
                            hasOthers = true;                           
                        }
                    }
                    sum -= bt[i];
                    int sub = timer - bt[i];
                    bt[i] = 0;
                    hash.put(i, 0);
                    if ((origBurst[i]-bt[i])<=q && rt[i] == 0){
                    	rt[i] = sub;
                    }
                    
  
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
        for (int i=0;i<n;i++){
        	totalResponseTime += rt[i];
        }
        double avgWaiting = (double) totalWaiting / n;
        double avgTurnAround = (double) totalTurnAround / n;
        double avgResponse = (double) totalResponseTime /n;
        System.out.printf("Average Waiting Time = %.3f ms\n", avgWaiting);
        System.out.printf("Average Turnover Time = %.3f ms\n", avgTurnAround);
        System.out.printf("Average Response Time = %.3f ms\n", avgResponse);
        System.out.println("Number of Context Switch = " + numSwitch);
    }

	public static void main(String[] args) throws IOException {
		Mean b = new Mean("C:/Users/phoebe/Desktop/try.txt");
		b.calculate();

	}

}


