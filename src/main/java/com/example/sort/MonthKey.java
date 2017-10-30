package com.example.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import lombok.ToString;

@ToString
public class MonthKey implements WritableComparable<MonthKey>{
	
	private String uniqueCarrier;
	private Integer month;
	
	public MonthKey(){
		
	}
	
	public MonthKey(String uniqueCarrier, Integer month){
		this.uniqueCarrier = uniqueCarrier;
		this.month = month;
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
	
	public void readFields(DataInput in) throws IOException {
		uniqueCarrier = WritableUtils.readString(in);
		month = in.readInt();
	}

	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, uniqueCarrier);
		out.writeInt(month);
	}
	
	public int compareTo(MonthKey key) {
		int result = uniqueCarrier.compareTo(key.uniqueCarrier);
		if(0 == result){
			result = month.compareTo(key.month);
		}
		return result;
	}
	

}
