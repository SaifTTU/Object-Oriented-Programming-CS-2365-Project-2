//Spoke with instructor about adding java fx and used swing controls a little bit. 
//Main window uses javafx
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class UnoWorkout {
    public static void main(String[] args) throws IOException {
        
        
        
        int d = option(1);
        int p = option(2);
        int s = option(3);
        String output = createHTMLName();
        
        
        File myFile = new File(output+".txt");
    	if(myFile.createNewFile()) {
    		System.out.println("File created: " + myFile.getName());
    		System.out.println("File created at: " + myFile.getAbsolutePath());
    	}
    	FileWriter writer = new FileWriter(output+".txt");
    	writer.write("Workout");
        
        
        
        Card fakeCardArray[] = new Card[384];
        fakeCardArray=createDeck(3, fakeCardArray); 
        
         
        Scanner sc = new Scanner(System.in); // Create a Scanner object
        System.out.println("\nHow many decks?");
        //int d = sc.nextInt(); // Read user input
        System.out.println("Number of Decks: " + d); // Output user input
        System.out.println("How many players?");
        //int p = sc.nextInt(); 
        System.out.println("Number of players: " + p); 
        System.out.println("Shuffling:\n1.Together\n2.Separately?");
        //int s = sc.nextInt();
        System.out.print("Shuffling: "); 
        switch(s){
            case(1):
               System.out.println("Together");
            break;
            case(2):
               System.out.println("Together");
            break;
            default: 
               System.out.println("Together");
            break;
        }

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
        
        if(s!=2){
            cardArray = shuffleDeck(cardArray); //use this to shuffle your array regardless of size
         }
        else{
            Card[] cardArray1 = new Card[108];
            Card[] cardArray2 = new Card[108];
            Card[] cardArray3 = new Card[108];
            for (int i = 0; i < cardArray1.length; i++) {
                  cardArray1[i] = new Card();
                  cardArray2[i] = new Card();
                  cardArray2[i] = new Card();
            }
        
            cardArray1 = shuffleDeck(cardArray); //shuffle each array separately before adding them all together in a big loop
            cardArray2 = shuffleDeck(cardArray);
            cardArray3 = shuffleDeck(cardArray); 
            //int count = 0; //the count was for testing purposes
            for(int i=0; i<116;i++){
               cardArray[i]=cardArray1[i];
               //count++;
               //System.out.println(count+"). "+cardArray[i].col+cardArray[i].val);
            }
            if(d>1)
            for(int i=0; i<116;i++){
               cardArray[i+115]=cardArray2[i];
               //count++;
               //System.out.println(count+"). "+cardArray[i].col+cardArray[i].val);
               }
            if(d>2)
            for(int i=0; i<116;i++){
               cardArray[i+231]=cardArray2[i];
               //count++;
               //System.out.println(count+"). "+cardArray[i].col+cardArray[i].val);
            }
            
         }
        displayDeck(cardArray); //use this to display what the current deck looks like

        showHand(cardArray, p); //
        
        //add cards to the bottom of the deck
        /*
        Card fakeCard = new Card();
        fakeCard.val = 2;
        fakeCard.col = 'B';
        cardArray = addTo(cardArray, fakeCard); 
        */
        
   //Game Loop: any type of program that has repeating elements needs a game loop, a way to for the player 
	//or user to iterate every turn. We have several of these methods repeat each turn. 
	//For example, removeFrom() method removes a certain number of cards per the number of players each turn
	//display deck is mainly for us to debug and keep track of how many cards are in the deck
	//and see if it matches the players' hands. 
	//updateHand changes the values in the player[]'s int and char arrays
	//showHand, an old version of of sortHand which I decided to keep to show presorting, show's unsorted hands
	//in sortHand, we utilize bubbling sorting 4 times to get them in the best order: first we order them based on their color,
	//and then value. The reason we had to do this 4 times instead of 2 was because we used separate arrays for the value and color
	//which then needed to be sorted correctly based on their indexes
	//after this we implemented the actionCard method. This method utlizes several other methods like skip and reverse to modify workout values
	//showWorkout also utlizes the player arrays and the reason this is not void is beacuse we attached total values to the player class/objects
	//And finally I added an additional text that appears when there arent anycards remaining because I felt like our previous warnings werent specific enough
	//That way the player knows to press 0 to save their code. We chose to do this because, to be honestly since there were two of us who wrote it, 
	//each with a different approach. this is like the combination of our approach. 

        int z=1;
        while(z!=0)
        {
           //cardArray = containsReverse(cardArray, p, 7);
           cardArray = removeFrom(cardArray, p, 7); //removing cards*numberof Players from the top of the deck. necessary!
           System.out.println("\nNew Deck: ");
           displayDeck(cardArray);
           updateHand(player,cardArray,p);
           showHand(cardArray, p);
           //showWorkOut(player);
           player=sortHand(player);
           player=actionCard(player,cardArray);
           player=showWorkOut(player);
           System.out.println("\n1. Proceed... (Exit = 0)");
           if(cardArray.length<7)
               System.out.print("(Out of cards. Enter 0 to save the results) ");
           z = sc.nextInt();
           
        }
        
        dispTotals(player, writer); //it sort of "Saves As" on the file
        
        writer.close();
        System.out.println("File created at: " + myFile.getAbsolutePath());
        
        createHTML(output); //output = a string with the text name, this method is going to look for it
    }
    //Class or Method: Method
    //Type: void
    //Name: displayDeck
    //Functionality: outputs every element in the array in the order it is given
    //Parameters: cardArray
    //Utilizes card objects' showData() method to display value int and color char
    public static void displayDeck(Card[] cardArray) {
        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i].showData();
        }
    }
    
    //Method
    //Type: void
    //Name: dispTotals
    //Functionality: writes total workouts for each player into the output file
    //Parameters: player[] - an array of all the players, writer - the file writer,
    //Utilizes player objects' totalPushup, totalLunge etc. values
    public static void dispTotals(Player[] player, FileWriter writer) throws IOException {
    	
    	writer.write("\n\n----------TOTALS--------\n\n");
    	
    	for(int i = 0; i < player.length; i++)
    	{
    		writer.write("\nPlayer: " + (i+1));
    		writer.write("\nTotal Pushups: " + player[i].totalPushup);
    		writer.write("\nTotal Squats: " + player[i].totalSquat);
    		writer.write("\nTotal Situps: " + player[i].totalSitup);
    		writer.write("\nTotal Lunges: " + player[i].totalLunge);
         writer.write("\nTotal Burpes: " + player[i].totalBurpe);
    	}
    }
    //Method
    //Type: void
    //Name: showHand
    //Functionality: shows the unsorted hands of each of the players, with action cards.
    //Parameters: cardArray - the deck of cards, players - an int which holds the number of players
    //This was an older method I made which counted every 7 cards in the deck and called that the "player's hand" before I 
    //inserted them into the class
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

    //Method
    //Type: Card object
    //Name: shuffleDeck
    //Functionality: shuffles deck
    //Parameters: cardArray - the deck of all cards
    //turns array of card objects into a list and then shuffles them and converts it back to array before returning the new Array value
    public static Card[] shuffleDeck(Card[] cardArray) {
        List < Card > cardList = Arrays.asList(cardArray); //card Array to to cardList, shuffled and then back to Array 

        Collections.shuffle(cardList);

        cardList.toArray(cardArray);

        System.out.println("\nShuffling deck... ");

        return cardArray;
    }
    
    
    
    
    //Method
    //Type: Player object array
    //Name: actionCard
    //Functionality: scans players hand for action cards and modifies the values, deck, and hand of the player accordingly
    //Parameters: cardArray - the deck of all cards, player - an array of all player objects
    //uses several methods within method based upon what value a certain player card has, which includes 11, Skip, 12, Draw2, 13, Reverse, 14, Wild, 15 Wild Draw 4
    public static Player[] actionCard(Player[] player, Card[] cardArray){
         //String curAct; //current Action
         int value = 0;
         boolean exists = false; //if an action card is in a players hand this becomes true
         
         for(int j=0;j<player.length;j++){
               System.out.print("\nPlayer "+(j+1)+" has ");
               for(int i=0;i<player[j].val.length;i++){
                    if(player[j].val[i]>10){
                        exists = true;
                        player[j].value=player[j].val[i];
                              switch(player[j].val[i]){
                                    case 11:
                                        player[j]=skip(player[j],player[j].col[i]);
                                        System.out.print("a "+player[j].col[i]+" Skip card, (Skip all cards of this color), "); //removes
                                        //System.out.print("(Skip: all "+player[j].col[i]+" card will be removed from this round)");
                                        //System.out.print(", ");
                                        break;
                                    case 12:
                                        
                                        System.out.print("a "+player[j].col[i]+"Draw 2 card, "); //doubles color
                                        player[j]=draw2(player[j],player[j].col[i]);
                                        break;
                                    case 13:
                                        
                                        System.out.print("a "+player[j].col[i]+" Reverse card (Add all cards of this color back to the deck)"); //removes and adds to the bottom
                                        player[j]=reverse(player[j],player[j].col[i],cardArray);
                                        //System.out.print(" (Reverse: all "+player[j].col[i]+" card will be removed from this round and added to the bottom of the deck)");
                                        System.out.print(", ");
                                        break;
                                    case 14:
                                        System.out.print("a Wild card, +4 burpes, ");
                                        break;
                                    default:
                                        System.out.print("a Wild Draw 4 card, *4 burpes,");
                                }
                    }  
               }
               if(exists==false){
                  System.out.print(" no action cards");
                  }
                  exists=true;      
         }
         System.out.print("\n");
         return player; //returns player array
    }
    
	
    //Method
    //Type: Player object
    //Name: skip
    //Functionality: it deletes the value attributed to a specific color for a players entire hand. 
    //Parameters: player object, col - color character
    //since this method is called in the actionCard method we chose to call it individually and as such, could make this method work for 1 Player at a time
    //instead of all the players in an array at once
    public static Player skip(Player player, char col){ //rellies on actionCards method, does it one player at a time rather than all at once,
         for(int i=0;i<player.val.length;i++){
               if(col==player.col[i] /*&&player.val[i]<11 */ ){ //if its the same color and less than 11
                     //System.out.print("\n[Card: "+player.val[i]+player.col[i]+" removed]");
                     player.val[i]=0; //remove the value of this card
                     }
         }
         return player; //returns player object
    }
    
	
    //Method
    //Type: Player object
    //Name: reverse
    //Functionality: functions similar to skip, except it utilizes the addTo() method to add cards of a specific color to the back of the deck
    //Parameters: player object, col - color character, cardArray - the entire deck whose values we are going to be manipulating when we add more to it
    //Similarly deletes the value attributed to a color but we recreate the cardArray deck exactly as it was except it will have a certain number of new
    //cards added to it based on the color
    public static Player reverse(Player player, char col, Card[] cardArray){ 
             for(int i=0;i<player.val.length;i++){
               if(col==player.col[i]&&player.val[i]!=13 /*&&player.val[i]<11 */ ){ //if its the same color, not a reverse, and less than 11
                     Card newCard = new Card();
                     newCard.col=player.col[i];
                     newCard.val=player.val[i];
                     //System.out.print("\n[Card: "+player.val[i]+player.col[i]+" removed]");
                     addTo(cardArray, newCard);
                     player.val[i]=0; //remove the value of this card
               }
         }
         return player; //returns player object
    }
    
    //Method
    //Type: Player object
    //Name: draw2
    //Functionality: goes through the player hand and multiplies the total amount to add for workouts by two if the color is matched to the one given
    //Parameters: player object, col - color character (we are given the color to look for in this case)
    public static Player draw2(Player player, char col){ 
         int total= 0;
         for(int i=0;i<player.val.length;i++){
               if(col==player.col[i] && player.val[i] != 12 ){
                     //System.out.print("["+(2*player.val[i])+player.col[i]+"]");
                     total = total + player.val[i]*2; 
                     }
                     //System.out.print("total: "+player.col[i]+total); 
         }
         System.out.print("**Double values for "+col+". Total "+col+" values becomes:"+total+"! **,"); 
         return player; //returns player object
    }
    
    //Method
    //Type: Player[] object array
    //Name: sortHand
    //Functionality: sorts hand
    //utlizes bubble sort algorithm (4 times) to sort player's hand based on value and color
    //(we did this 4 times because the player array has separate array variables instead of char arrays, because this
    //made it much easier to call them in several parts of our project, the 2nd and 4th loop put the respective col and val back with their corrrect corresponding value or color
    //Parameters: player[] object array
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
                           if(player[k].val[j]<11&&player[k].val[j]!=0){
                              System.out.print(" "+player[k].val[j]+""+player[k].col[j]);
                              }
                           else if(player[k].val[j]==11){ //doesnt work yet, I'd have to fix it later
                              System.out.print("  "+player[k].col[j]+" Skip ");
                              }
                           else if(player[k].val[j]==12){
                              System.out.print("  "+player[k].col[j]+" Draw 2 ");
                              }
                           else if(player[k].val[j]==13){
                              System.out.print("  "+player[k].col[j]+" Reverse ");
                              }
                          else if(player[k].val[j]==14){
                              System.out.print(" WILD");
                              }
                          else if(player[k].val[j]==15){
                              System.out.print("  WILD Draw 4  ");
                              }
                              
                     }
               //} 
               
          }
          return player;
  
    }
	
    //Method
    //Type: Card[] object array - returns the entire deck essentially
    //Name: addTo
    //Functionality: adds 1 card to the back of the deck at a time
    //Parameters: cardArray - the original deck, and a predifined card object fakeCard
    public static Card[] addTo(Card[] cardArray, Card fakeCard) {
        System.out.print(" [Adding " + fakeCard.val + "" + fakeCard.col + " to the back of the deck], ");
        Card[] newArray = new Card[cardArray.length + 1];
        for (int i = 0; i < cardArray.length; i++) {
            newArray[i] = cardArray[i];
        }
        newArray[cardArray.length] = fakeCard;
        return newArray;
    }
    
    //Method
    //Type: Card[] object array - returns the entire deck 
    //Name: removeFrom
    //Functionality: removes several cards from the front of the deck at a time, based on how many players there are
    //so if there are 3 players, remove 3*7 cards from the deck and return the new deck
    //Parameters: cardArray - the deck, people - the number of players, hand - the number of cards in the players hand (it is always 7)
    public static Card[] removeFrom(Card[] cardArray, int people, int hand) {
        System.out.println("\nRemoving the following " + (people * 7) + " cards from the top of the deck: ");

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
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*            Out of cards! Take a short break and start again!                *");
            System.out.print("\n*                    (Press 0 to See the Results!     )                       *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            return cardArray;
        }
    }
    
    //Method
    //Type: Player[] object array - all players
    //Name: updateHand
    //Functionality: is needed to match the players hand to the right amount of cards in the deck
    //Parameters: cardArray - the deck, player[] - Player(s), numberOfPlayers
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
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*            Out of cards! Take a short break and start again!                *");
            //commented this out: but it prints what the total is in the textbox
            /*for(int i=0;i<player.length;i++){
            System.out.print("\n*                 Player "+(i+1)+"       "+player[i].totalPushup+"       "+player[i].totalSquat+"       "
            +player[i].totalSitup+"       "+player[i].totalLunge+"      "+player[i].totalBurpe+"          *");
            } */
            System.out.print("\n*                    (Press 0 to See the Results!     )                       *");
            System.out.print("\n*                                                                             *");
            System.out.print("\n*                                                                             *");
            return player;
        }
    }
    
    //Method
    //Type: Player[] an object array of Player class - all of the players
    //Name: showWorkOut
    //Functionality: shows the workout of the current turn for all players.
    //Parameters: cardArray - player[] - all players
    public static Player[] showWorkOut(Player[] player){
         int pushups; //blue
         int squats; //yellow
         int situps; //red
         int lunges; //green
         int burpes;
         boolean[] draw2 = { false, false, false, false};
         for(int i=0; i<player.length;i++){
               pushups = 0; //blue
               squats = 0; //yellow
               situps = 0; //red
               lunges = 0; //green
               burpes = 0; //wild cards
               System.out.println("\nPlayer "+(i+1)+":"); 
               for(int j=0; j<player[i].val.length; j++){
                 if(player[i].val[j]<11)
                 {
                     if(player[i].col[j]=='R'){
                           //System.out.println(player[i].val[j]); //Moved this towards the bottom underneath the draw 2 method (its still in for loop) (it still works)
                           situps = situps + player[i].val[j];
                           //player[i].totalSitup += situps;
                        }
                     if(player[i].col[j]=='B'){
                           pushups = pushups + player[i].val[j]; 
                           //player[i].totalPushup += pushups;
                        }
                     if(player[i].col[j]=='Y'){
                           squats = squats + player[i].val[j];
                           //player[i].totalSquat += squats;
                        }
                     if(player[i].col[j]=='G'){
                           lunges = lunges + player[i].val[j]; 
                           //player[i].totalLunges += lunges;
                        }
                  }
                  if(player[i].val[j]==12){
                        if(player[i].col[j]=='B')
                           draw2[0]=true;
                        if(player[i].col[j]=='Y')
                           draw2[1]=true;
                        if(player[i].col[j]=='R')
                           draw2[2]=true;
                        if(player[i].col[j]=='G')
                           draw2[3]=true;
                  }
                  if(player[i].val[j]==14){
                        burpes = burpes + 4; //if its a Wild Card
                        

                  }
                  if(player[i].val[j]==15){
                        burpes = burpes * 4; //if its a Wild Draw 4
                  } 
                  
               }
               
               
               if(draw2[0]==true)
               pushups=pushups*2;
               if(draw2[1]==true)
               squats=squats*2;   
               if(draw2[2]==true)
               situps=situps*2;   
               if(draw2[3]==true)
               lunges=lunges*2;
               
               player[i].totalSitup += situps; //I had had to change its location to appear after draw 2's occured
               player[i].totalPushup += pushups; //cause before it was printing way too few and I think this is why
               player[i].totalSquat += squats;
               player[i].totalLunge += lunges;
               player[i].totalBurpe += burpes;
               
               System.out.println("Pushups: "+ pushups );
               System.out.println("Squats: "+ squats );
               System.out.println("Situps: "+ situps );
               System.out.println("Lunges: "+ lunges );
               if(burpes!=0) //It only prints Burpes if they have them. Should we always include it? Idk kinda liked 4 displayed by default better.
               System.out.println("Burpes: "+ burpes );
         }
         
         return player; //returns "players" actually. (player[]) 
    }
    
    //Method
    //Type: Card[] - since this is the birth of our deck, the return value is a Card[] the entire set of 108-316 cards (of however many there is)
    //Name: createDeck
    //Functionality: shows the workout of the current turn for all players.
    //Parameters: int d - how many decks there are, Card[] cardArray - anempty Card array that will soon hold new values when we return it.
    public static Card[] createDeck(int d, Card[] cardArray){
         int val[] ={0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,15}; //the correct number of cards specified to the deck description
         char col[] ={'B','Y','R','G'}; //anything that goes past 10 is an "action card" and will have special effects on the workout.
         int numberOfDecks=d; //here we match the given number to the one from the method we created earlier
         
         Card oneDeck[]=new Card[val.length*4];
         Card card[]=new Card[val.length*4*numberOfDecks];
         for(int j=0; j<numberOfDecks;j++){
               System.out.println("\nDeck: "+(j+1));
               for(int i=0;i<oneDeck.length;i++){
                  oneDeck[i] = new Card(); //first we created a test deck called one deck and then set the created deck equal to card 
                  card[i*j] = new Card(); //and then we'll set card equal to cardArray and return cardArray
                  //for(int j=0;j<4;j++){
                  oneDeck[i].setData(val[i%29],col[i/29]); //utilizing modulus and char division to create the exam dimensions of real deck
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
    
    //Method
    //Type: Card[]
    //Name: containsReverse
    //Functionality: needed to check if each player has a reverse , check what color they are
    //and to reset the deck to have only those specific cards sent back to the bottom of the deck
    //Parameters: Card[] cardArray - deck, int p - number of players , int hand - number of cards per player
    public static Card[] containsReverse(Card[] cardArray, int p, int hand){
       boolean[] redReverse={false,false,false,false};//per player
       boolean[] blueReverse={false,false,false,false};
       boolean[] greenReverse={false,false,false,false};
       boolean[] yellowReverse={false,false,false,false};
       
         for(int i=0; i<p;i++){
            for(int j=0; j<hand;j++){
                  if(cardArray[j].val==13){
                     if(cardArray[j].col=='R')
                        redReverse[i]=true;
                     if(cardArray[j].col=='B')
                        blueReverse[i]=true;
                     if(cardArray[j].col=='G')
                        greenReverse[i]=true;
                     if(cardArray[j].col=='Y')
                        yellowReverse[i]=true;
                  }
            }
         }
         for(int i=0; i<p;i++){
            for(int j=0; j<hand;j++){
               if(redReverse[i]==true){
                   
               }
            }   
         }
         
         Card[] newArray= new Card[cardArray.length];
         
         
         return cardArray;
    }
    
	
    //Method
    //Type: int
    //Name: option
    //Functionality: I created a few GUI based options and put them in a method for the player at the start.
    //Asks them how many players, how many decks, and if they will be shuffling together or separately
    //Parameters: int o - short for option. o basically means each of the many option dialog boxes to print and what to return.
    //rather than creating several methods for each option, I though to number them in this manner instead. 
    public static int option(int o){
         int option=1;
         if(o==1){
            Integer[] options = {1, 2, 3};
            option = JOptionPane.showOptionDialog(null, "How many decks?",
                "Number of Decks",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,null);
         
         }
         if(o==2){
            Integer[] options = {1,2,3,4};
            option = JOptionPane.showOptionDialog(null, "How many players?",
                "Number of Players",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
         }
         if(o==3){
            String[] options = {"Together", "Separately"};
            option = JOptionPane.showOptionDialog(null, "Would you like to shuffle together or separately?",
                "Shuffle Together or Separate.",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
         }
         
         option++;       
         System.out.println(option);     
         
         return option;
    }
    
    //Method
    //Type: String
    //Name: createHMTMLName
    //Functionality: Rellies on information gathered from the createHTMLName method directly bellow
    //adds a new file with all the appropriate tags of an html file and then places lines
    //from our created txt file, one by one as a separate "paragraph" entity
    //and then saves the html so it all comes out looking nice.
    public static void createHTML(String output){
            String doctype = "<!DOCTYPE html>";
            String html="<html>";
            //String head="<head>";
            //String _head="</head>";
            String body="<body>";
            //String heading_1="<h1>";
            //String user_heading="THIS IS A TEST";
            //String _heading_1="</h1>"; //changed it to _ for "close" heading
            String paragraph = "<p>"; //I found that giving each of the tags a name like this really helped me mentally keep track of everything
            String user_paragraph="This is a paragraph."; //since coding is all about abstraction, its better to abstract away the tags in a manner more easy to understand
            String _paragraph = "</p>";//close paragraph
            String _body="</body>"; //close body
            String _html="</html>"; //close html
            try {
              //Filewriter writer = new Filewiter();
              File myFile = new File(output+".html");
              FileWriter writer = new FileWriter(output+".html");
              
              
       	      writer.write(doctype);
               writer.write(html);
               writer.write(body);
               //writer.write(paragraph);
               /*for(int i = 0;i<(output+".txt").length();i++){
                     writer.write(user_paragraph);
               }*/
               Scanner scanner = new Scanner(new File(output+".txt"));
               while (scanner.hasNextLine()) {
                  writer.write(paragraph);
                  String line = scanner.nextLine();
                  writer.write(line);
                  writer.write(_paragraph);
                  // process the line
               }
               
               writer.write(_body);
               writer.write(html);
               System.out.println("\nHTML file created at: " + myFile.getAbsolutePath());
               writer.close();
            }
            catch(IOException e) {
              e.printStackTrace();
            }
            
            
    }
	
    //Method
    //Type: String
    //Name: createHMTMLName
    //Functionality: Allows the user to name the file they wish to create.
    public static String createHTMLName(){
            String output;
            JFrame jframe = new JFrame();
            output=JOptionPane.showInputDialog(jframe,"Enter the name of the outpute file. If not entered (press cancel) a default name of \"Output.txt\" will be created. "); 
            if(output==null||output.length()<1)
            {
               output="Output";
            }
            System.out.println("Name of file: "+output+".txt" );
         return output;
    }
    
}

//Class or Method: Class
//Type: Card() - an object which holds two variables, val - for value, and col - for color (its a char variable)
//it also contains a boolean actionCard set to false that switches to true if the value raises above 11 in our program.
//in addition to this Card() also contains the following methods:
//setData - used to set the val and col of the respective Card
//showValue - for when we want to display the Value individually
//showColor - for when we want to display just the Color Char individually
//colText - which returns a String detailing the color in a string formath, For example B would be "Blue," R would be "Red," and so forth
//acText - though we do not utilize it - it accomplishes the same task as colText in that it returns whatever actionCard the card it is in String format,
//showData - lastly, showData() was my initial accessor that showed both the int and char of the respective card.
//Name: createHMTMLName
//Functionality: Allows the user to name the file they wish to create.
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
    
    String actionCardText;
    public String actionCardText()
    {
        switch(val){
            case 11:
                colText=("[Skip]");
            case 12:
                colText=("[Draw 2]");
            case 13:
                colText=("[Reverse]");
            case 14:
                colText=("[Wild]");
            case 15:
                colText=("[Wild]");
            default:
                colText=("");
        }
        return colText;
    }
    
    
    
    public void showData() {
        System.out.print(val);
        System.out.print(col + ", ");
    }

}

//Class or Method: Class
//Type: Player() - an object which holds three variables, val - for value, and col - for color, and a for the player number (which we dont end up needing all too much).
//there can be a total of 4 players created with this class in our version of the game.
//additionally it contains the totaling int variables: 
//totalPushup, totalSitup, totalSquat, totallunge, totalBurpe - all set to 0
//acText() - the player hand is so small (only 7 cards), we used two primitive arrays to represent the hands (which we were careful not to mix up their variables at any
//point, however it essentially will accomplish the same task that it does in Card(), whenever given a number between 11 and 15 it will return namne of the actionCard

//Name: createHMTMLName
//Functionality: Allows the user to name the file they wish to create.
class Player {
    int a; //player number
    int val[] = new int[7]; //card array with numbers
    char col[] = new char[7]; //card array with color chars

    int totalPushup=0;
	 int totalSitup=0;
	 int totalSquat=0;
	 int totalLunge=0;
    int totalBurpe=0;


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
