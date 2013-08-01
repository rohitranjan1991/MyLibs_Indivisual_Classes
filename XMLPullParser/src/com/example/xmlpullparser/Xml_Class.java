package com.example.xmlpullparser;

import java.util.ArrayList;
import java.util.HashMap;

public class Xml_Class {

	String TagName, Text;

	HashMap<String, String> attr;
	HashMap<String, Xml_Class> block = new HashMap<String, Xml_Class>();
	ArrayList<Xml_Class> tagList=new ArrayList< Xml_Class>();

	public void addBlockList(ArrayList<Xml_Class> blockToAdd) {
		tagList=blockToAdd;
	}

	
	public int getBlockListSize()
	{return  tagList.size();}
	
	public Xml_Class getBlock(int i)
	{
		return tagList.get(i);
	}
	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return TagName;
	}

	
	
	public Xml_Class getThis ()
	{return this;}
	
	/**
	 * @return the block
	 */
	public HashMap<String, Xml_Class> getBlock() {
		return block;
	}

	/**
	 * @param block
	 *            the block to set
	 */
	public void setBlock(HashMap<String, Xml_Class> block) {
		this.block = block;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return Text;
	}

	/**
	 * @return the attr
	 */
	public HashMap<String, String> getAttr() {
		return attr;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		TagName = tagName;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		Text = text;
	}

	/**
	 * @param attr
	 *            the attr to set
	 */
	public void setAttr(HashMap<String, String> attr) {
		this.attr = attr;
	}

}
