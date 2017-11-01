package com.example.delay;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.parser.AirlineParser;
import com.example.sort.MonthKey;

public class DelayCountReducerTest {
	
	MapDriver<LongWritable, Text, MonthKey, IntWritable> map;
	Configuration conf;
	
	Text recode1 = new Text("2008,1,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,NA,34,IND,BWI,515,3,10,0,,0,2,0,0,0,32");
	Text recode2 = new Text("2008,9,3,4,1829,1755,1959,1925,WN,3920,N464WN,90,90,77,40,NA,IND,BWI,NA,3,10,0,,0,2,0,0,0,32");


@Test
	public void testparser() {
		AirlineParser parser = new AirlineParser(recode1);
		System.out.println(parser.getMonth());
		System.out.println(parser.getUniqueCarrier());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() throws IOException {
		map = MapDriver.newMapDriver(new DelayCountMapper());
		map.withInput(new LongWritable(0), new Text(recode2));
		map.withOutput(new MonthKey("A, WN", 9), new IntWritable(1));

		map.runTest();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void test2() throws IOException {
		map = MapDriver.newMapDriver(new DelayCountMapper());
		map.withInput(new LongWritable(0), new Text(recode1));
		map.withOutput(new MonthKey("WN", 1), new IntWritable(1));

		map.runTest();
	}

}
