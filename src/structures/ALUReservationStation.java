package structures;

import memory.RegisterFile;

public class ALUReservationStation {
	StationId stationId;
	boolean isBusy;
	Operation operation = null;
	double vj = 0;
	double vk = 0;
	StationId qj = null;// we always get it from the register because the issuer sets the station id in it
	StationId qk = null;
	int operand1 = 0;
	int operand2 = 0;
	int destination = 0;
	boolean startedExecution = false;
	boolean freshData = false;
	int timeLeftToFinish = 0;
	int executionTime;
	double result = 0.0;
	public int getExecutionTime() {
		return executionTime;
	}

	
	public ALUReservationStation(StationId stationId,int executionTime) {
		this.stationId = stationId;
		this.isBusy = false;
		this.startedExecution = false;
		this.executionTime = executionTime;
	}
	public boolean isStartedExecution() {
		return startedExecution;
	}
	public void setStartedExecution(boolean startedExecution) {
		this.startedExecution = startedExecution;
	}
	public void assignUnit(Instruction instruction, RegisterFile registerFile) {
		reInitializeStation();
		isBusy = true;
		operation = instruction.operation;
		operand1 = instruction.operand1;
		operand2 = instruction.operand2;
		destination = instruction.destination;
		timeLeftToFinish = executionTime;
		
		
		if(registerFile.isRegisterAvailable(operand1)) {
			this.vj = registerFile.getDataFromReg(operand1);
		}
		else {
			// reg is unavailable
			this.qj = registerFile.getStationIdFromReg(operand1);
		}
		
		if(registerFile.isRegisterAvailable(operand2)) {
			this.vk = registerFile.getDataFromReg(operand2);
		}
		else {
			this.qk = registerFile.getStationIdFromReg(operand2);
		}
		if(qj == null && qk == null) {
			startedExecution = true;
			
		}
		registerFile.setRegUnavailable(instruction.destination,this.stationId);
		calculateResult();
		
		
		
	}
	public void calculateResult() {
		//ONLY CALL BEFORE CHECKING THAT VJ AND VK ARE READY
		switch(operation) {
		case ADD:
			result = vj+vk;
			break;
		case SUB:
			result = vj-vk;
			break;
		case DIV:
			result = vj/vk;
			break;
		case MUL:
			result = vj*vk;
			break;
		default:
		    System.out.println("You Tried to caluclate an invalid Alu operation please enter correct operations");
	}
	}
	public void updateStation(StationId stationId,double value) {
		if(isBusy()) {
			if(!isStartedExecution()) {
				if(getQj()==stationId) {
					setQj(null);
					setVj(value);
					if(getQk()==null) {
						startedExecution = true;
						freshData = true;
					}

				}
				else {
					if(getQk()==stationId) {
						setQk(null);
						setVk(value);
						if(getQj()==null) {
							startedExecution = true;
							freshData = true;						
						}

					}
				}
			}
		}
	}
	
	

	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public void reInitializeStation() {
		this.startedExecution = false;
		this.isBusy = false;
		this.operation = null;
		this.vj = 0;
		this.vk = 0;
		this.qj = null;
		this.qk = null;
		this.operand1 = 0;
		this.operand2 = 0;
		this.destination = 0;
		freshData = false;
	}
	
	public void printStation() {
		System.out.println("-------------------------------------------------------");
		System.out.println(" Printing Station : "+  stationId);
		System.out.println(" Is the Station busy ? : "+ isBusy+" || "+"Operation : "+ operation+" || "+" Vj =  "+ vj+" || "+" Vk =  "+ vk+" || "+" Qj =  "+ qj+" || "+" Qk =  "+ qk);
		System.out.println(" Operand 1 is : "+ operand1+" || "+"Operand 2 is  "+ operand2+" || "+"Destination is  "+ destination+" || "+" Awaited Result is   " + result);

		System.out.println(" This Station Has "+ timeLeftToFinish+" Cycles left");
		


	}
	
	public int getOperand1() {
		return operand1;
	}
	public void setOperand1(int operand1) {
		this.operand1 = operand1;
	}
	public int getOperand2() {
		return operand2;
	}
	public void setOperand2(int operand2) {
		this.operand2 = operand2;
	}
	public boolean isFree() {
		return (this.isBusy == false);
	}
	public int getTimeLeftToFinish() {
		return timeLeftToFinish;
	}
	public void setTimeLeftToFinish(int timeLeftToFinish) {
		this.timeLeftToFinish = timeLeftToFinish;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}


	public StationId getStationId() {
		return stationId;
	}


	public void setStationId(StationId stationId) {
		this.stationId = stationId;
	}


	public double getVj() {
		return vj;
	}


	public void setVj(double vj) {
		this.vj = vj;
	}


	public double getVk() {
		return vk;
	}


	public void setVk(double vk) {
		this.vk = vk;
	}


	public StationId getQj() {
		return qj;
	}


	public void setQj(StationId qj) {
		this.qj = qj;
	}


	public StationId getQk() {
		return qk;
	}


	public void setQk(StationId qk) {
		this.qk = qk;
	}


	public boolean isFreshData() {
		return freshData;
	}


	public void setFreshData(boolean freshData) {
		this.freshData = freshData;
	}
	
	
	
}
