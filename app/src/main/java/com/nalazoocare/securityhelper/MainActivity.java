package com.nalazoocare.securityhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nalazoocare.securityhelper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final static String TAG = "meme";

    private String eSunFlower;
    private String dSunFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.ebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binding.etext.setText(DES3.encode(binding.edit.getText().toString()));
                    eSunFlower =  DES3.encode(binding.edit.getText().toString());
                    Log.d(TAG,"meme encode :" +eSunFlower );
                } catch (Exception e) {
                    Log.d(TAG,"meme exception:" + e);
                }
            }
        });

        binding.dbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.dtext.setText(DES3.decode(eSunFlower));
                dSunFlower = DES3.decode(eSunFlower);
                Log.d(TAG,"meme decode:" +dSunFlower);
            }
        });
    }
}
