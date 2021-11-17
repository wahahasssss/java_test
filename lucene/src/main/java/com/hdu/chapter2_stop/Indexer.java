package com.hdu.chapter2_stop;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/6
 * @Time 上午10:49
 */
public class Indexer {
    private IndexWriter writer;
    private final static String luceneData = "/Users/shushoufu/Desktop/document/java_test/lucence/luceneData";
    private final static String luceneIndex = "/Users/shushoufu/Desktop/document/java_test/lucence/luceneIndex/";

    public Indexer(String indexDir) throws IOException {
        Directory directory = FSDirectory.open(Paths.get(indexDir));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(directory, config);
    }

    public void close() throws IOException {
        writer.close();
    }

    public int index(String dataDir) throws IOException {
        File[] files = new File(dataDir).listFiles();
        for (File f : files) {
            indexFile(f);
        }
        return writer.numDocs();
    }


    private void indexFile(File file) throws IOException {
        System.out.println("索引文件：" + file.getCanonicalPath());
        List<Document> documents = getDocument(file);
        writer.addDocuments(documents);
    }


    private List<Document> getDocument(File file) throws IOException {
        List<Document> documents = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String contentLine = "";
        int count = 0;
        while ((contentLine = bufferedReader.readLine()) != null) {
            count++;
            Document document = new Document();
            TextField contentField = new TextField("content", contentLine, Field.Store.YES);
            TextField fileNameField = new TextField("filename", file.getName() + String.valueOf(count), Field.Store.YES);
            TextField fullPathField = new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES);
            document.add(contentField);
            document.add(fileNameField);
            document.add(fullPathField);
            documents.add(document);
        }


        return documents;
    }


    public static void main(String[] args) throws IOException, ParseException {
        Indexer indexer = null;

        int numIndexed = 0;
        long start = System.currentTimeMillis();
        try {
            indexer = new Indexer(luceneIndex);
            numIndexed = indexer.index(luceneData);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                indexer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("索引：" + numIndexed + " 个文件 花费了" + (end - start) + " 毫秒");


        Searcher searcher = new Searcher();
        searcher.searchContent("南京", luceneIndex);
    }


}
