package com.dubbo.example.provider;

import com.open.cloud.business.api.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class AccountServiceImpl implements AccountService {


    private JdbcTemplate jdbcTemplate;

    /**
     * Sets jdbc template.
     *
     * @param jdbcTemplate the jdbc template
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void debit(String userId, int money) {
        log.info("Account Service ... xid: " + RootContext.getXID());
        log.info("Deducting balance SQL: update ACCOUNT_TBL set money = money - {} where user_id = {}", money,
            userId);
        jdbcTemplate.update("update ACCOUNT_TBL set money = money - ? where user_id = ?", money, userId);
        log.info("Account Service End ... ");
    }
}
