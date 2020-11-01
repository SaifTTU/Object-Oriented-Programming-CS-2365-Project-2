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
   public static int p = 1; //number of players. supposed to be 1. 
   public static int d = 1; //number of decks
   public static int s = 1; //way to shuffle together or separate
   public static int z = 1; //for the game loop
   Player player = new Player();
   Card cardArray[] = new Card[384];
   
   
   @Override 
   public void start(Stage stage) { 
      //Label label = new Label();
      Group group = new Group();
      
      for(int i=0;i<cardArray.length;i++){
         cardArray[i] = new Card();
      }
      
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
   
   
   
   public static void main(String args[])throws IOException{ 
        launch(args);
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







