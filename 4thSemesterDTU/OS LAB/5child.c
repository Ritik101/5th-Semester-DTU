#include <stdio.h>
#include <unistd.h>
#include<stdlib.h>

int main()
{
    int p_id,p_pid;
     
    
     
    for (int id=0; id<5; id++) {
        
        if (fork()==0) {
            p_id=getpid();  /*process id*/
            p_pid=getpid(); /*parent process id*/
            printf("Son Process ID: %d and Parent Process ID : %d\n",p_id,p_pid);
    
            exit(0);      
        }
    }
    for (int i = 0; i < 5; i++)
    {
        wait(NULL);
    }
    printf("Parent ID : %d ", p_pid);
    return 0;
}