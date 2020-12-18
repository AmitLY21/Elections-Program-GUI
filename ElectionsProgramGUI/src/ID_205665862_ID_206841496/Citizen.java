package ID_205665862_ID_206841496;

import java.time.LocalDate;


public class Citizen implements Comparable<Citizen> {
	private static final int MINMUM_AGE = 18;
	protected String name;
	protected String idNumber;
	protected int yearOfBirth;
	protected String address;
	protected String electChoice;

	public Citizen(String name, String idNumber, int yearOfBirth, String kalpiAddress){
		this.name = name;
		this.idNumber = idNumber;
		this.yearOfBirth = yearOfBirth;
		this.address = kalpiAddress;	
	}

	public Citizen(Citizen copy) {
		this.name = copy.getName();
		this.idNumber = copy.getIdNumber();
		this.yearOfBirth = copy.getYearOfBirth();
		this.address = copy.getAddress();

	}

	public boolean isOver18() {
		LocalDate today = LocalDate.now();
		if (today.getYear()-yearOfBirth >= MINMUM_AGE)
			return true;
		return false;
	}

	public void isValidID() throws Exception {
		int digitsCounter = 0;
		StringBuffer tempId = new StringBuffer(idNumber);
		digitsCounter = tempId.length();
		if (digitsCounter < 9) {
			fixID(idNumber);
			throw new Exception("ID has less than 9 digits (ID Fixed)");
		} else if (digitsCounter > 9) {
			reduceNumbers(idNumber);
			throw new Exception("ID has too many digits (ID Fixed)");
		}
	}

	private void reduceNumbers(String idNumber2) {
		StringBuffer str = new StringBuffer(idNumber2);
		str.substring(0, 8);
		this.idNumber = str.toString();
	}

	private void fixID(String idNumber) {
		StringBuffer temp = new StringBuffer(idNumber);
		int numberOfZeros = 9 - temp.length();
		for (int i = 1; i <= numberOfZeros; i++) {
			temp.insert(0, '0');
		}
		this.idNumber = temp.toString();
	}

	@Override
	public int compareTo(Citizen t) {
		if (Integer.parseInt(((Citizen) t).getIdNumber()) == Integer.parseInt(this.idNumber))
			return 0;
		if (Integer.parseInt(((Citizen) t).getIdNumber()) < Integer.parseInt(this.idNumber))
			return -1;
		else
			return 1;
	}

	@Override
	public boolean equals(Object other) {
		Citizen temp = (Citizen) other;
		if (temp.getIdNumber() != idNumber) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\nID: " + idNumber + "\nYear Of Birth: " + yearOfBirth + "\nAddress: "
				+ address;// + "\nIs in isolation: "+ isIsolate;
	}

	// *********setter&getter*************
	public String getElectChoice() {
		return electChoice;
	}

	public void setElectChoice(String electChoice) {
		this.electChoice = electChoice;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getName() {
		return name;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public String getAddress() {
		return address;
	}

}
