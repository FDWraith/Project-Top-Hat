import java.util.concurrent.TimeUnit;

public class Tax extends Slot{
    private int payPrice;
    private String name;

    public Tax(int ID,String title, int price){
	super(ID);
	name = title;
	payPrice = price;
    }

    public int getPayPrice(){
	return payPrice;
    }
    public String getName(){
	return name;
    }
    
    //Mandatory method
    public void doAction(Player entry){
	if(getLocation()==4){
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
	}else{
	    TaxDisplay one = new TaxDisplay(entry, this);
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
