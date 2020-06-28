package com.hdu.join;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.Consumer;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/7
 * @Time 上午9:58
 */
public class StreamJoinMain {
    public static StormTopology buildTopology(){
        FixedBatchSpout spout = new FixedBatchSpout(new Fields("key","value1"),3,
                new Values("a","1"),new Values("b","2"),new Values("a","3"),new Values("a","4"));
        spout.setCycle(true);
        FixedBatchSpout spout2 = new FixedBatchSpout(new Fields("key","value2"),3,
                new Values("a","1"),new Values("b","2"),new Values("a","3"),new Values("a","5"),
                new Values("a","6"));

//        FixedBatchSpout spout3 = new FixedBatchSpout()
        spout2.setCycle(true);
        TridentTopology topology = new TridentTopology();
        Stream stream1 = topology.newStream("spout1",spout);
        Stream stream2 = topology.newStream("spout2",spout2);

        topology.join(stream1,new Fields("key"),stream2,new Fields("key"),new Fields("key","value1","value2"))
                .peek(new Consumer() {
                    @Override
                    public void accept(TridentTuple input) {
                        System.out.println(input.toString());
                        try {
                            Thread.sleep(2000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
        return topology.build();
    }

    public static void main(String[] args){
        Config config = new Config();
        config.setMaxSpoutPending(20);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("wordCounter",config,buildTopology());
    }
}
