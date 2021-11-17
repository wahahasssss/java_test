package com.hdu.chapter2_stop;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/17
 * @Time 下午3:30
 */
public class Searcher {
    public void searchContent(String keyWord, String indexPath) throws IOException, ParseException {
        File file = new File(indexPath);
        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(file.toPath()));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        StandardAnalyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("content", analyzer);
        TopDocs topDocs = indexSearcher.search(parser.parse(keyWord), 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Explanation explanation = indexSearcher.explain(parser.parse("content"), 10);
        System.out.println(explanation.getDescription());
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document);
        }
    }
}
