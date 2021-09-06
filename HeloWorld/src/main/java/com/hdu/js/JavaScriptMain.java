package com.hdu.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @ClassName：JavaScriptMain
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/8/25 10:36 上午
 * @Versiion：1.0
 */
public class JavaScriptMain {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("js");
        String jsFunction =  "''' '''";

        scriptEngine.eval(jsFunction);

        Invocable invocable = (Invocable)scriptEngine;
        Object res   = invocable.invokeFunction("add", 1,2);
        System.out.println(res);

    }
}