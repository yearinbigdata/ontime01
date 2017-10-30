package com.example.delay;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.parser.AirlineParser;
import com.example.parser.DelayCounters;
import com.example.sort.MonthKey;

public class DelayCountMapper extends Mapper<LongWritable, Text, MonthKey, IntWritable> {

	private final static IntWritable outputValue = new IntWritable(1);
	
	private MonthKey outputKey = new MonthKey();
	
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		AirlineParser parser = new AirlineParser(value);
		
		if(parser.isDepDelayAvailable()){
			if(parser.getDepDelay() > 0){
				outputKey.setUniqueCarrier("D, " + parser.getUniqueCarrier());
				outputKey.setMonth(parser.getMonth());
				
				context.write(outputKey, outputValue);
			}else if (parser.getDepDelay() == 0){
				context.getCounter(DelayCounters.scheduled_departure).increment(1);
			}else if (parser.getDepDelay() < 0){
				context.getCounter(DelayCounters.early_departure).increment(1);
			}
		}else {
			context.getCounter(DelayCounters.not_available_departure).increment(1);
		}
		
		if(parser.isArrDelayAvailable()){
			if(parser.getArrDelay() > 0){
				outputKey.setUniqueCarrier("D, " + parser.getUniqueCarrier());
				outputKey.setMonth(parser.getMonth());
				
				context.write(outputKey, outputValue);
			}else if (parser.getArrDelay() == 0){
				context.getCounter(DelayCounters.scheduled_arrival).increment(1);
			}else if (parser.getArrDelay() < 0){
				context.getCounter(DelayCounters.early_arrival).increment(1);
			}
		}else {
			context.getCounter(DelayCounters.not_available_arrival).increment(1);
		}
		
	}
	
}
