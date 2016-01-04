package com.mj.mkitodl.models;

public class Song extends SongBasic {
	public String category, image, artist_name, artist_url;

	@Override
	public String toString() {
		return super.toString()+"\nimage_url: "
				+image;
	}
}
