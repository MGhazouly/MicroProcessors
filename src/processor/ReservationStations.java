package processor;

import java.util.LinkedList;
import java.util.Queue;

import memory.Cache;
import memory.RegisterFile;
import structures.ALUReservationStation;
import structures.Instruction;
import structures.MemoryReservationStation;
import structures.Operation;
import structures.StationId;

public class ReservationStations {
	ALUReservationStation A1; //ADD AND SUB
	ALUReservationStation A2;
	ALUReservationStation A3;
	ALUReservationStation A4; 
	ALUReservationStation M1; //MUL AND DIV
	ALUReservationStation M2;
	ALUReservationStation M3;
	MemoryReservationStation S1; // SW
	MemoryReservationStation S2;
	MemoryReservationStation L1; //LW
	MemoryReservationStation L2;
	Queue<StationId> execInst;
	RegisterFile registerFile;
	Cache cache;
	
	public ReservationStations(RegisterFile regFile, int addExecutionTime, int mulExecutionTime,int memExecutionTime,Cache cache) {
		this.A1 = new ALUReservationStation(StationId.A1, addExecutionTime);
		this.A2 = new ALUReservationStation(StationId.A2, addExecutionTime);
		this.A3 = new ALUReservationStation(StationId.A3, addExecutionTime);
		this.A4 = new ALUReservationStation(StationId.A4, addExecutionTime);
		this.M1 = new ALUReservationStation(StationId.M1, mulExecutionTime);
		this.M2 = new ALUReservationStation(StationId.M2, mulExecutionTime);
		this.M3 = new ALUReservationStation(StationId.M3, mulExecutionTime);
		this.S1 = new MemoryReservationStation(StationId.S1, memExecutionTime,cache);
		this.S2 = new MemoryReservationStation(StationId.S2, memExecutionTime,cache);
		this.L1 = new MemoryReservationStation(StationId.L1, memExecutionTime,cache);
		this.L2 = new MemoryReservationStation(StationId.L2, memExecutionTime,cache);
		this.cache = cache;
		this.registerFile = regFile;
		execInst = new LinkedList<StationId>();
	}
	public void setExecInst(Queue<StationId> execInst) {
		this.execInst = execInst;
	}
	public void printA1() {
		this.A1.printStation();
	}
	
	public boolean canReserveAdd() {
		return A1.isFree() || A2.isFree() || A3.isFree() || A4.isFree();
	}

	public boolean canReserveMultiplication() {
		return M1.isFree() || M2.isFree() || M3.isFree();
	}
	public boolean canReserveStore() {
		return S1.isFree() || S2.isFree();
	}
	public boolean canReserveLoad() {
		return L1.isFree() || L2.isFree();
	}
	public StationId assignInstruction(Instruction instruction) {
		if(instruction.getOperation().equals(Operation.ADD) || instruction.getOperation().equals(Operation.SUB))
		{
			if(canReserveAdd()){
					if(A1.isFree()){
						A1.assignUnit(instruction, this.registerFile);
						System.out.println("---------------------");
						System.out.println("instruction has been Issued to A1");
						System.out.println("---------------------");
						A1.printStation();
						return StationId.A1;
					}
					else if(A2.isFree()) {
						A2.assignUnit(instruction, this.registerFile);
						System.out.println("---------------------");
						System.out.println("instruction has been Issued to A2");
						System.out.println("---------------------");
						A2.printStation();
						return StationId.A2;
					}
					else if(A3.isFree()) {
						A3.assignUnit(instruction, this.registerFile);
						System.out.println("---------------------");
						System.out.println("instruction has been Issued to A3");
						System.out.println("---------------------");
						A3.printStation();
						return StationId.A3;
					}
					else if(A4.isFree()) {
						A4.assignUnit(instruction, this.registerFile);
						System.out.println("---------------------");
						System.out.println("instruction has been Issued to A4");
						System.out.println("---------------------");
						A4.printStation();
						return StationId.A4;
					}
					else{
						System.out.println("No Available Add Stations, Must wait");
						
					}
			}
		}else if(instruction.getOperation().equals(Operation.MUL) || instruction.getOperation().equals(Operation.DIV)) {
			if(canReserveMultiplication()){
				if(M1.isFree()){
					M1.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("instruction has been Issued to M1");
					System.out.println("---------------------");
					M1.printStation();
					return StationId.M1;
				}
				else if(M2.isFree()) {
					M2.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("instruction has been Issued to M2");
					System.out.println("---------------------");
					M2.printStation();
					return StationId.M2;
				}
				else if(M3.isFree()) {
					M3.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("instruction has been Issued to M3");
					System.out.println("---------------------");
					M3.printStation();
					return StationId.M3;
				}
				else{
					System.out.println("No Available Multiplication Stations, Must wait");
					
				}
			}
		}else if( instruction.getOperation().equals(Operation.SW)) {
			if(canReserveStore()){
				if(S1.isFree()){
					S1.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("Instruction has been Issued to S1");
					System.out.println("---------------------");
					S1.printStation();
					return StationId.S1;
				}
				else if(S2.isFree()) {
					S2.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("Instruction has been Issued to S1");
					System.out.println("---------------------");
					S2.printStation();
					return StationId.S2;
				}
				else{
					System.out.println("No Available Memory Stations, Must wait");
					
				}
			}
		}
		else if(instruction.getOperation().equals(Operation.LW) ) {
			if(canReserveLoad()){
				if(L1.isFree()){
					L1.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("Instruction has been Issued to L1");
					System.out.println("---------------------");
					L1.printStation();
					return StationId.L1;
				}
				else if(L2.isFree()) {
					L2.assignUnit(instruction, this.registerFile);
					System.out.println("---------------------");
					System.out.println("Instruction has been Issued to L2");
					System.out.println("---------------------");
					L2.printStation();
					return StationId.L2;
				}
				else{
					System.out.println("No Available Memory Stations, Must wait");
					
				}
			}
		}
		else {
			System.out.println("You entered an instruction that does not exist and got caught in the stations");
			
		}
		return null;
		
		
		
	}
	public void updateStations(StationId stationId,double value) {
		A1.updateStation(stationId, value);
		A2.updateStation(stationId, value);
		A3.updateStation(stationId, value);
		A4.updateStation(stationId, value);
		M1.updateStation(stationId, value);
		M2.updateStation(stationId, value);
		M3.updateStation(stationId, value);
		L1.updateStation(stationId, value);
		L2.updateStation(stationId, value);
		// lesa mhtagen nktb update lel load wel store
		
	}

	public void execute() {
		int i = 0;
		boolean writeback = false;
		int execInstSize = execInst.size();
		StationId writebackStation = null;
		while(i < execInstSize) {
			StationId x = execInst.poll();

			if(x.equals(StationId.A1)) {
				if(A1.isBusy()) {
					if(A1.isStartedExecution() && ! A1.isFreshData()) { 
						if(A1.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println(" Station A1 is in Execution and will finish in " + A1.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							A1.printStation();
							A1.setTimeLeftToFinish(A1.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.A1,A1.getResult());
								updateStations(StationId.A1, A1.getResult());
								System.out.println("--------------------");
								System.out.println("A1 has published its result:" +  A1.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.A1;
								A1.reInitializeStation();
								A1.printStation();
							}else {
								execInst.add(x);
							}
							
						}
					}else {
						A1.setFreshData(false);
						execInst.add(x);
					}
				}
			}
			
			
			else if(x.equals(StationId.A2)) {
				if(A2.isBusy()) {
					if(A2.isStartedExecution()&& ! A2.isFreshData()) {
						if(A2.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println("Station A2 is in Execution and will finish in " + A2.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							A2.printStation();
							A2.setTimeLeftToFinish(A2.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.A2,A2.getResult());
								updateStations(StationId.A2, A2.getResult());
								System.out.println("--------------------");
								System.out.println("A2 has published its result:" +  A2.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.A2;
								A2.reInitializeStation();
								A2.printStation();
							}else {
								execInst.add(x);
							}
						}
					}else {
						A2.setFreshData(false);
						execInst.add(x);
					}
			
				}
			}
			
			else if(x.equals(StationId.A3)) {
				if(A3.isBusy()) {
					if(A3.isStartedExecution()&&! A3.isFreshData()) {
						if(A3.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println("Station A3 is in Execution and will finish in " + A3.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							A3.printStation();
							A3.setTimeLeftToFinish(A3.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.A3,A3.getResult());
								updateStations(StationId.A3, A3.getResult());
								System.out.println("--------------------");
								System.out.println("A3 has published its result:" +  A3.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.A3;
								A3.reInitializeStation();
								A3.printStation();
							}else {
								execInst.add(x);
							}
						}
					}
					else {
						A3.setFreshData(false);
						execInst.add(x);
					}
		
				}
			}
			else if(x.equals(StationId.A4)) {
				if(A4.isBusy()) {
					if(A4.isStartedExecution()&&! A4.isFreshData()) {
						if(A4.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println("Station A4 is in Execution and will finish in " + A4.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							A1.printStation();
							A4.setTimeLeftToFinish(A4.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.A4,A4.getResult());
								updateStations(StationId.A4, A4.getResult());
								System.out.println("--------------------");
								System.out.println("A4 has published its result:" +  A4.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.A4;
								A4.reInitializeStation();
								A4.printStation();
							}else {
								execInst.add(x);
							}
						}
					}else {
						A4.setFreshData(false);
						execInst.add(x);
					}
				}
			}
			
			
		
		if(x.equals(StationId.M1)) {
			if(M1.isBusy()) {
				if(M1.isStartedExecution()&&! M1.isFreshData()) {
					if(M1.getTimeLeftToFinish() != 0) {
						System.out.println("--------------------");
						System.out.println("Station M1 is in Execution and will finish in " + M1.getTimeLeftToFinish() + " Cycles");
						System.out.println("--------------------");
						M1.printStation();
						M1.setTimeLeftToFinish(M1.getTimeLeftToFinish() - 1);
						execInst.add(x);
						
//						M1.printStation();
						
					}
					else {
						if(!writeback) {
							registerFile.updateRegisters(StationId.M1,M1.getResult());
							updateStations(StationId.M1, M1.getResult());
							System.out.println("--------------------");
							System.out.println("M1 has published its result:" +  M1.getResult() + "to the bus" );
							System.out.println("--------------------");
							writeback = true;
							writebackStation = StationId.M1;
							M1.reInitializeStation();
							M1.printStation();
						}else {
							execInst.add(x);
						}
					}
				}else {
					M1.setFreshData(false);
					execInst.add(x);
				}
				
			}
		}
		else if(x.equals(StationId.M2)) {
				if(M2.isBusy()) {
					if(M2.isStartedExecution()&&! M2.isFreshData()) {
						if(M2.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println(" Station M2 is in Execution and will finish in " + M2.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							M2.printStation();
							M2.setTimeLeftToFinish(M2.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.M2,M2.getResult());
								updateStations(StationId.M2, M2.getResult());
								System.out.println("--------------------");
								System.out.println("M2 has published its result:" +  A2.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.M2;
								M2.reInitializeStation();
								M2.printStation();
							}else {
								execInst.add(x);
							}
						}
					}else {
						M2.setFreshData(false);
						execInst.add(x);
					}
				}
			}
		else if(x.equals(StationId.M3)) {
				if(M3.isBusy()) {
					if(M3.isStartedExecution()&&! M3.isFreshData()) {
						if(M3.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println(" Station M3 is in Execution and will finish in " + M3.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							M3.setTimeLeftToFinish(M3.getTimeLeftToFinish() - 1);
							M3.printStation();
							execInst.add(x);
							
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.M3,M3.getResult());
								updateStations(StationId.M3, M3.getResult());
								System.out.println("--------------------");
								System.out.println("M3 has published its result:" +  M3.getResult() + "to the bus" );
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.M3;
								M3.reInitializeStation();
							}else {
								execInst.add(x);
							}
						}
					}else {
						M3.setFreshData(false);
						execInst.add(x);
					}
			
				}
			}
		else if(x.equals(StationId.L1)) {
		
			if(L1.isBusy()) {
				if(L1.isStartedExecution()&&! L1.isFreshData()) {
					if(L1.getTimeLeftToFinish() != 0) {
						System.out.println("--------------------");
						System.out.println(" Station L1 is in Execution and will finish in " + L1.getTimeLeftToFinish() + " Cycles");
						System.out.println("--------------------");
						L1.printStation();
						L1.setTimeLeftToFinish(L1.getTimeLeftToFinish() - 1);
						execInst.add(x);
					}
					else {
						if(!writeback) {
							registerFile.updateRegisters(StationId.L1, L1.getvAddress());
							updateStations(StationId.L1, L1.getvAddress());
							registerFile.setRegData(L1.getvRegister(),L1.getvAddress());
							System.out.println("--------------------");
							System.out.println("L1 has loaded in register:" + L1.getvRegister() + "value" + L1.getvAddress());
							System.out.println("--------------------");
							writeback = true;
							writebackStation = StationId.L1;
							L1.reInitializeStation();
							L1.printStation();
						}else {
							L1.setFreshData(false);
							execInst.add(x);
						}
					}
				}
			}
		}else if(x.equals(StationId.L2)) {
			
			
				if(L2.isBusy()) {
					if(L2.isStartedExecution()&&! L2.isFreshData()) {
						if(L2.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println(" Station L2 is in Execution and will finish in " + L2.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							L2.printStation();
							L2.setTimeLeftToFinish(L2.getTimeLeftToFinish() - 1);
							execInst.add(x);
							
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.L2, L2.getvAddress());
								updateStations(StationId.L2, L2.getvAddress());
								registerFile.setRegData(L2.getvRegister(),L2.getvAddress());
								System.out.println("--------------------");
								System.out.println("L2 has loaded in register:" + L2.getvRegister() + "value" + L2.getvAddress());
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.L2;
								L2.reInitializeStation();
								L2.printStation();
							}else {
								L2.setFreshData(false);
								execInst.add(x);
							}
						}
						
						}
				}
		}
		if(x.equals(StationId.S1)) {
		
			if(S1.isBusy()) {
				if(S1.isStartedExecution()&&! S1.isFreshData()) {
					if(S1.getTimeLeftToFinish() != 0) {
						System.out.println("--------------------");
						System.out.println(" Station S1 is in Execution and will finish in " + S1.getTimeLeftToFinish() + " Cycles");
						System.out.println("--------------------");
						S1.printStation();
						S1.setTimeLeftToFinish(S1.getTimeLeftToFinish() - 1);
						execInst.add(x);
					}
					else {
						if(!writeback) {
							registerFile.updateRegisters(StationId.S1, S1.getvAddress());
							updateStations(StationId.S1, S1.getvAddress());
							int address = S1.getvAddress();
							int value = (int) registerFile.getDataFromReg(S1.getvRegister());
							cache.setData(value, address);
							System.out.println("--------------------");
							System.out.println("S1 has stored value: " + address + "in mem location:" + value);
							System.out.println("--------------------");
							writeback = true;
							writebackStation = StationId.S1;
							S1.reInitializeStation();
							S1.printStation();
						}
					}
					
					
					
				}
				else {
					S1.setFreshData(false);
				}
			}
		}else if(x.equals(StationId.S2)) {
				if(S2.isBusy()) {
					if(S2.isStartedExecution()&&! S2.isFreshData()) {
						if(S2.getTimeLeftToFinish() != 0) {
							System.out.println("--------------------");
							System.out.println(" Station S2 is in Execution and will finish in " + S2.getTimeLeftToFinish() + " Cycles");
							System.out.println("--------------------");
							S2.printStation();
							S2.setTimeLeftToFinish(S2.getTimeLeftToFinish() - 1);
							execInst.add(x);
						}
						else {
							if(!writeback) {
								registerFile.updateRegisters(StationId.S2, S2.getvAddress());
								updateStations(StationId.S2, S2.getvAddress());
								int address = S2.getvAddress();
								int value = (int) registerFile.getDataFromReg(S2.getvRegister());
								cache.setData(value, address);
								System.out.println("--------------------");
								System.out.println("S2 has stored value: " + value + "in mem location:" + address);
								System.out.println("--------------------");
								writeback = true;
								writebackStation = StationId.S2;
								S2.reInitializeStation();
								S2.printStation();
							}
						}
					
					}
					else {
						S2.setFreshData(false);
					}
				}
		}
		i++;
	}
		
	}
	public Queue<StationId> getExecInst() {
		return execInst;
	}
	
}
