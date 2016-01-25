public class Slot {
    private int location;
    Player owner;
    boolean owned;
    
    public Slot(int ID){
	setLocation(ID);
	owner = null;
	owned = false;
    }

    public void setLocation(int nwLocation){
	if(nwLocation<=39 && nwLocation >=0){
	    location = nwLocation;
	}
    }
    
    public int getLocation(){
	return location;
    }





    
   //Accessors (mandatory methods)

    /**Use this to get the name of the property
     *@return name of the property;
     */
    public String getName(){
	return "";
    }

    /**Use this to get the price of the unowned property
     *@return price of property
     */
    public int getBuyPrice(){
	return 0;
    }

    /**Use this to get the price that needs to be paid if you land on this owned property.
     *@return renting fee
     */
    public int getRentPrice(){
	return 0;
    }

    /**Use this to get the price of a house.
     *@return the cost of a single house.
     */
    public int getHousePrice(){
	return 0;
    }

    /**Use this to get the price of a hotel.
     *@return the cost of a single hotel.
     */
    public int getHotelPrice(){
	return 0;
    }
    
    /**Use this to get the mortgaged status of the property
     *@return a boolean 
     */
    public boolean getMortgage(){
	return false;
    }
    public int getMortgageV(){
	return 0;
    }    

    /**Use this to figure out which colorGroup that this property belongs to.
     *@return a String that represents the colorGroup.
     */
    public String getColorGroup(){
	return "";
    }

    /**Use this to determine if the property is owned or not.
     *@return a boolean that dictates ownership.
     */
    public boolean getOwned(){
	return false;
    }
    
    /**Use this to determine the owner of this property, if there is one.
     *@return Returns the Player that owns this property.
     */
    public Player getOwner(){
	return owner;
    }

    
    //Mutator(mandatory methods except for setOwner);
    
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

    }
    
    /**A player may buy a house to put on this property. The rentPrice and houseCount will be changed and up to four houses are allowed.
     */
    public void buyHouse(){

    }
    
    /**A player may buy a hotel for this property if he/she has four houses already.
     */
    public void buyHotel(){

    }

    /**A player may choose to buy this Property, for a cost, of course.
     *@param the Player who chose to buy this property.
     */
    public void buyProperty(Player name){

    }

    /**If this property has no houses and a player has a monopoly of a colorGroup that matches this one, then the rentPrice is doubled.
     *@param String of the colorGroup.
     */
    public void monopolizeProperty(String color){

    }

    /**Use this when the property has to be reset (different from being sold).
     */
    public void reset(){

    }

    /**Use this to sell a house. A house is sold at half it's bought price.
     */
    public void sellHouse(){

    }

    /**Use this to sell the property. This will not only reset the property to it's original state, but also sell all houses on this property.Any houses will be sold for money, but property's price itself will not be factored in.
     */
    public void sellProperty(){

    }

    /**Mortgages the property by selling all houses on the property and setting the rentPrice to zero. Ownership is kept, however. 
     */
    public void mortgageProperty(){

    }

    /**If a player wants to get rid of the mortgage, he/she has to pay the mortgage value plus 10% interest. 
     */
    public void endMortgage(){

    }

    public void buyRailroad(Player buyer){
    }

    public void sellRailroad(){
    }

    /**Accounts for the price doubling effect of owning multiple railroads.
     *Also asks all other railroads to adjust their prices as well.//Can't get this to work :(
     */
    public void adjustRentPrice(){
	
    }

    public int getDiceFactor(){
	return 0;
    }
    
    public void adjustDiceFactor(){
    }
    
    public void buyUtility(Player buyer){

    }
    

    //Mandatory Method
    public void doAction(Player name){
	//does nothing in default Slot
    }
}
