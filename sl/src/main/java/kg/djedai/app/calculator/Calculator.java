package kg.djedai.app.calculator;

/**
 *
 *
 */
public class Calculator{
	/**
	*
	*/
	private int result;
	
	/**
	 addition objects 
	 @param params
	 */
	public void add(int... params)
	{
		for(Integer param : params)
		{
			this.result += param;
		}
	}
	/**
	 * subtraction objects	
	 * @param first,second
	 */
	public void subtraction(int first, int second)
	{		
		this.result = first-second;		
	}
	/**
	 * multiply objects
	 * @param args  income arguments
	 */
	public void multiply(int... args)
	{	
		for(Integer param : args)
			this.result *= param;	
	}
	/**
	 * division objects
	 * @param args income arguments
	 * @throws UserException throw, if no args or division by 0
	 */
	public void division(int... args) throws UserException{
		if(args.length > 0)
		{
			this.result = args[0];
			for(int index=1;index!=args.length;++index)
			{
				if(args[index] != 0)
				{
					this.result /= args[index];
				} else	{
					throw new UserException("Error, you try to divide by 0");
				}
			}			
		} else {
			throw new UserException("Error, You should enter at least two args!");
		}
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
	 Cleaning the result of calculation
	 */
	public void cleanResult()
	{
		this.result =0;
	}
}
