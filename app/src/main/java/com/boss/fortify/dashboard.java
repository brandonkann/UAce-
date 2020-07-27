package com.boss.fortify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dashboard extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Button button1 = findViewById(R.id.butl);
        Button button2 = findViewById(R.id.libbut);
        Button button3 = findViewById(R.id.profile_button);
        Button button4 = findViewById(R.id.tips);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butl:
                Intent intent = new Intent(dashboard.this, subjectUI.class);
                startActivity(intent);
                break;
            case R.id.libbut:
                Intent intent1 = new Intent(dashboard.this, answerorquestion.class);
                startActivity(intent1);
                break;
            case R.id.profile_button:
                Intent intent2 = new Intent(dashboard.this, profile.class);
                startActivity(intent2);
                break;
            case R.id.tips:
                Intent intent3 = new Intent(dashboard.this, tips.class);
                startActivity(intent3);

        }
    }
}
