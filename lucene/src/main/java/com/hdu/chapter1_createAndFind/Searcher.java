package com.hdu.chapter1_createAndFind;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/5/2
 * @Time 上午10:31
 */
public class Searcher {
    IndexSearcher indexSearcher;
    QueryParser queryParser;
    Query query;

    public Searcher(String indexDirectoryPath) throws IOException {
        File file = new File(indexDirectoryPath);
        Directory indexDirectory = FSDirectory.open(file.toPath());
        IndexReader indexReader = new ExitableDirectoryReader(StandardDirectoryReader.open(indexDirectory), new QueryTimeoutImpl(1000));
        indexSearcher = new IndexSearcher(indexReader);
        queryParser = new QueryParser(LuceneConstant.CONTENTS, new StandardAnalyzer());
    }

    public TopDocs search(String searchQuery) throws ParseException, IOException {
        query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, LuceneConstant.MAX_SEARCH);
    }

    public Document getDocument(ScoreDoc scoreDoc) throws IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }

    public void close() {
        //todo

    }
}
