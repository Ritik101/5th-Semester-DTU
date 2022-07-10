public class alu_12
{
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//	alu_12();
				public final int add()
				{
				 get_first_operand(); // copy into operand1[12] the data from the appropriate
									   /* register -- namely mult_unit_3.multiplicand */
									   /* NOTE: if you do not know how to give get_first_operand()
									   access to mult_unit_3.multiplicand.store, you can make
									   the latter a public class member (rather than private) */
				 get_second_operand();
				 //implement the binary addition operand1 + operand2 ;
				 write_result(); // copy the result into the appropriate positions of
									  /* mult_unit_3.product */
				}

//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//				int sub(); // returns bit-13 of the result
	  private bit[] operand1 = new bit[12];
				private bit[] operand2 = new bit[12];
				private bit[] result = new bit[13];
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//				void get_first_operand();
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//				void get_second_operand();
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//				void write_result();
}