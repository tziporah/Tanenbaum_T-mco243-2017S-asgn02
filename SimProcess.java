package asgn02;

public class SimProcess implements IProcess{
	
	private IRandomValueGenerator instance;
	private int pid;
	private String procName;
	private int totalInstructions;
	private int currInstruction;
	
	public SimProcess(IRandomValueGenerator random, int pid, String procName, int totalInstructions)
	{
		this.instance = random;
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
		this.currInstruction = 1;
	}
	
	public int getPID()
	{
		return this.pid;
	}
	
	public String getProcName()
	{
		return this.procName;
	}
	
	public int getInstruction()
	{
		return this.currInstruction;
	}
	
	public void setInstruction(int i)
	{
		this.currInstruction = i;
	}
	
	public ProcessState execute(int i)
	{
		System.out.println(" Proc " + this.procName + ", PID: " + this.pid + " executing instruction: " + i);
		currInstruction++;
		
		if (i >= this.totalInstructions)
		{
			return ProcessState.FINISHED;
		}
		else
		{
			if(instance.getTrueWithProbability(.15))
			{
				return ProcessState.BLOCKED;
			}
			else
			{
				return ProcessState.READY;
			}
		}
	}

}
