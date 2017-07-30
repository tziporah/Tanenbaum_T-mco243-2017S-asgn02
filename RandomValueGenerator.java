package asgn02;
import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{
	
	private Random random;
	
	public RandomValueGenerator()
	{
		random = new Random(System.currentTimeMillis());
	}
	
	public int getNextInt()
	{
		return random.nextInt();
	}
	
	public Boolean getTrueWithProbability(Double p)
	{
		if (random.nextInt(100) <= p*10)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
