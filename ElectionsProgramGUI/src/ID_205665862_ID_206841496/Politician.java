package ID_205665862_ID_206841496;

public class Politician extends Citizen {
	private int indexPlaceInParty;
	private Party myParty;


	public Politician(String name, String idNumber, int yearOfBirth, String ballotBoxAddress,
			int indexPlaceInParty, Party myParty) throws Exception {
		super(name, idNumber, yearOfBirth, ballotBoxAddress);
		this.indexPlaceInParty = indexPlaceInParty;
		this.myParty = myParty;
	}

	public Politician(Politician copy) {
		super((Citizen)copy);
		this.indexPlaceInParty=copy.getIndexPlaceInParty();
		this.myParty=copy.getParty();
	}

	//Politician equals method is the same as parent(Citizens)
	@Override
	public String toString() {
		return super.toString() + "\nPolitican party rank: " + indexPlaceInParty + "\nparty: " + myParty;
	}

	public int getIndexPlaceInParty() {
		return indexPlaceInParty;
	}

	public String getMyParty() {
		return myParty.getName();
	}
	
	public Party getParty() {
		return myParty;
	}

	public void setIndexPlaceInParty(int indexPlaceInParty) {
		this.indexPlaceInParty = indexPlaceInParty;
	}

	public void setMyParty(Party myParty) {
		this.myParty = myParty;
	}

}
