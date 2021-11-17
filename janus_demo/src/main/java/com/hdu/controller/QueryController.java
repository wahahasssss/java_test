package com.hdu.controller;

import com.hdu.chapter01.QueryDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName：QueryController
 * @Description：TODO
 * @Author：ssf
 * @Date：2020/12/10 11:05 上午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private QueryDemo queryDemo;

    @RequestMapping("/any")
    public Object queryDemo(@RequestParam("name") String name) {
        return queryDemo.queryAnyThing(name);
    }


    @RequestMapping("/initSchema")
    public Object initSchema() {
        queryDemo.initSchema();
        return true;
    }

    @RequestMapping("/add")
    public Object add() {
        queryDemo.addV();
        return true;
    }

    @RequestMapping("/query")
    public Object query(@RequestParam("name") String name) {
        return queryDemo.queryWithTraversal(name);
    }

    @RequestMapping("/load")
    public Object load() {
        queryDemo.load();
        return true;
    }

    @RequestMapping("/clientQuery")
    public Object clientQuery() {
        queryDemo.clientQuery();
        return null;
    }

    @RequestMapping("/clientBatchInsert")
    public Object clientInsert() {
        return null;
    }

    @RequestMapping("/source1Query")
    public Object sourceQuery1() {
        queryDemo.source1Query();
        return null;
    }
}