package ID_205665862_ID_206841496;


import java.util.Vector;


public class BallotBox<T extends Citizen> {

	protected static int counter = 100;
	protected int ID;
	protected String address;
	protected Set<T> validCitizens = new Set<T>();
	protected double votePrecentage;
	protected Vector<Party> allParties = new Vector<Party>();

	public BallotBox(String address) {
		this.ID = counter++;
		this.address = address;
		System.out.println("BallotBox " + address + " has been created");
	}

	public void addCitizen(T temp) throws Exception {
		if (temp.isOver18())
			validCitizens.add(temp);
		else
			throw new Exception("Underage, can't vote");
	}

	@SuppressWarnings("unchecked")
	public void updateParties(Vector<Party> partiesUpdate) {
		allParties = (Vector<Party>) partiesUpdate.clone();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		BallotBox<T> temp = (BallotBox<T>) other;
		if (ID == temp.getID()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nBallotBox address: " + address + ", ID: " + ID ;
	}

	public String getAddress() {
		return address;
	}

	public int getID() {
		return this.ID;
	}

	public Vector<Party> getAllParties() {
		return allParties;
	}

	
}
