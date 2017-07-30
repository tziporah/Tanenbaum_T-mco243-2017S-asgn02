package asgn02;

public interface IProcess {
	
	public int getPID();
	
	public String getProcName();
	
	public int getInstruction();
	
	public void setInstruction(int i);
	
	public ProcessState execute(int i);

}
