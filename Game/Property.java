public class Property extends Slot{
    private int buyPrice, rentPrice, housePrice, hotelPrice ,mortgageValue, houseCount;
    private String colorGroup;
    
    //Constructors
    
    /**Use this to create a Property. There is no default constructor.
     *@param price to buy unowned property.
     *@param price if you land on this property that is owned by someone else. 
     *@param price if you want to build a house
     *@param price if you want to build a hotel
     *@param return values if you mortgage this property.
     *@param the color group that this property belongs to.
     */
    public Property(int buyP, int rentP, int houseP, int hotelP, int mortgageV,String colorG){
	buyPrice = buyP;
	rentPrice = rentP;
	housePrice = houseP;
	hotelPrice = hotelP;
	mortgageValue = mortgageV;
	houseCount = 0;
	colorGroup = colorG;
    }


    //Mandatory method.
    public void doAction(){
    }

}