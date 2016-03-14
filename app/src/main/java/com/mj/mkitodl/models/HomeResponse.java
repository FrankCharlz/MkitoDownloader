package com.mj.mkitodl.models;

import java.util.List;

public class HomeResponse {
    public int success;
    public List<Song> songs;

    @Override
    public String toString() {
        return "success : " + success + "\n" + songs.toString();
    }
}