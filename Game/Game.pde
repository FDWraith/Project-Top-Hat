PImage board;

void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);

  PlayerSet playercalc = new PlayerSet();
  int numPlayer = playercalc.NumPlayer;
  print(numPlayer);
}
  
void draw(){
    
}

