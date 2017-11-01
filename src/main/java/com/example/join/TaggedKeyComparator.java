package com.example.join;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TaggedKeyComparator extends WritableComparator{
	
	protected TaggedKeyComparator(){
		super(TaggedKey.class, true);
	}
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		TaggedKey k1 = (TaggedKey)a;
		TaggedKey k2 = (TaggedKey)b;
		
		int cmp = k1.getCarrierCode().compareTo(k2.getCarrierCode());
		if(cmp != 0){
			return cmp;
		}
		
		return k1.getTag().compareTo(k2.getTag());

	}

}
