package com.example.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MonthKeyComparator extends WritableComparator{
	protected MonthKeyComparator(){
		super(MonthKey.class, true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {

		MonthKey k1 = (MonthKey)a;
		MonthKey k2 = (MonthKey)b;
		
		int cmp = k1.getUniqueCarrier().compareTo(k2.getUniqueCarrier());
		if(cmp!=0){
			return cmp;
		}
		
		return k1.getMonth() == k2.getMonth() ? 0 : (k1.getMonth() < k2.getMonth() ? -1 : 1);
	}

}
