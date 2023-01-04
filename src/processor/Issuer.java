package processor;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import structures.Operation;
import structures.StationId;
import memory.Cache;
import memory.RegisterFile;
import structures.Instruction;

public class Issuer {
	FileEncoder fileEncoder;
	RegisterFile registerFile;
	Queue<Instruction> fetchedInstructionQueue;
	Queue<StationId> execInst = new LinkedList<StationId>();
	ArrayList<Instruction> Instructions;
	ReservationStations reservationStations;
	Cache cache;
	int addExecutionTime = 2;
	int mulExecutionTime = 10;
	int memExecutionTime = 2;
	String fileName = "test.txt";
	int cycleNumber = 1;
	int maxCycleNumber = 31;
	
	int pc = 0;

	public Issuer() {
		this.fetchedInstructionQueue= new LinkedList<Instruction>();
		
		
	}
	public static void build(String fileName,Issuer issuer) {
		Scanner scanner = new Scanner(System.in);
		
	    System.out.println("Please Enter Add Time: ");
	    String addTimeString = scanner.nextLine();  // Read user input
	    int addTime = Integer.parseInt(addTimeString);
	    System.out.println("Add Time is: " + addTime);
	    
	    System.out.println("Please Enter Mul Time: ");
	    String mulTimeString = scanner.nextLine();  // Read user input
	    int mulTime = Integer.parseInt(mulTimeString);
	    System.out.println("Mul Time is: " + mulTime);
	    
	    System.out.println("Please Enter Mem Time: ");
	    String memTimeString = scanner.nextLine();  // Read user input
	    int memTime = Integer.parseInt(memTimeString);
	    System.out.println("Mem Time is: " + memTime);
	    
	    System.out.println("Please Enter Maximum Cycle Count: ");
	    String maxTimeString = scanner.nextLine();  // Read user input
	    int maxTime = Integer.parseInt(maxTimeString);
	    System.out.println("Max Time is: " + maxTime);
	    
	    issuer.maxCycleNumber = maxTime;
	    

		issuer.cache = new Cache();
		issuer.registerFile= new RegisterFile();
		
		ArrayList<String> list =  new ArrayList<>();
		
		issuer.fileEncoder = new FileEncoder();
		issuer.fileEncoder.readFileIntoList(fileName, list);
		
		issuer.Instructions = new ArrayList<>();

		issuer.Instructions = issuer.fileEncoder.splitListToInstructions(list);
		
		issuer.reservationStations =  new ReservationStations(issuer.registerFile,addTime,mulTime ,memTime,issuer.cache);
		
		
	}
	
	public  void excecuteCycle() {

		Issue();
		reservationStations.execute();
		
	}
	public  void runTomasulo() {


		while(cycleNumber<=maxCycleNumber) {
			System.out.println("Printing cycle number " + cycleNumber);
			System.out.println("********************************************************************************************************"); 
			
			
			if(cycleNumber == 1) {
				
				Issue();
				cycleNumber++;
				printStations();
				continue;
			}
 
			reservationStations.execute();
			Issue();
			

			
			
			
			
			cycleNumber++;
			
		}
		registerFile.printRegisterFile();
		
	}
	public void printStations() {
		reservationStations.A1.printStation();
		reservationStations.A2.printStation();
		reservationStations.A3.printStation();
		reservationStations.A4.printStation();
		reservationStations.M1.printStation();
		reservationStations.M2.printStation();
		reservationStations.M3.printStation();
		reservationStations.L1.printStation();
		reservationStations.L2.printStation();
		reservationStations.S1.printStation();
		reservationStations.S2.printStation();
	}

	public static void main(String[] args) {
		Issuer issuer = new Issuer();
		
		build("test.txt",issuer);
		
		issuer.fileEncoder.printInstructionList(issuer.Instructions);
		
		
		issuer.runTomasulo();
	
		
		
		
		

		
		
		

	}
	public void Issue() {
		
		if(pc < Instructions.size() ) {

			
			Instruction instruction = Instructions.get(pc);
			if(instruction.getOperation().equals(Operation.ADD) || instruction.getOperation().equals(Operation.SUB)){
				if(reservationStations.canReserveAdd()) {
					pc = pc +1;
					StationId x = reservationStations.assignInstruction(instruction);
					reservationStations.getExecInst().add(x);
					
					
				}
				else {
					System.out.println("Can't Issue"+instruction.getOperation()+" F"+instruction.getDestination()+" F"+instruction.getOperand1()+" R"+instruction.getOperand2() +" Yet");
					
				}
			}else if(instruction.getOperation().equals(Operation.MUL) || instruction.getOperation().equals(Operation.DIV)) {
				if(reservationStations.canReserveMultiplication()) {
					pc = pc +1;
					StationId x = reservationStations.assignInstruction(instruction);
					reservationStations.getExecInst().add(x);
				}
				else {
					System.out.println("Can't Issue"+instruction.getOperation()+" F"+instruction.getDestination()+" F"+instruction.getOperand1()+" R"+instruction.getOperand2() +" Yet");
				}
			}else if(instruction.getOperation().equals(Operation.LW)) {
				if(reservationStations.canReserveLoad()) {
					pc = pc +1;
					StationId x = reservationStations.assignInstruction(instruction);
					reservationStations.getExecInst().add(x);
				}
				else {
					System.out.println("Can't Issue"+instruction.getOperation()+" F"+instruction.getDestination()+" F"+instruction.getOperand1()+" R"+instruction.getOperand2() +" Yet");
				}
			}else if(instruction.getOperation().equals(Operation.SW)) {
				if(reservationStations.canReserveStore()) {
					pc = pc +1;
					StationId x = reservationStations.assignInstruction(instruction);
					reservationStations.getExecInst().add(x);
					
				}
			}
			

				
			}

			
			
		}
	}
		
	
	
	

