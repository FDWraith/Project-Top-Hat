import java.util.concurrent.TimeUnit;

public class Jail extends Slot{
    public Jail(int locat){
    super(locat);
    }

    public void doAction(Player player){
	if(player.getJailTime() > 0){
	    System.out.println("Got here!");
	    JailMenu one = new JailMenu(player);
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    //nothing;
		}
	    }while(one.done == false);
	    one.dispose();
	}else{
	    //do nothing to player
	}
    }
    
    
}
