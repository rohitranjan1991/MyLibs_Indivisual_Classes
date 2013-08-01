package com.example.xmlpullparser;

public class songs {

	String id, title, artist, duration, thumb_url;

	public songs() {
		super();
	}

	public songs(String id, String title, String artist, String duration,
			String thumb_url) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.thumb_url = thumb_url;
	}

	public String get(int i) {
		String ret = null;
		switch (i) {
		case 0:
			ret = id;
			break;
		case 1:
			ret = title;
			break;
		case 2:
			ret = artist;
			break;
		case 3:
			ret = duration;
			break;
		case 4:
			ret = thumb_url;
			break;
		}
		return ret;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @return the thumb_url
	 */
	public String getThumb_url() {
		return thumb_url;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @param thumb_url
	 *            the thumb_url to set
	 */
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public int getChildrenSize() {

		return 5;
	}

}