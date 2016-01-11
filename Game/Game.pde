import java.util.*;

PImage board;
//pre-Board settings
private final static String [] Tokens= {"barrow","boot","car","dog","hat","iron","ship","thimple"};
public static  ArrayList<String> AvailableTokens = new ArrayList<String>(Arrays.asList(Tokens));
private static int numPlayer;

//Game variables
private final static Slot[] SlotsList;
private static String[] PlayerTokens;


void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);

  PlayerSet playercalc = new PlayerSet();
  numPlayer = playercalc.NumPlayer;
  print(numPlayer);
  PlayerTokens = new String[numPlayer];
  for(int i = 0; i < PlayerTokens.length ; i++){
    TokenSet x = new TokenSet(i+1);
    PlayerTokens[i] = x.getToken();
    while(x.waitnext){
      try{
        Thread.sleep(1000);
      } catch(InterruptedException ex){
        Thread.currentThread().interrupt();
      }
    }
  }
  
  //Make the Tokens appear.
  for(int i =0; i<PlayerTokens.length;i++){
       
  }
 
  
  //Construct the Slot array
  
  
  
  
}
  
void draw(){
     
}