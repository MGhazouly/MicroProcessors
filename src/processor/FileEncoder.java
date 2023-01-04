package processor;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import structures.Instruction;
import structures.Operation;

public class FileEncoder {

	protected void readFileIntoList(String fileName, ArrayList<String> list) {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(fileName);

			br = new BufferedReader(fr);

			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	protected static ArrayList<String> splitStringBySpace(String string) {
		String[] r = string.split("\\s+");
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < r.length; i++) {
			result.add(r[i]);
		}
		return result;
	}
	public  ArrayList<Instruction> splitListToInstructions(ArrayList<String> inputList) {
		ArrayList<Instruction> returnedList =  new ArrayList<>();
		ArrayList<String> instructionStringList =  new ArrayList<>();
		for (int i = 0; i < inputList.size(); i++) {
			Instruction instruction = new Instruction();
			//Split instruction into words
			instructionStringList = splitStringBySpace(inputList.get(i));
			
			//set Operation and destination Register
			setInstructionOperation(instructionStringList, instruction);
			setInsructionDestination(instructionStringList, instruction);
			
			if(instruction.getOperation().toString() == "LW" || instruction.getOperation().toString() == "SW" )
			{
				instruction.setAddress(Integer.parseInt(instructionStringList.get(2)));
			}
			else {
				//set operand1
				setInstructionOperand1(instructionStringList, instruction);
				// set operand 2
				setInstructionOperand2(instructionStringList, instruction);
			}

			returnedList.add(instruction);
		}
		return returnedList;
	}


	
//	public static void main(String[] args) {
//
//		FileEncoder encoder =  new FileEncoder();
//		ArrayList<String> list =  new ArrayList<>();
//		
//		encoder.readFileIntoList("test.txt", list);
//		
//		ArrayList<Instruction> returnedList = new ArrayList<>();
//
//		returnedList = encoder.splitListToInstructions(list);
//		
//		encoder.printInstructionList(returnedList);
//
//		
//
//	}
	public static void printList(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	public  void printInstructionList(ArrayList<Instruction> list) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).printInstruction();
		}
	}
	private static void setInstructionOperand2(ArrayList<String> instructionStringList, Instruction instruction) {
		switch(instructionStringList.get(3).toString()) {
			case "R0":
				System.out.println("You entered R0 in an instruction as operand 2 this is our PC Register");
				break;
			case "R1":
				instruction.setOperand2(1);
				break;
			case "R2":
				instruction.setOperand2(2);
				break;
			case "R3":
				instruction.setOperand2(3);
				break;
			case "R4":
				instruction.setOperand2(4);
				break;
			case "R5":
				instruction.setOperand2(5);
				break;
			case "R6":
				instruction.setOperand2(6);
				break;
			case "R7":
				instruction.setOperand2(7);
				break;
			case "R8":
				instruction.setOperand2(8);
				break;
			case "R9":
				instruction.setOperand2(9);
				break;
			case "R10":
				instruction.setOperand2(10);
				break;
			case "R11":
				instruction.setOperand2(11);
				break;
			case "R12":
				instruction.setOperand2(12);
				break;
			case "R13":
				instruction.setOperand2(13);
				break;
			case "R14":
				instruction.setOperand2(14);
				break;
			case "R15":
				instruction.setOperand2(15);
				break;
			case "R16":
				instruction.setOperand2(16);
				break;
			default:
			    System.out.println("You entered an invalid register number we only have register from 0 to 16 inclusive");
		}
	}
	private static void setInstructionOperand1(ArrayList<String> instructionStringList, Instruction instruction) {
		switch(instructionStringList.get(2).toString()) {
			case "R0":
				System.out.println("You entered R0 in an instruction as operand 1 this is our PC Register");
				break;
			case "R1":
				instruction.setOperand1(1);
				break;
			case "R2":
				instruction.setOperand1(2);
				break;
			case "R3":
				instruction.setOperand1(3);
				break;
			case "R4":
				instruction.setOperand1(4);
				break;
			case "R5":
				instruction.setOperand1(5);
				break;
			case "R6":
				instruction.setOperand1(6);
				break;
			case "R7":
				instruction.setOperand1(7);
				break;
			case "R8":
				instruction.setOperand1(8);
				break;
			case "R9":
				instruction.setOperand1(9);
				break;
			case "R10":
				instruction.setOperand1(10);
				break;
			case "R11":
				instruction.setOperand1(11);
				break;
			case "R12":
				instruction.setOperand1(12);
				break;
			case "R13":
				instruction.setOperand1(13);
				break;
			case "R14":
				instruction.setOperand1(14);
				break;
			case "R15":
				instruction.setOperand1(15);
				break;
			case "R16":
				instruction.setOperand1(16);
				break;
			default:
			    System.out.println("You entered an invalid register number we only have register from 0 to 16 inclusive");
		}
	}
	private static void setInsructionDestination(ArrayList<String> instructionStringList, Instruction instruction) {
		switch(instructionStringList.get(1).toString()) {
			case "R0":
				System.out.println("You entered R0 in an instruction as destination this is our PC Register");
				break;
			case "R1":
				instruction.setDestination(1);
				break;
			case "R2":
				instruction.setDestination(2);
				break;
			case "R3":
				instruction.setDestination(3);
				break;
			case "R4":
				instruction.setDestination(4);
				break;
			case "R5":
				instruction.setDestination(5);
				break;
			case "R6":
				instruction.setDestination(6);
				break;
			case "R7":
				instruction.setDestination(7);
				break;
			case "R8":
				instruction.setDestination(8);
				break;
			case "R9":
				instruction.setDestination(9);
				break;
			case "R10":
				instruction.setDestination(10);
				break;
			case "R11":
				instruction.setDestination(11);
				break;
			case "R12":
				instruction.setDestination(12);
				break;
			case "R13":
				instruction.setDestination(13);
				break;
			case "R14":
				instruction.setDestination(14);
				break;
			case "R15":
				instruction.setDestination(15);
				break;
			case "R16":
				instruction.setDestination(16);
				break;
			default:
			    System.out.println("You entered an invalid register number as destination we only have register from 0 to 16 inclusive");
		}
	}
	private static void setInstructionOperation(ArrayList<String> instructionStringList, Instruction instruction) {
		switch(instructionStringList.get(0).toString()) {
			case "ADD":
				instruction.setOperation(Operation.ADD);
				break;
			case "SUB":
				instruction.setOperation(Operation.SUB);
				break;
			case "MUL":
				instruction.setOperation(Operation.MUL);
				break;
			case "DIV":
				instruction.setOperation(Operation.DIV);
				break;
			case "LW":
				instruction.setOperation(Operation.LW);
				break;
			case "SW":
				instruction.setOperation(Operation.SW);
				break;
			default:
			    System.out.println("failed to find operation, You entered an invalid operation type");
			}
	}

}
