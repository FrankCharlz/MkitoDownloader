package com.mj.mkitodl.models;

import com.mj.mkitodl.utils.M;

public class Song extends SongBasic {
	public String category, image, artist_name, artist_url;

	@Override
	public String toString() {
		return super.toString()+"\nimage_url: "
				+image;
	}
	public String getImageUrl() {
		//https://mkito.com/images/artists/artist_5eb154350078e8d080abadcb596ad6c6.jpg
		return "https:"+image;
	}

	public String getCategory() {
		return category;
	}

	public String getImage() {
		return image;
	}

	public String getArtistName() {
		return M.capitalize(artist_name);
	}

	public String getArtistUrl() {
		return "https:"+artist_url;
	}
}
