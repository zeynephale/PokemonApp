package com.example.pokemonheroapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView txtDetailName, txtDetailType, txtDetailGroup, txtDetailPokedexNo;
    private TextView txtDetailBoy, txtDetailAgirlik, txtDetailAbilities, txtDetailWeaknesses, txtDetailAciklama;

    private YouTubePlayerView youtubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        // 1) Layout bileşenlerini yakala
        youtubePlayerView = findViewById(R.id.youtube_player_view);
        imgDetail = findViewById(R.id.imgDetail);
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailType = findViewById(R.id.txtDetailType);
        txtDetailGroup = findViewById(R.id.txtDetailGroup);
        txtDetailPokedexNo = findViewById(R.id.txtDetailPokedexNo);
        txtDetailBoy = findViewById(R.id.txtDetailBoy);
        txtDetailAgirlik = findViewById(R.id.txtDetailAgirlik);
        txtDetailAbilities = findViewById(R.id.txtDetailAbilities);
        txtDetailWeaknesses = findViewById(R.id.txtDetailWeaknesses);
        txtDetailAciklama = findViewById(R.id.txtDetailAciklama);
        getLifecycle().addObserver(youtubePlayerView);

        // 3) Intent ile gelen verileri çek
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        int pokedexNo = getIntent().getIntExtra("pokedexNo", 0);
        String group = getIntent().getStringExtra("group");
        ArrayList<String> images = getIntent().getStringArrayListExtra("images");
        String videoUrl = getIntent().getStringExtra("videoUrl"); // örn: "https://www.youtube.com/watch?v=abc123"
        ArrayList<String> weaknesses = getIntent().getStringArrayListExtra("weaknesses");
        ArrayList<String> abilities = getIntent().getStringArrayListExtra("abilities");
        String boy = getIntent().getStringExtra("boy");
        String agirlik = getIntent().getStringExtra("agirlik");
        String aciklama = getIntent().getStringExtra("aciklama");

        // 4) TextView'lere verileri set et
        if (name != null) {
            txtDetailName.setText(name);
        }
        if (type != null) {
            txtDetailType.setText("Type: " + type);
        }
        if (group != null) {
            txtDetailGroup.setText("Group: " + group);
        }
        // int için default 0 yazıyoruz
        txtDetailPokedexNo.setText("PokedexNo: " + pokedexNo);

        if (boy != null) {
            txtDetailBoy.setText("Boy: " + boy);
        }
        if (agirlik != null) {
            txtDetailAgirlik.setText("Ağırlık: " + agirlik);
        }
        if (abilities != null && !abilities.isEmpty()) {
            txtDetailAbilities.setText("Yetenekler: " + abilities.toString());
        }
        if (weaknesses != null && !weaknesses.isEmpty()) {
            txtDetailWeaknesses.setText("Zayıflıklar: " + weaknesses.toString());
        }
        if (aciklama != null) {
            txtDetailAciklama.setText(aciklama);
        }

        // 5) Resim yükleme (sadece ilk resim)
        if (images != null && !images.isEmpty() && images.get(0) != null) {
            Glide.with(this)
                    .load(images.get(0))
                    .placeholder(R.drawable.placeholder_pokemon)
                    .into(imgDetail);
        } else {
            // resim yoksa placeholder kullan
            imgDetail.setImageResource(R.drawable.placeholder_pokemon);
        }
        final String videoId = extractVideoId(videoUrl);
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                String videoId = extractVideoId(videoUrl);
                youTubePlayer.loadVideo(videoId, 0f);
            }
        });
    }

    private String extractVideoId(String fullUrl) {
        if (fullUrl == null) return "";
        if (fullUrl.contains("watch?v=")) {
            return fullUrl.substring(fullUrl.indexOf("v=") + 2);
        }
        return fullUrl; // belki embed link vb. gelirse
    }
}