package com.hdu.join;

import org.apache.storm.testing.FeederSpout;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/7
 * @Time 上午11:05
 */
public class SingleJoinMain {
    public static void main(String[] args){
        FeederSpout genderSpout = new FeederSpout(new Fields("id","gender"));
        FeederSpout ageSpout = new FeederSpout(new Fields("id","age"));

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("gender",genderSpout);
        builder.setSpout("age",ageSpout);

    }
}
