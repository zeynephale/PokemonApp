package com.example.pokemonheroapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ImageView imgPokeball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase'i başlat
        FirebaseApp.initializeApp(this);

        imgPokeball = findViewById(R.id.imageView);
        startPokeballRotation();

        // Birkaç saniye sonra kontrol
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkInternetAndFirebase();
            }
        }, 2000);
    }

    private void checkInternetAndFirebase() {
        if (!isInternetAvailable()) {
            Toast.makeText(this, "İnternet yok!", Toast.LENGTH_SHORT).show();
            stopPokeballRotation();
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pokemons");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Başarılı
                stopPokeballRotation();
                Intent intent = new Intent(MainActivity.this, PokemonListActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                stopPokeballRotation();
                Toast.makeText(MainActivity.this, "Veri okunamadı!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        }
        return false;
    }

    private void startPokeballRotation() {
        RotateAnimation rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        imgPokeball.startAnimation(rotate);
    }

    private void stopPokeballRotation() {
        imgPokeball.clearAnimation();
    }
}