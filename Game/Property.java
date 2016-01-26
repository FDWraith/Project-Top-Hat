import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Property extends Slot{
    private String name;
    private int buyPrice, rentPrice, housePrice, hotelPrice ,mortgageValue, houseCount;
    private String colorGroup;
    private boolean owned,mortgaged;
    public int[]housePrices;
    private boolean monopolized;
    
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

    public int getMortgageV(){
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
    public int getHouseCount(){
	return houseCount;
    }
    public boolean getMonopolized(){
	return monopolized;
    }

    
    //Mutator


    //Methods
    public void changeMonopolized(boolean enter){
	monopolized = enter;
    }

    
    public void adjustRentPrice(){
	if(houseCount == 0){
	    if(monopolized){
		rentPrice = 2 * housePrices[0];
	    }else{ 
		rentPrice = housePrices[0];
	    }
	}else{
	    rentPrice = housePrices[houseCount];
	}
    }
    
    /**A player may buy a house to put on this property. The rentPrice and houseCount will be changed and up to four houses are allowed.
     */
    public void buyHouse(){
	if(houseCount >= 4){
	    return;
	}
	else{
	    owner.changeMoney(-1*hotelPrice);
	    houseCount += 1;
	    adjustRentPrice();
	}
    }
    
    /**A player may buy a hotel for this property if he/she has four houses already.
     */
    public void buyHotel(){
	if(houseCount == 4){
	    owner.changeMoney(-1*housePrice);
	    houseCount += 1;
	    adjustRentPrice();
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
	monopolizeProperty();
    }

    /**Consistently check if the owner has a monopoly that includes this property.
     */
    public void monopolizeProperty(){
        String[]colors = {"brown","light blue","pink","orange","red","yellow","green","blue"};
	int[][]propSlots = { {2,4,4}, {6,8,9},{11,13,14},{16,18,19},{21,23,24},{26,27,29}, {31,32,34},{37,39,39}};
	int index = Arrays.binarySearch(colors,this.colorGroup);
	//Checker algorithm for counting properties that belong to cGroup
	int count = 0;
	for(int i =0;i<3;i++){
	    if(owner.getProperties().contains(propSlots[index][i])){
		count++;
	    }
	}
	if(count == 3){
	    this.monopolized = true;//set monopoly of this to be true
	    for(int i =0;i<3;i++){
		((Property)(Game.SlotsList[propSlots[index][i]])).changeMonopolized(true);
	    }
	}

	//adjusting RentPrice.
	adjustRentPrice();
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
	adjustRentPrice();
	monopolizeProperty() // <-- This should be here, but I don't know how to determine if there is a monopoly...
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
	owner.changeMoney(mortgageValue);
	rentPrice = 0;
	mortgaged = true;
	owner.getDisplayer().updateProperty();
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
	
	if(owned){
	    monopolizeProperty();//run this everytime
	    owner.changeMoney(rentPrice);
	    name.changeMoney(-1*rentPrice);
	}else{
	    PropertyMenu one = new PropertyMenu(name,this);
	    one.setVisible(true);
	    do{
		try{
		    TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e){
		    
		}
	    }while(one.done == false);
	    one.dispose();
	}
    }

}
