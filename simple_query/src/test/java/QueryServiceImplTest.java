import com.hdu.SimpleQueryApplication;
import com.hdu.model.dto.*;
import com.hdu.model.enums.LogicEnum;
import com.hdu.model.enums.OrderByTypeEnum;
import com.hdu.service.QueryService;
import com.hdu.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest(classes = {SimpleQueryApplication.class})
class QueryServiceImplTest {

    List<String> numberFields = Arrays.asList("n1", "n2", "n3");
    List<String> stringFields = Arrays.asList("s1", "s2", "s3");
    @Autowired
    private QueryService queryService;

    //升序排序测试
    @Test
    void queryAsc() {
        QueryParamDto ascParam = buildAscParam();
        List<Map<String, Object>> results = queryService.query(ascParam);
        for (Map<String, Object> r : results) {
            System.out.println(JsonUtil.toJsonString(r));
        }
        assert results != null;
    }

    //降序排序测试
    @Test
    void query() {
        QueryParamDto descParam = buildDescParam();
        List<Map<String, Object>> results = queryService.query(descParam);
        for (Map<String, Object> r : results) {
            System.out.println(JsonUtil.toJsonString(r));
        }
        assert results != null;
    }


    //多字段条件 与 测试
    @Test
    void queryMultiField() {
        QueryParamDto descParam = buildMultiFiledParam();
        List<Map<String, Object>> results = queryService.query(descParam);
        for (Map<String, Object> r : results) {
            System.out.println(JsonUtil.toJsonString(r));
        }
        assert results != null;
    }

    /**
     * 构建升序
     *
     * @return
     */
    private QueryParamDto buildAscParam() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            buildNumberData(map, i);
            buildStringData(map, i);
            data.add(map);
        }
        WhereDetailDto condition1 = WhereDetailDto.builder().constant(3).expression(">").field("n1").build();

        WhereDto where = WhereDto.builder().logicEnum(LogicEnum.AND)
                .wheres(Arrays.asList(condition1)).build();
        OrderByDto orderBy = OrderByDto.builder().orderByField("n1")
                .orderType(OrderByTypeEnum.ASC)
                .build();
        LimitDto limit = LimitDto.builder().limit(10).build();

        QueryParamDto param = QueryParamDto.builder().data(data)
                .limit(limit)
                .orderBy(orderBy)
                .where(where)
                .data(data)
                .build();
        return param;
    }

    private QueryParamDto buildDescParam() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            buildNumberData(map, i);
            buildStringData(map, i);
            data.add(map);
        }
        WhereDetailDto condition1 = WhereDetailDto.builder().constant(3).expression(">").field("n1").build();

        WhereDto where = WhereDto.builder().logicEnum(LogicEnum.AND)
                .wheres(Arrays.asList(condition1)).build();
        OrderByDto orderBy = OrderByDto.builder().orderByField("n1")
                .orderType(OrderByTypeEnum.DESC)
                .build();
        LimitDto limit = LimitDto.builder().limit(10).build();

        QueryParamDto param = QueryParamDto.builder().data(data)
                .limit(limit)
                .orderBy(orderBy)
                .where(where)
                .data(data)
                .build();
        return param;
    }


    private QueryParamDto buildMultiFiledParam() {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            buildNumberData(map, i);
            buildStringData(map, i);
            data.add(map);
        }
        WhereDetailDto condition1 = WhereDetailDto.builder().constant(3).expression(">").field("n1").build();
        WhereDetailDto condition2 = WhereDetailDto.builder().constant(80).expression("<").field("n2").build();

        WhereDto where = WhereDto.builder().logicEnum(LogicEnum.AND)
                .wheres(Arrays.asList(condition1, condition2)).build();
        OrderByDto orderBy = OrderByDto.builder().orderByField("n1")
                .orderType(OrderByTypeEnum.DESC)
                .build();
        LimitDto limit = LimitDto.builder().limit(1000).build();

        QueryParamDto param = QueryParamDto.builder().data(data)
                .limit(limit)
                .orderBy(orderBy)
                .where(where)
                .data(data)
                .build();
        return param;
    }


    private void buildNumberData(Map<String, Object> map, int index) {
        for (String field : numberFields) {
            map.put(field, index);
        }
    }

    private void buildStringData(Map<String, Object> map, int index) {
        for (String field : stringFields) {
            map.put(field, String.valueOf(index));
        }
    }

}