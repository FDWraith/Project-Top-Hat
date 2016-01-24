public class tpde extends Token{
  PImage f;
  public tpde(Player p1, PImage img){
    super(p1);
    f = img;
  }

  void display(){
    //System.out.println("Player "+ player.getIndex() + " token is at "+ xy[0] + " and " +xy[1] );
    image(f, xy[0], xy[1]);
  }

  void move(){
    System.out.println("Moving Player " + player.getIndex());
    for(int i = 0; i<20 ; i++){
	    if(!samelocation()){
        if(player.getLocation() < 10){
          xy[0]= xy[0] - 1;
        }else if(player.getLocation() < 20){
          xy[1] = xy[1] - 1;
        }else if(player.getLocation() < 30){
          xy[0] = xy[0] + 1;
        }else{
          xy[1] = xy[1] + 1;
        } 
	    }else{
        i = 20;
      }
    }
  }

  boolean samelocation(){
    return (Arrays.equals(xy, Game.locations[player.getLocation()]));
  }
}
