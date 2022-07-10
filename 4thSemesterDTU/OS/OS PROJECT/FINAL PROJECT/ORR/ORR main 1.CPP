
#include<iostream>
using namespace std;


struct process
{
	int processNo;
	int arrivalTime;
	int burstTime;
	int waitingTime;
	int tat;
	int ct;
	int rt;
};

int n;
struct process p[100];


void input()
{
	cout<<"\n\n\t\t\t\t\t\t*********************************************";
	cout<<"\n\t\t\t\t\t\t    A PRIORITY BASED ROUND ROBIN ALGORITHM ";
	cout<<"\n\t\t\t\t\t\t*********************************************";
	cout<<"\n\n\t\t\t\t\t\tEnter the number of processes : ";
	cin>>n;
	cout<<"\n";
	//cout<<"\t\t\t\t\t\t";
	
	
	for(int i=0;i<n;i++)
	{
		cout<<"\t\t\t\t\t\tEnter PROCESS NUMBER, ARRIVAL TIME and BURST TIME : \n";
		cout<<"\t\t\t\t\t\t";cin>>p[i].processNo>>p[i].arrivalTime>>p[i].burstTime;
	
		p[i].rt = p[i].burstTime;
		p[i].waitingTime = 0;
		p[i].ct = p[i].arrivalTime;
	}
}

void rr()
{
	
	int i,j,flag;
	struct process q[100],r[100];
	int count = 0;
	int time = 0;
	int tq = 25;
	int time_exec ;
	int rstart = 0;
	int rend = 0;
	float l,h;
	l=h=0;

	float awaitingTime = 0,averageTurnaroundTime = 0;
	
	for(i=0;i<n;i++)
	{
		q[i] = p[i];
	}
	
	flag = 1;
	cout<<"\t\t\t\t\t\t";
	while(count!=n)
	{		
		
			if(flag == 1)
			{
				for(i=0;i<n;i++)
				{
					if(q[i].arrivalTime>=l && q[i].arrivalTime<=h)
					{
						r[rend] = q[i];
						rend++;
					}
				}
				if(rend!=0)
				{
					flag --;
				}
				else
				{
					l=h;
					h++;
					continue;
				}
			}
		
		cout<<"["<<time<<"]"<<"   "<<r[rstart].processNo<<"   ";
		
		
		
		time_exec = (r[rstart].rt>tq)?tq:r[rstart].rt;
		r[rstart].rt -=time_exec;
		r[rstart].waitingTime += time - r[rstart].ct;
		time +=time_exec;
		r[rstart].ct = time;	
		
	
		l = h+0.001;
		h = h + time_exec; 
		
		for(i=0;i<n;i++)
		{
			if(q[i].arrivalTime>=l && q[i].arrivalTime<=h)
			{
				r[rend] = q[i];
				rend++;
			}
		}
		
		if(r[rstart].rt == 0)
		{
			count++;
			awaitingTime += r[rstart].waitingTime;
			r[rstart].tat = r[rstart].ct - r[rstart].arrivalTime;
			averageTurnaroundTime += r[rstart].tat;
			rstart++;
			continue;
		}
		else
		{
			r[rend] = r[rstart];
			rend++;
			rstart++;
		}
	}
	cout<<"["<<time<<"]";
	
	cout<<"\n\n\t\t\t\t\t\t\tAwaitingTime : "<<(awaitingTime)/n<<"  averageTurnaroundTime : "<<(averageTurnaroundTime)/n<<" "<<endl;
}

void orr()
{
	
	int i,j,flag;
	struct process q[100],r[100];
	int rstart = 0;
	int rend = 0;
	float l,h;
	l=h=0;
	int count = 0;
	int time = 0;
	int tq = 25;
	int time_exec ;
	float averageWaitingTime = 0,averageTurnaroundTime = 0;
	
	
	struct process temp;
	float median;
	int mid;
	int oqt;
	int n_temp = 0;
	int exec_temp = 0;
	
	
	for(i=0;i<n;i++)
	{
		q[i] = p[i];
	}
	
	flag = 1;
	cout<<"\t\t\t\t\t\t";
	while(count!=n)
	{		
			//Appends the processes to the ready queue-->In the beginning
			if(flag == 1)
			{
				for(i=0;i<n;i++)
				{
					if(q[i].arrivalTime>=l && q[i].arrivalTime<=h)
					{
						r[rend] = q[i];
						rend++;
						n_temp++;
					}
				}
				if(rend!=0)
				{
					flag --;
				}
				else
				{
					l=h;
					h++;
					continue;
				}
			}
		
		for(i=rstart;i<rend;i++)
		{
			for(j=rstart;j<rend-1;j++)
			{
				if(r[j].burstTime>r[j+1].burstTime)
				{
					temp = r[j];
					r[j] = r[j+1];
					r[j+1] = temp;
				}
			}
		}
			
		if(exec_temp == 0)
		{
				
				if((rend-rstart)%2 == 0)
				{
					mid = (rend+rstart)/2;
					median = 0.5*(r[mid].burstTime+r[mid-1].burstTime);
				}
				else
				{
				mid = (rend+rstart)/2;
				median = r[mid].burstTime;
				}
			
				
				tq = (median + r[rend -1].burstTime)/2;	
		}	
		
		cout<<"["<<time<<"]"<<"   "<<r[rstart].processNo<<"   ";
		exec_temp = (exec_temp+1)%(n_temp);
		
		
		time_exec = (r[rstart].rt>tq)?tq:r[rstart].rt;
		r[rstart].rt -=time_exec;
		r[rstart].waitingTime += time - r[rstart].ct;
		time +=time_exec;
		r[rstart].ct = time;	


		l = h+0.001;
		h = h + time_exec; 
		

		for(i=0;i<n;i++)
		{
			if(q[i].arrivalTime>=l && q[i].arrivalTime<=h)
			{
				r[rend] = q[i];
				rend++;
			}
		}
		
		if(r[rstart].rt == 0)
		{
			count++;
			averageWaitingTime += r[rstart].waitingTime;
			r[rstart].tat = r[rstart].ct - r[rstart].arrivalTime;
			averageTurnaroundTime += r[rstart].tat;
			rstart++;
			continue;
		}
		else
		{
			r[rend] = r[rstart];
			rend++;
			rstart++;
		}
	}
	cout<<"["<<time<<"]";
	
	cout<<"\n\n\t\t\t\t\t\t\tAverage Waiting Time : "<<(averageWaitingTime)/n<<" Average Turnaround Time : "<<(averageTurnaroundTime)/n<<endl;
}

int main()
{
	 
	input();
	
	cout<<"\n\n\n\n";
	cout<<"\t\t\t\t\t\t\t########################";
	cout<<"\n\t\t\t\t\t\t\t   SIMPLE ROUND ROBIN "; 
	cout<<"\n\t\t\t\t\t\t\t########################";
	cout<<"\n\n\n";
	rr();
	
	
	cout<<"\n\n\n\n";
	cout<<"\t\t\t\t\t\t\t###########################";
	cout<<"\n\t\t\t\t\t\t\t   OPTIMIZED ROUND ROBIN  ";
	cout<<"\n\t\t\t\t\t\t\t###########################";
	cout<<"\n\n\n";
	orr();
	return 0;
}

