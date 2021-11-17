package com.hdu;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/17
 * @Time 上午10:22
 */
public class Bolt extends BaseBasicBolt {
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String word = (String) tuple.getValue(0);
        String out = "I'm " + word + "!";
        System.out.println("out = " + out);
        Values boltValue = new Values(String.format("%s-bolt", word));
        basicOutputCollector.emit(boltValue);
    }


    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
