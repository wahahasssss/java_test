//package com.hdu.tasks;
//
//import com.hdu.connectors.FileStreamSourceConnector;
//import org.apache.kafka.connect.data.Schema;
//import org.apache.kafka.connect.source.SourceRecord;
//import org.apache.kafka.connect.source.SourceTask;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
///**
// * DESCRIPTION:
// *
// * @author shushoufu
// * @Date 2019/4/30
// * @Time 3:13 PM
// */
//public class FileStreamSourceTask extends SourceTask{
//
//    private String filename;
//    private InputStream inputStream;
//    private String topic;
//
//    @Override
//    public void start(Map<String, String> props) {
//        filename = props.get(FileStreamSourceConnector.FILE_CONFIG);
//        topic = props.get(FileStreamSourceConnector.TOPIC_CONFIG);
//
//    }
//
//    @Override
//    public void stop() {
//        try {
//            inputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String version() {
//        return null;
//    }
//
//    @Override
//    public List<SourceRecord> poll() throws InterruptedException {
////        try {
////            ArrayList<SourceRecord> records = new ArrayList<>();
////            while (streamValid(stream) && records.isEmpty()) {
////                LineAndOffset line = readToNextLine(stream);
////                if (line != null) {
////                    Map<String, Object> sourcePartition = Collections.singletonMap("filename", filename);
////                    Map<String, Object> sourceOffset = Collections.singletonMap("position", streamOffset);
////                    records.add(new SourceRecord(sourcePartition, sourceOffset, topic, Schema.STRING_SCHEMA, line));
////                } else {
////                    Thread.sleep(1);
////                }
////            }
////            return records;
////        } catch (IOException e) {
////            // Underlying stream was killed, probably as a result of calling stop. Allow to return
////            // null, and driving thread will handle any shutdown if necessary.
////        }
////        return null;
//    }
//}
