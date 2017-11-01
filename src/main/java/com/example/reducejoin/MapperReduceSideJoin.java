package com.example.reducejoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.join.TaggedKey;
import com.example.parser.AirlineParser;
import com.example.parser.CarrierCodeParser;
import com.example.parser.DelayCountParser;

public class MapperReduceSideJoin extends Mapper<LongWritable, Text, TaggedKey, Text>{
	
	TaggedKey outputKey = new TaggedKey();
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		DelayCountParser parser = new DelayCountParser(value);
		outputKey.setCarrierCode(parser.getUniqueCarrier());
		outputKey.setTag(1);
		context.write(outputKey, value);
	}

}
