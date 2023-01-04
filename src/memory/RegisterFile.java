package memory;

import java.util.ArrayList;

import structures.Register;
import structures.StationId;

public class RegisterFile {
	
	private ArrayList<Register> regList;
	//Register 0 is the PC and we have 16 other multiPurpose Registers
	public RegisterFile() {
		this.regList = new ArrayList<Register>();
		fillRegisters();
		setRegData(2, 1.5);
		setRegData(3, 2.5);
		setRegData(4, 1.5);
		setRegData(5, 2.5);
		setRegData(6, 1.5);
		setRegData(7, 2.5);
		setRegData(8, 2.5);
		setRegData(9, 2.5);

	}


	private void fillRegisters() {
		for (int i = 0; i <= 16; i++) {
			Register reg = new Register(i);
			regList.add(reg);
		}
	}
	public void updateRegisters(StationId stationId, double result) {
		for (int i = 0; i <= 16; i++) {
			if(regList.get(i).getStationId() == stationId)
			{
				setRegStationId(i, null);
				setRegData(i, result);
			}
		}
	}
	public void printRegisterFile() {
		System.out.println("********************************************************************************************");
		System.out.println("Printing Register File ");
		for (int i = 0; i <= 16; i++) {
			System.out.println("--------------");
			System.out.println("F" + regList.get(i).getName()+" Data ="+regList.get(i).getData());
		}
	}

	public void setRegUnavailable(int regNumber, StationId stationId) {	//Like tomasulo 0.00 means that data is unavailable
		regList.get(regNumber).setData(0.00);
		regList.get(regNumber).setStationId(stationId);
	}
	public boolean isRegisterAvailable(int regNumber) {
		if(regList.get(regNumber).getStationId() != null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	///////////////////////////////////////////////
	
	public StationId getStationIdFromReg(int regNumber) {
		return regList.get(regNumber).getStationId();
	}
	public void setRegStationId(int regNumber,StationId stationId) {
		regList.get(regNumber).setStationId(stationId);
	}
	public double getDataFromReg(int regNumber) {
		return regList.get(regNumber).getData();
	}
	public void setRegData(int regNumber,double value) {
		if (regNumber == 0) {
			System.out.println("can't edit PC register ,check your numbers");
		} else {
			regList.get(regNumber).setData(value);
		}
	}
	
}
