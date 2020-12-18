package ID_205665862_ID_206841496;


public class CoronaSoldier extends CoronaCitizen implements Coronable{
	boolean carryWeapon = false;
	
	public CoronaSoldier(String name, String idNumber, int yearOfBirth, String kalpiAddress, int daysInIsolation,boolean carryWeapon) throws Exception {
		super(name, idNumber, yearOfBirth, kalpiAddress, daysInIsolation);
		this.carryWeapon=carryWeapon;
		System.out.println("soldier corona");
	}

	@Override
	public String toString() {
		return super.toString()+" carryWeapon: " + carryWeapon;
	}
	
	
}
