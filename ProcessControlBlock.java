package asgn02;

public class ProcessControlBlock {
	
	private IProcess proc;
	//private int currInstruction;
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;
	
	public ProcessControlBlock(IProcess proc)
	{
		this.proc = proc;
	}
	
	public IProcess getProc()
	{
		return this.proc;
	}
	
	public int getCurrInstruction()
	{
		return this.proc.getInstruction();
	}
	
	public void setCurrInstruction(int val)
	{
		this.proc.setInstruction(val);
	}
	
	public int get1()
	{
		return reg1;
	}
	
	public int get2()
	{
		return reg2;
	}
	
	public int get3()
	{
		return reg3;
	}
	
	public int get4()
	{
		return reg4;
	}
	
	public void set1(int i)
	{ 
		reg1 = i;
	}
	
	public void set2(int i)
	{
		reg2 = i;
	}
	
	public void set3(int i)
	{
		reg3 = i;
	}
	
	public void set4(int i)
	{
		reg4 = i;
	}

}
