package cpm.hdu.requestlog.source;

import com.hdu.requestlog.utils.JsonUtil;
import cpm.hdu.requestlog.model.StudentsActivityInfo;
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName：StudentActivitySchema
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/3/18 11:40 上午
 * @Versiion：1.0
 */
public class StudentActivitySchema extends AbstractDeserializationSchema<StudentsActivityInfo> {
    @Override
    public StudentsActivityInfo deserialize(byte[] message) throws IOException {
        String studentActivityString = new String(message, StandardCharsets.UTF_8);
        StudentsActivityInfo studentActivity = JsonUtil.string2Obj(studentActivityString, StudentsActivityInfo.class).get();
        return studentActivity;
    }
}