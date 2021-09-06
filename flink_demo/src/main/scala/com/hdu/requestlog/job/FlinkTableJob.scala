package com.hdu.requestlog.job

class FlinkTableJob {


  // 计算订单的sku_id去重ID
  val sql = "select 'order_id', order_id, 'count', sum(sku_id) from mall_order_submit where company_id <> '60669113' group by order_id"

  val sql2 = "select 'company_id', company_id, count(1) from mall_order_submit where company_id not in ('60669113') group by company_id"

  // 一个小时内商家的总
  val sql1 = "select company_id, SUM(total_goods_amount) from mall_order_submit group by TUMBLE(proctime, INTERVAL '1' MINUTES),company_id"

  //近一分钟商户的下单数 rows over 窗口  每一行都会作为一个数据
  val sql3: String = "select company_id," + " COUNT(DISTINCT order_id) OVER (PARTITION BY company_id ORDER BY process_time ROWS BETWEEN INTERVAL '10' SECONDS preceding AND CURRENT ROW) as order_count " + "from mall_order_submit "

  //同一分钟商户的下单数 滚动窗口
  val sql4: String = "SELECT\n" + "company_id," + "  TUMBLE_START(process_time, INTERVAL '1' MINUTE) AS t,\n" + "  COUNT(*) AS cnt\n" + "FROM mall_order_submit\n" + "GROUP BY \n" + "  TUMBLE(process_time, INTERVAL '1' MINUTES),company_id"


  // 滑动窗口
  val sql5: String = "SELECT 'company_id',company_id,COUNT(DISTINCT 1) FROM mall_order_submit GROUP BY HOP(process_time,INTERVAL '1' SECONDS,INTERVAL '1' HOURS),company_id"
  //        String sql = "SELECT * FROM mall_order_submit";
}
