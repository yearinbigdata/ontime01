package com.example.reducejoin;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.join.TaggedGroupKeyComparator;
import com.example.join.TaggedGroupKeyPartitioner;
import com.example.join.TaggedKey;
import com.example.join.TaggedKeyComparator;

public class ReduceSideJoinJob extends Configured implements Tool{
	
	public static void main(String[] args) throws Exception{
		ToolRunner.printGenericCommandUsage(System.out);
		args = new String[] { "-fs", "hdfs://bigdata01:9000", "-jt", "bigdata01:9001", };
		ToolRunner.run(new ReduceSideJoinJob(), args);
		
		
	}

	@Override
	public int run(String[] args) throws Exception {
//		Configuration conf = new Configuration();
		
		Job job = new Job(getConf(), "ReduceSideJoinJob");
		
//		MultipleInputs.addInputPath(job, new Path("/home/java/input/1987_nohead.csv"), TextInputFormat.class,
//				MapperReduceSideJoin.class);
		MultipleInputs.addInputPath(job, new Path("/home/java/delaycount/codemonthTotal"), TextInputFormat.class,
				MapperReduceSideJoin.class);
		MultipleInputs.addInputPath(job, new Path("/home/java/meta/carriers.csv"),
				TextInputFormat.class, CarrierCodeMapper.class);
		
//		Path inputDir = new Path("/home/java/input/1987_nohead.csv");
//		Path inputDir = new Path("hdfs://bigdata01:9000/home/java/input/1987_nohead.csv");
//		Path outputDir = new Path("/home/java/dataexpo_out/delaycountmapper");
//		Path outputDir = new Path("/home/java/dataexpo_out/carriermapper");
		Path outputDir = new Path("/home/java/dataexpo_out/reducejoin");
		
		FileOutputFormat.setOutputPath(job, outputDir);
		
		job.setJarByClass(ReduceSideJoinJob.class);
		job.setPartitionerClass(TaggedGroupKeyPartitioner.class);
		job.setGroupingComparatorClass(TaggedGroupKeyComparator.class);
		job.setSortComparatorClass(TaggedKeyComparator.class);
		
		job.setReducerClass(ReducerReduceSideJoin.class);
//		job.setNumReduceTasks(0);
		
//		job.setInputFormatClass(TextInputFormat.class);
//		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapOutputKeyClass(TaggedKey.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileSystem hdfs = FileSystem.get(getConf());
		hdfs.delete(outputDir, true);
		hdfs.close();
		
		job.waitForCompletion(true);
		return 0;
	}

}


