import java.util.Scanner;
import java.util.Objects;
//import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;

public class MedievalGame {

  /* Instance Variables */
private Player player;
  /* Main Method */
  public static void main(String[] args) {
    
    Scanner console = new Scanner(System.in);
    MedievalGame game = new MedievalGame();

    game.player = game.start(console);

    game.addDelay(500);
    System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
    System.out.println(game.player);

    game.addDelay(1000);
    System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
    game.save();

    game.addDelay(2000);
    System.out.println("We just saved your game...");
    System.out.println("Now we are going to try to load your character to make sure the save worked...");

    game.addDelay(1000);
    System.out.println("Deleting character...");
    String charName = game.player.getName();
    game.player = null;

    game.addDelay(1500);
    game.player = game.load(charName, console);
    System.out.println("Loading character...");

    game.addDelay(2000);
    System.out.println("Now let's print out your character again to make sure everything loaded:");

    game.addDelay(500);
    System.out.println(game.player);
    
  } // End of main

  /* Instance Methods */
  private Player start(Scanner console) {
    // start functionality here
    Player player;
    //art=homeScreen called;
    Art.homeScreen();
    //Introduction
    System.out.println("Welcome to the new world of adventure!");
    System.out.println("Tell me good fella, other than your dream, have you been here before in reality? ");
    System.out.print("Enter 'y' to hop in game, 'n' to create a new adventure: ");
    String answer = console.next().toLowerCase();
    while (true) {
  addDelay(500);
    if(answer.equals("y")){
      System.out.print("\nLets Wake up your Sleeping buddy, Enter your last character name:");
      player = load(console.next(), console);
    break;
    }else if(answer.equals("n")){
       System.out.print("\nAhh you are new here,lets make you bigshot! Enter your new character name:");
       String newCharacter = console.next();
        while (true) {
      System.out.println("Welcome " + newCharacter + ", am I pronouncing that correctly? (Enter 'y' to confirm, 'n' to enter a new name");
      String nameResponse = console.next().toLowerCase();
      if (Objects.equals(nameResponse, "y")) break;
      System.out.println("So sorry, can you spell it for me again?");
      newCharacter = console.next();
    }
       player = new Player(newCharacter);
        break;
    }else{
      System.out.print("\nNahhhh you are newbie,to enter press 'y' or to create press'n':");
       answer = console.next().toLowerCase();
    }
    }
    return player;
  } // End of start

  private void save() {
    // save functionality here
    String fileName = player.getName()+".svr";
    try{
    FileOutputStream userSaveFile = new FileOutputStream(fileName);
    ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
    playerSaver.writeObject(this.player);
    playerSaver.close();
    }
    catch(IOException e){
      System.out.println("Unable to save the game properly! Please Try Again.");
    }
  } // End of save

  private Player load(String playerName, Scanner console) {
    // Add load functionality here
    Player loadedPlayer;
    try{
    FileInputStream userSaveFile = new FileInputStream(playerName+".svr");
    ObjectInputStream playerLoadFile = new ObjectInputStream(userSaveFile);
    loadedPlayer = (Player) playerLoadFile.readObject();
    playerLoadFile.close();
    }catch(IOException | ClassNotFoundException e){
      addDelay(1500);
      System.out.println("Something went wrong!...OOOhh man!...wait!!");
       System.out.println("If you're sure the spelling is correct, your character file may no longer exist, please reload the game if you'd like to try again.");
       System.out.println("Game must go on Dont you worry boy! we'll create you a new character with the name: " + playerName);
      addDelay(2000);
      loadedPlayer =  new Player(playerName);
    }
    return loadedPlayer;
  } // End of load

  // Adds a delay to the console so it seems like the computer is "thinking"
  // or "responding" like a human, not instantly like a computer.
  private void addDelay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}