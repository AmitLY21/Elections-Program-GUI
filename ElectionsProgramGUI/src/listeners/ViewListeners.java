package listeners;

import java.util.Vector;

import ID_205665862_ID_206841496.Party;

public interface ViewListeners {
	void addBallotBoxToModel(String address,String type);
	void addCitizenToModel(String name, String idNumber, String yearOfBirth, String ballotBoxAddress,boolean carryWeapon , boolean isIsolated , String daysInIsolation ,String citizenType);
	void addPartyToModel(String name , String politicalPos);
	void addCandidateToModel(String partyName , String id);
	String getPartyListFromModel();
	String getBallotBoxListFromModel();
	String getCitizensListFromModel();
	String getElectionsResultsFromModel();
	Vector<Party> getSinglePartyFromModel();
	void elections();
	boolean setElectChoice(String id, String partyChoise);


}
