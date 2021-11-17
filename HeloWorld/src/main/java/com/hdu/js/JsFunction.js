function add(a, b) {
    var s = a + b;
    console.log("s : " + s)
    return s;
}


function parsePhoneSection(variableMap) {
    if (variableMap["phone"] != null) {
        var phoneString = variableMap["phone"].toString()
        if (phoneString.length > 3) {
            var p = phoneString.slice(0, 3)
            variableMap["phone_section_no"] = p;
        }
    }
}


"function calOiidAmount(variableMap) {\n" +
"   var price = variableMap[\"unit_ssu_price\"]\n " +
"   var num = variableMap[\"num\"]\n " +
"   var mut = price * num;\n" +
"   variableMap[\"oiid_goods_amount\"] = mut \n" +
"}"

//            if (contextVo.getScenarioDto().getBusinessKey().equalsIgnoreCase("order_pay") && param.containsKey("unit_ssu_price") && param.containsKey("num")){
//                BigDecimal price = new BigDecimal(String.valueOf(param.get("unit_ssu_price")));
//                BigDecimal num = new BigDecimal(String.valueOf(param.get("num")));
//                BigDecimal mut = price.multiply(num);
//                param.put("oiid_goods_amount", mut.setScale(4, RoundingMode.HALF_UP).doubleValue());
//            }

var variableMap = {"rev_phone": 15023661219}


parsePhoneSection(variableMap)

console.log(variableMap)