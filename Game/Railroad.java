public class Railroad extends Slot{
    private String name;
    private int buyPrice;
    
    public Railroad(int ID, String title,int price){
	super(ID);
	name = title;
	buyPrice = price;
    }
    
    public void doAction(Player name){
	//currently does nothign
    }
}
