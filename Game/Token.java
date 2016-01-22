import java.util.*;

public class Token{
  Player player ;
  float[] xy;

  public Token(Player p1 ){
    player = p1;
    xy = Game.locations[0];
  }

  

  void display(){
  }

  void move(){
    while(!samelocation()){
      if(player.getLocation() < 10){
        xy[0]= xy[0] - 1;
      }else if(player.getLocation() < 20){
        xy[1] = xy[1] - 1;
      }else if(player.getLocation() < 30){
        xy[0] = xy[0] + 1;
      }else{
        xy[1] = xy[1] + 1;
      } 
    }
  }

  boolean samelocation(){
    return (Arrays.equals(xy, Game.locations[player.getLocation()]));
  }

  
}
