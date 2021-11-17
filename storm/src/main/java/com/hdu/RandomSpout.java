package com.hdu;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/17
 * @Time 上午10:14
 */
public class RandomSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private static final AtomicInteger number = new AtomicInteger(0);
    private static String[] words = {"happy", "excited", "angry"};

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    public void nextTuple() {
        String word = words[new Random().nextInt(words.length)];
        int count = number.incrementAndGet();
        collector.emit(new Values(word), count);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("randomstring"));
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("handle success" + msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println(msgId);
    }
}
