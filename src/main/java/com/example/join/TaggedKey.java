package com.example.join;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class TaggedKey implements WritableComparable<TaggedKey>{
	
	private String carrierCode;
	private Integer tag;
	
	public TaggedKey() {
	}

//	public TaggedKey(String carrierCode, int tag) {
//		this.carrierCode = carrierCode;
//		this.tag = tag;
//	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public String getCarrierCode() {
		return carrierCode;
	}

	public Integer getTag() {
		return tag;
	}
	

	@Override
	public int compareTo(TaggedKey key) {
		int result = this.carrierCode.compareTo(key.carrierCode);
		
		if(result==0){
			return this.tag.compareTo(key.tag);
		}
		
		return result;
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		
		WritableUtils.writeString(out, carrierCode);
		out.writeInt(tag);
		
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		
		carrierCode = WritableUtils.readString(in);
		tag = in.readInt();
		
	}
	
	@Override
	public String toString() {
		return carrierCode + "," + tag + ",";
	}

	
}
