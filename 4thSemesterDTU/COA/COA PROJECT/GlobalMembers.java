//====================================================================================================
//The Free Edition of C++ to Java Converter limits conversion output to 100 lines per file.

//To purchase the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-cplus-to-java.html
//====================================================================================================

public class GlobalMembers
{
	public static void add(int[] ac, int[] x, int q)
	{
	   int i;
	   int c = 0;
	   for (i = 0; i < q; i++)
	   {
		  ac[i] = ac[i] + x[i] + c;
		  if (ac[i] > 1)
		  {
			 ac[i] = ac[i] % 2;
			 c = 1;
		  }
		  else
		  {
			 c = 0;
		  }
	   }
	}

	public static void complement(int[] a, int n)
	{
	   int i;
	   int[] x = {null, 0, 0, 0, 0, 0, 0, 0};
	   x[0] = 1;
	   for (i = 0; i < n; i++)
	   {
		  a[i] = (a[i] + 1) % 2;
	   }
	   add(a, x, n);
	}

	   public static void ashr(int[] ac, int[] qr, tangible.RefObject<Integer> qn, int q)
	   {
		  int temp;
		  int i;
		  temp = ac[0];
		  qn.argValue = qr[0];
		  System.out.print("\t\tashr\t\t");
		  for (i = 0; i < q - 1; i++)
		  {
			 ac[i] = ac[i + 1];
			 qr[i] = qr[i + 1];
		  }
		  qr[q - 1] = temp;
	   }

	   public static void display(int[] ac, int[] qr, int qrn)
	   {
		  int i;
		  for (i = qrn - 1; i >= 0; i--)
		  {
			 System.out.print(ac[i]);
		  }
		  System.out.print(" ");
		  for (i = qrn - 1; i >= 0; i--)
		  {
			 System.out.print(qr[i]);
		  }
	   }

	   public static void main(String[] args)
	   {
		  int[] mt = new int[10];
		  int[] br = new int[10];
		  int[] qr = new int[10];
		  int sc;
		  int[] ac = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		  int brn;
		  int qrn;
		  int i;
		  int qn;
		  int temp;
		  System.out.print("------------------------------------------------------------------------------------------------------");
		  System.out.print("\n/t/t/t--Enter the multiplicand and multipier in signed 2's complement form if negative--");
		  System.out.print("------------------------------------------------------------------------------------------------------");
		  System.out.print("\n Number of multiplicand bit =  ");
		  brn = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
		  System.out.print("\nmultiplicand =       ");

		  for (i = brn - 1; i >= 0; i--)
		  {
			 br[i] = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
		  }

		  for (i = brn - 1; i >= 0; i--)
		  {
			 mt[i] = br[i];
		  }
		  complement(mt, brn);
		  System.out.print("\nNo. of multiplier bit = ");
		  qrn = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
		  sc = qrn;
		  System.out.print("Multiplier = ");

		  for (i = qrn - 1; i >= 0; i--)
		  {
			 qr[i] = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));
		  }
			 qn = 0;
			 temp = 0;
			 System.out.print(" qn\tq[n+1]\t\tBR\t\tAC\tQR\t\tsc\n ");
			 System.out.print("\t\t\tinitial\t\t");
			 display(ac, qr, qrn);
			 System.out.print("\t\t");
			 System.out.print(sc);
			 System.out.print("\n");

			 while (sc != 0)
			 {
				System.out.print(qr[0]);
				System.out.print("\t");
				System.out.print(qn);
				if ((qn + qr[0]) == 1)
				{
				   if (temp == 0)
				   {
					  add(ac, mt, qrn);
					  System.out.print("\t\tsubtracting BR\t");
					  for (i = qrn - 1; i >= 0; i--)
					  {
						 System.out.print(ac[i]);
					  }
					  temp = 1;
				   }

				else if (temp == 1)
				{
				   add(ac, br, qrn);
				   System.out.print("\t\tadding BR\t");
				   for (i = qrn - 1; i >= 0; i--)
				   {
					  System.out.print(ac[i]);
				   }
					  temp = 0;
				}

				System.out.print("\n\t");
			tangible.RefObject<Integer> tempRef_qn = new tangible.RefObject<Integer>(qn);
				ashr(ac, qr, tempRef_qn, qrn);
				qn = tempRef_qn.argValue;
				}

			 else if (qn - qr[0] == 0)
			 {
			tangible.RefObject<Integer> tempRef_qn2 = new tangible.RefObject<Integer>(qn);
				ashr(ac, qr, tempRef_qn2, qrn);
				qn = tempRef_qn2.argValue;
			 }
				display(ac, qr, qrn);
				System.out.print("\t");
				sc--;
				System.out.print("\t");

//====================================================================================================
//End of the allowed output for the Free Edition of C++ to Java Converter.

//To purchase the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-cplus-to-java.html
//====================================================================================================
