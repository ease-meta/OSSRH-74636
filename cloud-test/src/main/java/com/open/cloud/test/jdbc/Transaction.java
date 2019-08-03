package com.open.cloud.test.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Case;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.数据库事务
 * 脏读：指一个事务读取到了另一个事务未提交的数据;
 * 不可重复读：指在一个事务内读取表中的某一行数据，多次读取结果不同。
 * 虚读(幻读)：指在一个事务内读取到了别的事务插入的数据，导致前后读取不一致。
 * 2.事务的传播机制
 * 3.快照读与当前读
 */
@Slf4j
public class Transaction {

    public static final String url = "jdbc:mysql://127.0.0.1:3306/dtx?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8";
    private static final String password = "root";
    private static final String username = "root";

    public static void main(String[] args) {
        //创建一个单线程池，确保执行的先后顺序
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            String sql = "INSERT INTO account_point (tx_id, account_no, point, status) VALUES ('3', '1', 11, 1)";
            try  {
                Connection connection = getConnection(1);
                connection.setAutoCommit(false);
                JdbcUtils.execute(connection, sql);
                //connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.execute(() -> {
            String sql = "SELECT * from account_point";
            try (Connection connection = getConnection(1)) {
                List<Object> parameters = new LinkedList<>();
                List<Map<String, Object>> mapList=JdbcUtils.executeQuery(connection,sql,parameters);
                log.info("{}",mapList);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }

    public static DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(password);
        druidDataSource.setPassword(username);
        return druidDataSource;
    }

    /**
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(int level) throws Exception {
        Connection connection = getDataSource().getConnection();
        switch (level) {
            case 1:
                //读未提交，会产生脏读、不可重复读、幻读
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            case 2:
                //读提交，会产生不可重复读、幻读
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            case 4:
                //可重复读，会产生幻读
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            case 8:
                //串行化
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            default:
                //TODO nothing
        }
        return connection;
    }
}
