/**
 * DESCRIPTION:
 * DDD:infrastructure 基础实施层 最底层（可以与所有层进行交互）
 * 向其他层提供通用的技术能力（比如工具类，第三方库类支持，常用基本配置，数据访问底层实现）
 * 基础实施层主要包含以下的内容：
 * 为应用层 传递消息
 * 为领域层 提供持久化机制（最底层的实现）
 * 为用户界面层 提供组件配置
 * 基础设施层还能通过架构跨国家爱来支持四个层次间的交互模式。
 *
 *
 * @author shushoufu
 * @Date 2019/5/30
 * @Time 3:50 PM
 */
package com.hdu.ddd_demo.infrastructure;