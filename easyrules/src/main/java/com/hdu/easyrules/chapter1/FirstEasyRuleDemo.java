package com.hdu.easyrules.chapter1;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * @author shushoufu
 * @date 2020/11/08
 **/
@Rule(name = "first rule", description = "this is first description")
public class FirstEasyRuleDemo {

    @Condition
    public boolean when() {
        return true;
    }


    @Action
    public void then() {
        System.out.println("when true then print something");
    }

    public static void main(String[] args) {
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new FirstEasyRuleDemo());

        RulesEngine engine = new DefaultRulesEngine();
        engine.fire(rules, facts);
    }
}
