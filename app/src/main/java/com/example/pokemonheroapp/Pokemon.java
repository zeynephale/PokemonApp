package com.example.pokemonheroapp;

import java.util.List;

public class Pokemon {
    private String name;
    private String type;
    private Integer pokedexNo;
    private String group;
    private List<String> images;
    private String videoUrl;
    private List<String> weaknesses;
    private List<String> abilities;
    private String boy;
    private String agirlik;
    private String aciklama;

    // Boş constructor (Firebase için zorunlu).
    public Pokemon() {
    }

    // Tüm alanları içeren isteğe bağlı bir dolu constructor.
    public Pokemon(String name,
                   String type,
                   Integer pokedexNo,
                   String group,
                   List<String> images,
                   String videoUrl,
                   List<String> weaknesses,
                   List<String> abilities,
                   String boy,
                   String agirlik,
                   String aciklama) {
        this.name = name;
        this.type = type;
        this.pokedexNo = pokedexNo;
        this.group = group;
        this.images = images;
        this.videoUrl = videoUrl;
        this.weaknesses = weaknesses;
        this.abilities = abilities;
        this.boy = boy;
        this.agirlik = agirlik;
        this.aciklama = aciklama;
    }

    // Getter - Setter'lar

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPokedexNo() {
        return pokedexNo;
    }

    public void setPokedexNo(Integer pokedexNo) {
        this.pokedexNo = pokedexNo;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public String getBoy() {
        return boy;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public String getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(String agirlik) {
        this.agirlik = agirlik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}