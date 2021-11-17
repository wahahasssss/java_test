package com.hdu.chapter1_createAndFind;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableFieldType;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/27
 * @Time 下午3:14
 */
public class Indexer {
    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException {
        Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath).toPath());
        IndexWriterConfig config = new IndexWriterConfig();
        writer = new IndexWriter(indexDirectory, new IndexWriterConfig());
    }


    public void close() throws IOException {
        writer.close();
    }

    private Document getDocument(File file) throws IOException {
        Document document = new Document();
        IndexableFieldType fieldType = new FieldType();
        Field contentField = new Field(LuceneConstant.CONTENTS, new FileReader(file), fieldType);
//        Field fileNameField = new Field(LuceneConstant.FILE_NAME,file.getName(),fieldType);
//        Field filePathField = new Field(LuceneConstant.FILE_PATH,file.getCanonicalPath(),fieldType);
        document.add(contentField);
//        document.add(fileNameField);
//        document.add(filePathField);
        return document;
    }

    private void indexFile(File file) throws IOException {
        System.out.println("Indexing " + file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    public int createIndex(String dataDirPath, FileFilter fileFilter) throws IOException {
        File[] files = new File(dataDirPath).listFiles();
        for (File f : files) {
            if (!f.isDirectory()
                    && !f.isHidden()
                    && f.exists()
                    && f.canRead()
                    && fileFilter.accept(f)) {
                indexFile(f);
            }
        }
        return writer.numDocs();
    }
}
