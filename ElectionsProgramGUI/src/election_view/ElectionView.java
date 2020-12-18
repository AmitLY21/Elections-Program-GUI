package election_view;


import java.time.LocalDate;
import java.util.Vector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listeners.ViewListeners;

public class ElectionView extends Application {
	private static Vector<ViewListeners> allListeners = new Vector<ViewListeners>();
	static StackPane spRootMiddle = new StackPane();
	static Label lblInvalidID = new Label();
	static Label lblHasVoted= new Label();
	static Label lblDontExists= new Label();

	public static void changeVisiblityToFalse(Pane tempPane) {
		for (int i = 0; i < spRootMiddle.getChildren().size(); i++) {
			spRootMiddle.getChildren().get(i).setVisible(false);
		}
		tempPane.setVisible(true);
	}

	public ElectionView(Stage theStage) {
		theStage.setTitle("Election Program -Version 2020");
		theStage.getIcons().add(new Image("file:elections.png"));

		Label lblTopHeadline = new Label("Election Program");
		Label lblBottomNames = new Label("By - Tor Hanan & Amit Levy");
		LocalDate date = LocalDate.now();
		Label lblDate = new Label(date.toString());
		Button btnAddBallotBox = new Button("Add Ballot Box			");
		Button btnAddCitizen = new Button("Add Citizen			");
		Button btnAddPoliticalParty = new Button("Add Political Party		");
		Button btnAddCandidate = new Button("Add Candidate		");
		Button btnShowBallotBoxLists = new Button("Show Ballot Box Lists	");
		Button btnShowCitizensLists = new Button("Show Citizens Lists		");
		Button btnShowPartyLists = new Button("Show Political Party Lists	");
		Button btnElections = new Button("Election				");
		Button btnElectionsResults = new Button("Show Election Results	");
		Button btnExit = new Button("Exit");
		Button btnClearText = new Button("Clear Text");

		BorderPane brRootBox = new BorderPane();
		brRootBox.setPadding(new Insets(10));

		VBox vbRoot = new VBox();
		vbRoot.setSpacing(10);
		vbRoot.setPadding(new Insets(10));
		vbRoot.setAlignment(Pos.CENTER_LEFT);

		HBox hbRootTop = new HBox();
		hbRootTop.setSpacing(500);
		hbRootTop.setPadding(new Insets(10));
		hbRootTop.setAlignment(Pos.CENTER_LEFT);

		HBox hbRootBottom = new HBox();
		hbRootBottom.setSpacing(200);
		hbRootBottom.setPadding(new Insets(10));
		hbRootBottom.setAlignment(Pos.CENTER_LEFT);

		GridPane gpAddBallotBox = new GridPane();
		gpAddBallotBox.setPadding(new Insets(10));
		gpAddBallotBox.setHgap(10);
		gpAddBallotBox.setVgap(10);
		gpAddBallotBox.setAlignment(Pos.TOP_LEFT);

		GridPane gpAddCitizen = new GridPane();
		gpAddCitizen.setPadding(new Insets(10));
		gpAddCitizen.setHgap(10);
		gpAddCitizen.setVgap(10);
		gpAddCitizen.setAlignment(Pos.TOP_LEFT);

		GridPane gpAddParty = new GridPane();
		gpAddParty.setPadding(new Insets(10));
		gpAddParty.setHgap(10);
		gpAddParty.setVgap(10);
		gpAddParty.setAlignment(Pos.TOP_LEFT);

		GridPane gpAddCandidate = new GridPane();
		gpAddCandidate.setPadding(new Insets(10));
		gpAddCandidate.setHgap(10);
		gpAddCandidate.setVgap(10);
		gpAddCandidate.setAlignment(Pos.TOP_LEFT);

		VBox vbBallotBoxList = new VBox();
		vbBallotBoxList.setSpacing(10);
		vbBallotBoxList.setPadding(new Insets(10));
		vbBallotBoxList.setAlignment(Pos.CENTER_LEFT);

		ScrollPane scpShowBallotBox = new ScrollPane();
		scpShowBallotBox.setPadding(new Insets(10));
		scpShowBallotBox.setVmax(440);
		scpShowBallotBox.setContent(vbBallotBoxList);

		VBox vbCitizenList = new VBox();
		vbCitizenList.setSpacing(10);
		vbCitizenList.setPadding(new Insets(10));
		vbCitizenList.setAlignment(Pos.CENTER_LEFT);

		ScrollPane scpShowCitizen = new ScrollPane();
		scpShowCitizen.setPadding(new Insets(10));
		scpShowCitizen.setVmax(440);
		scpShowCitizen.setContent(vbCitizenList);

		VBox vbPartyList = new VBox();
		vbPartyList.setSpacing(10);
		vbPartyList.setPadding(new Insets(10));
		vbPartyList.setAlignment(Pos.CENTER_LEFT);

		ScrollPane scpShowParty = new ScrollPane();
		scpShowParty.setPadding(new Insets(10));
		scpShowParty.setVmax(440);
		scpShowParty.setContent(vbPartyList);

		VBox vbElectionsResults = new VBox();
		vbElectionsResults.setSpacing(10);
		vbElectionsResults.setPadding(new Insets(10));
		vbElectionsResults.setAlignment(Pos.TOP_LEFT);

		GridPane gpElections = new GridPane();
		gpElections.setPadding(new Insets(10));
		gpElections.setHgap(10);
		gpElections.setVgap(10);
		gpElections.setAlignment(Pos.TOP_LEFT);

		Label lblPartyOptions = new Label();
		ComboBox<String> cmbPartyChoise = new ComboBox<String>();

		spRootMiddle.setPadding(new Insets(10));

// Handling main(LEFT) buttons
		btnClearText.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scpShowBallotBox.setContent(null);
				scpShowCitizen.setContent(null);
				scpShowParty.setContent(null);
				vbElectionsResults.getChildren().clear();
				lblInvalidID.setText(null);
				lblDontExists.setText(null);
				lblHasVoted.setText(null);
			}
		});
//ballotbox
		btnAddBallotBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeVisiblityToFalse(gpAddBallotBox);
				btnClearText.setVisible(false);
			}
		});
//addcitizen
		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeVisiblityToFalse(gpAddCitizen);
				btnClearText.setVisible(true);
			}
		});
//add party
		btnAddPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeVisiblityToFalse(gpAddParty);
				btnClearText.setVisible(false);
			}
		});
//add candidate
		btnAddCandidate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				changeVisiblityToFalse(gpAddCandidate);
				btnClearText.setVisible(false);
			}
		});

// showBallotBox
		btnShowBallotBoxLists.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for (int i = 0; i < spRootMiddle.getChildren().size(); i++) {
					spRootMiddle.getChildren().get(i).setVisible(false);
				}
				scpShowBallotBox.setVisible(true);
				Label lblBallotBoxList = new Label(allListeners.get(0).getBallotBoxListFromModel());
				vbBallotBoxList.getChildren().add(lblBallotBoxList);
				scpShowBallotBox.setContent(lblBallotBoxList);
				btnClearText.setVisible(true);
			}
		});

// showCitizen
		btnShowCitizensLists.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < spRootMiddle.getChildren().size(); i++) {
					spRootMiddle.getChildren().get(i).setVisible(false);
				}
				scpShowCitizen.setVisible(true);
				Label lblCitizenList = new Label();
				vbCitizenList.getChildren().add(lblCitizenList);
				lblCitizenList.setText(allListeners.get(0).getCitizensListFromModel());
				scpShowCitizen.setContent(lblCitizenList);
				btnClearText.setVisible(true);
			}
		});

// showParty
		btnShowPartyLists.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < spRootMiddle.getChildren().size(); i++) {
					spRootMiddle.getChildren().get(i).setVisible(false);
				}
				scpShowParty.setVisible(true);
				Label lblPartyList = new Label(allListeners.get(0).getPartyListFromModel());
				vbPartyList.getChildren().add(lblPartyList);
				scpShowParty.setContent(lblPartyList);
				btnClearText.setVisible(true);
			}
		});

//elections
		btnElections.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				cmbPartyChoise.getItems().clear();
				changeVisiblityToFalse(gpElections);
				btnClearText.setVisible(true);
				if (!allListeners.get(0).getPartyListFromModel().equalsIgnoreCase("")) {
					lblPartyOptions.setText(allListeners.get(0).getPartyListFromModel());
					for (int i = 0; i < allListeners.get(0).getSinglePartyFromModel().size(); i++) {
						cmbPartyChoise.getItems().add(allListeners.get(0).getSinglePartyFromModel().get(i).getName());
					}
					allListeners.get(0).elections();
				} else
					lblPartyOptions.setText("There are no parties");
			}
		});

// election sub methods
		
// showElectionResults
		btnElectionsResults.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (int i = 0; i < spRootMiddle.getChildren().size(); i++) {
					spRootMiddle.getChildren().get(i).setVisible(false);
				}
				vbElectionsResults.setVisible(true);
				Label lblResults = new Label(allListeners.get(0).getElectionsResultsFromModel());
				vbElectionsResults.getChildren().add(lblResults);

				btnClearText.setVisible(true);
			}
		});

// exit app
		btnExit.setOnAction(e -> Platform.exit());

// ballotBox background
		Label lblBallotBoxName = new Label("Address:");
		TextField tfBallotBoxName = new TextField();
		Button btnCreateBallotBox = new Button("Create Ballot Box");
		ComboBox<String> cmbBallotBoxType = new ComboBox<String>();
		cmbBallotBoxType.getItems().addAll("Regular", "Corona", "Military", "Military-Corona");

		gpAddBallotBox.add(lblBallotBoxName, 0, 0);
		gpAddBallotBox.add(tfBallotBoxName, 1, 0);
		gpAddBallotBox.add(cmbBallotBoxType, 0, 1);
		gpAddBallotBox.add(btnCreateBallotBox, 0, 2);

		btnCreateBallotBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				for (ViewListeners l : allListeners) {
					l.addBallotBoxToModel(tfBallotBoxName.getText(), cmbBallotBoxType.getValue());
				}
				tfBallotBoxName.clear();
				cmbBallotBoxType.setValue(null);
			}
		});

// addCitizen background
		Label lblType = new Label("Type:");
		ComboBox<String> cmbCitizenType = new ComboBox<String>();
		cmbCitizenType.getItems().addAll("Regular", "Corona", "Soldier", "Corona-Soldier");
		TextField tfCitizenName = new TextField();
		Label lblCitizenName = new Label("Name: ");
		TextField tfID = new TextField();
		Label lblID = new Label("ID:");
		TextField tfYearOfBirth = new TextField();
		Label lblYearOfBirth = new Label("Year Of Birth:");
		TextField tfAddress = new TextField();
		Label lblAddress = new Label("Address:");
		TextField tfDaysInIsolation = new TextField();
		Label lblDaysInIsolation = new Label("Days In Isolation:");
		CheckBox cbCarryWeapon = new CheckBox("Carry weapon? ");
		CheckBox cbIsIsolated = new CheckBox("Isolated? ");
		Button btnCreateCitizen = new Button("Add Citizen");

		if (cmbCitizenType.getValue() == null) {
			tfCitizenName.setDisable(true);
			tfID.setDisable(true);
			tfYearOfBirth.setDisable(true);
			tfAddress.setDisable(true);
			tfDaysInIsolation.setDisable(true);
			cbCarryWeapon.setDisable(true);
			cbIsIsolated.setDisable(true);
			btnCreateCitizen.setDisable(true);
		}

		cmbCitizenType.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (cmbCitizenType.getValue() != null && cmbCitizenType.getValue().equalsIgnoreCase("Corona")) {
					tfCitizenName.setDisable(false);
					tfID.setDisable(false);
					tfYearOfBirth.setDisable(false);
					tfAddress.setDisable(false);
					tfDaysInIsolation.setDisable(false);
					cbCarryWeapon.setDisable(true);
					cbIsIsolated.setDisable(false);
					btnCreateCitizen.setDisable(false);
				}
				if (cmbCitizenType.getValue() != null &&cmbCitizenType.getValue().equalsIgnoreCase("Corona-Soldier")) {
					tfCitizenName.setDisable(false);
					tfID.setDisable(false);
					tfYearOfBirth.setDisable(false);
					tfAddress.setDisable(false);
					tfDaysInIsolation.setDisable(false);
					cbCarryWeapon.setDisable(false);
					cbIsIsolated.setDisable(false);
					btnCreateCitizen.setDisable(false);
				}
				if (cmbCitizenType.getValue() != null && cmbCitizenType.getValue().equalsIgnoreCase("Soldier")) {
					tfCitizenName.setDisable(false);
					tfID.setDisable(false);
					tfYearOfBirth.setDisable(false);
					tfAddress.setDisable(false);
					cbCarryWeapon.setDisable(false);
					tfDaysInIsolation.setDisable(true);
					cbIsIsolated.setDisable(true);
					btnCreateCitizen.setDisable(false);
				}
				if (cmbCitizenType.getValue() != null && cmbCitizenType.getValue().equalsIgnoreCase("regular")) {
					tfCitizenName.setDisable(false);
					tfID.setDisable(false);
					tfYearOfBirth.setDisable(false);
					tfAddress.setDisable(false);
					tfDaysInIsolation.setDisable(true);
					cbCarryWeapon.setDisable(true);
					cbIsIsolated.setDisable(true);
					btnCreateCitizen.setDisable(false);
				}
			}
		});

		gpAddCitizen.add(lblType, 0, 0);
		gpAddCitizen.add(cmbCitizenType, 1, 0);
		gpAddCitizen.add(lblCitizenName, 0, 1);
		gpAddCitizen.add(tfCitizenName, 1, 1);
		gpAddCitizen.add(lblID, 0, 2);
		gpAddCitizen.add(tfID, 1, 2);
		gpAddCitizen.add(lblYearOfBirth, 0, 3);
		gpAddCitizen.add(tfYearOfBirth, 1, 3);
		gpAddCitizen.add(lblAddress, 0, 4);
		gpAddCitizen.add(tfAddress, 1, 4);
		gpAddCitizen.add(lblDaysInIsolation, 0, 6);
		gpAddCitizen.add(tfDaysInIsolation, 1, 6);
		gpAddCitizen.add(cbCarryWeapon, 0, 7);
		gpAddCitizen.add(cbIsIsolated, 0, 5);
		gpAddCitizen.add(btnCreateCitizen, 0, 8);
		gpAddCitizen.add(lblInvalidID, 0, 9,3,1);
		lblInvalidID.setVisible(false);
		lblInvalidID.setTextFill(Color.RED);

		btnCreateCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (ViewListeners l : allListeners) {
					l.addCitizenToModel(tfCitizenName.getText(), tfID.getText(), tfYearOfBirth.getText(),
							tfAddress.getText(), cbCarryWeapon.isSelected(), cbIsIsolated.isSelected(),
							tfDaysInIsolation.getText(), cmbCitizenType.getValue().toString());
				}
				cmbCitizenType.setValue(null);
				tfCitizenName.clear();
				tfID.clear();
				tfYearOfBirth.clear();
				tfAddress.clear();
				cbCarryWeapon.setSelected(false);
				tfDaysInIsolation.clear();
				cbIsIsolated.setSelected(false);
			}
		});

// addParty
		Label lblPartyName = new Label("Name:");
		TextField tfPartyName = new TextField();
		Button btnCreateParty = new Button("Create Political Party");
		Label lblPoliticalPos = new Label("Political Position: ");
		ComboBox<String> cmbPartyPos = new ComboBox<String>();
		cmbPartyPos.getItems().addAll("Right", "Center", "Left");

		btnCreateParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("heyParty");
				for (ViewListeners l : allListeners) {
					System.out.println(l.getClass().getSimpleName());
					l.addPartyToModel(tfPartyName.getText(), cmbPartyPos.getValue().toString().toLowerCase());
				}
				tfPartyName.clear();
				cmbPartyPos.setValue(null);
			}
		});

		gpAddParty.add(lblPartyName, 0, 0);
		gpAddParty.add(tfPartyName, 1, 0);
		gpAddParty.add(lblPoliticalPos, 0, 1);
		gpAddParty.add(cmbPartyPos, 1, 1);
		gpAddParty.add(btnCreateParty, 0, 2);

// addCandidate
		Label lblPartyNameToAdd = new Label("Party Name:");
		TextField tfPartyNameToAdd = new TextField();
		Button btnAddCandidateToParty = new Button("Add Candidate");
		Label lblCitizenID = new Label("Citizen ID: ");
		TextField tfCitizenID = new TextField();

		btnAddCandidateToParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("heyCandidate");
				for (ViewListeners l : allListeners) {
					System.out.println(l.getClass().getSimpleName());
					l.addCandidateToModel(tfPartyNameToAdd.getText(), tfCitizenID.getText());
				}
				tfPartyNameToAdd.clear();
				tfCitizenID.clear();
			}
		});

		gpAddCandidate.add(lblPartyNameToAdd, 0, 0);
		gpAddCandidate.add(tfPartyNameToAdd, 1, 0);
		gpAddCandidate.add(lblCitizenID, 0, 1);
		gpAddCandidate.add(tfCitizenID, 1, 1);
		gpAddCandidate.add(btnAddCandidateToParty, 0, 2);

// elections
		Button btnDontVote = new Button("I don't want to vote");
		Label lblEnterId = new Label("Enter ID Number:");
		TextField tfElectionVoterID= new TextField();
		Button btnEnter = new Button("Submit");
		Button btnVote = new Button("Vote");
		btnClearText.setVisible(false);
		btnVote.setDisable(true);
		btnDontVote.setDisable(true);
		cmbPartyChoise.setDisable(true);
		lblDontExists.setVisible(false);
		lblHasVoted.setVisible(false);

		btnEnter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				btnVote.setDisable(false);
				btnDontVote.setDisable(false);
				cmbPartyChoise.setDisable(false);
				tfElectionVoterID.setDisable(true);
			}
		});
		
		btnDontVote.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				allListeners.get(0).setElectChoice(tfElectionVoterID.getText(),null);
				btnVote.setDisable(true);
				btnDontVote.setDisable(true);
				cmbPartyChoise.setDisable(true);
				tfElectionVoterID.clear();
				cmbPartyChoise.setValue(null);
				tfElectionVoterID.setDisable(false);

			}
		});
		btnVote.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				allListeners.get(0).setElectChoice(tfElectionVoterID.getText(),cmbPartyChoise.getValue().toString());
				btnVote.setDisable(true);
				btnDontVote.setDisable(true);
				cmbPartyChoise.setDisable(true);
				tfElectionVoterID.clear();
				cmbPartyChoise.setValue(null);
				tfElectionVoterID.setDisable(false);
			}
		});
		

		gpElections.add(lblEnterId, 0, 0);
		gpElections.add(tfElectionVoterID,1,0);
		gpElections.add(btnEnter, 2, 0);
		gpElections.add(lblPartyOptions, 0, 2);
		gpElections.add(cmbPartyChoise, 0, 4);
		gpElections.add(btnVote, 2, 4);
		gpElections.add(btnDontVote,3, 4);
		gpElections.add(lblHasVoted,0, 5,3,1);
		gpElections.add(lblDontExists,0, 6,3,1);

// adding children to boxes
		vbRoot.getChildren().addAll(btnAddBallotBox, btnAddCitizen, btnAddPoliticalParty, btnAddCandidate,
				btnShowBallotBoxLists, btnShowCitizensLists, btnShowPartyLists, btnElections, btnElectionsResults);

		hbRootTop.getChildren().addAll(lblTopHeadline, lblDate);
		hbRootBottom.getChildren().addAll(lblBottomNames, btnClearText, btnExit);

// center
		spRootMiddle.getChildren().addAll(gpAddBallotBox, gpAddCitizen, gpAddParty, gpAddCandidate, scpShowBallotBox,
				scpShowCitizen, scpShowParty, gpElections, vbElectionsResults);
		gpAddBallotBox.setVisible(false);
		gpAddCitizen.setVisible(false);
		gpAddParty.setVisible(false);
		gpAddCandidate.setVisible(false);
		scpShowBallotBox.setVisible(false);
		scpShowCitizen.setVisible(false);
		scpShowParty.setVisible(false);
		vbElectionsResults.setVisible(false);
		gpElections.setVisible(false);

		brRootBox.setLeft(vbRoot);
		brRootBox.setCenter(spRootMiddle);
		brRootBox.setTop(hbRootTop);
		brRootBox.setBottom(hbRootBottom);

		Scene scene = new Scene(brRootBox, 700, 450);
		theStage.setScene(scene);
		theStage.show();
	}

	public void registerListeners(ViewListeners newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

	}

	public void setlblInvalidID(String idNumber) {
		lblInvalidID.setVisible(true);
		lblInvalidID.setText("the ID entered is not valid, the fixed ID is: "+idNumber);
		
		
		
	}

	public void hasVoted(String id) {
		lblHasVoted.setText(id+" has Voted");
		lblHasVoted.setVisible(true);
		
	}

	public void dontExists(String id) {
		lblDontExists.setText(id+" Does Not Exists, Please Enter a Valid ID");
		lblDontExists.setVisible(true);
	}

	
}
