package com.mj.mkitodl.models;

public class SearchEntry {
    public String song_name, result_url, image, artist_name;
    public int result_type;
    
    @Override
    public String toString() {
    	return "type : "+result_type+
    			"\nsong name : "+song_name+
    			"\n res url : "+result_url;
    }
}