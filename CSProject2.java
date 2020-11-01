//Spoke with instructor about adding java fx and used swing controls a little bit. 
//Main window uses javafx
//Authors Tucker Hortman 
//Saif Chowdhury
//Date Due: November 1st, 2020
//Name: Project 2 - Uno Workout With GUI / HTML feature

import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; //to color rectangles
import javafx.scene.control.Button;
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public class CSProject2 extends Application { 
   public static int p = 4; //number of players. supposed to be 1. 
   public static int d = 1; //number of decks
   public static int s = 1; //way to shuffle together or separate
   public static int z = 1; //for the game loop
   Player player = new Player();
   Card cardArray[] = new Card[384];
   
   
   @Override 
   public void start(Stage stage) throws Exception{ 
      //Label label = new Label();
      Group group = new Group();
      
      //----------------------------------------
      //"business logic" goes here
      for(int i=0;i<cardArray.length;i++){
         cardArray[i] = new Card();
      }
      Card fakeCardArray[] = new Card[384];
      fakeCardArray=createDeck(3, fakeCardArray);
      cardArray=createDeck(d, cardArray);
      for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = new Card();
            cardArray[i].setData(fakeCardArray[i%(d*29)].val, fakeCardArray[i/(d)].col);
            System.out.print(cardArray[i].val + ", "+cardArray[i].col);

      }
      cardArray = shuffleDeck(cardArray);
      //displayDeck(cardArray); //use this to display what the current deck looks like
      showHand(cardArray, p); //
      
      //-----------------------------------------
      //
      
      Rectangle Uno[][] = new Rectangle[5][4];
      Text Una[][] = new Text[5][4]; //just named it something similar but this is the text array of the total values
             
      
      for(int i=0; i<5;i++){ //initialize all.
         for(int j=0;j<p;j++){
            Uno[i][j] = new Rectangle();
            Una[i][j] = new Text();
         }
      }
      
      for(int j=0; j<(p);j++){ //printing rectangles
         for(int i=0;i<(5);i++){
            Uno[i][j].setX(150+ (100.0f*i));
            Uno[i][j].setY(100+ (100.0f*j));
            Uno[i][j].setWidth(40.0f); 
            Uno[i][j].setHeight(70.0f);
            if(i==0){
               Uno[i][j].setFill(Color.BLUE);
            }
            else if(i==1){
               Uno[i][j].setFill(Color.RED);
            }
            else if(i==2){
               Uno[i][j].setFill(Color.YELLOW);
            }
            else if(i==3){
               Uno[i][j].setFill(Color.GREEN);
            }
            group.getChildren().add(Uno[i][j]); 
         }
      }
      String value;
      for(int j=0; j<(p);j++){ //printing text
         for(int i=0;i<(5);i++){
            Una[i][j].setX(164+ (100.0f*i));
            Una[i][j].setY(140+ (100.0f*j));
            if(i==0){
               value = Integer.toString(player.totalPushup);
               Una[i][j].setText(value);
               Una[i][j].setFill(Color.WHITE);  
            }
            else if(i==1){
               value = Integer.toString(player.totalLunge);
               Una[i][j].setText(value);
               Una[i][j].setFill(Color.WHITE);
            }
            else if(i==2){
               value = Integer.toString(player.totalSquat);
               Una[i][j].setText(value);
               Una[i][j].setFill(Color.BLACK); //because if its Yellow, text should be black
            }
            else if(i==3){
               value = Integer.toString(player.totalSitup);
               Una[i][j].setText(value);
               Una[i][j].setFill(Color.WHITE);
            }
            else if(i==4){
               value = Integer.toString(player.totalBurpe);
               Una[i][j].setText(value);
               Una[i][j].setFill(Color.WHITE);
            }
            Una[i][j].setFont(new Font(20));
            group.getChildren().add(Una[i][j]); 
         }
      }
      
     Button proceed = new Button("Proceed");
     Button end = new Button("End");
     proceed.setLayoutX(150.0f); 
     end.setLayoutX(550.0f); 
     proceed.setLayoutY(75+ (125.0f*p));  //like the setX for buttons
     end.setLayoutY(75+ (125.0f*p)); 
     
     
     
     group.getChildren().add(proceed);
     group.getChildren().add(end);
      
      
     
      
      //Creating a scene object 
      Scene scene = new Scene(group, 750, 750);  
      
      //Setting title to the Stage 
      stage.setTitle("Uno Workout"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
      
      
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
                        if((cardArray[i].val)==0) 
                           System.out.print("(Take a 2 minutes rest), "); 
                    }
                }
            catch (Exception e) {
                System.out.println("Not enough cards..");
            }
        }
    }
   
   
   public static void main(String args[])throws IOException{ 
        launch(args);
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

    int totalPushup=9;
	 int totalSitup=1;
	 int totalSquat=3;
	 int totalLunge=4;
    int totalBurpe=2;


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










