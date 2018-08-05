package com.example.root.lecturealarm;


import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class HomeActivity extends AppCompatActivity {


    private Button btnSet , btnStop;
    private ImageButton btnIncrease , btnDecrease;
    private EditText txtTime;
    private long totaltimeInSec;
    private TimerC timerC;
    private TimerVibrator timerVibrator;
    String timerStatesVibrator = "";
    String timerStatesCount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnIncrease = (ImageButton)findViewById(R.id.btn_increase);
        btnDecrease = (ImageButton)findViewById(R.id.btn_decrease);
        btnSet = (Button) findViewById(R.id.btn_set);
        btnStop = (Button) findViewById(R.id.btn_stop);
        txtTime = (EditText) findViewById(R.id.txt_time);




        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtTime.getText().toString().equals("")){

                    String Strtime = txtTime.getText().toString();
                    int time = Integer.parseInt(Strtime);

                    if (time < 999){

                        txtTime.setText(time + 1 + "");

                    }


                }else{

                    txtTime.setText(0 + "");

                }




            }
        });


        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtTime.getText().toString().equals("")){


                    String Strtime = txtTime.getText().toString();
                    int time = Integer.parseInt(Strtime);

                    if (time != 0){
                        txtTime.setText(time - 1 + "");
                    }

                }else {

                    txtTime.setText(0 + "");

                }



            }
        });



        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!txtTime.getText().toString().equals("") && !txtTime.getText().toString().equals("0")){

                    String Strtime = txtTime.getText().toString();
                    int time = Integer.parseInt(Strtime);
                    totaltimeInSec = time * 60;

                    CountDown(totaltimeInSec);


                }

            }
        });



        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                txtTime.setEnabled(true);
                btnIncrease.setEnabled(true);
                btnDecrease.setEnabled(true);

                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(3);
                txtTime.setFilters(fArray);


                if (timerStatesCount.equals("counting")){

                    timerC.cancel();

                }
                if (timerStatesVibrator.equals("vibrating")){

                    timerVibrator.cancel();

                }else {

                }




            }
        });



    }


    private void StartAlarm(){


        timerVibrator = new TimerVibrator(30000 , 4000);
        timerVibrator.start();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    private void CountDown(long sec){

        txtTime.setEnabled(false);
        btnIncrease.setEnabled(false);
        btnDecrease.setEnabled(false);
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(5);
        txtTime.setFilters(fArray);

        String Strtime = txtTime.getText().toString();
        long t = Integer.parseInt(Strtime) * 60;
        txtTime.setText(t + "");

        timerC = new TimerC(sec * 1000 , 1000);
        timerC.start();


    }

    class TimerC extends CountDownTimer{

        long time;
        long intervals;


        public TimerC(long _time , long _intervals){
            super(_time,_intervals);
            time = _time;
            intervals = _intervals;

        }

        @Override
        public void onTick(long l) {

            String Strtime = txtTime.getText().toString();
            long t = Long.parseLong(Strtime);
            txtTime.setText(t - 1 + "");
            timerStatesCount = "counting";

        }

        @Override
        public void onFinish() {

            StartAlarm();
            timerStatesCount = "finished";

        }
    }



    class TimerVibrator extends CountDownTimer{

        long time;
        long intervals;


        public TimerVibrator(long _time , long _intervals){
            super(_time,_intervals);
            time = _time;
            intervals = _intervals;

        }

        @Override
        public void onTick(long l) {

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(2000);
            timerStatesVibrator = "vibrating";

        }

        @Override
        public void onFinish() {

            CountDown(totaltimeInSec);
            timerStatesVibrator = "finished";

        }
    }


}
