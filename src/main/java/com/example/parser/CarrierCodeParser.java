package com.example.parser;

import org.apache.hadoop.io.Text;

public class CarrierCodeParser {
	
	private String carrierCode;	//1
	private String carrierName; //2
	
	public CarrierCodeParser(Text value) {
	    this(value.toString());
	  }
	
	public CarrierCodeParser(String value){
		
		String[] columns = value.toString().split(",");
		if (columns != null && columns.length > 0) {
		carrierCode = columns[0];
		carrierName = columns[1];
		}

	}


	public String getCarrierCode() {
		return carrierCode;
	}


	public String getCarrierName() {
		return carrierName;
	}

//	public void setCarrierCode(String carrierCode) {
//		this.carrierCode = carrierCode;
//	}
//
//	public void setCarrierName(String carrierName) {
//		this.carrierName = carrierName;
//	}

}
