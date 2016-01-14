public class Tax extends Slot{
    private int payPrice;
    private String name;

    public Tax(int ID,String title, int price){
	super(ID);
	name = title;
	payPrice = price;
    }

    //Mandatory method
    public void doAction(Player name){
	if(getLocation()==4){
	    if(name.getMoney()/10 < payPrice){
		name.changeMoney(payPrice *-1);
		return;
	    }
	}
	name.changeMoney(payPrice*-1);
    }
    
}
