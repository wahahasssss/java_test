package com.hdu.chapter4;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/7
 * @Time 下午2:29
 */
public class RamDicDemo {
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer=  new IndexWriter(directory,config);
        Document document = new Document();
        String text = "This is the text to be indexed.";
        document.add(new Field("filedName",text, TextField.TYPE_STORED));
        writer.addDocument(document);
        writer.close();

        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        QueryParser parser = new QueryParser("filedName",analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = indexSearcher.search(query,10).scoreDocs;
        for (int i = 0;i < hits.length;i++){
            Document hitDoc = indexSearcher.doc(hits[i].doc);
            System.out.println(hitDoc.get("filedName"));
        }
        directoryReader.close();
        directory.close();
    }
}
