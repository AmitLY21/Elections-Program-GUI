package ID_205665862_ID_206841496;


public class CoronaCitizen extends Citizen implements Coronable {
	int daysInIsolation;
	

	public CoronaCitizen(String name, String idNumber, int yearOfBirth, String kalpiAddress,int daysInIsolation) throws Exception {
		super(name, idNumber, yearOfBirth, kalpiAddress);
		this.daysInIsolation=daysInIsolation;
		System.out.println("corona citizen");
	}
	
	public String toString() {
		return super.toString() + "\nCurrently in isolation for " + daysInIsolation + " days";
	}

	@Override
	public void calcDaysInIsloation() {
	
	}

	

}
