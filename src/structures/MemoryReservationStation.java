package structures;

import memory.RegisterFile;
import memory.Cache;

public class MemoryReservationStation {
	
	StationId stationId;
	boolean isBusy;
	Operation operation;
	
	int vRegister;
	int vAddress;
	double cacheValue =0.0;
	int destination;
	StationId qRegister = null;// we always get it from the register because the issuer sets the station id in it
	StationId qAddress = null;
	boolean isReadyToExec;
	boolean startedExecution = false;
	boolean freshData = false;
	int expectedCycleToFinish = 0;
	int timeLeftToFinish = 0;
	int executionTime;
	Cache cache;
	
	public MemoryReservationStation(StationId stationId, int executionTime , Cache cache) {
		
		this.stationId = stationId;
		this.executionTime = executionTime;
		this.startedExecution = false;
		this.isBusy = false;
		this.isReadyToExec = false;
		this.operation = null;
		this.cache = cache;
	}
	
	public boolean isReadyToExec() {
		return isReadyToExec;
	}

	public void setReadyToExec(boolean isReadyToExec) {
		this.isReadyToExec = isReadyToExec;
	}

	public boolean isStartedExecution() {
		return startedExecution;
	}

	public void setStartedExecution(boolean startedExecution) {
		this.startedExecution = startedExecution;
	}

	public void assignUnit(Instruction instruction , RegisterFile registerFile) {
		if(instruction.operation.equals(Operation.LW)) {
			reInitializeStation();
			isBusy = true;
			operation = instruction.operation;
			destination = instruction.destination;
			isReadyToExec = true;
			timeLeftToFinish = executionTime;

			// get dest , get address,
			if(registerFile.isRegisterAvailable(instruction.destination)) {
				vAddress = cache.getData(instruction.address);
				vRegister = instruction.destination;
				startedExecution = true;
				registerFile.setRegUnavailable(instruction.destination, this.stationId);
				
			}
			else {
				this.vAddress = cache.getData(instruction.address); //hena el mafrood tkoon QAddress
				this.qRegister = registerFile.getStationIdFromReg(instruction.destination);
				
				
			}
			
		}else if (instruction.operation.equals(Operation.SW)) {
			
			this.reInitializeStation();
			this.isBusy = true;
			this.operation = instruction.operation;
			if(registerFile.isRegisterAvailable(instruction.destination)) {
				this.vAddress = cache.getData(instruction.address);
				this.vRegister = instruction.destination;
				registerFile.setRegUnavailable(instruction.destination,this.stationId );
				
			}
			else {
				this.vAddress = cache.getData(instruction.address);
				this.qAddress = registerFile.getStationIdFromReg(instruction.address);
				
				
			}
			
			
		}

		
	}
	public int getTimeLeftToFinish() {
		return timeLeftToFinish;
	}

	public void setTimeLeftToFinish(int timeLeftToFinish) {
		this.timeLeftToFinish = timeLeftToFinish;
	}

	public int getvRegister() {
		return vRegister;
	}

	public void setvRegister(int vRegister) {
		this.vRegister = vRegister;
	}

	public int getvAddress() {
		return vAddress;
	}

	public void setvAddress(int vAddress) {
		this.vAddress = vAddress;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	

	public StationId getqRegister() {
		return qRegister;
	}

	public void setqRegister(StationId qRegister) {
		this.qRegister = qRegister;
	}

	public void printStation() {
		System.out.println("-------------------------------------------------------");

		System.out.println(" Printing Station : "+ this.stationId);
		
		System.out.println(" StartedExecution At : "+this.startedExecution+" || "+" Is the Station busy ? : "+this.isBusy+" || "+"Operation : "+this.operation+" || "+" Destination =  "+this.vRegister);

		System.out.println(" Value in Address =  "+ this.vAddress+" || "+" Destination Q =  "+this.qRegister+" || "+" Address q =  "+this.qAddress+" || "+"Destination is" +this.destination+" || "+" This Station Has "+ timeLeftToFinish+" Cycles left");


	}
	public void updateStation(StationId stationId,double value) {
		if(isBusy()) {
			if(!isStartedExecution()) {
				if(getqRegister()==stationId) {
					setqRegister(null);
					setvAddress((int)value);
					if(getqRegister()==null)
					{
						startedExecution = true;
						freshData = true;
					}

				}
				
			}
		}
	}
	
	
	
	
	
	
	
	
	

	public void reInitializeStation() {
		this.startedExecution = false;
		this.isBusy = false;
		this.operation = null;
		vRegister = 0;
		vAddress = 0;
		qRegister = null;
		qAddress = null;
		cacheValue = 0;
		freshData = false;

	}
	public boolean isFree() {
		return (this.isBusy == false);
	}

	public boolean isFreshData() {
		return freshData;
	}

	public void setFreshData(boolean freshData) {
		this.freshData = freshData;
	}
	
	

}
