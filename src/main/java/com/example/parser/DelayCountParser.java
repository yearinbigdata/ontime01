package com.example.parser;

import org.apache.hadoop.io.Text;

public class DelayCountParser {
	
	private String ad; //1
	private String uniqueCarrier; //2
	private int month; //3
	private int delaycount; //4

	
	public DelayCountParser(Text text){
		
		String[] columns = text.toString().split(",");
		
		ad = columns[0];
		uniqueCarrier = columns[1];
		month = Integer.parseInt(columns[2]);
		delaycount = Integer.parseInt(columns[3]);
		
	}


	public int getMonth() {
		return month;
	}


	public String getUniqueCarrier() {
		return uniqueCarrier;
	}


	public String getAd() {
		return ad;
	}


	public int getDelaycount() {
		return delaycount;
	}


}
