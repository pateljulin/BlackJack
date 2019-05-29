/**
 *
 * This class is intended to be an object representing each individual player. Whenever, a player is added
 * a new object of Player type will be created
 *
 * */
import java.util.Scanner;

public class Player extends Person {

    boolean stay = false;
    int rank = 0;
    int totalWins = 0;  //players will each have a total number of wins to determine their rank

    //function has user decide if they will hit or stay
    public boolean hitOrNo() {
        if(stay)
            return false;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Would you like to Hit or Stay: ");
        System.out.println("1: Hit \n2: Stay");
        int answer = userInput.nextInt();

        if (answer == 1)
            return true;
        else
            stay = true;
            return false;
    }

    //Set players rank
    /**
    public static void setPlayerRank(String fileName, String playerName, int rank)
    {
        try {
            FileWriter fileWriter = new FileWriter(fileName,true);
            BufferedWriter fileOut = new BufferedWriter(fileWriter);

            fileOut.write(playerName + " " + rank + "\n");
            fileOut.close();
        }
        catch (Exception e)
        {
            System.out.println(e + " Error writing to file.");
        }
    }
     **/

}
