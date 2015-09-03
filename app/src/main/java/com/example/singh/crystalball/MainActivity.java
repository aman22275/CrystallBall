package com.example.singh.crystalball;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
   // Button btn;
    TextView tv;
    ImageView iv;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;



    //crystalball class
    private CrystalBall mCrystalBall=new CrystalBall();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=(ImageView)findViewById(R.id.iv);
        tv=(TextView)findViewById(R.id.tv);
        iv.setImageResource(R.drawable.ball01);

      /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //Method we create in crystalball.java
                handleNewAnswer();
            }
        });*/


        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector=new ShakeDetector(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake() {
                  handleNewAnswer();
            }
        });

     //   mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        super.onPause();
         mSensorManager.unregisterListener(mShakeDetector);

    }

    //for shaking
    private void handleNewAnswer() {
        String answer=mCrystalBall.getAnAnswer();

      //  mCrystalBall.answers[0]="WASSP";

        tv.setText(answer);

        animateCrystalBall();
        animateAnswer();
        playSound();
    }

    //animate crystal ball onclick
    private void animateCrystalBall()
    {

        ImageView cBall=(ImageView)findViewById(R.id.iv);
        cBall.setImageResource(R.drawable.ball_animation);
        AnimationDrawable ballAnim=(AnimationDrawable) cBall.getDrawable();

        if(ballAnim.isRunning()){
            ballAnim.stop();
        }

        ballAnim.start();

    }


    //animate answer onclick
    private void animateAnswer()
    {
        AlphaAnimation fadeIn=new AlphaAnimation(0, 1);
        fadeIn.setDuration(1500);
        fadeIn.setFillAfter(true);
        tv.setAnimation(fadeIn);
    }

//play song on click
    private void playSound()
    {
        MediaPlayer player=MediaPlayer.create(this, R.raw.crystal_ball);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

              mp.release();
            }
        });
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
