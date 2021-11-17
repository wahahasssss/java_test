package com.hdu.chapter2_stop;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/6
 * @Time 上午11:00
 */
public class IndexerTest {


    private final static String luceneData = "/Users/shushoufu/Desktop/java_test/lucence/luceneData";
    private final static String luceneIndex = "/Users/shushoufu/Desktop/java_test/lucence/luceneIndex/";

    private Directory dic;
    private IndexReader reader;
    private IndexSearcher indexSearcher;

    @Before
    public void setUp() throws Exception {
        dic = FSDirectory.open(Paths.get(luceneIndex));
        reader = DirectoryReader.open(dic);
        indexSearcher = new IndexSearcher(reader);
    }


    @Test
    public void index() {
    }


    @Test
    public void search() throws IOException, ParseException, InvalidTokenOffsetsException {
        String searchField = "content";

        Analyzer analyzer = new StandardAnalyzer();
        QueryParser queryParser = new QueryParser(searchField, analyzer);
        Query query = queryParser.parse("SessionListener");
        TopDocs topDocs = indexSearcher.search(query, 10);
        QueryScorer scorer = new QueryScorer(query);

        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);


        for (ScoreDoc doc : topDocs.scoreDocs) {
            Document document = indexSearcher.doc(doc.doc);
            System.out.print(document.get("content"));
            System.out.println(">>>>" + document.get("filename"));
            TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(document.get("content")));
            System.out.println(highlighter.getBestFragment(tokenStream, document.get("content")));
        }


    }
}