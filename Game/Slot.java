public  class Slot {
    private int location;
    public Slot(int location){
	setLocation(location);
    }

    public void setLocation(int nwLocation){
	if(nwLocation<=39 && nwLocation >=0){
	    location = nwLocation;
	}
    }
    
    public int getLocation(){
	return location;
    }

    public void doAction(Player name){
	
    }
}
