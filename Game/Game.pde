PImage board;

void setup(){
  size(800,800);  
  board = loadImage("./images/monopolyBoard.jpg");
  board.resize(800,800);
  background(board);
  javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        PlayerSet.createGUI();
      }
    });
  
void draw(){
    
}

