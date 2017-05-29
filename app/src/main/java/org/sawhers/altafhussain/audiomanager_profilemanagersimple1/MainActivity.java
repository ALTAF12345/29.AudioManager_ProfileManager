package org.sawhers.altafhussain.audiomanager_profilemanagersimple1;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rdbProfileGroup;
    Button btnMode;
    AudioManager audioManager;//this is responsible for all the audiuo work of phone
    RadioButton rdbSilent,rdbVibration,rdbNormal,rdbNarmalVibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdbProfileGroup= (RadioGroup) findViewById(R.id.rdb_ProfileGroup);
        rdbSilent= (RadioButton) findViewById(R.id.rdb_silent);
        rdbVibration= (RadioButton) findViewById(R.id.rdb_vibration);
        rdbNormal= (RadioButton) findViewById(R.id.rdb_loud);
        rdbNarmalVibration= (RadioButton) findViewById(R.id.rdb_ringer_vibration);


        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);//here we initilize the audiomanager
        //we define globle or oncreate b/c we call this in btn and groph
        btnMode= (Button) findViewById(R.id.btn_showCurrentMode);
        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mode=audioManager.getRingerMode();
                if (mode==AudioManager.RINGER_MODE_SILENT) {
                    Toast.makeText(MainActivity.this, "SILENT", Toast.LENGTH_SHORT).show();
                    rdbSilent.setChecked(true);

                }
                else if (mode==AudioManager.RINGER_MODE_VIBRATE) {
                    Toast.makeText(MainActivity.this, "VIBRATE", Toast.LENGTH_SHORT).show();
                    rdbVibration.setChecked(true);
                }
                else if (mode==AudioManager.RINGER_MODE_NORMAL){
                    Toast.makeText(MainActivity.this, "NORMAL", Toast.LENGTH_SHORT).show();
                    rdbNormal.setChecked(true);
                }

            }

        });
        rdbProfileGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //RadioButton radioButtonCheck=rdbProfileGroup.getCheckedRadioButtonId();//this is give the id of check radio button
                RadioButton radioButtonCheck= (RadioButton) findViewById(rdbProfileGroup.getCheckedRadioButtonId());
                if (radioButtonCheck.getText().equals("Silent")){
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
                else if (radioButtonCheck.getText().equals("vibration")){
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                }
                else if (radioButtonCheck.getText().equals("loud")){
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
                else if (radioButtonCheck.getText().equals("ringer_vibration")){
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL | AudioManager.RINGER_MODE_VIBRATE);
                }
            }
        });




    }

}
