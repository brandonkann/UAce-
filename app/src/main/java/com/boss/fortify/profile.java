package com.boss.fortify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.lang.reflect.Member;

public class profile extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    ImageView imageView;
    EditText editText;
    Uri uriProfileImage;
    ProgressBar progressBar;
    String profileImageUrl;
    FirebaseAuth mAuth;
    TextView textView;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    DatabaseReference reff;
    Button btnsave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        editText = (EditText) findViewById(R.id.editTextDisplayName);
        imageView = (ImageView) findViewById(R.id.profile_image);
        progressBar = findViewById(R.id.progressbar3);
        textView = (TextView) findViewById(R.id.textViewVerified);






        
        loadUserInformation();

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();

        }

            private void saveUserInformation() {
                String displayName = editText.getText().toString();

                if(displayName.isEmpty()) {
                    editText.setError("Name required");
                    editText.requestFocus();
                    return;
                }
                FirebaseUser user = mAuth.getCurrentUser();
                if(user!=null) {
                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                            .setDisplayName(displayName)
                            .build();


                    user.updateProfile(profile)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(profile.this, "Profile updated", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

        });
}

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() ==null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }



    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

            if (user.getDisplayName() != null) {
                editText.setText(user.getDisplayName());
            }


            if (user.isEmailVerified()) {
                textView.setText("Email Verified");
            }
            else {
                textView.setText("Email Not Verified (Click Here to Verfiy)");
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(profile.this, "Verification sent", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
            }


        }





    @Override
    public boolean onCreateOptionsMenu(Menu logout) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, logout);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }

        return true;
    }



}
