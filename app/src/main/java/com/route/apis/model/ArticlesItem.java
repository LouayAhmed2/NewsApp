package com.route.apis.model;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem{

	@SerializedName("publishedAt")
	public String publishedAt;

	@SerializedName("author")
	public String author;

	@SerializedName("urlToImage")
	public String urlToImage;

	@SerializedName("description")
	public String description;

	@SerializedName("source")
	public SourcesItem source;

	@SerializedName("title")
	public String title;

	@SerializedName("url")
	public String url;

	@SerializedName("content")
	public String content;


	@Override
 	public String toString(){
		return 
			"ArticlesItem{" + 
			"publishedAt = '" + publishedAt + '\'' + 
			",author = '" + author + '\'' + 
			",urlToImage = '" + urlToImage + '\'' + 
			",description = '" + description + '\'' + 
			",source = '" + source + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}