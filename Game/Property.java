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

    //Accessors 
    

    /**Use this to get the price of the unowned property
     *@return price of property
     */
    public int getBuyPrice(){
	return buyPrice;
    }

    /**Use this to get the price that needs to be paid if you land on this owned property.
     *@return renting fee
     */
    public int getRentPrice(){
	return rentPrice;
    }

    /**Use this to get the price of a house.
     *@return the cost of a single house.
     */
    public int getHousePrice(){
	return housePrice;
    }

    /**Use this to get the price of a hotel.
     *@return the cost of a single hotel.
     */
    public int getHotelPrice(){
	return hotelPrice;
    }
    
    /**Use this to get the mortgage value of this property.
     *@return the mortgage value.
     */
    public int getMortgage(){
	return mortgageValue;
    }

    /**Use this to figure out which colorGroup that this property belongs to.
     *@return a String that represents the colorGroup.
     */
    public String getColorGroup(){
	return colorGroup;
    }


    //Mandatory method.
    
    /**This is what happens when you land on the property. 
     */
    public void doAction(){
    }

}