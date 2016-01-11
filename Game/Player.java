import java.util.*;
public class Player{
    private int money;
    private int location;
    private String token;
    private ArrayList<Integer> properties;//int array because properties will be associated with a number. 

    //Constructors
    
    /**
     *Default constructor (only constructor). Initializes a player with starting money and intilizies the empty properties list. 
     */
    public Player(String token){
	money = 1500;
	location = 0;
	this.token = token;
	properties = new ArrayList<Integer>();
    }

    //Accessor methods
    
    /**Use this to figure out how much money this player has.
     *@return returns the amount of money.
     */
    public int getMoney(){
	return money;
    }

    /**Use this if you want to figure out all the properties that this player owns.
     *@return Returns an Arraylist of property IDs
     */
    public ArrayList<Integer> getProperties(){
	return properties;
    }

    //Mutator methods
    
    /**Use this if you want to add or subtract some amount of money from this person's possession.
     *@param money amount
     */
    public void changeMoney(int income){
	money = money + income;
    }

    /**Use this if you want to add a property to this player's ownership.
     *@param property's ID number
     */
    public void addProperty(int ID){
	properties.add(ID);
    }

    /**Use this if you want to remove a property from this player's ownership.
     *@param property's ID number
     */
    public void removeProperty(int ID){
	properties.remove(ID);
    }

    //Other methods

    /**Use this to check if player is bankrupt or not.
     *@return returns a boolean.
     */
    public boolean checkBankruptcy(){
	return money <= 0;
    }

    /**
     *Use this to bankrupt the player (or the player forfeits).
     */
    public void declareBankrupt(){
	money = 0;
    }
    
    
    
    
    
}
