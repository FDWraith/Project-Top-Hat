import java.util.concurrent.TimeUnit;

public class Railroad extends Slot{
    private String name;
    private int buyPrice,rentPrice;
    private Player owner;
    private boolean owned;
    
    
    public Railroad(int ID, String title,int price){
	super(ID);
	name = title;
	buyPrice = price;
	owned = false;
	owner = null;
	rentPrice = 25;
    }
    
    //Accesors;
    public String getName(){
	return name;
    }

    public int getBuyPrice(){
	return buyPrice;
    }

    public int getRentPrice(){
	return rentPrice;
    }
    public boolean getOwned(){
	return owned;
    }
    public Player getOwner(){
	return owner;
    }
		 


    public void buyRailroad(Player buyer){
	buyer.changeMoney(-1*buyPrice);
	buyer.addProperty(getLocation());
	owned = true;
	owner = buyer;
	adjustRentPrice();
    }

    public void sellRailroad(){
	owner.removeProperty(getLocation());
	rentPrice = 25;
	owner = null;
	owned = false;
    }

    /**Accounts for the price doubling effect of owning multiple railroads.
     *Also asks all other railroads to adjust their prices as well.//Can't get this to work :(
     */
    public void adjustRentPrice(){
	if(owned){
	    int count = 0;
	    for(int i =5;i<40;i+=10){
		if(owner.getProperties().contains(i)){count++;}
		//Game.SlotsList[i].adjustRentPrice();
	    }
	    rentPrice = (int)(Math.pow(2,count-1) * 25);
	}
	
    }
    
    public void doAction(Player name){
	this.adjustRentPrice();
	if(owned){
	    owner.changeMoney(rentPrice);
	    name.changeMoney(-1*rentPrice);
	}else{
	    RailroadMenu one = new RailroadMenu(name,this);
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    //do nothing
		}
	    }while(one.done == false);
	    one.dispose();
	}
    }
}
