import java.util.*;

PImage board,token1,token2,token3,token4;
//pre-Board settings
private final static String [] Tokens= {"barrow","boot","car","dog","hat","iron","ship","thimple"};
public static  ArrayList<String> AvailableTokens = new ArrayList<String>(Arrays.asList(Tokens));
private static int numPlayer;

//Game variables
private final static Slot[] SlotsList;
private static Player[] PlayerList;
private static ArrayList<PImage> PlayerTokens;


void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);

  PlayerSet playercalc = new PlayerSet();
  numPlayer = playercalc.NumPlayer;
  print(numPlayer);
  PlayerList = new Player[numPlayer];
  
  for(int i = 0; i < PlayerTokens.length ; i++){
    TokenSet x = new TokenSet(i+1);
    PlayerList[i] = new Player(x.getToken());
    while(x.waitnext){
      try{
        Thread.sleep(1000);
      } catch(InterruptedException ex){
        Thread.currentThread().interrupt();
      }
    }
  }
  
  //Setup tokens for the players;
  PlayerTokens = new ArrayList<PImage>();//store the actual images themselves. Players have the string.
  if(numPlayer>=2){
      token1 = loadImage("./images/tokens/monopoly_token_"+PlayerList[0].getToken()+".png");
      token2 = loadImage("./images/tokens/monopoly_token_"+PlayerList[1].getToken()+".png");
      PlayerTokens.add(token1);
      PlayerTokens.add(token2);
  }
  if(numPlayer>=3){
      token3 = loadImage("./images/tokens/monopoly_token_"+PlayerList[2].getToken()+".png");
      PlayerTokens.add(token3);
  }
  if(numPlayer>=4){
      token4 = loadImage("./images/tokens/monopoly_token_"+PlayerList[3].getToken()+".png");
      PlayerTokens.add(token4);
  }
  
  
  //Construct the Slot array, your job kevin, GO is 0 on the slot array 
  
}

//conversion for location IDs to x-cor and y-cor with respect to the display

 
  
  
void draw(){
    background(board);
    for(int i =0;i < numPlayer; i++){
       int locat = PlayerList[i].getLocation();
       PImage disp = PlayerTokens.get(i);
       image(disp, convertLocationToXCor(locat), convertLocationToYCor(locat), 10, 10);
    }
       
}