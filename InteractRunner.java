import java.util.Scanner;
/**

 */
public class InteractRunner {
    public static void main (String[] arg)
    {
        Scanner reader = new Scanner(System.in);
        try
        {
            Calculator calc = new Calculator();
            String exit = "n";

            while(!exit.equals("y"))
            {
                String first = "";
                String second = "";
                String answer = "";
                if(calc.getResult()!=0)
                {
					System.out.println("Hey, again let's Calculate ;)");
                    System.out.println("Do you want to use the saved result as first or second: 1/2 ");
                    answer= reader.next();
                    if(answer.equals("1"))
                    {
						first = ""+calc.getResult();
                        System.out.println("Enter second arg : ");
                        second = reader.next();
                    }
					
                    if(answer.equals("2"))
                    {
						second=""+calc.getResult();
                        System.out.println("Enter first arg : ");
                        first = reader.next();
                    }
                }
                else
                {
					System.out.println("Hey, let's Calculate ;)");
                    System.out.println("Enter first arg : ");
                    first = reader.next();
                    System.out.println("Enter second arg : ");
                    second = reader.next();
                }
				

                System.out.println("Choose function?: * | / | + | - ");
                String functionality = reader.next();
                
                switch(functionality){
                    case("+"):
                        calc.add(Integer.valueOf(first),Integer.valueOf(second));
                        break;
                    case("-"):
                        calc.subtraction(Integer.valueOf(first),Integer.valueOf(second));
                        break;
                    case("*"):
                        calc.multiply(Integer.valueOf(first),Integer.valueOf(second));
                        break;
                    case("/"):
                        calc.division(Integer.valueOf(first),Integer.valueOf(second));
                        break;
                }

                System.out.println("Result: "+ calc.getResult());
                System.out.println("Do you want to save the result?: y/n");
                answer = reader.next();
                if(!answer.equals("y"))
                    calc.cleanResult();

                System.out.println("Exit : y/n ");
                exit = reader.next();
            }
			System.out.println("Chao-Chao, if you need some help, you know where I am )) :)");

        }finally {
            reader.close();
        }
    }
}