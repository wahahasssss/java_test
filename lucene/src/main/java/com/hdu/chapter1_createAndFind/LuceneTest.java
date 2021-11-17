package com.hdu.chapter1_createAndFind;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/2
 * @Time 上午11:09
 */
public class LuceneTest {
    //    String indexDir = "/Users/shushoufu/iCloud 云盘（归档） - 1/Desktop/document/java_test/lucene/lucene/index";
//    String dataDir = "/Users/shushoufu/iCloud 云盘（归档） - 1/Desktop/document/java_test/lucene/lucene/data";
    String indexDir = "/Users/shushoufu/Desktop/document/java_test/lucene/lucene/index";
    String dataDir = "/Users/shushoufu/Desktop/document/java_test/lucene/lucene/data";
    Indexer indexer;
    Searcher searcher;

    public static void main(String[] args) {
        LuceneTest test;
        try {
            test = new LuceneTest();
            test.createIndex();
            test.search("INFO");
        } catch (IOException e) {
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        } catch (ParseException e) {
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }
    }

    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new FilterToText());
        Long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed + " File indexed, time taken: "
                + (endTime - startTime) + " ms");
    }

    private void search(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits +
                " documents found. Time :" + (endTime - startTime));
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document document = searcher.getDocument(scoreDoc);
            System.out.println("File:" + document.get(LuceneConstant.FILE_PATH));
        }
        searcher.close();
    }
}
