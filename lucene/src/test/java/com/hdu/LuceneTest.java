package com.hdu;


import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/2
 * @Time 下午4:36
 */
public class LuceneTest {

    @Test
    public void addInstance() throws IOException {
        Article article = new Article(1, "Lucene 全文检索",
                "Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎（英文与德文两种西方语言）。");
        final Path path = Paths.get("./article/");

        Directory directory = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter indexWriter = new IndexWriter(directory, config);

        Document document = new Document();
        document.add(new TextField("id", article.getId().toString(), Field.Store.YES));
        document.add(new TextField("title", article.getTitle(), Field.Store.YES));
        document.add(new TextField("content", article.getContent(), Field.Store.YES));

        indexWriter.addDocument(document);
        indexWriter.close();

    }


    @Test
    public void addFile() throws IOException {
        final Path path = Paths.get("./article/");

        Directory directory = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);


        IndexWriter indexWriter = new IndexWriter(directory, config);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/shushoufu/Desktop/logs.log")));
        String content = "";
        while ((content = bufferedReader.readLine()) != null) {
            System.out.println(content);
            Document document = new Document();
            document.add(new TextField("logs", content, Field.Store.YES));
            indexWriter.addDocument(document);
        }
        indexWriter.close();
    }


    @Test
    public void searchFiles() throws IOException, ParseException, InvalidTokenOffsetsException {
        String queryString = "contextDestroyed";
        final Path path = Paths.get("./article/");
        Directory directory = FSDirectory.open(path);
        Analyzer analyzer = new StandardAnalyzer();

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        QueryParser queryParser = new QueryParser("logs", analyzer);
        Query query = queryParser.parse(queryString);
        TopDocs topDocs = indexSearcher.search(query, 10);

        Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, scorer);

        long count = topDocs.totalHits;

        System.out.println("检索总条数" + count);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            String highlighterContent = highlighter.getBestFragment(analyzer, "logs", document.get("logs"));
            System.out.print("相关度：" + scoreDoc.score + "-----time" + document.get("time"));
            System.out.println(document.get("logs"));
        }
    }


}
