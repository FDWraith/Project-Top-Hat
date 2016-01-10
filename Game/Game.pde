import java.util.*;

PImage board;
private final static String [] Tokens= {"barrow","boot","car","dog","hat","iron","ship","thimple"};
public static  ArrayList<String> AvailableTokens = new ArrayList<String>(Arrays.asList(Tokens));

void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);

  PlayerSet playercalc = new PlayerSet();
  int numPlayer = playercalc.NumPlayer;
  print(numPlayer);
  TokenSet test = new TokenSet(numPlayer);
}
  
void draw(){
    
}

