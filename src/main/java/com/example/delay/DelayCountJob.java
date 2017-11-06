package com.example.delay;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.reducejoin.ReduceSideJoinJob;
import com.example.sort.GroupKeyComparator;
import com.example.sort.GroupKeyPartitioner;
import com.example.sort.MonthKey;
import com.example.sort.MonthKeyComparator;

public class DelayCountJob extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.printGenericCommandUsage(System.out);
		args = new String[] { "-fs", "hdfs://bigdata01:9000", "-jt", "bigdata01:9001", };
		ToolRunner.run(new DelayCountJob(), args);

	}

	@Override
	public int run(String[] args) throws Exception {

		Job job = new Job(getConf(), "DelayCountJob");
		
//		FileInputFormat.setInputPaths(job, "/home/java/input/1987_nohead.csv");
//		FileInputFormat.addInputPaths(job, "/home/java/input/1988_nohead.csv");
		
		Path inputDir = new Path("/home/java/input");
		Path outputDir = new Path("/home/java/dataexpo_out/total");

		FileInputFormat.setInputPaths(job, inputDir);
		FileOutputFormat.setOutputPath(job, outputDir);

		job.setJarByClass(DelayCountJob.class);

		job.setMapperClass(DelayCountMapper.class);
		job.setReducerClass(DelayCountReducer.class);
//		 job.setNumReduceTasks(0);

//		job.setInputFormatClass(TextInputFormat.class);
//		job.setOutputFormatClass(TextOutputFormat.class);

		job.setMapOutputKeyClass(MonthKey.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileSystem hdfs = FileSystem.get(getConf());
		hdfs.delete(outputDir, true);
		hdfs.close();

		job.waitForCompletion(true);
		return 0;
	}

}
