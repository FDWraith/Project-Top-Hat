public class tpde extends Token{
  public tpde(Player p1){
    super(p1);
  }

  void display(){
    PImage disp = PlayerTokens.get(player.getIndex());
    image(disp, xy[0], xy[1]);
  }  
}
