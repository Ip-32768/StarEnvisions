package application;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application implements EventHandler<ActionEvent> {
	Stage SEWindow;
	Scene sceneIntroduction, sceneMenu, sceneCombat, sceneLost, sceneShop, sceneInventory;
	Button Head, Body, LeftArm, RightArm, LeftLeg, RightLeg, Push, Pull;
	Label PlayerUpdate, EnemyUpdate, PlayerHealthStatus, StranderHealthStatus, KurtzLabel, ShopActionIndication;
	Label MedkitIL, EpinephrineIL;
	double StranderHealth  = 30;
	double StranderDamage = 3;
	double PlayerHealth = 100;
	double PlayerDamage = 5;
	int Kurtz = 15;
	boolean PlayerStandingPosition = true;
	boolean EnemyStandingPosition = true;
	boolean AUX45_CombatKnife = false;
	boolean AUX45_CombatKnifePermission = false;
	int Medkit, Epinephrine = 0;
	int IncreasedIntuition = 0;
	Group CombatS;
	ImageView PlayerCharacter, PlayerCharacter_KnifeEquipped, PlayerCharacter_Shrug;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		SEWindow = primaryStage;
		SEWindow.setTitle("Authena");
		
		//Scene 1: Introduction
		Label IntroductionDialog = new Label();
		IntroductionDialog.setText("Greetings Pilot, there has been complications within the star system Authena.\nThere are rampent pirate attacks in the area, which has dismayed local and \nindustrial traders. These bands of pirates have attacked individual starseekers to\ncargo frieghters, that's why were her. Traders, locals, and starseekers have banded\ntogether to fund the Authean Confedration, which aims to seize and protect the\norder of Authena.\n\nThat's why we need you! We are willing to recruit, train, and equip the saviors of\nAuthena.\n\nWhat do you say, pilot?");
		//IntroductionDialog.setWrapText(true);
		IntroductionDialog.setLayoutX(30);
		IntroductionDialog.setLayoutY(50);
		
		Button Conformation = new Button();
		Conformation.setText("I'm ready.");
		Conformation.setLayoutX(380);
		Conformation.setLayoutY(400);
		Conformation.setOnAction(e -> {
			System.out.println(Conformation);
			SEWindow.setScene(sceneMenu);
		});
		
		Button Denial = new Button();
		Denial.setText("Not right now");
		Denial.setOnAction(e -> {
			System.out.println("Denial");
			SEWindow.close();
		});
		Denial.setLayoutX(380);
		Denial.setLayoutY(435);
		
		Group Introduction = new Group();
		Introduction.getChildren().add(IntroductionDialog);
		Introduction.getChildren().add(Conformation);
		Introduction.getChildren().add(Denial);
		sceneIntroduction = new Scene(Introduction, 500, 500);
		
		SEWindow = primaryStage;
		SEWindow.setTitle("Authena");
		SEWindow.setScene(sceneIntroduction);
		primaryStage.show();
		
		//Scene 2: Menu
		Label MenuTitle = new Label();
		MenuTitle.setText("     Menu\nWhat now?");
		MenuTitle.setLayoutX(200);
		MenuTitle.setLayoutY(50);
		
		Button Combat = new Button("Combat");
		Combat.setLayoutX(200);
		Combat.setLayoutY(150);
		Combat.setOnAction(e -> {
			System.out.println("Combat");
			SEWindow.setScene(sceneCombat);
		});
		
		Button Inventory = new Button("Inventory");
		Inventory.setLayoutX(200);
		Inventory.setLayoutY(200);
		Inventory.setOnAction(e -> {
			System.out.println("Inventory");
			SEWindow.setScene(sceneInventory);
		});
		
		Button Shop = new Button("Shop");
		Shop.setLayoutX(200);
		Shop.setLayoutY(250);
		Shop.setOnAction(e -> {
			System.out.println("Shop");
			SEWindow.setScene(sceneShop);
			ShopActionIndication.setText("Terminal: Greetings Customer!");
			ShopActionIndication.setLayoutX(175);
		});
		
		Group Menu = new Group();
		Menu.getChildren().add(MenuTitle);
		Menu.getChildren().add(Combat);
		Menu.getChildren().add(Inventory);
		Menu.getChildren().add(Shop);
		sceneMenu = new Scene(Menu, 500, 500);
		
		//Scene 6: Inventory
		
		Label InventoryLabel = new Label();
		InventoryLabel.setText("Inventory");
		InventoryLabel.setLayoutX(220);
		InventoryLabel.setLayoutY(25);
		
		Button MedkitII = new Button("Medkit");
		MedkitII.setLayoutX(50);
		MedkitII.setLayoutY(355);
		MedkitII.setOnAction(e -> {
			if(Medkit >= 1 && PlayerHealth < 100) {
				Medkit--;
				MedkitIL.setText("Medkit: " + Medkit);
				PlayerHealth = PlayerHealth + 35;
				while(PlayerHealth > 100) {
					PlayerHealth--;
				}
				System.out.println("Medkit Used");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			} else if(Medkit < 1) {
				System.out.println("There are no medkits available.");
			}
		});
		
		MedkitIL = new Label();
		MedkitIL.setText("Medkit: " + Medkit);
		MedkitIL.setLayoutX(52);
		MedkitIL.setLayoutY(340);
		
		Button EpinephrineII = new Button("Ephinephrine Injection");
		EpinephrineII.setLayoutX(150);
		EpinephrineII.setLayoutY(355);
		EpinephrineII.setOnAction(e -> {
			if(Epinephrine >= 1) {
				Epinephrine--;
				EpinephrineIL.setText("Epinephrine: " + Epinephrine);
				IncreasedIntuition = IncreasedIntuition + 3;
				System.out.println("-Epinephrine used-");
				System.out.println("Increased Intuition: " + IncreasedIntuition);
			} else if(Epinephrine < 1) {
				System.out.println("Epineprine not available");
			}
		});
		
		EpinephrineIL = new Label();
		EpinephrineIL.setText("Epinephrine: " + Epinephrine);
		EpinephrineIL.setLayoutX(178);
		EpinephrineIL.setLayoutY(340);
		
		Button AUX45_CKII = new Button("AUX45-CK");
		AUX45_CKII.setLayoutX(325);
		AUX45_CKII.setLayoutY(355);
		AUX45_CKII.setOnAction(e -> {
			if(AUX45_CombatKnifePermission == true) {
				if(AUX45_CombatKnife == false) {
					AUX45_CombatKnife = true;
					System.out.println("AUX-45 Combat Knife Equipped");
					PlayerDamage = PlayerDamage + 3;
					CombatS.getChildren().remove(PlayerCharacter);
					CombatS.getChildren().add(PlayerCharacter_KnifeEquipped);
				} else if (AUX45_CombatKnife == true) {
					AUX45_CombatKnife = false;
					System.out.println("AUX-45 Combat Knife Unequipped");
					PlayerDamage = PlayerDamage - 3;
					CombatS.getChildren().remove(PlayerCharacter_KnifeEquipped);
					CombatS.getChildren().add(PlayerCharacter);
				}
			} else {
				System.out.println("AUX-45 Combat Knife is not available");
			}
		});
		
		Button ReturnToMenuFI = new Button();
		ReturnToMenuFI.setText("Return");
		ReturnToMenuFI.setLayoutX(15);
		ReturnToMenuFI.setLayoutY(15);
		ReturnToMenuFI.setOnAction(e -> {
			System.out.println("Return to Menu from Inventory");
			SEWindow.setScene(sceneMenu);
		});
		
		Group InventoryLayout = new Group();
		InventoryLayout.getChildren().add(InventoryLabel);
		InventoryLayout.getChildren().add(ReturnToMenuFI);
		InventoryLayout.getChildren().add(MedkitII);
		InventoryLayout.getChildren().add(EpinephrineII);
		InventoryLayout.getChildren().add(AUX45_CKII);
		InventoryLayout.getChildren().add(EpinephrineIL);
		InventoryLayout.getChildren().add(MedkitIL);
		sceneInventory = new Scene(InventoryLayout, 500, 500);
		
		//Scene 5: Shop
		Label T400 = new Label("T-400");
		T400.setLayoutX(220);
		T400.setLayoutY(25);
		
		ImageView SAM = new ImageView(new Image(getClass().getResourceAsStream("/Textures/S.A.M..jpg")));
		SAM.setLayoutX(0);
		SAM.setLayoutY(40);
		Group ShopLayout = new Group();
		
		KurtzLabel = new Label("Kurtz: " + Kurtz);
		KurtzLabel.setLayoutX(385);
		KurtzLabel.setLayoutY(45);
		
		ShopActionIndication = new Label("Termianl: Greetings Customer!");
		ShopActionIndication.setLayoutX(175);
		ShopActionIndication.setLayoutY(480);
		
		Label MKPrice = new Label("25 Kurtz");
		MKPrice.setLayoutX(55);
		MKPrice.setLayoutY(338);
		
		Button MedkitLabel = new Button("Medkit");
		MedkitLabel.setLayoutX(50);
		MedkitLabel.setLayoutY(355);
		MedkitLabel.setOnAction(e -> {
			if(Kurtz >= 25) {
				System.out.println("MedkitAcquired");
				Kurtz = Kurtz - 25;
				System.out.println("Kurtz: " + Kurtz);
				KurtzLabel.setText("Kurtz: " + Kurtz);
				Medkit++;
				MedkitIL.setText("Medkit: " + Medkit);
				System.out.println("Medkit Acquired");
				ShopActionIndication.setText("Termial: -Medkit Acquired-");
				ShopActionIndication.setLayoutY(480);
			} else {
				System.out.println("-Terminal: Kurtz below expected price point-");
				ShopActionIndication.setText("Terminal: Kurtz below expected price point");
				ShopActionIndication.setLayoutX(135);
			}
		});
		
		Label EIPrice = new Label("20 Kurtz");
		EIPrice.setLayoutX(170);
		EIPrice.setLayoutY(338);
		
		//Allows for two moves before enemy's move (at the cost of 5 health on use)
		Button EpinephrineLabel = new Button("Ephinephrine Injection");
		EpinephrineLabel.setLayoutX(125);
		EpinephrineLabel.setLayoutY(355);
		EpinephrineLabel.setOnAction(e -> {
			if(Kurtz >= 20) {
				Kurtz = Kurtz - 20;
				KurtzLabel.setText("Kurtz: " + Kurtz);
				System.out.println("Terminal: -Epinephrine Acquired-");
				ShopActionIndication.setText("Terminal: -Epinephrine Acquired");
				Epinephrine++;
				EpinephrineIL.setText("Epinephrine: " + Epinephrine);
			} else {
				System.out.println("Terminal: -Kurtz below expected price point-");
				ShopActionIndication.setText("Terminal: Kurtz below expected price point");
				ShopActionIndication.setLayoutX(135);
			}
		});
		
		Label AUX45_CK_Price = new Label("50 Kurtz");
		AUX45_CK_Price.setLayoutX(320);
		AUX45_CK_Price.setLayoutY(338);
		
		Button AUX45_CK = new Button("AUX-45 Combat Knife");
		AUX45_CK.setLayoutX(281);
		AUX45_CK.setLayoutY(355);
		AUX45_CK.setOnAction(e -> {
			if(Kurtz >= 50) {
				Kurtz = Kurtz - 50;
				KurtzLabel.setText("Kurtz: " + Kurtz);
				System.out.println("Terminal: -Auxilary-45 Combat Knife Acquired-");
				AUX45_CombatKnifePermission = true;
				ShopLayout.getChildren().remove(AUX45_CK);
				ShopLayout.getChildren().remove(AUX45_CK_Price);
			} else {
				System.out.println("Terminal: -Kurtz below expected price point-");
				ShopActionIndication.setText("Terminal: Kurtz below expected price point");
				ShopActionIndication.setLayoutX(135);
			}
		});
		
		Button ReturnToMenuFS = new Button("Return");
		ReturnToMenuFS.setLayoutX(15);
		ReturnToMenuFS.setLayoutY(15);
		ReturnToMenuFS.setOnAction(e -> {
			System.out.println("Return to Menu from Shop");
			SEWindow.setScene(sceneMenu);
		});
		
		ShopLayout.getChildren().add(T400);
		ShopLayout.getChildren().add(SAM);
		ShopLayout.getChildren().add(KurtzLabel);
		ShopLayout.getChildren().add(ReturnToMenuFS);
		ShopLayout.getChildren().add(ShopActionIndication);
		ShopLayout.getChildren().add(EIPrice);
		ShopLayout.getChildren().add(EpinephrineLabel);
		ShopLayout.getChildren().add(MKPrice);
		ShopLayout.getChildren().add(AUX45_CK_Price);
		ShopLayout.getChildren().add(AUX45_CK);
		ShopLayout.getChildren().add(MedkitLabel);
		
		sceneShop = new Scene(ShopLayout, 500, 500);
		
		//Scene 3: Combat
		
		CombatS = new Group();
		
		PlayerCharacter = new ImageView(new Image(getClass().getResourceAsStream("/Textures/PC.png")));
		PlayerCharacter.setLayoutX(75);
		PlayerCharacter.setLayoutY(185);
		
		PlayerCharacter_KnifeEquipped = new ImageView(new Image(getClass().getResourceAsStream("/Textures/PC (Knife Equipped).png")));	
		PlayerCharacter_KnifeEquipped.setLayoutX(75);
		PlayerCharacter_KnifeEquipped.setLayoutY(185);
		
		PlayerCharacter_Shrug = new ImageView(new Image(getClass().getResourceAsStream("/Textures/PC_Shrug.gif")));
		PlayerCharacter_Shrug.setLayoutX(75);
		PlayerCharacter_Shrug.setLayoutY(185);
		PlayerCharacter_Shrug.setFitHeight(96);
		PlayerCharacter_Shrug.setFitWidth(96);
		
		AnimationTimer TimerI = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				System.out.println("-Animation Timer is running-");
			}
			
		};
		
		Timer timer = new Timer();
		
		//Timerline timer_FA = new Timeline();
		
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("-Timer is running-");
				CombatS.getChildren().remove(PlayerCharacter_Shrug);
				CombatS.getChildren().add(PlayerCharacter);
			}
		};
		
		Button Shrug = new Button("Shurg");
		Shrug.setLayoutX(100);
		Shrug.setLayoutY(325);
		Shrug.setOnAction(e -> {
			CombatS.getChildren().remove(PlayerCharacter);
			CombatS.getChildren().add(PlayerCharacter_Shrug);
			timer.schedule(task, 1000);
		});
		
		Label CombatLabel = new Label("Encounter!");
		CombatLabel.setLayoutX(207);
		CombatLabel.setLayoutY(10);
		
		PlayerHealthStatus = new Label();
		PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
		PlayerHealthStatus.setLayoutX(25);
		PlayerHealthStatus.setLayoutY(40);
		
		StranderHealthStatus = new Label();
		StranderHealthStatus.setText("Strander: " + StranderHealth);
		StranderHealthStatus.setLayoutX(300);
		StranderHealthStatus.setLayoutY(40);
		
		PlayerUpdate = new Label("Player Status");
		PlayerUpdate.setLayoutX(25);
		PlayerUpdate.setLayoutY(55);
		
		EnemyUpdate = new Label("Enemy Status");
		EnemyUpdate.setLayoutX(300);
		EnemyUpdate.setLayoutY(55);
		
		Head = new Button("Head");
		Head.setLayoutX(225);
		Head.setLayoutY(350);
		Head.setOnAction(this);
		
		Body = new Button("Body");
		Body.setLayoutX(225);
		Body.setLayoutY(400);
		Body.setOnAction(this);
		
		LeftArm = new Button("Left Arm");
		LeftArm.setLayoutX(125);
		LeftArm.setLayoutY(385);
		LeftArm.setOnAction(this);
		
		RightArm = new Button("Right Arm");
		RightArm.setLayoutX(305);
		RightArm.setLayoutY(385);
		RightArm.setOnAction(this);
		
		LeftLeg = new Button("Left Leg");
		LeftLeg.setLayoutX(150);
		LeftLeg.setLayoutY(435);
		LeftLeg.setOnAction(this);
		
		RightLeg = new Button("Right Leg");
		RightLeg.setLayoutX(285);
		RightLeg.setLayoutY(435);
		RightLeg.setOnAction(this);
		
		Push = new Button("Push");
		Push.setLayoutX(50);
		Push.setLayoutY(325);
		Push.setOnAction(this);
		
		Button Loose = new Button();
		Loose.setOnAction(e -> {
			SEWindow.setScene(sceneLost);
		});
		
		
		CombatS.getChildren().add(CombatLabel);
		CombatS.getChildren().add(Head);
		CombatS.getChildren().add(Body);
		CombatS.getChildren().add(LeftArm);
		CombatS.getChildren().add(RightArm);
		CombatS.getChildren().add(LeftLeg);
		CombatS.getChildren().add(RightLeg);
		CombatS.getChildren().add(Push);
		CombatS.getChildren().add(PlayerUpdate);
		CombatS.getChildren().add(PlayerHealthStatus);
		CombatS.getChildren().add(StranderHealthStatus);
		CombatS.getChildren().add(EnemyUpdate);
		CombatS.getChildren().add(Shrug);
		CombatS.getChildren().add(PlayerCharacter);
		//CombatS.getChildren().add(PlayerCharacter_Shrug);
		CombatS.getChildren().add(Loose);
		sceneCombat = new Scene(CombatS, 500, 500);
		
		//Scene 4: Lost
		
		Label LostIndication = new Label("You were killed in the Mezzorak wasteland.");
		LostIndication.setLayoutX(132);
		LostIndication.setLayoutY(230);
		
		Button Restart = new Button("Return to the wasteland");
		Restart.setLayoutX(175);
		Restart.setLayoutY(250);
		Restart.setOnAction(e -> {
			System.out.println("Restart");
			SEWindow.setScene(sceneIntroduction);
		});
		Group PlayerLost = new Group();
		PlayerLost.getChildren().add(LostIndication);
		PlayerLost.getChildren().add(Restart);
		sceneLost = new Scene(PlayerLost, 500, 500);
	}
	
	//Scene 3: Combat (Functions)
	
	@Override
	public void handle(ActionEvent event) {
		Random HC = new Random();
		int AppendageHitChance = HC.nextInt(2);
		int HeadHitChance = HC.nextInt(4);
		int BodyHitChance = HC.nextInt(5);
		int PushChance = HC.nextInt(10);
		int KurtzLoot = 5+HC.nextInt(45);
		int StranderAttack = HC.nextInt(5);
		int StranderAppendageHitChance = HC.nextInt(2);
		int StranderHeadHitChance = HC.nextInt(4);
		int StranderBodyHitChance = HC.nextInt(5);
		
		//Player's Turn
		
		//Push - 40%
		if(event.getSource()==Push) {
			switch(PushChance) {
			case 0:
			case 1:
			case 2:
				EnemyStandingPosition = false;
				System.out.println("You push the Strander to the ground");
			break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				System.out.println("You attempt to push the Strander to the ground, but fail");
			break;
			}
		}
		
		//Head - 25%
		if(event.getSource()==Head) {
			//Hit
			if(HeadHitChance == 0) {
				if(IncreasedIntuition > 0) {
					StranderHealth = StranderHealth - ((PlayerDamage*3) * 1.5);
					Epinephrine--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
					System.out.println("Execution of a critical hit was carried out");
					System.out.println("Strander: " + StranderHealth);
					PlayerUpdate.setText("Execution of a critical hit was carried out");
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				} else {
					StranderHealth = StranderHealth - (PlayerDamage*3);
					System.out.println("Execution of a critical hit was carried out");
					System.out.println("Strander: " + StranderHealth);
					PlayerUpdate.setText("Execution of a critical hit was carried out");
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			//Miss
			} else if(HeadHitChance > 0) {
				System.out.println("You aim for the head, but miss");
				PlayerUpdate.setText("You aim for the head, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
			}
		}
		
		//Body - 80%
		if(event.getSource()==Body) {
			//Miss
			if(BodyHitChance > 3) {
				System.out.println("You aim for the body, but miss");
				PlayerUpdate.setText("You aim for the body, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
			//Hit
			}else if(BodyHitChance <= 3) {
				if(IncreasedIntuition > 0) {
					StranderHealth = StranderHealth - (PlayerDamage*1.5);
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
					System.out.println("Strander: " + StranderHealth);
					System.out.println("You succesfully hit the body");
					PlayerUpdate.setText("You succesfully hit the body");
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				} else {
					StranderHealth = StranderHealth - PlayerDamage;
					System.out.println("Strander: " + StranderHealth);
					System.out.println("You succesfully hit the body");
					PlayerUpdate.setText("You succesfully hit the body");
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			}
		}
		
		//LeftArm - 50%
		//Miss
		if(event.getSource()==LeftArm) {
			if(AppendageHitChance == 0) {
				System.out.println("You aim for the left arm, but miss");
				PlayerUpdate.setText("You aim for the left arm, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
		//Hit
		}else if(AppendageHitChance == 1) {
			if(IncreasedIntuition > 0) {
				StranderHealth = StranderHealth - ((PlayerDamage*1.5) * 1.5);
				System.out.println("You succesfully hit the left arm");
				PlayerUpdate.setText("You succesfully hit the left arm");
				System.out.println("Strander: " + StranderHealth);
				StranderHealthStatus.setText("Strander: " + StranderHealth);
				IncreasedIntuition--;
				System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				} else {
					StranderHealth = StranderHealth - (PlayerDamage*1.5);
					System.out.println("You succesfully hit the left arm");
					PlayerUpdate.setText("You succesfully hit the left arm");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			}
		}
		
		//RightArm - 50%
		if(event.getSource()==RightArm) {
			if(AppendageHitChance==0) {
				System.out.println("You aim for the right arm, but miss");
				PlayerUpdate.setText("You aim for the right arm, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
			}else if(AppendageHitChance==1) {
				if(IncreasedIntuition > 0) {
					StranderHealth = StranderHealth - ((PlayerDamage*1.5) * 1.5);
					System.out.println("You succesfully hit the right arm");
					PlayerUpdate.setText("You succesfully hit the right arm");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				} else {
					StranderHealth = StranderHealth - (PlayerDamage*1.5);
					System.out.println("You succesfully hit the right arm");
					PlayerUpdate.setText("You succesfully hit the right arm");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			}
		}
		
		//LeftLeg - 50%
		if(event.getSource()==LeftLeg) {
			if(AppendageHitChance==0) {
				System.out.println("You aim for the left leg, but miss");
				PlayerUpdate.setText("You aim for the left leg, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
			}else if(AppendageHitChance==1) {
				if(IncreasedIntuition > 0) {
					StranderHealth = StranderHealth - ((PlayerDamage*1.5 * 1.5));
					System.out.println("You succesfully hit the left leg");
					PlayerUpdate.setText("You succesfully hit the left leg");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				} else {
					StranderHealth = StranderHealth - (PlayerDamage*1.5);
					System.out.println("You succesfully hit the left leg");
					PlayerUpdate.setText("You succesfully hit the left leg");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			}
		}
		
		//RightLeg - 50%
		if(event.getSource()==RightLeg) {
			//Miss
			if(AppendageHitChance==0) {
				System.out.println("You aim for the right arm, but miss");
				PlayerUpdate.setText("You aim for the right arm, but miss");
				if(IncreasedIntuition > 0) {
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				}
			//Hit	
			}else if(AppendageHitChance==1) {
				if(IncreasedIntuition > 0) {
					StranderHealth = StranderHealth - ((PlayerDamage*1.5) * 1.5);
					System.out.println("You succesfully hit the right leg");
					PlayerUpdate.setText("You succesfully hit the right leg");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
					IncreasedIntuition--;
					System.out.println("-Ephinephrine Used-");
					System.out.println("Epinephrine II: " + IncreasedIntuition);
				} else {
					StranderHealth = StranderHealth - (PlayerDamage*1.5);
					System.out.println("You succesfully hit the right leg");
					PlayerUpdate.setText("You succesfully hit the right leg");
					System.out.println("Strander: " + StranderHealth);
					StranderHealthStatus.setText("Strander: " + StranderHealth);
				}
			}
		}
		
		//Strander's Turn
		switch(StranderAttack) {
		//Strander Damage to Player's Head
		case 0:
			switch(StranderHeadHitChance) {
			case 0:
				PlayerHealth = PlayerHealth - (StranderDamage * 3);
				System.out.println("The Strander hit a critical to the head!");
				EnemyUpdate.setText("The Strander hit a critical hit \nto the head!");
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			case 1:
			case 2:
			case 3:
				System.out.println("The Strander swings at the head, but misses");
				EnemyUpdate.setText("The Strander swings at the head, \nbut misses");
			break;
			}
		break;
		//Strander Damage to Player's Body
		case 1:
			switch(StranderBodyHitChance) {
			case 0:
			case 1:
			case 2:
			case 3:
				PlayerHealth = PlayerHealth - StranderDamage;
				System.out.println("The Strander swipes at your body successfully");
				EnemyUpdate.setText("The Strander swipes at \nyour body successfully");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			case 4:
				System.out.println("The Strander swipes at your body, but misses");
				EnemyUpdate.setText("The Strander swipes at \nyour body, but misses");
			}
		break;
		//Strander Damage to Player's Left Arm
		case 2:
			switch(StranderAppendageHitChance) {
			case 0:
				System.out.println("The Strander swipes at your left arm, but misses");
				EnemyUpdate.setText("The Strander swipes at \nyour left arm, but misses");
			break;
			case 1:
				PlayerHealth = PlayerHealth - (StranderDamage * 1.5);
				System.out.println("The Strander successfully swipes at your left arm");
				EnemyUpdate.setText("The Strander successfully swipes at \nyour left arm");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			}
		break;
		//Strander Damage to Player's Right Arm
		case 3:
			switch(StranderAppendageHitChance) {
			case 0:
				System.out.println("The Strander swipes at your right arm, but misses");
				EnemyUpdate.setText("The Strander swipes at \nyour right arm, but misses");
			break;
			case 1:
				PlayerHealth = PlayerHealth - (StranderDamage * 1.5);
				System.out.println("The Strander successfully swipes at your right arm");
				EnemyUpdate.setText("The Strander successfully swipes at \nyour right arm");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			}
		break;
		//Strander Damage to Player's Left Leg
		case 4:
			switch(StranderAppendageHitChance) {
			case 0:
				System.out.println("The Strander swipes at your left leg, nbut misses");
				EnemyUpdate.setText("The Strander swipes at \nyour left leg, but misses");
			break;
			case 1:
				PlayerHealth = PlayerHealth - (StranderDamage * 1.5);
				System.out.println("The Strander successfully swipes at your left leg");
				EnemyUpdate.setText("The Strander successfully swipes at \nyour left leg");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			}
		break;
		//Strander Damage to Player's Right Leg
		case 5:
			switch(StranderAppendageHitChance) {
			case 0:
				System.out.println("The Strander swipes at your right leg, but misses");
				EnemyUpdate.setText("The Strander swipes at \nyour right leg, but misses");
			break;
			case 1:
				PlayerHealth = PlayerHealth - (StranderDamage * 1.5);
				System.out.println("The Strander successfully swipes at your right leg");
				EnemyUpdate.setText("The Strander successfully swipes at \nyour right leg");
				System.out.println("Pilot: " + PlayerHealth);
				PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			break;
			}
		break;
		}
		
		//Player has won
		if(StranderHealth <= 0) {
			System.out.println("Strander Defeated");
			StranderHealth = 30;
			Kurtz = Kurtz + KurtzLoot;
			System.out.println("Kurtz Rewarded: " + KurtzLoot);
			System.out.println("Kurtz: " + Kurtz);
			KurtzLabel.setText("Kurtz: " + Kurtz);
			PlayerUpdate.setText("Pilot Status");
			EnemyUpdate.setText("Strander Status");
			StranderHealthStatus.setText("Strander: " + StranderHealth);
			SEWindow.setScene(sceneMenu);
		}
	
		//Player has lost
		if(PlayerHealth <= 0) {
			PlayerHealth = 100;
			StranderHealth = 30;
			Kurtz = 0;
			KurtzLabel.setText("Kurtz: " + Kurtz);
			PlayerHealthStatus.setText("Pilot: " + PlayerHealth);
			StranderHealthStatus.setText("Strander: " + StranderHealth);
			System.out.print("Lost");
			SEWindow.setScene(sceneLost);
		}
	}
	
}
