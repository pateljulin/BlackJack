/**
 *
 * This is meant to be the driver class for the whole game. The main method is located here.
 *
 *
 * */


import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private static final String FILE_NAME = "playerRanks.txt";

    //update or add player to rank and update rank text file
    public static boolean addRank(HashMap rankMap, String playerName, boolean win)
    /**
     * This method is intended to add players into a hashmap and then store the data from that hashmap into a file.
     * */
    {
        if (win)
        {
            //if the player is in the ranks then update their win rank by 1
            if (rankMap.containsKey(playerName))
            {
                rankMap.put(playerName, (int)rankMap.get(playerName) + 1);
            }
            //if the player is not in the ranks then add them in the ranks
            else if (!(rankMap.containsKey(playerName)))
            {
                rankMap.put(playerName, 1);
            }
        }
        else
        {
            //if player is in the ranks then degrade their rank by 1
            if (rankMap.containsKey(playerName))
            {
                rankMap.replace(playerName, rankMap.get(playerName), (int)rankMap.get(playerName) - 1);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter fileOut = new BufferedWriter(fileWriter);

            for (Object key : rankMap.keySet())
            {
                fileOut.write(key + " " + rankMap.get(key) + "\n");
            }

            fileOut.close();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e + " Error writing to file.");
            return false;
        }

    }

    //prints to the console all ranks of current players
    public static boolean getAllRanks()
    {
        String line = null;
        try
        {
            //Read text files
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No Rank File Found.");
            return false;
        }
        catch (Exception e)
        {
            System.out.println(e + " Error occurred when reading in Rank File.");
            return false;
        }
    }

    public static void main(String [] args)
    {
        Scanner userInput = new Scanner(System.in);
        HashMap<String, Integer> rankMap = new HashMap<>();
        boolean exitGame = false;
        boolean busted = false;
        int userOption = 0;
        while (!exitGame)
        {
            try
            {
                System.out.println("Please select from the menu option below: ");
                System.out.println("1: Play Game");
                System.out.println("2: Get Ranks");
                System.out.println("3. Exit Game.");

                userOption = userInput.nextInt();

            }
            catch (Exception e)
            {
                System.out.println("Incorrect Input. Please choose a valid selection");
                userInput.nextLine();
            }

            //user has selected to play the game.
            if (userOption == 1)
            {
                System.out.println("Please enter players name: ");
                Person person = new Player();
                Player player = (Player) person;
                player.setName(userInput.next());
                Dealer dealer = new Dealer();
                dealer.setName("Dealer");

                System.out.println("Please enter buy in amount: ");
                player.bet(userInput.nextInt());
                dealer.bet(player.getTotalMoney());

                Random random = new Random();
                //random card generated. One for player and one for dealer
                for (int i = 0; i < 2; i++)
                {
                    int randomCard = random.nextInt(11) + 1;
                    Card card = new Card();
                    card.setFaceValue(randomCard);
                    player.recieveCard(card);

                    randomCard = random.nextInt(11) + 1;
                    Card dealerCard = new Card();
                    dealerCard.setFaceValue(randomCard);
                    dealer.recieveCard(dealerCard);

                }

                System.out.print("Player Cards: "); player.getCards();
                System.out.println();


                //until both players decide to stay or one player busts keeping asking to hit or stay
                while (!(player.stay && dealer.stay))
                {
                    if(player.hitOrNo())
                    {
                        int randomCard = random.nextInt(11) + 1;
                        Card card = new Card();
                        card.setFaceValue(randomCard);
                        player.recieveCard(card);
                    }
                    if (!(player.stay))
                        System.out.print("Player Cards: "); player.getCards(); System.out.println();
                    if(dealer.hitOrNo())
                    {
                        int randomCard = random.nextInt(11) + 1;
                        Card dealerCard = new Card();
                        dealerCard.setFaceValue(randomCard);
                        dealer.recieveCard(dealerCard);
                    }

                    if(player.getCardTotal() > 21)
                    {
                        System.out.println("Player Bust. Dealer Wins.");
                        addRank(rankMap,player.getName(),false);    //Modify players ranking
                        busted = true;
                        break;
                    }
                    else if (dealer.getCardTotal() > 21)
                    {
                        System.out.println("Dealer bust. Player Wins.");
                        addRank(rankMap,player.getName(),true); //modify players ranking
                        busted = true;
                        break;
                    }
                }

                if (!busted)
                {
                    //if neither player nor dealer busts, determine who wins.
                    if (player.getCardTotal() > dealer.getCardTotal())
                    {
                        System.out.println("Player wins.");
                        //increment the players points.
                        player.totalWins += 1;
                        //set that players rank within the file.
                        addRank(rankMap,player.getName(),true);    //Modify players ranking

                    }
                    else if (player.getCardTotal() == dealer.getCardTotal())
                    {
                        System.out.println("Player wins.");
                        //increment the players points.
                        player.totalWins += 1;
                        //set that players rank within the file.
                        addRank(rankMap,player.getName(),true);    //Modify players ranking
                    }
                    else
                    {
                        System.out.println("Dealer wins.");
                        addRank(rankMap,player.getName(),false);    //Modify players ranking

                    }


                }

                //Show both hands at the end.
                System.out.println("Player Total: " + player.getCardTotal());
                System.out.println("Dealer Total: " + dealer.getCardTotal());
                busted = false;

            }

            //user has selected to view all Player Ranks.
            else if (userOption == 2)
            {
                System.out.println("Player Ranks: ");
                getAllRanks();
            }

            //user has selected to exit the game
            else if (userOption == 3)
            {
                System.out.println("Thank You! GoodBye!");
                return;
            }
            //user has selected an incorrect input
            else if(userOption == 0)
            {

            }
            else
            {
                System.out.println("Incorrect Input! Try Again! \n");
            }


        }



    }
}
