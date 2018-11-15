package edu.up.cs301threadslab;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * This application displays several animations.  It is used for the threads lab in CS371.
 *
 * @author Andrew Nuxoll
 * @version Fall 2015
 */
public class MainActivity extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private AnimationView myAV;
    private Button theButton;
    private SeekBar theSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup the animation(s)
        myAV = (AnimationView)findViewById(R.id.animationArea);
        myAV.addAnimation(new StarAnimation(myAV.getMyWidth(), myAV.getMyHeight()));

        //Let me know when someone taps the button
        theButton = (Button)findViewById(R.id.button);
        theButton.setOnClickListener(this);

        //Let me know when someone adjusts the seekbar
        theSeekBar = (SeekBar)findViewById(R.id.seekBar);
        theSeekBar.setOnSeekBarChangeListener(this);

        ThreadSubclass ts = new ThreadSubclass("name");
        ts.start();
    }//onClick

    @Override
    public void onClick(View v) {
        myAV.postInvalidate();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        myAV.progressChange(seekBar.getProgress());
        myAV.postInvalidate();
    }

    /** These two methods aren't used */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        //CHECKPOINT 2
        //using this sleep method causes the seekbar to be slow.


        myAV.invalidate(); //added this so the animation draws automatically as the seekpar changes
        //CHECKPOINT 1
        // Much quicker to create a thread than a process.
        //Much quicker to switch between threads than to switch between processes.
        //Threads share data easily
    }
}
