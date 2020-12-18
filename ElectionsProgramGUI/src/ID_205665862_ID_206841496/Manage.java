package ID_205665862_ID_206841496;


import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

import elections_Controller.ElectionController;
import listeners.ManageListeners;


public class Manage implements Messageable {
	Scanner scan = new Scanner(System.in);
	private Set<Citizen> citizens = new Set<Citizen>();
	private Vector<Party> parties = new Vector<Party>();
	private Vector<BallotBox<Citizen>> regularBallotbox = new Vector<BallotBox<Citizen>>();
	private Vector<BallotBox<CoronaCitizen>> coronaBallotbox = new Vector<BallotBox<CoronaCitizen>>();
	private Vector<BallotBox<Soldier>> militaryBallotbox = new Vector<BallotBox<Soldier>>();
	private Vector<BallotBox<CoronaSoldier>> coronaMilitaryBallotbox = new Vector<BallotBox<CoronaSoldier>>();
	private Vector<Elections> ourElections = new Vector<Elections>();
	private int totalVotes = 0;
	private boolean ElectionHappenned = false;
	private static Vector<ManageListeners> allListeners = new Vector<ManageListeners>();

	public void addBallotBox(String city, String type) { // case 1
		int choose = returnType(type);
		switch (choose) {
		case 1:
			regularBallotbox.add(new BallotBox<Citizen>(city));

			break;
		case 2:
			coronaBallotbox.add(new BallotBox<CoronaCitizen>(city));
			break;
		case 3:
			militaryBallotbox.add(new BallotBox<Soldier>(city));
			break;
		case 4:
			coronaMilitaryBallotbox.add(new BallotBox<CoronaSoldier>(city));
			break;
		}

	}

	private int returnType(String type) {
		if (type.equalsIgnoreCase("Regular"))
			return 1;
		else if (type.equalsIgnoreCase("Corona"))
			return 2;
		else if (type.equalsIgnoreCase("Military"))
			return 3;
		else if (type.equalsIgnoreCase("Military-Corona"))
			return 4;
		else
			return 0;
	}

	public void addCitizen(String name, String idNumber, String yearOfBirth, String address, boolean carryWeapon,
			boolean isIsolated, String daysInIsolation, String citizenType) throws Exception { // case 2

		int tempYear = Integer.parseInt(yearOfBirth);
		if (citizenType.equalsIgnoreCase("Corona-Soldier")) {
			int tempDays = Integer.parseInt(daysInIsolation);
			System.out.println("corona soldier");
			citizens.add(new CoronaSoldier(name, idNumber, tempYear, address, tempDays, carryWeapon));
		} else if (citizenType.equalsIgnoreCase("Soldier")) {
			System.out.println("soldier");
			citizens.add(new Soldier(name, idNumber, tempYear, address, carryWeapon));
		} else if (citizenType.equalsIgnoreCase("Corona")) {
			int tempDays = Integer.parseInt(daysInIsolation);
			System.out.println("corona");
			citizens.add(new CoronaCitizen(name, idNumber, tempYear, address, tempDays));
		} else {
			System.out.println("citizen regular");
			citizens.add(new Citizen(name, idNumber, tempYear, address));
		}
		try{
			citizens.get(citizens.getCurrentSize() - 1).isValidID();
		} 
		catch (Exception e) {
			allListeners.get(0).fireInvalidID(citizens.get(citizens.getCurrentSize() - 1).getIdNumber());
			System.out.println(citizens.get(citizens.getCurrentSize() - 1).getIdNumber());
		}
	}

	
	public static boolean inArmy(int yearOfBirth) {
		boolean inArmy = false;
		LocalDate today = LocalDate.now();
		int age = today.getYear() - yearOfBirth;
		if (age <= 21) {
			inArmy = true;
		}
		return inArmy;
	}


	public void addParty(String name, String politicalPos) { // case3
		Party newParty = new Party(name, politicalPos);
		parties.add(newParty);
	}

	public void addCitizenAsCandidate2(String partyName, String id) throws Exception {// case 4
		Party tempParty = null;
		for (int i = 0; i < parties.size(); i++) {
			if (parties.get(i).getName().equalsIgnoreCase(partyName)) {
				tempParty = parties.get(i);
				System.out.println("Party found");
			}
		}
		boolean newPoliticain = false;
		for (int i = 0; i < citizens.getCurrentSize() && !newPoliticain; i++) {
			if (citizens.get(i).getIdNumber().equalsIgnoreCase(id)) {
				System.out.println(citizens.get(i).getName() + " has joined " + tempParty.getName());
				makePoliticain(citizens.get(i), tempParty);
				newPoliticain = true;
			}
		}
		newPoliticain = false;
	}

	private void makePoliticain(Citizen citizens2, Party myParty) throws Exception {
		Citizen newCandidate = new Politician(citizens2.getName(), citizens2.getIdNumber(), citizens2.getYearOfBirth(),
				citizens2.getAddress(), (myParty.getNumOfCandidates()) + 1, myParty);
		myParty.addCandidate((Politician) newCandidate);
		boolean changed = false;
		for (int i = 0; i < citizens.getCurrentSize() && !changed; i++) {
			if (newCandidate.getIdNumber() == citizens.get(i).getIdNumber()) {
				citizens.remove(citizens.get(i));
				citizens.add(newCandidate);
				changed = true;
			}
		}
	}

	public String showBallotBoxes() {// case 5
		int boxNumber = 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < regularBallotbox.size(); i++) {
			sb.append("Ballotbox " + (boxNumber++) + ": " + regularBallotbox.get(i).toString() + "\n");
		}
		for (int i = 0; i < coronaBallotbox.size(); i++) {
			sb.append("Ballotbox " + (boxNumber++) + ": " + coronaBallotbox.get(i).toString() + "\n");
		}
		for (int i = 0; i < militaryBallotbox.size(); i++) {
			sb.append("Ballotbox " + (boxNumber++) + ": " + militaryBallotbox.get(i).toString() + "\n");
		}
		for (int i = 0; i < coronaMilitaryBallotbox.size(); i++) {
			sb.append("Ballotbox " + (boxNumber++) + ": " + coronaMilitaryBallotbox.get(i).toString() + "\n");
		}
		return sb.toString();
	}

	public String getCitizensList() {// case6
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < citizens.getCurrentSize(); i++) {
			sb.append(citizens.get(i).toString() + "\n\n");
		}
		return sb.toString();
	}

	public String getPartyLists() {// case7
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < parties.size(); i++) {
			sb.append((i + 1) + ") " + parties.get(i).getName() + "\n");// not showing all details
		}
		return sb.toString();
	}

	public void elections() throws Exception {// case8
		ElectionHappenned = true;
		sortCitizensToBallotBoxes();
		updatePartyListForAllBallotboxes();
	}

	private <T extends Citizen> void sortCitizensToBallotBoxes() throws Exception {
		for (int i = 0; i < citizens.getCurrentSize(); i++) {
			if (inArmy(citizens.get(i).getYearOfBirth())) {
				if (citizens.get(i) instanceof Coronable)
					addToRelevantBallotBox(coronaMilitaryBallotbox, citizens.get(i));
				else
					addToRelevantBallotBox(militaryBallotbox, citizens.get(i));
			} else if (citizens.get(i) instanceof Coronable)
				addToRelevantBallotBox(coronaBallotbox, citizens.get(i));
			else
				addToRelevantBallotBox(regularBallotbox, citizens.get(i));
		}
	}

	private void updatePartyListForAllBallotboxes() {
		for (int i = 0; i < regularBallotbox.size(); i++)
			regularBallotbox.get(i).updateParties(parties);
		for (int i = 0; i < coronaBallotbox.size(); i++)
			coronaBallotbox.get(i).updateParties(parties);
		for (int i = 0; i < militaryBallotbox.size(); i++)
			militaryBallotbox.get(i).updateParties(parties);
		for (int i = 0; i < coronaMilitaryBallotbox.size(); i++)
			coronaMilitaryBallotbox.get(i).updateParties(parties);
	}

	@SuppressWarnings("unchecked")
	private <T extends Citizen> void addToRelevantBallotBox(Vector<BallotBox<T>> box, Citizen ctzn) throws Exception {
		for (int i = 0; i < box.size(); i++) {
			if (box.get(i).getAddress().equalsIgnoreCase(ctzn.getAddress()))
				try {
					box.get(i).addCitizen((T) ctzn);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

		}
	}

	public String electionsResults() {// case 9
		if (ElectionHappenned) {
			countVotes();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < parties.size(); i++) {
				if (parties.get(i) != null) {
					sb.append(parties.get(i).getName() + ": " + (parties.get(i).getNumOfVotes()) + "\n");
					totalVotes += parties.get(i).getNumOfVotes();
				}
			}
			saveElections();
			ElectionHappenned = false;
			return sb.toString();
		} else {
			return ("need to have elections first");
		}
	}

	protected void countVotes() {
		for (int i = 0; i < citizens.getCurrentSize(); i++) {
			for (int j = 0; j < parties.size(); j++) {
				if (citizens.get(i).getElectChoice() != null
						&& citizens.get(i).getElectChoice().equalsIgnoreCase(parties.get(j).getName())) {
					parties.get(j).addVote();
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private <T extends Citizen> void updateTotalVotes(BallotBox<T> ballotBox) {
		for (int i = 0; i < ballotBox.validCitizens.getCurrentSize(); i++) {
			for (int j = 0; j < parties.size(); j++) {
				if (ballotBox.validCitizens.get(i).getElectChoice() != null
						&& ballotBox.validCitizens.get(i).getElectChoice().equalsIgnoreCase(parties.get(j).getName())) {
					parties.get(j).addVote();
					totalVotes++;
				}
			}
		}
	}

	private void saveElections() {

		Elections newElections = new Elections(parties);
		double votePrecentage = totalVotes / citizens.getCurrentSize();
		newElections.setVotePrecentage(votePrecentage);
		ourElections.add(newElections);
		System.out.println("Elections has been saved to database");
		// note for programmer (the class Election is not used)
	}

	public Set<Citizen> getCitizens() {
		return citizens;
	}

	public Vector<Party> getParties() {
		return parties;
	}

	public Vector<BallotBox<Citizen>> getRegularBallotbox() {
		return regularBallotbox;
	}

	public Vector<BallotBox<CoronaCitizen>> getCoronaBallotbox() {
		return coronaBallotbox;
	}

	public Vector<BallotBox<Soldier>> getMilitaryBallotbox() {
		return militaryBallotbox;
	}

	public Vector<BallotBox<CoronaSoldier>> getCoronaMilitaryBallotbox() {
		return coronaMilitaryBallotbox;
	}

	@Override
	public void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	@Override
	public String getString(String str) {
		return JOptionPane.showInputDialog(str);
	}

	public void registerListeners(ElectionController electionController) {
		allListeners.add(electionController);

	}

}
