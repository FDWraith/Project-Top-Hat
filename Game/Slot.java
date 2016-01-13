public abstract class Slot {
    private int location;

    public void setLocation(int nwLocation){
	if(nwLocation<=39 && nwLocation >=0){
	    location = nwLocation;
	}
    }
    
    public int getLocation(){
	return location;
    }

    public abstract void doAction(Player name);
}