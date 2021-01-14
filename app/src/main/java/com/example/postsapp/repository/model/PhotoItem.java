package com.example.postsapp.repository.model;

import com.google.gson.annotations.SerializedName;

public class PhotoItem{

	@SerializedName("owner")
	private String owner;

	@SerializedName("server")
	private String server;

	@SerializedName("height_s")
	private int heightS;

	@SerializedName("url_s")
	private String urlS;

	@SerializedName("isfriend")
	private int isfriend;

	@SerializedName("secret")
	private String secret;

	@SerializedName("title")
	private String title;

	@SerializedName("isfamily")
	private int isfamily;

	@SerializedName("dateupload")
	private String dateupload;

	@SerializedName("width_s")
	private int widthS;

	@SerializedName("ispublic")
	private int ispublic;

	@SerializedName("farm")
	private int farm;

	@SerializedName("id")
	private String id;

	public String getOwner(){
		return owner;
	}

	public String getServer(){
		return server;
	}

	public int getHeightS(){
		return heightS;
	}

	public String getUrlS(){
		return urlS;
	}

	public int getIsfriend(){
		return isfriend;
	}

	public String getSecret(){
		return secret;
	}

	public String getTitle(){
		return title;
	}

	public int getIsfamily(){
		return isfamily;
	}

	public String getDateupload(){
		return dateupload;
	}

	public int getWidthS(){
		return widthS;
	}

	public int getIspublic(){
		return ispublic;
	}

	public int getFarm(){
		return farm;
	}

	public String getId(){
		return id;
	}
}