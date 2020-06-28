import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.junit.Test;

public class MapReduceExample {
	 
	  
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
	    conf.set("fs.default.name", "hdfs://192.168.56.197:10011");
	    conf.set("mapred.job.tracker", "http://192.168.56.197:10012");
	    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: wordcount <in> <out>");
	      System.exit(2);
	    }
	    Job job = new Job(conf, "Word Count");
	    job.setJarByClass(MapReduceExample.class);
	    job.setMapperClass(TokenizerMapper.class);
	    job.setCombinerClass(IntSumReducer.class);
	    job.setReducerClass(IntSumReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
	    /*
	    JobConf jobConf = new JobConf(MapReduceExample.class);
	    jobConf.setJobName("WordCount");
	    jobConf.setOutputKeyClass(Text.class);
	    jobConf.setOutputValueClass(IntWritable.class);
	    jobConf.setMapperClass(TokenizerMapper.class);
	    */
	  }
   
   public static class TokenizerMapper extends Mapper<Object,Text, Text, IntWritable>
   {
      private final static IntWritable one = new IntWritable(1);
      private Text word = new Text();
      public void map(Object key,Text value,Context context) throws IOException,InterruptedException {
		StringTokenizer itr = new StringTokenizer(value.toString());
		while(itr.hasMoreTokens())
		{
			word.set(itr.nextToken());
			context.write(word, one);
		}
	}
   }
   public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	private IntWritable result = new IntWritable();
	public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException,InterruptedException{
		int sum = 0;
		for(IntWritable val:values)
		{
			sum+=val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}
   
}
