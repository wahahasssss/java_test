package com.hdu;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/26
 * @Time 下午4:12
 */
public class RichBolt extends BaseRichBolt{

    private OutputCollector collector ;
    @Override
    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple input) {
//        collector.emit();
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
