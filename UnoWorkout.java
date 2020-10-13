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
            cardArray[i].setData(fakeCardArray[i%(d*29)].val, fakeCardArray[i/(d)].col);
            System.out.print(cardArray[i].val + ", "+cardArray[i].col);

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
        fakeCard.val = 2;
        fakeCard.col = 'B';

        cardArray = addTo(cardArray, fakeCard); //add cards to the bottom of the deck
        
        int z=1;
        while(z!=0)
        {
           cardArray = removeFrom(cardArray, p, 7); //removing cards*numberof Players from the top of the deck
           System.out.println("\nNew Deck: ");
           displayDeck(cardArray);
           updateHand(player,cardArray,p);
           showHand(cardArray, p);
           player=sortHand(player);
           //showWorkOut(player);
           player=actionCard(player,cardArray);
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
                        if((cardArray[i].val)<=10)
                           System.out.print((cardArray[i].val) + "" + (cardArray[i].col) + ", ");
                        if((cardArray[i].val)==11) //E is for reverse (because R was already taken)
                           System.out.print("[Skip]"+(cardArray[i].col) +", ");
                        if((cardArray[i].val)==12) 
                           System.out.print("[D2]"+(cardArray[i].col) +", ");
                        if((cardArray[i].val)==13) 
                           System.out.print("[Reverse]"+(cardArray[i].col) +", ");
                        if((cardArray[i].val)==14) 
                           System.out.print("[WILD], ");
                        if((cardArray[i].val)==15) 
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
    
    
    
    public static Player[] actionCard(Player[] player, Card[] cardArray){
         //String curAct; //current Action
         int value = 0;
         boolean exists = false;
         
         for(int j=0;j<player.length;j++){
               System.out.print("\nPlayer "+(j+1)+" has ");
               for(int i=0;i<player[j].val.length;i++){
                    if(player[j].val[i]>10){
                        exists = true;
                        player[j].value=player[j].val[i];
                              switch(player[j].val[i]){
                                    case 11:
                                        System.out.print("a Skip card "); //removes
                                        player[j]=skip(player[j],player[j].col[i]);
                                        System.out.print("(Skip: all "+player[j].col[i]+" card will be removed from this round)");
                                        System.out.print(", ");
                                        break;
                                    case 12:
                                        System.out.print("a Draw 2 card, "); //doubles color
                                        
                                        break;
                                    case 13:
                                        System.out.print("a Reverse card "); //removes and adds to the bottom
                                        player[j]=reverse(player[j],player[j].col[i],cardArray);
                                        System.out.print("(Reverse: all "+player[j].col[i]+" card will be removed from this round and added to the bottom of the deck)");
                                        System.out.print(", ");
                                        break;
                                    case 14:
                                        System.out.print("a Wild card, ");
                                        break;
                                    default:
                                        System.out.print("a Draw 4 card, ");
                                }
                         
                        
                    }
                       
               }
               System.out.print(" no action cards");
         }
         System.out.print("\n");
         return player; //returns player array
    }
    
    public static Player skip(Player player, char col){ //rellies on actionCards method, does it one player at a time rather than all at once,
         for(int i=0;i<player.val.length;i++){
               if(col==player.col[i]){
                     System.out.print("\n[Card: "+player.val[i]+player.col[i]+" removed]");
                     player.val[i]=0; //remove the value of this card
                     }
         }
         return player; //returns player object
    }
    
    public static Player reverse(Player player, char col, Card[] cardArray){ 
             for(int i=0;i<player.val.length;i++){
               if(col==player.col[i]&&player.val[i]!=13){
                     Card newCard = new Card();
                     newCard.col=player.col[i];
                     newCard.val=player.val[i];
                     addTo(cardArray, newCard);
                     player.val[i]=0; //remove the value of this card
               }
         }
         return player; //returns player object
    }
    
    
    public static Player[] sortHand(Player[] player) {  
        int n = 7;  
        int temp = 0; 
        char tempChar = 'B'; 
            for(int k=0;k<player.length;k++){
            
               
              
               for(int i=0; i < n; i++){  //sorting numbers
                       for(int j=1; j < (n-i); j++){  
                                if(player[k].val[j-1] > player[k].val[j]){  
                                        
                                       temp = player[k].val[j-1];  
                                       player[k].val[j-1] = player[k].val[j];  
                                       player[k].val[j] = temp;  
                                       
                                       tempChar = player[k].col[j-1];  
                                       player[k].col[j-1] = player[k].col[j];  
                                       player[k].col[j] = tempChar;
                               }  
                                
                       }  
               }
               
               for(int i=0; i < n; i++){  //sorting colors
                       for(int j=1; j < (n-i); j++){  
                                if(player[k].col[j-1] > player[k].col[j]){  
                                        
                                       tempChar = player[k].col[j-1];  
                                       player[k].col[j-1] = player[k].col[j];  
                                       player[k].col[j] = tempChar;  
                                       
                                       temp = player[k].val[j-1];  
                                       player[k].val[j-1] = player[k].val[j];  
                                       player[k].val[j] = temp;
                               }  
                                
                       }  
               }
               
               System.out.print("\nNew Player "+(k+1)+"'s Hand: ");
               //for(int i=0; i < player.length; i++){
                     for(int j=0; j < n; j++){ 
                           System.out.print(" "+player[k].val[j]+""+player[k].col[j]);
                     }
               //} 
               
          }
          return player;
  
    }

    public static Card[] addTo(Card[] cardArray, Card fakeCard) {
        System.out.print("\n[Adding " + fakeCard.val + "" + fakeCard.col + " to the back of the deck] ");
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
                System.out.print((cardArray[i].val) + "" + (cardArray[i].col) + ", ");
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
                for(int i=j*7; i<(player[j].col.length +j*7);i++){
                     player[j].val[i%7]=cardArray[i].val;
                     //System.out.print(cardArray[i].col);
                     player[j].col[i%7]=cardArray[i].col;
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
         int pushups; //blue
         int squats; //yellow
         int situps; //red
         int lunges; //green
         for(int i=0; i<player.length;i++){
               pushups = 0; //blue
               squats = 0; //yellow
               situps = 0; //red
               lunges = 0; //green
               System.out.println("\nPlayer "+(i+1)+":");
               for(int j=0; j<player[i].val.length; j++){
                  if(player[i].val[j]<11){
                     if(player[i].col[j]=='R'){
                           //System.out.println(player[i].val[j]);
                           situps = situps + player[i].val[j];
                        }
                     if(player[i].col[j]=='B'){
                           pushups = pushups + player[i].val[j];  
                        }
                     if(player[i].col[j]=='Y'){
                           squats = squats + player[i].val[j];  
                        }
                     if(player[i].col[j]=='G'){
                           lunges = lunges + player[i].val[j];  
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
         int val[] ={0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,15};
         char col[] ={'B','Y','R','G'};
         int numberOfDecks=d;
         
         Card oneDeck[]=new Card[val.length*4];
         Card card[]=new Card[val.length*4*numberOfDecks];
         for(int j=0; j<numberOfDecks;j++){
               System.out.println("\nDeck: "+(j+1));
               for(int i=0;i<oneDeck.length;i++){
                  oneDeck[i] = new Card();
                  card[i*j] = new Card();
                  //for(int j=0;j<4;j++){
                  oneDeck[i].setData(val[i%29],col[i/29]);
                  //System.out.print(oneDeck[i].a+""+oneDeck[i].col+", ");
                  card[i*j]=oneDeck[i];
                  if((card[i*j].val)<=10)
                     System.out.print(card[i*j].val+""+card[i*j].col+", ");
                        
                  
                  
                  if(card[i*j].val==11){//setup the skip card
                     //card[i*j].a=0;
                     //card[i*j].col=Character.toLowerCase(card[i*j].col);
                     System.out.print(card[i*j].col+"[Skip], ");
                     
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].val==12){//setup the draw 2 card
                     //card[i*j].a=0;
                     //card[i*j].col='D';
                     //card[i*j].col=Character.toLowerCase(card[i*j].col);
                     System.out.print(card[i*j].col+"[D2], ");
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].val==13){//setup the reverse card
                     //card[i*j].a=0;
                     //card[i*j].col='E';
                     //card[i*j].col=Character.toLowerCase(card[i*j].col);
                     System.out.print(card[i*j].col+"[Reverse], ");
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].val==14){//setup the wild card
                     //card[i*j].a=0;
                     System.out.print("[WILD], ");
                     //card[i*j].col='W';
                     card[i*j].actionCard =true;
                  }
                  if(card[i*j].val==15){//setup the wild card
                     //card[i*j].col='F';
                     //card[i*j].val=4;
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
    
    String colText;
    public String colText()
    {
        switch(col){
            case 'R':
                //System.out.print("Red");
                colText=("Red");
            case 'B':
                //System.out.print("Blue");
                colText=("Blue");
            case 'Y':
                //System.out.print("Yellow");
                colText=("Yellow");
            case 'G':
                //System.out.print("Green");
                colText=("Green");
            default:
                //System.out.print("black");
                colText=("black");
        }
        return colText;
    }
    
    
    
    public void showData() {
        System.out.print(val);
        System.out.print(col + ", ");
    }

}

class Player {
    int a; //player number
    int val[] = new int[7]; //card array with numbers
    char col[] = new char[7]; //card array with color chars

    String acText; //actionCard text
    int value = 11;
    public String acText()
    {
        
        switch(value){
            case 11:
                //System.out.print("Red");
                acText=("Skip");
                break;
            case 12:
                //System.out.print("Blue");
                acText=("Draw 2");
                break;
            case 13:
                //System.out.print("Yellow");
                acText=("Reverse");
                break;
            case 14:
                //System.out.print("Green");
                acText=("Wild");
                break;
            case 15:
                //System.out.print("Green");
                acText=("Draw 4");
                break;
            //default:
                //System.out.print("black");
                //acText=("Regular");
        }
        return acText;
    }


}
