package com.mj.mkitodl.models;

public class SongBasic {
	public String song_url, song_name, song_preview;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name : "+song_name+
				"\nurl : "+song_url+
				"\npreview url : "+song_preview;
	}
}
