package com.boss.fortify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class answerorquestion extends AppCompatActivity {

    private Button button_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerorquestion);

        button_1 = (Button) findViewById(R.id.option1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_gallery_main_activity();
            }
        });
    }

    public void open_gallery_main_activity() {
        Intent intent = new Intent(this, mainactivitygallery.class);
        startActivity(intent);
    }

}
