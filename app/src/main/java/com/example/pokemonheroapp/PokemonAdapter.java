package com.example.pokemonheroapp;

import android.content.Context;
import android.util.Log; // <-- Log.d kullanmak için
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {

    private Context context;
    private List<Pokemon> pokemonList;

    public PokemonAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // >>> Burası kritik: getView metodu <<<
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1) Layout inflate
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            // item_pokemon isimli layout dosyanızı kullanıyoruz. Sizde farklı ise ona göre düzeltin.
            convertView = inflater.inflate(R.layout.item_pokemon, parent, false);
        }

        // 2) Görsel bileşenleri yakala (ImageView, TextView vb.)
        ImageView imgPokemon = convertView.findViewById(R.id.imgPokemon);
        TextView txtName = convertView.findViewById(R.id.txtPokemonName);
        TextView txtType = convertView.findViewById(R.id.txtPokemonType);

        // 3) Listeden o anki Pokemon objesini al
        Pokemon p = pokemonList.get(position);

        // 4) Log'larla debug yapın - Hangi parametre null?
        Log.d("AdapterDebug", "==== getView for position " + position + " ====");
        Log.d("AdapterDebug", "imgPokemon = " + imgPokemon);  // Bu null mu?
        Log.d("AdapterDebug", "Pokemon object = " + p);       // Bu null mu?
        // p null değilse:
        if (p != null) {
            Log.d("AdapterDebug", "p.getImages() = " + p.getImages()); // images listesi
        }

        // 5) TextView'leri dolduralım (p null değilse)
        if (p != null) {
            // p.getName() vb. null değilse
            if (p.getName() != null) {
                txtName.setText(p.getName());
            } else {
                txtName.setText("No Name");
            }

            if (p.getType() != null) {
                txtType.setText(p.getType());
            } else {
                txtType.setText("No Type");
            }
        }

        // 6) GLIDE ile resim yükleme
        // +--- Burada null/boş liste/ilk öğe null kontrolü yapıyoruz
        if (p != null && p.getImages() != null && !p.getImages().isEmpty() && p.getImages().get(0) != null) {

            // 6a) imageUrl değişkenini alalım
            String imageUrl = p.getImages().get(0);
            Log.d("AdapterDebug", "imageUrl = " + imageUrl);

            // 6b) Glide'la yükleyelim
            Glide.with(context)
                    .load(imageUrl) // Bu null mu?
                    .placeholder(R.drawable.placeholder_pokemon)  // placeholder_pokemon var mı?
                    .into(imgPokemon);

        } else {
            // 6c) Liste yok, resim yok, ya da null => default resim
            Log.d("AdapterDebug", "No valid image. Using placeholder directly.");
            imgPokemon.setImageResource(R.drawable.placeholder_pokemon);
        }

        // 7) convertView'i döndür
        return convertView;
    }
}