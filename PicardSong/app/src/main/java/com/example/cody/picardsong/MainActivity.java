package com.example.cody.picardsong;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    MediaPlayer mp;
    int count;
    RatingBar rater;
    TextView playCount;
    TextView ranking;
    ImageButton pp;
    ProgressBar pb;
    Thread t;
    RelativeLayout rl;
    boolean isPrepared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.picard);
        count = 0;
        rater = (RatingBar)findViewById(R.id.ratingBar);
        playCount = (TextView)findViewById(R.id.textView3);
        ranking = (TextView)findViewById(R.id.textView4);
        pp = (ImageButton)findViewById(R.id.imageButton);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        rl = (RelativeLayout)findViewById(R.id.layout);
        pb.setProgress(0);
        pb.setMax(mp.getDuration());
        t = new Thread() {
            public void run() {


                while (true) {
                    pb.setProgress(mp.getCurrentPosition());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

    }

    public String determineRank(){
        String rank = "";
        if(count == 0)
            rank = "Your Rank: Noob";
        else if(count == 1)
            rank = "Your Rank: Novice";
        else if(count == 2)
            rank = "Your Rank: Newbie";
        else if(count == 3)
            rank = "Your Rank: Stud muffin";
        else if(count == 4)
            rank = "Your Rank: Stud muffin Deluxe";
        else if(count == 5)
            rank = "Your Rank: Wesley Crusher";
        else if(count == 6)
            rank = "Your Rank: foo!";
        else if(count == 7)
            rank = "Your Rank: Ferengi";
        else if(count == 8)
            rank = "Your Rank: Romulan";
        else if(count == 9)
            rank = "Your Rank: William T. Riker";
        else if(count == 10)
            rank = "Your Rank: Captain Jean-Luc Picard!";
        else
            rank = "Your Rank: Santa Claus, you win.";
        return rank;
    }

    public void playPause(View v){
        if(!mp.isPlaying()){
            mp.start();
            pp.setImageResource(android.R.drawable.ic_media_pause);
            playCount.setText(count + " times");
        } else {
            mp.pause();
            pp.setImageResource(android.R.drawable.ic_media_play);
            playCount.setText(count + " times");
        }

    }

    public void restart(View view){

        mp.seekTo(0);
        if(pb.getProgress() >= pb.getMax()-1500) {
            count++;
            pp.setImageResource(android.R.drawable.ic_media_play);
            ranking.setText(determineRank());
        } else if(rater.getRating() == 3){
            mp.seekTo(mp.getDuration()-15000);
        }
        playCount.setText(count + " times");

    }

    public void cheaterAlert(View view){

        rater.setRating((float)count);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
