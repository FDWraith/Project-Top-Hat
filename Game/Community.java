import java.util.*;
import java.util.concurrent.TimeUnit;

public class Community extends Slot{
    private String[] cards = {"Advance to Go (Collect 200)","Bank error in your favor - Collect $200", "Doctor's fee - Pay $50","Frrom sale of stock you get $50","Get Out of Jail Free - This card may be kept until needed or sold", "Go to Jail - Go directly to Jail - Do not pass Go - Do not collect $200", "Grand Opera Night - Collect $50 from every player for opening night seats", "Holiday Fund matures - Collect $100","Income tax refund - Collect $20","It is your birthday - Collect $10 from each player", "Life insurance matures - Collect $100", "Pay hospital fees of $100","Pay school fees of $150","Receive $25 consultancy fee","You are assessed for street repairs - $40 per house - $115 per hotel", "You have won second prize in a beauty contest - Collect $10","You inherit $100"};
    
    public Community(int ID){
	super(ID);
    }

    //Mandatory Action
    public void doAction(Player name){
	Random r = new Random();
	int x = r.nextInt(cards.length);  
        CommunityMenu one = new CommunityMenu(name,cards[x],x);
	one.setVisible(true);
	
	do{
	    try{
		TimeUnit.SECONDS.sleep(1);
	    }catch(InterruptedException e){
		//do nothing
	    }
	}while(one.done == false);
	one.dispose();
    }
   
}