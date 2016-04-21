public class Calculator{
	private int result;
	
	/**
	   addition 
	   @param params Аргументы суммирования
	*/
	public void add(int... params)
	{
		for(Integer param : params)
		{
			this.result += param;
		}
	}
	/**
		subtraction		
	*/
	public void subtraction(int i, int j)
	{		
		this.result = i-j;		
	}
	/**
		multiply

	*/
	public void multiply(int i, int j)
	{		
		this.result = i*j;	
	}
	/**
		division	
	*/
	public void division(int i, int j)
	{
		this.result = i/j;
	}
	/**
	   Get the result
	   @return result
	*/
	public int getResult()
	{
		return this.result;
	}
	/**
		cleaning the result calculation
	*/
	public void cleanResult()
	{
		this.result =0;
	}
}