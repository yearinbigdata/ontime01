package com.example.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class GroupKeyPartitioner extends Partitioner<MonthKey, IntWritable> {
	
	@Override
	public int getPartition(MonthKey key, IntWritable val, int numPartitions) {

		int hash = key.getUniqueCarrier().hashCode();
		int partition = hash % numPartitions;
		
		return partition;
	}

}
