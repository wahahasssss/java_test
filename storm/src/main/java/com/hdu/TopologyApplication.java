package com.hdu;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.utils.Utils;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/17
 * @Time 上午10:25
 */
public class TopologyApplication {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout",new RandomSpout());
        builder.setBolt("bolt",new Bolt()).shuffleGrouping("spout");
        builder.setBolt("secondBolt",new SecondBolt()).shuffleGrouping("spout");
        Config config  = new Config();
        config.setDebug(true);
        if (args!=null&&args.length>0){
            config.setNumWorkers(3);
            StormSubmitter.submitTopology(args[0],config,builder.createTopology());
        }else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("firstTopo",config,builder.createTopology());
            Utils.sleep(20000);
            cluster.killTopology("firstTopo");
            cluster.shutdown();
        }
    }
}
