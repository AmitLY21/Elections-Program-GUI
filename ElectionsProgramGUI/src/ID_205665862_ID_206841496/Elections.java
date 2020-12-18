package ID_205665862_ID_206841496;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

public class Elections {
	private int year;
	private int month;
	private double votePrecentage;
	private Party[] parties;

	public Elections(Vector<Party> parties2) {
		this.parties = new Party[parties2.size()];
		for (int i = 0; i < parties2.size(); i++) {
			this.parties[i]=parties2.get(i);
		}
		LocalDate temp = LocalDate.now();
		this.year = temp.getYear();
		this.month = temp.getMonthValue();
	}

	@Override
	public boolean equals(Object check) {
		Elections temp = (Elections)check;
		if(temp.getYear()!=year||temp.getMonth()!=month) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Elections: year: " + year + ", month: " + month + ", votePrecentage: " + votePrecentage + ", "
				+ (parties != null ? "parties: " + Arrays.toString(parties) : "");
	}

	public void setVotePrecentage(double votePrecentage) {
		this.votePrecentage = votePrecentage;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
}
