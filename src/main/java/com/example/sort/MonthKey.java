package com.example.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import lombok.ToString;

public class MonthKey implements WritableComparable<MonthKey>{
	
	private String uniqueCarrier;
	private Integer month;
//	private String sep = ",";
	
	public MonthKey(){
		
	}
	
	public MonthKey(String uniqueCarrier, Integer month){
		this.uniqueCarrier = uniqueCarrier;
		this.month = month;
//		this.sep = sep;
	}

	public String getUniqueCarrier() {
		return uniqueCarrier;
	}

	public void setUniqueCarrier(String uniqueCarrier) {
		this.uniqueCarrier = uniqueCarrier;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		uniqueCarrier = WritableUtils.readString(in);
		month = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, uniqueCarrier);
		out.writeInt(month);
	}
	@Override
	public int compareTo(MonthKey key) {
		int result = uniqueCarrier.compareTo(key.uniqueCarrier);
		if(0 == result){
			result = month.compareTo(key.month);
		}
		return result;
	}
	@Override
	public String toString() {
		
		return (new StringBuilder()).append(uniqueCarrier).append(",").append(month).append(",").toString();
	}

//	public String getSep() {
//		return sep;
//	}
//
//	public void setSep(String sep) {
//		this.sep = sep;
//	}
	
}
