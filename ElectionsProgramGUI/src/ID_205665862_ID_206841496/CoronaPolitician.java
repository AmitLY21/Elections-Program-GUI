package ID_205665862_ID_206841496;

import java.util.Scanner;

public class CoronaPolitician extends Politician implements Coronable {
	int daysInIsolation;

	public CoronaPolitician(String name, String idNumber, int yearOfBirth, String ballotBoxAddress,
			int indexPlaceInParty, Party myParty) throws Exception {
		super(name, idNumber, yearOfBirth, ballotBoxAddress, indexPlaceInParty, myParty);

	}

	@Override
	public String toString() {
		return super.toString()+ "\nCurrently in isolation for " + daysInIsolation + " days";
	}

	@Override
	public void calcDaysInIsloation() {
		Scanner s = new Scanner(System.in);
		System.out.println("how many days in isolation? ");
		daysInIsolation=s.nextInt();
		s.close();
	}
	
}
