package orcfile;

import com.sun.org.apache.xml.internal.utils.StringVector;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.*;
import org.apache.orc.CompressionKind;
import org.apache.orc.OrcFile;
import org.apache.orc.TypeDescription;
import org.apache.orc.Writer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/6
 * @Time 下午3:56
 */
public class OrcFileHandle {
    public static void main(String[] args) throws IOException {
        Path path = new Path("FileStremExample/test.orc");
        File file = new File("FileStremExample/test.orc");
        if (file.exists()) {
            file.delete();
        }
        Configuration conf = new Configuration();
        TypeDescription schema = TypeDescription.fromString("struct<field1:int,field2:int,field3:int>");
        schema.addField("field_map", TypeDescription.createMap(TypeDescription.createString(), TypeDescription.createInt()));
        schema.addField("field_5", TypeDescription.createInt());
        Writer writer = OrcFile.createWriter(path, OrcFile.writerOptions(conf)
                .setSchema(schema)
                .compress(CompressionKind.SNAPPY));
        VectorizedRowBatch batch = schema.createRowBatch();
        LongColumnVector first = (LongColumnVector) batch.cols[0];
        LongColumnVector second = (LongColumnVector) batch.cols[1];
        LongColumnVector third = (LongColumnVector) batch.cols[2];
        LongColumnVector five = (LongColumnVector) batch.cols[4];
        MapColumnVector four = (MapColumnVector) batch.cols[3];
        BytesColumnVector four_key = (BytesColumnVector) four.keys;
        LongColumnVector four_value = (LongColumnVector) four.values;
        int map_size = 5;
        final int BATCH_SIZE = batch.getMaxSize();
        four_key.ensureSize(BATCH_SIZE * map_size, false);
        four_key.ensureSize(BATCH_SIZE * map_size, false);

        for (int i = 0; i < 150000; i++) {
            int row = batch.size++;
            first.vector[row] = i;
            second.vector[row] = i * 3;
            third.vector[row] = i * 6;

            for (int m = (int) four.offsets[row]; m < (int) four.offsets[row] + map_size; m++) {
                four_key.setVal(m, String.format("key-%d", i).getBytes());
                four_value.vector[row] = i * 3;
            }
            five.vector[row] = i * 8;
            if (row == BATCH_SIZE - 1) {
                writer.addRowBatch(batch);
                batch.reset();
            }
        }

        if (batch.size != 0) {
            writer.addRowBatch(batch);
            batch.reset();
        }
        writer.close();
    }


}
