public class Utility extends Slot{
    private int buyPrice;
    private String name;
    
    public Utility(int ID,String title,int price){
	super(ID);
	name = title;
	buyPrice = price;
    }

    //Mandatory method
    public void doAction(Player name){
	//Fill in later
    }
    
}
