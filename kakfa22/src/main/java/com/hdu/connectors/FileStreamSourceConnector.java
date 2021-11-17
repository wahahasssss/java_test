package com.hdu.connectors;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/30
 * @Time 3:04 PM
 */
public class FileStreamSourceConnector extends SourceConnector {


    public static final String FILE_CONFIG = "file";
    public static final String TOPIC_CONFIG = "topic";

    private String filename;
    private String topic;

    @Override
    public Class<? extends Task> taskClass() {
        return null;
    }

    @Override
    public void start(Map<String, String> props) {
        filename = props.get(FILE_CONFIG);
        topic = props.get(TOPIC_CONFIG);
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        ArrayList<Map<String, String>> configs = new ArrayList<>();
        // Only one input stream makes sense.
        Map<String, String> config = new HashMap<>();
        if (filename != null)
            config.put(FILE_CONFIG, filename);
        config.put(TOPIC_CONFIG, topic);
        configs.add(config);
        return configs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public String version() {
        return null;
    }


}
