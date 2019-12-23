package com.dubbo.example.provider;

import com.open.cloud.business.Order;
import com.open.cloud.business.api.AccountService;
import com.open.cloud.business.api.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class OrderServiceImpl implements OrderService {


	private AccountService accountService;

	private JdbcTemplate jdbcTemplate;

	@Override
	public Order create(String userId, String commodityCode, int orderCount) {
		log.info("Order Service Begin ... xid: " + RootContext.getXID());

		// 计算订单金额
		int orderMoney = calculate(commodityCode, orderCount);

		// 从账户余额扣款
		accountService.debit(userId, orderMoney);

		final Order order = new Order();
		order.userId = userId;
		order.commodityCode = commodityCode;
		order.count = orderCount;
		order.money = orderMoney;

		KeyHolder keyHolder = new GeneratedKeyHolder();

		log.info(
				"Order Service SQL: insert into order_tbl (user_id, commodity_code, count, money) values ({}, {}, {}, {})",
				userId, commodityCode, orderCount, orderMoney);

		if (EnvContext.dbType == DBType.MYSQL) {
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pst = con.prepareStatement(
							"insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
					pst.setObject(1, order.userId);
					pst.setObject(2, order.commodityCode);
					pst.setObject(3, order.count);
					pst.setObject(4, order.money);
					return pst;
				}
			}, keyHolder);
			order.id = keyHolder.getKey().longValue();
		} else if (EnvContext.dbType == DBType.ORACLE) {

			String nextID = jdbcTemplate.queryForObject("select ORDER_TBL_SEQ.nextval from dual", String.class);
			jdbcTemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pst = con.prepareStatement(
							"insert into ORDER_TBL (id, user_id, commodity_code, count, money) values (?,?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
					pst.setObject(1, nextID);
					pst.setObject(2, order.userId);
					pst.setObject(3, order.commodityCode);
					pst.setObject(4, order.count);
					pst.setObject(5, order.money);
					return pst;
				}
			}, keyHolder);
			order.id = Long.parseLong(nextID);
		}

		log.info("Order Service End ... Created " + order);

		return order;
	}

	/**
	 * Sets account service.
	 *
	 * @param accountService the account service
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Sets jdbc template.
	 *
	 * @param jdbcTemplate the jdbc template
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private int calculate(String commodityId, int orderCount) {
		return 200 * orderCount;
	}

}
