package com.example.parser;

import org.apache.hadoop.io.Text;

public class AirlineParser {
	
	private int year;	//1
	private int month; //2
	private int dayofmonth;	//3
	private int dayofweek;	//4
	
	private int arrDelay = 0;	//15
	private int depDelay = 0;	//16
	private int lateDelay = 0;	//29
	
	
	private boolean arrDelayAvailable = true;
	private boolean depDelayAvailable = true;
	private boolean lateDelayAvailable = true;
	
	private String uniqueCarrier;	//9
	private String origin;	//17
	private String dest;		//18
	
	public AirlineParser(Text text){
		
		String[] columns = text.toString().split(",");
		
		year = Integer.parseInt(columns[0]);
		month = Integer.parseInt(columns[1]);
		dayofmonth = Integer.parseInt(columns[2]);
		dayofweek = Integer.parseInt(columns[3]);
		
		uniqueCarrier = columns[8];
		origin = columns[16];
		dest = columns[17];
		
		if(!columns[14].equals("NA")){
			arrDelay = Integer.parseInt(columns[14]);
		}else{
			arrDelayAvailable = false;
		}
		
		if(!columns[15].equals("NA")){
			depDelay = Integer.parseInt(columns[15]);
		}else{
			depDelayAvailable = false;
		}
		
		if(!columns[28].equals("NA")){
			lateDelay = Integer.parseInt(columns[28]);
		}else{
			lateDelayAvailable = false;
		}
	
		
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDayofmonth() {
		return dayofmonth;
	}

	public int getDayofweek() {
		return dayofweek;
	}

	public int getArrDelay() {
		return arrDelay;
	}

	public int getDepDelay() {
		return depDelay;
	}

	public int getLateDelay() {
		return lateDelay;
	}

	public boolean isArrDelayAvailable() {
		return arrDelayAvailable;
	}

	public boolean isDepDelayAvailable() {
		return depDelayAvailable;
	}

	public boolean isLateDelayAvailable() {
		return lateDelayAvailable;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDest() {
		return dest;
	}

}
