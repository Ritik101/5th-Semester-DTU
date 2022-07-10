/* Your program should constructed in a way similar to the following object
   oriented structure */

public enum bit
{
	F,
	T;

	public static final int SIZE = java.lang.Integer.SIZE;

	public int getValue()
	{
		return this.ordinal();
	}

	public static bit forValue(int value)
	{
		return values()[value];
	}
}