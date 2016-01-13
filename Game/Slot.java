public class Slot {
    private int location;
    public Slot(int ID){
	setLocation(ID);
    }

    public void setLocation(int nwLocation){
	if(nwLocation<=39 && nwLocation >=0){
	    location = nwLocation;
	}
    }
    
    public int getLocation(){
	return location;
    }

    //Mandatory Method
    public void doAction(Player name){
	//does nothing in default Slot
    }
}
