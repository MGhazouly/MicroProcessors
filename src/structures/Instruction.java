package structures;

public class Instruction {
	
	Operation operation;
	int operand1;
	int operand2;
	int destination;
	int address = -10;//-10 means that it still not have address // used for lw and sw
	
	
	
	
	
	
	
	
	

	
	
	
	
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
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
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public void printInstruction() {
		System.out.println("----");
		System.out.println("Instruction Operation :  "+this.getOperation().toString());
		System.out.println("Instruction Operand1 :  "+this.getOperand1());
		System.out.println("Instruction Operand2 :  "+this.getOperand2());
		System.out.println("Instruction Address :  "+this.getAddress());
		System.out.println("Instruction Destination Register :  "+this.getDestination());
	}
	
	
}
