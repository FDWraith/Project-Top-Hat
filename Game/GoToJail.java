import java.util.concurrent.TimeUnit;

public class GoToJail extends Slot{

    public GoToJail(int ID){
	super(ID);
    }
    
    //Mandatory method
    public void doAction(Player name){
	JailDisplay one = new JailDisplay(name, this);
	one.setVisible(true);
	do{
	    try{
		TimeUnit.SECONDS.sleep(1);
	    }catch(InterruptedException e){
		//nothing;
	    }
	}while(one.done == false);
	one.dispose();
    }   
    
}
