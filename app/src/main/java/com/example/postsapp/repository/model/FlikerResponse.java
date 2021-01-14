package com.example.postsapp.repository.model;

import com.google.gson.annotations.SerializedName;

public class FlikerResponse{

	@SerializedName("stat")
	private String stat;

	@SerializedName("photos")
	private Photos photos;

	public String getStat(){
		return stat;
	}

	public Photos getPhotos(){
		return photos;
	}
}