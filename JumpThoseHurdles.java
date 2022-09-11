import kareltherobot.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class JumpThoseHurdles {
  // Find the hurdle with the max height!!
  // This is the Robot we will use to jump the hurdles
  // hurdler.frontIsClear() determines if it can take a step
  Robot hurdler = new Robot(1, 1, Directions.East, 0);

  public static void main(String[] args) {
    new JumpThoseHurdles().start();
  }

  ArrayList<Integer> length = new ArrayList<Integer>();

  ArrayList<Integer> height = new ArrayList<Integer>();

  public void start() {
    loadWorld();

  }
  
  private void loadWorld() {
    try (Scanner input = new Scanner(System.in)) {
      System.out.println("Enter a letter a, b, c, or d");
      String letter = input.nextLine();
      String worldName = ("world" + letter + ".wld");
      World.readWorld(worldName);
      World.setVisible(true);
      World.setDelay(0);
    }

	if (hurdler.nextToABeeper()) {
		hurdler.turnOff();
		;
	  }
	else {

	while (hurdler.nextToABeeper() == false) {

		findHurdle();
		climbHurdle();
		clearHurdle();
	}
}


    if (height.size() == 0) {
    System.out.println("You haven't done any hurdles...");
    System.exit(0);
    }
	else {
	System.out.println("The minimum distance between all of the hurdles is " + Collections.min(length) + " blocks long.");
	System.out.println("The max height of all the hurdles is " + Collections.max(height) + " blocks long.");
	;
	}
    
      
    double total = 0;
    double avg;
    for (int i = 0; i < height.size(); i++) 
        total += height.get(i);
        avg = total / (height.size());
    

    if (length.size() == 0 || height.size() == 0) {
      System.out.println("You haven't done any hurdles...");
      System.exit(0);
    }
    else {
      System.out.println("The Average height of the hurdle is: " + avg + " blocks long");
      System.exit(0);
      }

    }

  /**
   * This method assumes the Robot is named hurdler and is facing East This moves
   * hurdler to the next wall (hurdle). It returns the number of moves it took to
   * get to the hurdle
   */
  private int findHurdle() {

    int num = 0;

    while (hurdler.frontIsClear() & hurdler.nextToABeeper() == false) {
        hurdler.move();
        num += 1;
      
    }
    length.add(num + 1);

    return 0;
  }

  /**
   * This method assumes the Robot is named hurdler, is facing East and is at the
   * base of the hurdle. This moves the Robot to the top of the hurdle so that it
   * can clear the wall.
   * 
   * @return The number of steps to get above the hurdle (height)
   */
  private int climbHurdle() {

    while (true) {
      if (hurdler.nextToABeeper() == false & hurdler.frontIsClear() == false) {

        hurdler.turnLeft();
        hurdler.move();
        hurdler.turnLeft();
        hurdler.turnLeft();
        hurdler.turnLeft();
      } else {
        break;
      }
    }

    return 0;
  }

  /**
   * Moves the Robot (hurdler) over the wall and moves it to the ground so that
   * the Robot has its back to the hurdle and is facing the next one.
   */
  private void clearHurdle() {

    int count = 0;

    if (hurdler.nextToABeeper() == false) {
      hurdler.move();
      hurdler.turnLeft();
      hurdler.turnLeft();
      hurdler.turnLeft();
    } else {
      ;
    }

    for (int i = 0; i <= count; count++) {
      if (hurdler.nextToABeeper() == false & hurdler.frontIsClear()) {
        hurdler.move();
      } else {
        hurdler.turnLeft();
        break;
      }
    }
    height.add(count);
  }
}