package com.hdu.easyrules.chapter2_json;

import com.alibaba.fastjson.JSON;
import com.hdu.easyrules.NumberUtil;
import com.hdu.easyrules.model.StudentInfo;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.spel.SpELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author shushoufu
 * @date 2020/11/08
 **/
public class JsonEasyRuleDemo {
    private static String JSON_EASY_RULE_STRING = "[\n" +
            "  {\n" +
            "  \"name\":\"3\",\n" +
            "  \"description\":\"3\",\n" +
            "  \"condition\":\"(age<50 || name == \\\"zzd\\\") && !number1.contains(112)  && grade >= grade1\",\n" +
            "  \"priority\":3,\n" +
            "  \"actions\":[\n" +
            "    \"System.out.println(\\\"default rule3 \\\")\"\n" +
            "  ]}\n" +
            "]";


    private static String JSON_EASY_RULE_STRING_V2 = "[\n" +
            "  {\n" +
            "  \"name\":\"3\",\n" +
            "  \"description\":\"3\",\n" +
            "  \"condition\":\"#{['number2'] < ['number3'] }\",\n" +
            "  \"priority\":3,\n" +
            "  \"actions\":[\n" +
            "    \"System.out.println(\\\"default rule3 \\\")\"\n" +
            "  ]}\n" +
            "]";

    private static String JSON_RULE = "[{\"name\":\"driver_split_cheat_rule2\",\"description\":\"同天同司机配送的订单中，下单手机号 或 收货手机号 = 司机手机号\",\"priority\":2,\"ruleStatus\":1," +
            "\"condition\":\"#{ (['order_rev_phone'] == ['order_driver_phone'] || ['order_ord_phone'] == ['order_driver_phone'] ) && ['driver_is_gas_vehicle_driver'] == 1 && ['driver_freight_growth_rate'] > 0.7 }\"," +
            "\"actions\":[\"#{ T(System).out.println(\\\"this is log out!\\\") }\"]}]";

    private static StudentInfo student = new StudentInfo();

    static {
        JsonEasyRuleDemo.student.setAge(12);
        JsonEasyRuleDemo.student.setName("zzd");
    }

    public static void main(String[] args) throws Exception {
        String mapString = "{\"bd_phone_rev_sa_cnt_1d\":\"0\",\"driver_split_ord_crm_driver_id\":\"129770\",\"company_is_virtual_reg\":\"0\",\"company_is_salecode_reg\":\"0\",\"order_pay_company_realmoney_1h\":\"461.06\",\"driver_split_ord_crm_order_time\":\"2021-01-26 16:43:38\",\"ordphone_childaccount_sacnt\":\"0\",\"sa_repeat_registered_bsop\":\"1\",\"company_device_ord_varcnt_1d\":\"1\",\"order_rev_phone\":\"18772509637\",\"order_pay_company_ordcnt_1h\":\"0\",\"company_same_bd_dev_sku_amount_sacnt_1d\":\"1\",\"company_sku_cnt_60d\":\"14\",\"writetime\":\"1611650618324\",\"is_formal_bd_phone\":\"0\",\"company_ord_amount_totoal_1d\":\"461.06\",\"driver_split_ord_crm_region\":\"湖北省区\",\"company_visit_cnt_30d\":\"0\",\"order_pay_pending_goods_num\":\"0\",\"driver_split_ord_crm_order_id\":1251440551,\"driver_split_ord_crm_bus_time\":\"2021-01-26 16:43:38\",\"company_bd_payno_sku_amount_sacnt_1d\":\"1\",\"company_childaccount_phonelist\":\"\",\"order_device_ord_skucnt_1d\":\"0\",\"company_risk_score\":\"3\",\"driver_split_ord_crm_driver_name\":\"陈向阳\",\"order_pay_company_profit_1h\":\"51.106666666666655\",\"order_ord_phone\":\"18772509637\",\"driver_split_ord_crm_is_reborn\":1,\"company_gmv_60d\":\"1575.87\",\"company_notstd_amount_60d\":\"832\",\"order_same_bd_skuamount_sacnt_1d\":\"1\",\"company_is_bd_device_reg\":\"0\",\"order_settlement_skucount\":\"7\",\"order_revphone_ord_cnt_1d\":\"1\",\"company_same_skucnt_1d\":\"1\",\"order_same_driver_dev_sku_amount_ordcnt\":\"1\",\"company_cancel_order_cnt_5d\":\"0\",\"order_same_driver_amount_sacnt\":\"1\",\"bd_same_skuamount_sacnt_1d\":\"1\",\"company_status_invalid_2d\":\"0\",\"company_childaccount_cnt\":\"0\",\"company_ordphone_is_bd_1d\":\"1\",\"order_pay_sop_profit\":\"51.106666666666655\",\"driver_split_ord_crm_bd_name\":\"王晨光\",\"company_cancel_order_goodsnum_5d\":\"0\",\"company_geohash7_regcnt_30d\":\"0\",\"company_nearly_binddays\":\"5\",\"company_regdays\":\"1102\",\"order_driver_phone\":\"18772509637\",\"driver_split_ord_crm_main_city_name\":\"武汉\",\"order_revphone_is_driver\":\"1\",\"driver_split_ord_crm_order_money\":461.06,\"order_pay_pending_ord_cnt\":\"0\",\"order_pay_city_bigordcnt_1h\":\"1\",\"order_pay_city_bigordmoney_1h\":\"3590.0\",\"company_reg_ip_sameday_cnt\":\"0\",\"order_pay_real_money\":\"461.06\",\"bd_same_payno_sacnt_1d\":\"0\",\"company_reg_device_sameday_cnt\":\"0\",\"company_b_sop\":\"1\",\"company_notstd_skucnt_60d\":\"12\",\"driver_split_ord_crm_company_id\":6561508,\"order_device_ord_sacnt_1d\":\"0\",\"order_same_bd_payno_sacnt_1d\":\"0\",\"company_ord_before_bind\":\"1\",\"company_profitrate_60d\":\"0.037437\",\"order_pay_goodsnum\":\"8\",\"driver_is_gas_vehicle_driver\":\"1\",\"order_pay_system_cancel_ordcnt_day\":\"0\",\"bd_phone_rev_ord_cnt_1d\":\"0\",\"order_pay_samehour_device_amount_sku_sacnt\":\"1\",\"bd_driverphone_same_sacnt_1d\":\"0\",\"bd_device_sacnt_1d\":\"0\",\"driver_freight_growth_rate\":\"\",\"company_is_salerphone_reg_ord\":\"1\",\"bd_same_driveramount_sacnt_1d\":\"1\",\"sa_admin_include_bd_bsop\":\"1\",\"order_revphone_sa_cnt_1d\":\"1\",\"bd_device_skucnt_1d\":\"0\",\"company_saler_rephone_ord_30d\":\"2\",\"company_days_last_visit\":\"32\",\"company_ord_skucnt_totoal_1d\":\"7\",\"company_ord_is_childaccount_1d\":\"0\",\"driver_split_ord_crm_sku_cnt\":7,\"order_same_bd_skuamount_ordcnt_1d\":\"1\",\"order_pay_order_skucnt\":\"7\",\"order_pay_bdphone_in_childaccount\":\"0\",\"order_money\":\"461.060000\",\"order_real_money\":\"461.060000\",\"company_admin_has_bdphone\":\"1\",\"order_pay_payment_is_use_credit\":\"0\",\"company_order_cnt_his\":\"15\",\"driver_split_ord_crm_sa_device_id\":\"79ba935c-49fe-38ab-8470-f87fee2e673a\",\"bd_same_skuamount_ordcnt_1d\":\"1\",\"order_same_driver_payno_sacnt\":\"1\",\"driver_split_ord_crm_bd_id\":1345904,\"company_sameday_bind_cnt\":\"12\",\"order_pay_system_cancel_goodsnum_day\":\"0\",\"company_orddays_30d_s1\":\"2\",\"company_is_bd_valid_ord\":\"1\",\"order_same_device_skuamount_ordcnt_1d\":\"0\",\"driver_split_ord_crm_dt\":\"20210126\",\"driver_split_ord_crm_city\":\"武汉\",\"order_pay_ordphone_in_childaccount\":\"0\",\"order_revphone_is_bd\":\"0\",\"company_phone_diff_ratio_30d\":\"0.0\",\"order_driverphone_ordcnt_1d\":\"1\",\"company_stdord_ratio_30d\":\"0.5\",\"order_device_is_bd\":\"0\"}";
        Map<Object, Object> map = JSON.parseObject(mapString, Map.class);

//        SpELRuleFactory ruleFactory = new SpELRuleFactory(new JsonRuleDefinitionReader());
//        Rules rules = new Rules();
//        Rule jsonRule = ruleFactory.createRule(new StringReader(JSON_RULE));
//        rules.register(jsonRule);


        String s = "2989:3";
        String[] strings = s.split(":");

        //装载进spel factory中，批量装载规则
        SpELRuleFactory factory = new SpELRuleFactory(new JsonRuleDefinitionReader());

        Rules rules = factory.createRules(new StringReader(JSON_EASY_RULE_STRING_V2));

        Facts facts = new Facts();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            facts.put(entry.getKey().toString(),
                    NumberUtil.isNumeric(entry.getValue().toString()) ?
                            Double.valueOf(entry.getValue().toString()) : entry.getValue());
        }
        facts.put("age", 44);
        facts.put("name", "zzd");
        facts.put("grade", "1.11339");
        facts.put("grade1", "0.4567");
        facts.put("number1", Arrays.asList(12, 23, 34));
        facts.put("number2", "3.1.1");
        facts.put("number3", "4.0");

//        RulesEngine engine = new DefaultRulesEngine();
//        engine.fire(rules, facts);

        Iterator<Rule> iterator = rules.iterator();

        while (iterator.hasNext()) {
            Rule rule = iterator.next();
            try {
                boolean evaluationResult = rule.evaluate(facts);
                System.out.println(evaluationResult);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
