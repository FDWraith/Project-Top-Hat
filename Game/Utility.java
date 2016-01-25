import java.util.concurrent.TimeUnit;
import java.util.*;

public class Utility extends Slot{
    private int buyPrice,diceFactor;
    private String name;
    private boolean owned;
    private Player owner;
    
    public Utility(int ID,String title,int price){
	super(ID);
	name = title;
	buyPrice = price;
	diceFactor = 4;
    }

    public String getName(){
	return name;
    }
    
    public boolean getOwned(){
	return owned;
    }
    public Player getOwner(){
	return owner;
    }
    public int getBuyPrice(){
	return buyPrice;
    }
    public int getDiceFactor(){
	return diceFactor;
    }
    
    public void adjustDiceFactor(){
	if(owned){
	    int count = 0;
	    if(owner.getProperties().contains(12)){count++;}
	    if(owner.getProperties().contains(28)){count++;}
	    if(count ==1){
		diceFactor = 4;
	    }else{
		diceFactor = 10;
	    }
	}else{
	    diceFactor = 4;
	}
    }
    
    public void buyUtility(Player buyer){
	buyer.changeMoney(-1 * this.buyPrice);
	owned = true;
	owner = buyer;
	adjustDiceFactor();
	owner.addProperty(getLocation());
    }
        
    //Mandatory method
    public void doAction(Player name){
	if(owned){
	    adjustDiceFactor();
	    Random r = new Random();
	    int sum = diceFactor * (r.nextInt(6)+1);
	    name.changeMoney(-1 * sum);
	    owner.changeMoney(sum);
	}else{
	    UtilityMenu one = new UtilityMenu(name,this);
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    //nothing;
		}
	    }while(one.done == false);
	    one.dispose();
	}
    }
    
}
