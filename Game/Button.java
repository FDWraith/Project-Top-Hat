public class Button{
    private int xcor,ycor,height,width;
    private String doSomething;
    public Button(int xcor, int ycor, int w, int h,String action){
	this.xcor = xcor;
	this.ycor = ycor;
	height = h;
	width = w;
	doSomething = action;
    }

    public int getXCor(){
	return xcor;
    }
    public int getYCor(){
	return ycor;
    }
    public int getHeight(){
	return height;
    }
    public int getWidth(){
	return width;
    }

    public void trigger(){
	//whatever options the Button should do.
    }
}