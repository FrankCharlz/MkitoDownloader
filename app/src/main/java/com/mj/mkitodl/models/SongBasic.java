package com.mj.mkitodl.models;

import com.mj.mkitodl.utils.M;

public class SongBasic {
	public String song_url, song_name, song_preview;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name : "+song_name+
				"\nurl : "+song_url+
				"\npreview url : "+song_preview;
	}

	public String getSongUrl() {
		return "https:"+song_url;
	}

	public String getSongName() {
        return M.capitalize(song_name);
	}

	public String getSongpreviewUrl() {
		return "https:"+song_preview;
	}
}
