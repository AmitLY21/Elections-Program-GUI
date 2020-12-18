package ID_205665862_ID_206841496;

import java.time.LocalDate;

public class Party {
	public enum ePoliticalPosition {right, left, center};
	
	private String name;
	private final int MAX_POLITICANS_IN_PARTY = 20;
	private ePoliticalPosition position;
	private LocalDate creationDate;
	private Politician[] candidates = new Politician[MAX_POLITICANS_IN_PARTY];
	private int numOfCandidates=0;
	private int numOfVotes=0;

	public Party(String name,String politicalPos) {
		this.name = name;
		this.position=ePoliticalPosition.valueOf(politicalPos);
		this.creationDate = LocalDate.now();
		System.out.println(name+" has created\nadd candidates seperatly(press 4)");
	}
	
	public Party(String name, ePoliticalPosition position, LocalDate creationDate, Politician[] candidates) {
		this.name = name;
		this.position = position;
		this.creationDate = creationDate;
		for (int i = 0; i < candidates.length; i++) {
			this.candidates[i] = candidates[i];
			candidates[i].setMyParty(this);
			candidates[i].setIndexPlaceInParty(i+1);
			numOfCandidates++;
		}
	}
	
	public Party(Party copy) {
		this.name=copy.getName();
		this.position=copy.getPosition();
		this.creationDate=copy.getCreationDate();
		for (int i = 0; i < candidates.length; i++) {
			if(copy.getCandidates()[i]!=null) {
			candidates[i]=new Politician(copy.getCandidates()[i]);
			}
		}
	}
	
	public void addCandidate(Politician politican) {
		candidates[numOfCandidates]=politican;
		numOfCandidates++;
	}
	
	public void addVote() {
		this.numOfVotes++;
	}
	
	@Override
	public boolean equals(Object obj) {
		Party temp = (Party)obj;
		if(temp.getName().equalsIgnoreCase(name)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return name+", political position: "+position;
				
	}

	public void printVotes() {
		System.out.println("Party: " + name + " Votes: " + numOfVotes);
	}

	public Politician[] getCandidates() {
		return candidates;
	}

	public void setCandidates(Politician[] candidates) {
		this.candidates = candidates;
	}

	public String getName() {
		return name;
	}
	
	public int getNumOfCandidates() {
		return numOfCandidates;
	}

	public void setNumOfCandidates(int numOfCandidates) {
		this.numOfCandidates = numOfCandidates;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}

	public void setNumOfVotes(int numOfVotes) {
		this.numOfVotes = numOfVotes;
	}

	public ePoliticalPosition getPosition() {
		return position;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
}
