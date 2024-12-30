package com.example.pokemonheroapp;

import android.os.Bundle;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.util.List;

public class PokemonListActivity extends AppCompatActivity {

    private ListView listView;
    private PokemonAdapter adapter;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        listView = findViewById(R.id.pokemonListView);
        pokemonList = new ArrayList<>();

        adapter = new PokemonAdapter(this, pokemonList);
        listView.setAdapter(adapter);

        loadPokemons();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Pokemon selected = pokemonList.get(position);
            Intent intent = new Intent(PokemonListActivity.this, PokemonDetailActivity.class);

            // Tek tek putExtra
            intent.putExtra("name", selected.getName());
            intent.putExtra("type", selected.getType());
            intent.putExtra("pokedexNo", selected.getPokedexNo());
            intent.putExtra("group", selected.getGroup());
            if (selected.getImages() != null) {
                intent.putStringArrayListExtra("images", new ArrayList<>(selected.getImages()));
            }
            intent.putExtra("videoUrl", selected.getVideoUrl());
            if (selected.getWeaknesses() != null) {
                intent.putStringArrayListExtra("weaknesses", new ArrayList<>(selected.getWeaknesses()));
            }
            if (selected.getAbilities() != null) {
                intent.putStringArrayListExtra("abilities", new ArrayList<>(selected.getAbilities()));
            }
            intent.putExtra("boy", selected.getBoy());
            intent.putExtra("agirlik", selected.getAgirlik());
            intent.putExtra("aciklama", selected.getAciklama());

            startActivity(intent);
        });
    }

    private void loadPokemons() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pokemons");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pokemonList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Pokemon p = ds.getValue(Pokemon.class);
                    if (p != null) {
                        pokemonList.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(PokemonListActivity.this, "Veri yüklenemedi!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}