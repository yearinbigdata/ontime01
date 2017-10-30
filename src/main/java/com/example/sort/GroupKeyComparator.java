package com.example.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator{
	
	protected GroupKeyComparator(){
		super(MonthKey.class, true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		MonthKey k1 = (MonthKey)a;
		MonthKey k2 = (MonthKey)b;

		return k1.getUniqueCarrier().compareTo(k2.getUniqueCarrier());
	}

}
