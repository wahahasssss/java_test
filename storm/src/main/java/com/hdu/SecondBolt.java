package com.hdu;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/17
 * @Time 下午2:15
 */
public class SecondBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String sourceStreamId = input.getSourceStreamId();
        String sourceGlobalStreamId = input.getSourceGlobalStreamId().get_streamId();
        String messageId = input.getMessageId().toString();
        String word = (String) input.getValue(0);
        System.out.println("second bolt value is " + word);

    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}
