package elections_Controller;

import java.util.Vector;

import ID_205665862_ID_206841496.Manage;
import ID_205665862_ID_206841496.Party;
import election_view.ElectionView;
import listeners.ManageListeners;
import listeners.ViewListeners;

public class ElectionController implements ViewListeners, ManageListeners {
	private Manage manageModel = new Manage();
	private ElectionView electionView;

	public ElectionController(Manage manage, ElectionView view) {
		this.manageModel = manage;
		this.electionView = view;

		electionView.registerListeners(this);
		manageModel.registerListeners(this);
	}

	@Override
	public void addBallotBoxToModel(String address, String type) {
		manageModel.addBallotBox(address, type);
	}

	@Override
	public void addCitizenToModel(String name, String idNumber, String yearOfBirth, String ballotBoxAddress,
			boolean carryWeapon, boolean isIsolated, String daysInIsolation, String citizenType) {
		try {
			manageModel.addCitizen(name, idNumber, yearOfBirth, ballotBoxAddress, carryWeapon, isIsolated,
					daysInIsolation, citizenType);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	@Override
	public void addPartyToModel(String name, String politicalPos) {
		manageModel.addParty(name, politicalPos);
	}

	@Override
	public void addCandidateToModel(String partyName, String id) {
		try {
			manageModel.addCitizenAsCandidate2(partyName, id);
		} catch (Exception e) {
			System.out.println("not working");
		}
	}

	@Override
	public String getPartyListFromModel() {
		return manageModel.getPartyLists();
	}

	@Override
	public String getBallotBoxListFromModel() {
		return manageModel.showBallotBoxes();
	}

	@Override
	public String getCitizensListFromModel() {
		return manageModel.getCitizensList();
	}

	@Override
	public String getElectionsResultsFromModel() {
		return manageModel.electionsResults();
	}

	@Override
	public Vector<Party> getSinglePartyFromModel() {
		return manageModel.getParties();
	}

	@Override
	public void elections() {
		try {
			manageModel.elections();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean setElectChoice(String id, String partyChoise) {
		for (int i = 0; i < manageModel.getCitizens().getCurrentSize(); i++) {
			if (id.equals(manageModel.getCitizens().get(i).getIdNumber())) {
				manageModel.getCitizens().get(i).setElectChoice(partyChoise);
				electionView.hasVoted(id);
				return true;
			}
		}
		electionView.dontExists(id);
		return false;
	}

	@Override
	public void fireInvalidID(String idNumber) {
		electionView.setlblInvalidID(idNumber);

	}

}
