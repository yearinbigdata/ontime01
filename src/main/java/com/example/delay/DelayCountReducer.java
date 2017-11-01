package com.example.delay;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.example.sort.MonthKey;

public class DelayCountReducer extends Reducer<MonthKey, IntWritable, MonthKey, 	IntWritable>{
	
//	private MultipleOutputs<MonthKey, IntWritable> mos;
	
	private MonthKey outputKey = new MonthKey();		//string, integer
	private IntWritable result = new IntWritable();
//	private Text outValue = new Text();
	
//	@Override
//	public void setup(Reducer<MonthKey, IntWritable, MonthKey, IntWritable>.Context context)
//			throws IOException, InterruptedException {
//		mos = new MultipleOutputs<MonthKey, IntWritable>(context);
//	}
	
	public void reduce(MonthKey key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		
		String[] columns = key.getUniqueCarrier().split(",");
		
		int sum = 0;
		Integer bmonth = key.getMonth();
		if(columns[0].equals("D")){
			for(IntWritable value : values){
				if(bmonth != key.getMonth()){
					result.set(sum);
					outputKey.setUniqueCarrier("D," + key.getUniqueCarrier().substring(2));
					outputKey.setMonth(bmonth);
					context.write(outputKey, result);
//					outValue.set("," + result);
//					context.write(outputKey, outValue);
					sum = 0;
				}
				sum += value.get();
				bmonth = key.getMonth();
			}
			if (key.getMonth() == bmonth){
				outputKey.setUniqueCarrier("D," + key.getUniqueCarrier().substring(2));
				outputKey.setMonth(key.getMonth());
				result.set(sum);
				context.write(outputKey, result);
//				outValue.set("," + result);
//				context.write(outputKey, outValue);
			}
		}else {
			for(IntWritable value : values){
				if(bmonth != key.getMonth()){
					result.set(sum);
					outputKey.setUniqueCarrier("A," + key.getUniqueCarrier().substring(2));
					outputKey.setMonth(bmonth);
					context.write(outputKey, result);
//					outValue.set("," + result);
//					context.write(outputKey, outValue);
					sum = 0;
				}
				sum += value.get();
				bmonth = key.getMonth();
			}
			if (key.getMonth() == bmonth){
				outputKey.setUniqueCarrier("A," + key.getUniqueCarrier().substring(2));
				outputKey.setMonth(key.getMonth());
				result.set(sum);
				context.write(outputKey, result);
//				outValue.set("," + result);
//				context.write(outputKey, outValue);
			}
		}
		
	}
	
	@Override
	public void cleanup(Context context)
			throws IOException, InterruptedException {
//		mos.close();
	}

}
