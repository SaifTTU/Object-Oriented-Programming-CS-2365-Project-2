import java.util.*;
public class UnoWorkout {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a Scanner object
        System.out.println("How many decks?");
        int d = sc.nextInt(); // Read user input
        System.out.println("Number of Decks: " + d); // Output user input
        System.out.println("How many players?");
        int p = sc.nextInt(); // Read user input
        System.out.println("Number of players: " + p); // Output user input

        int numberOfCards = 108;
        int numberOfPlayers = 1;
        
        numberOfCards = numberOfCards * d;
        numberOfPlayers = numberOfPlayers * p;

        Card cardArray[] = new Card[numberOfCards];
        Player player[] = new Player[numberOfPlayers]; //number of players

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = new Card();
            cardArray[i].setData(i, 'R');
            System.out.print(cardArray[i].a + ", ");

        }
        
        for (int i = 0; i < player.length; i++) {
            player[i] = new Player();
        }

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i].showData(); //use this to show whats on one card
        }
        cardArray = shuffleDeck(cardArray); //use this to shuffle your array regardless of size
        displayDeck(cardArray); //use this to display what the current deck looks like

        showHand(cardArray, p); //


        Card fakeCard = new Card();
        fakeCard.a = 2;
        fakeCard.b = 'B';

        cardArray = addTo(cardArray, fakeCard); //add cards to the bottom of the deck
        
        int z=1;
        while(z!=0)
        {
           cardArray = removeFrom(cardArray, p, 7); //removing cards*numberof Players from the top of the deck
           System.out.println("\nNew Deck: ");
           displayDeck(cardArray);
           showHand(cardArray, p);
           updateHand(player,cardArray,p);
           System.out.println("\n1. Proceed... (Exit = 0)");
           z = sc.nextInt();
        }
        
        
         
        
        
    }

    public static void displayDeck(Card[] cardArray) {
        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i].showData();
        }
    }

    public static void showHand(Card[] cardArray, int players) {
        for (int j = 1; j <= players; j++) {
            if (players >= j)
                try {
                    System.out.print("\nPlayer " + j + "'s Hand: ");
                    for (int i = ((j - 1) * 7); i < (7 * j); i++) {

                        System.out.print((cardArray[i].a) + "" + (cardArray[i].b) + ", ");
                    }
                }
            catch (Exception e) {
                System.out.println("Not enough cards..");
            }
        }
    }

    public static Card[] shuffleDeck(Card[] cardArray) {
        List < Card > cardList = Arrays.asList(cardArray); //card Array to to cardList, shuffled and then back to Array 

        Collections.shuffle(cardList);

        cardList.toArray(cardArray);

        System.out.println("\nShuffling deck... ");

        return cardArray;
    }

    public static Card[] addTo(Card[] cardArray, Card fakeCard) {
        System.out.println("\nAdding fake card with values " + fakeCard.a + " and " + fakeCard.b + " to the back of the deck");
        Card[] newArray = new Card[cardArray.length + 1];
        for (int i = 0; i < cardArray.length; i++) {
            newArray[i] = cardArray[i];
        }
        newArray[cardArray.length] = fakeCard;
        return newArray;
    }

    public static Card[] removeFrom(Card[] cardArray, int people, int hand) {
        System.out.println("Removing the following " + (people * 7) + " cards from the top of the deck: ");

        int amountToRemove = people * hand;
        try {
            Card[] newArray = new Card[cardArray.length - amountToRemove];
            for (int i = 0; i < amountToRemove; i++) {
                System.out.print((cardArray[i].a) + "" + (cardArray[i].b) + ", ");
            }
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = cardArray[i + amountToRemove];
            }
            return newArray;
        } catch (Exception e) {
            System.out.println("Out of cards 3. Start Again");
            return cardArray;
        }
    }
    public static Player[] updateHand(Player player[], Card[] cardArray, int numberOfPlayers) {
       try {
          for(int j=0; j<numberOfPlayers;j++){
                for(int i=j*7; i<(player[j].b.length +j*7);i++){
                     player[j].b[i%7]=cardArray[i].a;
                     //System.out.print(cardArray[i].b);
                     player[j].c[i%7]=cardArray[i].b;
                }
          }
          return player;
       }
       catch (Exception e) {
            System.out.println("Out of cards! 3. Start Again");
            return player;
        }
    }
    
}

class Card {
    int a;
    char b;

    public void setData(int c, char d) {
        a = c;
        b = d;
    }

    public void showData() {
        System.out.print(a);
        System.out.print(b + ", ");
    }

}

class Player {
    int a; //player number
    int b[] = new int[7]; //card array with numbers
    char c[] = new char[7]; //card array with color chars

}




