package asgn02;

import java.util.ArrayList;

public class Program {
	
	public static void main(String[] args)
	{
		IRandomValueGenerator random = new RandomValueGenerator();
		
		IProcessor processor = new SimProcessor();
		
		ArrayList<ProcessControlBlock> ready = new ArrayList<ProcessControlBlock>();
		ArrayList<ProcessControlBlock> blocked = new ArrayList<ProcessControlBlock>();
		
		int pid = 0;
		for (int i = 0; i < 10; i++)
		{
			String procName = "proc" + ++pid;
			int totalInstructions = random.getNextInt() % 300 + 100;
			if (totalInstructions <= 0)
			{
				totalInstructions *= -1;
				totalInstructions = totalInstructions % 300 + 100;
			}
			ProcessControlBlock pcb = new ProcessControlBlock(new SimProcess(random, pid, procName, totalInstructions));
			ready.add(pcb);
		}
		
		final int QUANTUM = 5;
		
		boolean contextSwitch = false;
		int quantum = 0;
		
		ProcessControlBlock current = ready.get(0);
		processor.setProcess((SimProcess)current.getProc());
		
		ProcessState state = null;
		
		for (int i = 0; i < 1000; i++)
		//for (int i = 0; i < 3000; i++)
		{
			System.out.print("Step " + i);
			current = ready.get(0);
			
			if (!contextSwitch)
			{
				//increment quantum
				if (quantum <= QUANTUM)
				{
					quantum++;
				}
				
				//execute current's process
				state = processor.executeNextInstruction();
				if (state == ProcessState.FINISHED)
				{
					contextSwitch = true;
					System.out.println("***Process Completed***");
				}
				else if (state == ProcessState.BLOCKED)
				{
					contextSwitch = true;
					System.out.println("***Process Blocked***");
				}
				else if(quantum == QUANTUM)
				{
					contextSwitch = true;
					quantum = 0;
					System.out.println("***Quantum Expired***");
				}
			}
			else
			{
				contextSwitch = false;
				quantum = 0;
				//contextSwitch(current);
				System.out.println(" Context Switch: saving process " + current.getProc().getPID());
				System.out.println("\tInstruction: " + current.getCurrInstruction() + " R1 " + processor.getRegisterValue(1) + " R2: " +
						processor.getRegisterValue(1) + " R3 " + processor.getRegisterValue(2) + " R4 " 
						+ processor.getRegisterValue(3));
				
				//update pcb
				current.setCurrInstruction(current.getProc().getInstruction());
				current.set1(processor.getRegisterValue(1));
				current.set2(processor.getRegisterValue(2));
				current.set3(processor.getRegisterValue(3));
				current.set4(processor.getRegisterValue(4));
				
				//remove currProc from processor
				if (state == ProcessState.FINISHED)
				{
					ready.remove(0);
				}
				if (state == ProcessState.BLOCKED)
				{
					blocked.add(ready.get(0));
					ready.remove(0);
				}
				else //process ran for a full quantum
				{
					ready.add(ready.get(0));
					ready.remove(0);
				}
				
				
				//set next pcb as currproc on processor & update processor
				processor.setProcess((SimProcess)ready.get(0).getProc());
				processor.setRegisterValue(0, processor.getRegisterValue(0));
				processor.setRegisterValue(1, processor.getRegisterValue(1));
				processor.setRegisterValue(2, processor.getRegisterValue(1));
				processor.setRegisterValue(3, processor.getRegisterValue(2));
				processor.setInstruction(ready.get(0).getCurrInstruction());
				
				current = ready.get(0);
				
				System.out.println(" Context Switch: restoring process " + current.getProc().getPID());
				System.out.println("\tInstruction " + current.getProc().getInstruction() + " R1: " + current.get1() +
						" R2: " + current.get2() + " R3: " + current.get3() + " R4 " + current.get4());
				
			}
			//loop through blocked processes and wake up each one with 30% probability
			for (int j = 0; j < blocked.size(); j++)
			{
				if (random.getTrueWithProbability(3.0))
				{
					ready.add(blocked.get(j));
					blocked.remove(j);
				}
			}
		}
		
		
	}//end main
	
//	public static void contextSwitch(ProcessControlBlock pcb, ArrayList<ProcessControlBlock>)
//	{
//		//update pcb with values of all registers and currinstruction
//		pcb.setCurrInstruction(pcb.getProc().getInstruction());
//		pcb.set1(pcb.get1());
//		pcb.set2(pcb.get2());
//		pcb.set3(pcb.get3());
//		pcb.set4(pcb.get4());
//		
//	}
	

}//end class
