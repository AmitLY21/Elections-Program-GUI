package ID_205665862_ID_206841496;


public class Soldier extends Citizen {
	boolean carryWeapon = false;

	public Soldier(String name, String idNumber, int yearOfBirth, String kalpiAddress, boolean hasWeapon) throws Exception {
		super(name, idNumber, yearOfBirth, kalpiAddress);
		this.carryWeapon = hasWeapon;
	}

	public String toString() {
		return super.toString() + "Carry weapon: " + carryWeapon;
	}

}
