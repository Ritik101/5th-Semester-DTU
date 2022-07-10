#include<iostream>
using namespace std;
void add(int m1[], int m2[], int m3);
void complement(int m1[], int a1) {
   int i;
   int m2[8] = {0};
   m2[0] = 1;
   for (i = 0; i < a1; i++) {
      m1[i] = (m1[i] + 1) % 2;
   }
   add(m1, m2, a1);
}
void add(int z1[], int m2[], int m3) {
   int i, v1 = 0;
   for (i = 0; i < m3; i++) {
      z1[i] = z1[i] + m2[i] + v1;
      if (z1[i] > 1) {
         z1[i] = z1[i] % 2;
         v1 = 1;
      }else
         v1 = 0;
      }
   }
   void ashr(int z1[], int z2[], int &z3, int m3) {
      int temp, i;
      temp = z1[0];
      z3 = z2[0];
      cout << "\t\tashr\t\t";
      for (i = 0; i < m3 - 1; i++) {
         z1[i] = z1[i + 1];
         z2[i] = z2[i + 1];
      }
      z2[m3 - 1] = temp;
   }
   void display(int z1[], int z2[], int v3) {
      int i;
      for (i = v3 - 1; i >= 0; i--)
         cout << z1[i];
      cout << " ";
      for (i = v3 - 1; i >= 0; i--)
         cout << z2[i];
   }
   int main(int argc, char **argv) {
      int f1[10], ff[10], z2[10], yz, z1[10] = { 0 };
      int o, v3, i, z3, temp;
      cout<<"\n\n\t---------------------------------------------------------------------------------";
      cout << "\n\t   Enter the multiplicand and multipier in signed 2's complement form if negative";
      cout<<"\n\t   --------------------------------------------------------------------------------";
	  cout << "\n\n\t\t Number of multiplicand bit = ";
      cin >> o;
      cout << "\n\t\t multiplicand = ";
      for (i = o - 1; i >= 0; i--)
         cin >> ff[i]; //multiplicand
      for (i = o - 1; i >= 0; i--)
         f1[i] = ff[i];
      complement(f1, o);
      cout << "\n\t\t No. of multiplier bit = ";
      cin >> v3;
      yz = v3;
      cout << "\n\t\t Multiplier = ";
      for (i = v3 - 1; i >= 0; i--)
         cin >> z2[i];
         z3 = 0;
         temp = 0;
         cout << "\nqn\tq[n+1]\t\tBR\t\tAC\tQR\t\tsc\n";
         cout << "\t\t\tinitial\t\t";
         display(z1, z2, v3);
         cout << "\t\t" << yz << "\n";
         while (yz != 0) {
            cout << z2[0] << "\t" << z3;
            if ((z3 + z2[0]) == 1) {
               if (temp == 0) {
                  add(z1, f1, v3);
                  cout << "\t\tsubtracting BR\t";
                  for (i = v3 - 1; i >= 0; i--)
                     cout << z1[i];
                  temp = 1;
               }
            else if (temp == 1) {
               add(z1, ff, v3);
               cout << "\t\tadding BR\t";
               for (i = v3 - 1; i >= 0; i--)
                  cout << z1[i];
                  temp = 0;
            }
            cout << "\n\t";
            ashr(z1, z2, z3, v3);
         }
         else if (z3 - z2[0] == 0)
            ashr(z1, z2, z3, v3);
            display(z1, z2, v3);
            cout << "\t";
            yz--;
            cout << "\t" << yz << "\n";
   }
   cout << "\n\t\t Result =  ";
   display(z1, z2, v3);
   }

