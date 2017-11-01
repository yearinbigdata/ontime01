package com.example.reducejoin;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.example.join.TaggedKey;

public class ReducerReduceSideJoin extends Reducer<TaggedKey, Text, Text, Text>{
	private Text outputKey = new Text();
	private Text outValue = new Text();
	
	public void reduce(TaggedKey key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		Iterator<Text> iterator = values.iterator();
		
		Text carrierName = new Text(iterator.next());
		
		while(iterator.hasNext()){
			Text record = iterator.next();
			outputKey.set(key.getCarrierCode());
			outValue = new Text(carrierName.toString() + "\t" + record.toString());
			context.write(outputKey, outValue);
		}
	}

}
