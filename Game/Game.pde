import java.util.*;

PImage board,token1,token2,token3,token4;
//pre-Board settings
private final static String [] Tokens= {"barrow","boot","car","dog","hat","iron","ship","thimple"};
public static  ArrayList<String> AvailableTokens = new ArrayList<String>(Arrays.asList(Tokens));
private static int numPlayer;
public static String [] TokenList;

//Game variables
private static Slot[] SlotsList = new Slot[40];
private static Player[] PlayerList;
private static ArrayList<PImage> PlayerTokens;
private static final int[][] locations = { {750,750} , {660,750} , {595,750}, {530,750}, {465,750}, {400,750}, {335,750}, {270,750}, {205,750}, {140,750}, 
                                           {50,750}, {50,660}, {50,595}, {50,530}, {50,465}, {50,400}, {50,335}, {50,270}, {50,205}, {50,140},
                                           {50,50}, {140,50}, {205,50}, {270,50}, {335,50}, {400,50}, {465,50}, {530,50}, {595,50}, {660,50},
                                           {750,50}, {750,140}, {750,205}, {750,270}, {750,335}, {750,400}, {750,465}, {750,530}, {750,595}, {750,660} };

void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);

  PlayerSet playercalc = new PlayerSet();
  numPlayer = playercalc.NumPlayer;
  TokenList = new String[numPlayer];  
  //print(numPlayer);
  PlayerList = new Player[numPlayer];
  
  for(int i = 0; i < PlayerList.length ; i++){
    TokenSet x = new TokenSet(i+1);
    //print(TokenList[i]);
    while(x.waitnext){
      try{
        Thread.sleep(1000);
      } catch(InterruptedException ex){
        Thread.currentThread().interrupt();
      }
    }
    PlayerList[i] = new Player(TokenList[i]);
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
  for(int i=0; i<PlayerTokens.size();i++){
      PlayerTokens.get(i).resize(40,40); 
  }
  
  //Construct the Slot array, your job kevin, GO is 0 on the slot array 
  
}

//conversion for location IDs to x-cor and y-cor with respect to the display
int convertLocationToXCor(int locat){
   return locations[locat][0];
}
int convertLocationToYCor(int locat){
   return locations[locat][1]; 
}
  
  
void draw(){
    background(board);
    for(int i =0;i < numPlayer; i++){
       int locat = PlayerList[i].getLocation();
       PImage disp = PlayerTokens.get(i);
       imageMode(CENTER);
       image(disp, convertLocationToXCor(locat), convertLocationToYCor(locat));
    }
       
}

void SetProperty(){
  SlotsList[0] = new Slot(0);
  
  int [] MeditH = {10,30,90,60,250};  
  SlotsList[1] = new Property(1,"Mediterranean Ave",60,2,50,50,30,"brown",MeditH);
  
  SlotsList[2] = new Community(2);
  
  int [] BaltiH = {20,60,180,320,450};
  SlotsList[3] = new Property(3,"Baltic Ave",60,4,50,50,30,"brown",BaltiH);
  
  SlotsList[4] = new Tax(4);
  SlotsList[5] = new Railroad(5,"Reading Railroad");
  
  int [] OrientH = {30,90,270,400,550};
  SlotsList[6] = new Property(6,"Oriental Ave",100,6,50,50,50,"light blue",OrientH);
  
  SlotsList[7] = new Chance(7);
  
  int [] VermH = {30,90,270,400,500}
  SlotsList[8] = new Propety(8,"Vermont Ave",100,6,50,50,50,"light blue",VermH);
  int [] ConnH = {40,100,300,450,600};
  SlotsList[9] = new Propety(9,"Connecticut Ave",120,8,50,50,60,"light blue",ConnH);
  
  SlotsList[10] = new Slot(10);
  int [] StcH = {50,150,450,625,750};
  SlotsList[11] = new Propety(11,"St. Charles pl.",140,10,100,100,70,"pink",StcH);
  SlotsList[12] = new Utility(12);
  SlotsList[13] = new Propety(13);
  SlotsList[14] = new Property(14);
  SlotsList[15] = new Railroad(15);
  SlotsList[16] = new Propety(16);
  SlotsList[17] = new Community(17);
  SlotsList[18] = new Property(18);
  SlotsList[19] = new Property(19);
  SlotsList[20] = new Slot(20);
}
