public class Utility extends Slot{
    private int buyPrice;
    private String name;
    private boolean owned;
    private Player owner;
    
    public Utility(int ID,String title,int price){
	super(ID);
	name = title;
	buyPrice = price;
    }

    public boolean getOwned(){
	return owned;
    }
    public Player getOwner(){
	return owner;
    }
    
    public void buyUtility(Player buyer){
	buyer.changeMoney(-1 * this.buyPrice);
	owned = true;
	owner = buyer;
    }

    
    
    
    //Mandatory method
    public void doAction(Player name){
	//Fill in later
    }
    
}
