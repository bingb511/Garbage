package com.example.togglebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private ToggleButton toggle;
    private Switch switchs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        switchs = (Switch) findViewById(R.id.switchss);
        toggle.setOnCheckedChangeListener(this);
        switchs.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.toggleButton:
                if(compoundButton.isChecked()) Toast.makeText(this,"������",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"������",Toast.LENGTH_SHORT).show();
                break;
            case R.id.switchss:
                if(compoundButton.isChecked()) Toast.makeText(this,"����:ON",Toast.LENGTH_SHORT).show();
                else Toast.makeText(this,"����:OFF",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}