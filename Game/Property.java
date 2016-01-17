public class Property extends Slot{
    private String name;
    private int buyPrice, rentPrice, housePrice, hotelPrice ,mortgageValue, houseCount;
    private String colorGroup;
    private boolean owned,mortgaged;
    private int[]housePrices;
    private Player owner;

    //Constructors
    
    /**Use this to create a Property. There is no default constructor.
     *@param int for Property ID/location of property.
     *@param string title for the name of the property.
     *@param price to buy unowned property.
     *@param price if you land on this property that is owned by someone else. 
     *@param price if you want to build a house
     *@param price if you want to build a hotel
     *@param return values if you mortgage this property.
     *@param the color group that this property belongs to.
     *@param int array of rentPrices depending on houseCount.
     */
    public Property(int ID,String title,int buyP, int rentP, int houseP, int hotelP, int mortgageV,String colorG,int[]housePs){
	super(ID);
	name = title;
	buyPrice = buyP;
	rentPrice = rentP;
	housePrice = houseP;
	hotelPrice = hotelP;
	mortgageValue = mortgageV;
	housePrices = housePs;
	houseCount = 0;
	colorGroup = colorG;
	owned = false;
	owner = null;
	mortgaged = false;
    }

    //Accessors

    /**Use this to get the name of the property
     *@return name of the property;
     */
    public String getName(){
	return name;
    }

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
    
    /**Use this to get the mortgaged status of the property
     *@return a boolean 
     */
    public boolean getMortgage(){
	return mortgaged;
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
    
    /**Use this to determine the owner of this property, if there is one.
     *@return Returns the Player that owns this property.
     */
    public Player getOwner(){
	return owner;
    }

    
    //Mutator
    
    /**Change the status of whether the property is owned or unowned 
     *@param a Player who now owns the property.
     */
    public void setOwner(Player enter){
	owned = true;
	owner = enter;
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
	    owner.changeMoney(-1*housePrice);
	    houseCount += 1;
	    changeRentPrice(houseCount);
	}
    }
    
    /**A player may buy a hotel for this property if he/she has four houses already.
     */
    public void buyHotel(){
	if(houseCount == 4){
	    owner.changeMoney(-1*housePrice);
	    houseCount += 1;
	    changeRentPrice(houseCount);
	}else{
	    return;
	}
    }

    /**A player may choose to buy this Property, for a cost, of course.
     *@param the Player who chose to buy this property.
     */
    public void buyProperty(Player name){
	owner = name;
	owned = true;
	owner.addProperty(this.getLocation());
	owner.changeMoney(-1 * this.buyPrice);
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

    /**Use this when the property has to be reset (different from being sold).
     */
    public void reset(){
	rentPrice = housePrices[0];
	owned = false;
	owner = null;
	mortgaged = false;
	houseCount = 0;
    }

    /**Use this to sell a house. A house is sold at half it's bought price.
     */
    public void sellHouse(){
	houseCount -= 1;
	changeRentPrice(houseCount);
	//monopolizeProperty()  <-- This should be here, but I don't know how to determine if there is a monopoly...
	owner.changeMoney( housePrice / 2);//int division is fine since houses cannot cost an odd number of dollars
    }

    /**Use this to sell the property. This will not only reset the property to it's original state, but also sell all houses on this property.Any houses will be sold for money, but property's price itself will not be factored in.
     */
    public void sellProperty(){
	while(houseCount > 0){
	    sellHouse();
	}
	owner.removeProperty(this.getLocation());
	owned = false;//This will have to be reset later for a separate owner
	owner = null;
    }

    /**Mortgages the property by selling all houses on the property and setting the rentPrice to zero. Ownership is kept, however. 
     */
    public void mortgageProperty(){
	int sum = 0;
	while(houseCount > 0){
	    sellHouse();
	}
	rentPrice = 0;
	mortgaged = true;
    }

    /**If a player wants to get rid of the mortgage, he/she has to pay the mortgage value plus 10% interest. 
     */
    public void endMortgage(){
	int sum = 0;
	sum += mortgageValue;
	sum += mortgageValue / 10;
	mortgaged = false;
	owner.changeMoney(-1 * sum);
    }

    //Mandatory method.
    
    /**This is what happens when you land on the property. 
     *@param Player who lands on this property.
     */
    public void doAction(Player name){
	
	if(owned && owner != null){
	    owner.changeMoney(rentPrice);
	    name.changeMoney(-1*rentPrice);
	}
    }

}
