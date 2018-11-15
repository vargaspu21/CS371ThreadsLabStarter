package edu.up.cs301threadslab;

public class ThreadSubclass extends Thread {
    private AnimationView myAV;

    public ThreadSubclass(String name) {
        super(name);
        //initialize the image view here
        myAV = new AnimationView();
    }

    public void run(){
        super.run();
        myAV.postInvalidate();
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
