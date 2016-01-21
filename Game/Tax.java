

public class Tax extends Slot{
    private int payPrice;
    private String name;

    public Tax(int ID,String title, int price){
	super(ID);
	name = title;
	payPrice = price;
    }

    //Mandatory method
    public void doAction(Player entry){
	if(getLocation()==4){
	    if(entry.getMoney()/10 < payPrice){
		entry.changeMoney(payPrice *-1);
		return;
	    }
	}else{
	    TaxMenu one = new TaxMenu(entry, this);
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    //nothing
		}
	    }while(one.done == false);
	    one.dispose();
	}
	
    }
    
}
