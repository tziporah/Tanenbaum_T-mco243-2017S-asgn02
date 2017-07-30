package asgn02;

public interface IProcessor {
	
	public IProcess getProcess();
	
	public void setProcess(SimProcess proc);
	
	public int getInstruction();
	
	public void setInstruction(int i);
	
	public ProcessState executeNextInstruction();
	
	public void setRegisterValue(int i, int val);
	
	public int getRegisterValue(int i);

}
