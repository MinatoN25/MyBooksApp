package com.stackroute.favouriteservice.domain;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="book")
public class Book {
	
	@ApiModelProperty(name="Id",notes="Id of the entity",dataType="int")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;	
	
	@ApiModelProperty(name="User Id",notes="Id of the user",dataType="string")
	@Column(name="user_id")
	private String userId;
	
	@ApiModelProperty(name="Cover image url",notes="Link for the cover image",dataType="string")
	@JsonProperty("cover_i")
	@Column(name="cover_image")
	private String coverImage;

	@ApiModelProperty(name="Title",notes="Title of the book",dataType="string")
	@Column(name="title")
	private String title;

	@ApiModelProperty(name="Author Name",notes="Name of the book author",dataType="string[]")
	@JsonProperty("author_name")
	@Column(name="author_name")
	private String[] authorName;
	
	@ApiModelProperty(name="First Publish Year",notes="Year of the first published edition",dataType="int")
	@JsonProperty("first_publish_year")
	@Column(name="first_publish_year")
	private int firstPublishYear;
	
	@ApiModelProperty(name="Edition Count",notes="Number of editions published",dataType="int")
	@JsonProperty("edition_count")
	@Column(name="edition_count")
	private int editionCount;
	
	
	
	public Book(String userId, String coverImage, String title, String[] authorName, int firstPublishYear,
			int editionCount) {
		super();
		this.userId = userId;
		this.coverImage = coverImage;
		this.title = title;
		this.authorName = authorName;
		this.firstPublishYear = firstPublishYear;
		this.editionCount = editionCount;
	}


	


	public Book() {
		super();
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}	


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String[] getAuthorName() {
		return authorName;
	}


	public void setAuthorName(String[] authorName) {
		this.authorName = authorName;
	}


	public int getFirstPublishYear() {
		return firstPublishYear;
	}


	public void setFirstPublishYear(int firstPublishYear) {
		this.firstPublishYear = firstPublishYear;
	}


	public int getEditionCount() {
		return editionCount;
	}


	public void setEditionCount(int editionCount) {
		this.editionCount = editionCount;
	}


	public String getCoverImage() {
		return coverImage;
	}


	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}





	@Override
	public String toString() {
		return "Book [id=" + id + ", userId=" + userId + ", coverImage=" + coverImage + ", title=" + title
				+ ", authorName=" + Arrays.toString(authorName) + ", firstPublishYear=" + firstPublishYear
				+ ", editionCount=" + editionCount + "]";
	}
	
}
