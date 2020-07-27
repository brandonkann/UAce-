package com.boss.fortify;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class subjectUI extends AppCompatActivity implements View.OnClickListener{

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_ui);
        Button butm = findViewById(R.id.butm);
        Button buts = findViewById(R.id.buts);
        Button butc = findViewById(R.id.butc);
        Button butp = findViewById(R.id.butp);





        butm.setOnClickListener(this);
        buts.setOnClickListener(this);
        butc.setOnClickListener(this);
        butp.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butm:
                Intent intent = new Intent(subjectUI.this, gallery.class);
                startActivity(intent);
                break;
            case R.id.buts:
                Intent intent1 = new Intent(subjectUI.this, gallery.class);
                startActivity(intent1);
                break;
            case R.id.butc:
                Intent intent2 = new Intent(subjectUI.this, gallery.class);
                startActivity(intent2);
                break;
            case R.id.butp:
                Intent intent3 = new Intent(subjectUI.this, gallery.class);
                startActivity(intent3);
                break;

        }

    }
}



