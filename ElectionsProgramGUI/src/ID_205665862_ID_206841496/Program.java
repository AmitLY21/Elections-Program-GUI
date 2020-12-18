package ID_205665862_ID_206841496;


import java.time.LocalDate;
import java.time.Month;

import ID_205665862_ID_206841496.Party.ePoliticalPosition;
import election_view.ElectionView;
import elections_Controller.ElectionController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Program extends Application {
		static Manage manage = new Manage();
		

	public static void main(String[] args)  throws Exception {
		
		LocalDate date1 = LocalDate.of(1960, Month.FEBRUARY, 2);
		LocalDate date2 = LocalDate.of(1945, Month.APRIL, 3);
		LocalDate date3 = LocalDate.of(1956, Month.DECEMBER, 3);
		try {
		Citizen p1 = new Citizen("name1", "000001001", 1994, "tel-aviv");
		Citizen p2 = new Politician("name2", "000001002", 1972, "beniBrak",0,null);
		Citizen p3 = new CoronaCitizen("name3", "000001003", 1955, "beniBrak", 5);// corona
		Citizen p4 = new Citizen("name4", "000001004", 1997, "tel-aviv");
		Citizen p5 = new CoronaSoldier("name5", "000001005", 1999, "tel-aviv", 7,true);// soldier corona //not used
		Citizen p6 = new Politician("name6", "000001006", 1991, "tel-aviv",0,null);
		Citizen p7 = new Politician("name7", "000001007", 1968, "tel-aviv",0,null);
		Citizen p8 = new Politician("name8", "100001008", 1995, "beniBrak",0,null);
		Citizen p9 = new CoronaCitizen("name9","100001009", 1995, "beniBrak", 2);// corona
		Citizen p10 = new Soldier("name10", "100001010", 2000, "tel-aviv", false);// soldier // not used
		Citizen p11 = new Politician("name11","000001011", 1991, "tel-aviv", 0, null);
		Citizen p12 = new Politician("name12", "000001012", 1992, "tel-aviv",0, null);
		Citizen[] allCitizens = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12};
		for (int i = 0; i < allCitizens.length; i++) {
			manage.getCitizens().add(allCitizens[i]);
		}
		
		BallotBox<Citizen> telAvivBox = new BallotBox<Citizen>("tel-aviv");
		BallotBox<CoronaCitizen> telAvivBox1 = new BallotBox<CoronaCitizen>("tel-aviv");
		BallotBox<Soldier> telAvivBox2 = new BallotBox<Soldier>("tel-aviv");
		BallotBox<CoronaSoldier> telAvivBox3= new BallotBox<CoronaSoldier>("tel-aviv");
		BallotBox<Citizen> beniBrakBox = new BallotBox<Citizen>("beniBrak");
		BallotBox<CoronaCitizen> beniBrakBox1 = new BallotBox<CoronaCitizen>("beniBrak");
		BallotBox<Soldier> beniBrakBox2 = new BallotBox<Soldier>("beniBrak");
		BallotBox<CoronaSoldier> beniBrakBox3 = new BallotBox<CoronaSoldier>("beniBrak");
		
		manage.getRegularBallotbox().add(telAvivBox);
		manage.getRegularBallotbox().add(beniBrakBox);
		manage.getCoronaBallotbox().add(telAvivBox1);
		manage.getCoronaBallotbox().add(beniBrakBox1);
		manage.getCoronaMilitaryBallotbox().add(telAvivBox3);
		manage.getCoronaMilitaryBallotbox().add(beniBrakBox3);
		manage.getMilitaryBallotbox().add(telAvivBox2);
		manage.getMilitaryBallotbox().add(beniBrakBox2);
		
		Politician[] poliSet1 = { (Politician) p11, (Politician) p12 };
		Party partyA = new Party("partyA", ePoliticalPosition.center, date1, poliSet1);
		Politician[] poliSet2 = { (Politician) p7, (Politician) p8 };
		Party partyB = new Party("partyB",ePoliticalPosition.right,date2,poliSet2);
		Politician[] poliSet3 = { (Politician) p6, (Politician) p2 };
		Party partyC = new Party("partyC",ePoliticalPosition.left,date3,poliSet3);
		Party[] allParties = {partyA,partyB,partyC};
		for (int j = 0; j < allParties.length; j++) {
			manage.getParties().add(allParties[j]);
		}
		}
		catch (Exception e) {
			manage.showMessage(e.getMessage());
			System.out.println(e.getMessage());
		}

		launch(args);

	}

	@SuppressWarnings("unused")
	@Override
	public void start(Stage theStage) throws Exception {
		ElectionView view = new ElectionView(theStage);
		ElectionController controller = new ElectionController(manage, view);
	}
	
}
