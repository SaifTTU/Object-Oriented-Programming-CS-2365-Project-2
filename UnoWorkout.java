import java.util.*;
public class UnoWorkout {
    public static void main(String[] args) {
    
        Card fakeCardArray[] = new Card[384];
        fakeCardArray=createDeck(3, fakeCardArray); 
        
         
        Scanner sc = new Scanner(System.in); // Create a Scanner object
        System.out.println("\nHow many decks?");
        int d = sc.nextInt(); // Read user input
        System.out.println("Number of Decks: " + d); // Output user input
        System.out.println("How many players?");
        int p = sc.nextInt(); // Read user input
        System.out.println("Number of players: " + p); // Output user input

        int numberOfCards = 116;
        int numberOfPlayers = 1;
        
        numberOfCards = numberOfCards * d;
        numberOfPlayers = numberOfPlayers * p;
        
        
        Card cardArray[] = new Card[numberOfCards];
        System.out.println("\nThe number of cards: "+cardArray.length);
        cardArray=createDeck(d, cardArray);
        System.out.println("\nThe number of cards: "+cardArray.length);
        
        Player player[] = new Player[numberOfPlayers]; //number of players

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = new Card();
            cardArray[i].setData(fakeCardArray[i%(d*29)].a, fakeCardArray[i/(d)].b);
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
           showWorkOut(player);
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
                        if((cardArray[i].a)<=10)
                           System.out.print((cardArray[i].a) + "" + (cardArray[i].b) + ", ");
                        if((cardArray[i].a)==11) //E is for reverse (because R was already taken)
                           System.out.print("[Skip]"+(cardArray[i].b) +", ");
                        if((cardArray[i].a)==12) 
                           System.out.print("[D2]"+(cardArray[i].b) +", ");
                        if((cardArray[i].a)==13) 
                           System.out.print("[Reverse]"+(cardArray[i].b) +", ");
                        if((cardArray[i].a)==14) 
                           System.out.print("[WILD], ");
                        if((cardArray[i].a)==15) 
                           System.out.print("[WILD D4], ");
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
    
    public static void showWorkOut(Player[] player){
         int pushups = 0; //blue
         int squats = 0; //yellow
         int situps = 0; //red
         int lunges = 0; //green
         for(int i=0; i<player.length;i++){
               pushups = 0; //blue
               squats = 0; //yellow
               situps = 0; //red
               lunges = 0; //green
               System.out.println("\nPlayer "+(i+1)+":");
               for(int j=0; j<player[i].b.length; j++){
                  if(player[i].b[j]<11){
                     if(player[i].c[j]=='R'){
                           situps = situps + player[i].b[j];
                        }
                     if(player[i].c[j]=='B'){
                           pushups = pushups + player[i].b[j];  
                        }
                     if(player[i].c[j]=='Y'){
                           squats = squats + player[i].b[j];  
                        }
                     if(player[i].c[j]=='G'){
                           lunges = squats + player[i].b[j];  
                        }
                  }
               }
               System.out.println("Pushups: "+ pushups );
               System.out.println("Squats: "+ squats );
               System.out.println("Situps: "+ situps );
               System.out.println("Lunges: "+ lunges );
         }
    }
    
    public static Card[] createDeck(int d, Card[] cardArray){
         int a[] ={0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,15};
         char b[] ={'B','Y','R','G'};
         int numberOfDecks=d;
         
         Card oneDeck[]=new Card[a.length*4];
         Card card[]=new Card[a.length*4*numberOfDecks];
         for(int j=0; j<numberOfDecks;j++){
               System.out.println("\nDeck: "+(j+1));
               for(int i=0;i<oneDeck.length;i++){
                  oneDeck[i] = new Card();
                  card[i*j] = new Card();
                  //for(int j=0;j<4;j++){
                  oneDeck[i].setData(a[i%29],b[i/29]);
                  //System.out.print(oneDeck[i].a+""+oneDeck[i].b+", ");
                  card[i*j]=oneDeck[i];
                  if((card[i*j].a)<=10)
                     System.out.print(card[i*j].a+""+card[i*j].b+", ");
                        
                  
                  
                  if(card[i*j].a==11){//setup the skip card
                     //card[i*j].a=0;
                     //card[i*j].b=Character.toLowerCase(card[i*j].b);
                     System.out.print(card[i*j].b+"[Skip], ");
                     
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].a==12){//setup the draw 2 card
                     //card[i*j].a=0;
                     //card[i*j].b='D';
                     //card[i*j].b=Character.toLowerCase(card[i*j].b);
                     System.out.print(card[i*j].b+"[D2], ");
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].a==13){//setup the reverse card
                     //card[i*j].a=0;
                     //card[i*j].b='E';
                     //card[i*j].b=Character.toLowerCase(card[i*j].b);
                     System.out.print(card[i*j].b+"[Reverse], ");
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].a==14){//setup the wild card
                     //card[i*j].a=0;
                     System.out.print("[WILD], ");
                     card[i*j].b='W';
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].a==15){//setup the wild card
                     card[i*j].b='F';
                     System.out.print("[WILD D4], ");
                     card[i*j].actionCard =true;
                  }
                  
                  //E is for reverse (because R was already taken)

               }
            }
         cardArray=card;
         return cardArray;
    }
    
}

class Card {
    int val;
    char col;
    boolean actionCard =false;

    public void setData(int c, char d) {
        val = c;
        col = d;
    }

    public int showValue()
    {
       return val; 
    }
    
    public char showColor()
    {
        return col;
    }
    
    public void colText()
    {
        switch(col){
            case r:
                System.out.print("Red");
            case b:
                System.out.print("Blue");
            case y:
                System.out.print("Yellow");
            case g:
                System.out.print("Green");
            default:
                System.out.print("black");
        }
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
