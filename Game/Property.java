public class Property extends Slot{
    private int buyPrice, rentPrice, housePrice, hotelPrice ,mortgageValue, houseCount;
    private String colorGroup;
    private boolean owned;
    private int[]housePrices;
    
    //Constructors
    
    /**Use this to create a Property. There is no default constructor.
     *@param price to buy unowned property.
     *@param price if you land on this property that is owned by someone else. 
     *@param price if you want to build a house
     *@param price if you want to build a hotel
     *@param return values if you mortgage this property.
     *@param the color group that this property belongs to.
     *@param int array of rentPrices depending on houseCount.
     */
    public Property(int buyP, int rentP, int houseP, int hotelP, int mortgageV,String colorG,int[]housePs){
	buyPrice = buyP;
	rentPrice = rentP;
	housePrice = houseP;
	hotelPrice = hotelP;
	mortgageValue = mortgageV;
	housePrices = housePs;
	houseCount = 0;
	colorGroup = colorG;
	owned = false;
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

    /**Use this to determine if the property is owned or not.
     *@return a boolean that dictates ownership.
     */
    public boolean getOwned(){
	return owned;
    }
    
    //Mutator
    
    /**Change the status of whether the property is owned or unowned 
     *@param a boolean to dictate status. True for being under ownership.
     */
    public void setOwned(boolean enter){
	owned = enter;
    }

    //Methods
    
    /**Change the rentPrice based on number of houses.
     *@param how many houses the property possesses (a hotel is 5 houses).
     */
    private void changeRentPrice(int count){
	rentPrice = housePrices[count];
    }
    
    /**A player may buy a house to put on this property. The rentPrice and houseCount will be changed and up to four houses are allowed.
     */
    public void buyHouse(){
	if(houseCount >= 4){
	    return;
	}
	else{
	    houseCount += 1;
	    changeRentPrice(houseCount);
	}
    }
    
    /**A player may buy a hotel for this property if he/she has four houses already.
     */
    public void buyHotel(){
	if(houseCount == 4){
	    houseCount += 1;
	    changeRentPrice(houseCount);
	}else{
	    return;
	}
    }

    /**If this property has no houses and a player has a monopoly of a colorGroup that matches this one, then the rentPrice is doubled.
     *@param String of the colorGroup.
     */
    public void monopolizeProperty(String color){
	if(houseCount == 0 && color.equals(colorGroup)){
	    rentPrice = rentPrice * 2;
	}else{
	    return;
	}
    }

    /**Use this when the property has to be reset.
     */
    public void reset(){
	rentPrice = housePrices[0];
	owned = false;
	houseCount = 0;
    }

    /**Use this to sell a house. A house is sold at half it's bought price.
     *@return int indicating the earnings that come from selling the house.
     */
    public int sellHouse(){
	houseCount -= 1;
	changeRentPrice(houseCount);
	//monopolizeProperty()  <-- This should be here, but I don't know how to determine if there is a monopoly...
	return housePrice / 2;//int division is fine since houses cannot cost an odd number of dollars
    }

    /**Use this to sell the property. This will not only reset the property to it's original state, but also sell all houses on this property.
     *@return int indicating the earnings that come from selling this property (does not factor in property price);
     */
    public int sellProperty(){
	int sum = 0;
	while(houseCount > 0){
	    sum += sellHouse();
	}
	owned = false;//This will have to be reset later for a separate owner
	return sum;
    }

    /**Mortgages the property by selling all houses on the property and setting the rentPrice to zero. Ownership is kept, however. 
     *@return any earnings that come from selling houses is returned as an int.
     */
    public int mortgageProperty(){
	int sum = 0;
	while(houseCount > 0){
	    sum += sellHouse();
	}
	rentPrice = 0;
	return sum;
    }

    /**If a player wants to get rid of the mortgage, he/she has to pay the mortgage value plus 10% interest. 
     *@return int representing the costs accumulated from the mortgage.
     */
    public int endMortgage(){
	int sum = 0;
	sum += mortgageValue;
	sum += mortgageValue / 10;
	return sum;
    }
    

    //Mandatory method.
    
    /**This is what happens when you land on the property. 
     */
    public void doAction(){
    }

}
