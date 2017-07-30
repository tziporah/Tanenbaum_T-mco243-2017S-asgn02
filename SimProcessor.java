package asgn02;

public class SimProcessor implements IProcessor{
	
	private IRandomValueGenerator random;
	private IProcess currentProc;
	private int[] registers;
	private int currInstruction;
	
	
	public SimProcessor()
	{
		this.random = new RandomValueGenerator();
		registers = new int[4];
		for (int i = 0; i < 4; i++)
		{
			setRegisterValue(i, random.getNextInt());
		}
	}
	
	public IProcess getProcess()
	{
		return this.currentProc;
	}
	
	public void setProcess(SimProcess proc)
	{
		this.currentProc = proc;
		this.currInstruction = proc.getInstruction();
	}
	
	
	public void setRegisterValue(int i, int val)
	{
		this.registers[i] = val;
	}
	
	public int getRegisterValue(int i)
	{
		return random.getNextInt();
	}
	
	public int getInstruction()
	{
		return this.currentProc.getInstruction();
	}
	
	public void setInstruction(int i)
	{
		this.currentProc.setInstruction(i);
	}
	
	public ProcessState executeNextInstruction()
	{
		ProcessState state = currentProc.execute(currInstruction);
		this.currInstruction = currentProc.getInstruction();
		return state;
	}

}
