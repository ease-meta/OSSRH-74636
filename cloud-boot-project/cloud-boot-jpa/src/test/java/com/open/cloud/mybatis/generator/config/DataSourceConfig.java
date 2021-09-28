package com.open.cloud.mybatis.generator.config;

import com.open.cloud.mybatis.generator.util.DbType;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库配置
 *
 * @author YangHu, hcl, hubin
 * @since 2016/8/30
 */
public class DataSourceConfig {

    public SqlSession getSqlSessionFactory;
    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接密码
     */
    private String password;
    /**
     * 数据源实例
     *
     * @since 3.5.0
     */
    private DataSource dataSource;
    /**
     * 数据库连接
     *
     * @since 3.5.0
     */
    private Connection connection;
    private String tableSchema;

    private DataSourceConfig() {
    }

    public String getTableSchema() {
        return username;
    }

    public DbType getDbType() {
        return this.getDbType(this.url.toLowerCase());
    }


    private DbType getDbType(String str) {
        if (str.contains(":mysql:")) {
            return DbType.MYSQL;
        } else if (str.contains(":oracle:")) {
            return DbType.ORACLE;
        } else if (str.contains(":mariadb:")) {
            return DbType.MARIADB;
        } else {
            return DbType.OTHER;
        }
    }


    /**
     * 创建数据库连接对象
     * 这方法建议只调用一次，毕竟只是代码生成，用一个连接就行。
     *
     * @return Connection
     */
    public Connection getConn() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                synchronized (this) {
                    if (dataSource != null) {
                        connection = dataSource.getConnection();
                    } else {
                        this.connection = DriverManager.getConnection(url, username, password);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public String getUrl() {
        return url;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    /**
     * 数据库配置构建者
     *
     * @author nieqiurong 2020/10/10.
     * @since 3.5.0
     */
    public static class Builder {

        private final DataSourceConfig dataSourceConfig;

        private Builder() {
            this.dataSourceConfig = new DataSourceConfig();
        }

        /**
         * 构造初始化方法
         *
         * @param url      数据库连接地址
         * @param username 数据库账号
         * @param password 数据库密码
         */
        public Builder(String url, String username, String password) {
            this();
            if (StringUtils.isBlank(url)) {
                throw new RuntimeException("无法创建文件，请正确输入 url 配置信息！");
            }
            this.dataSourceConfig.url = url;
            this.dataSourceConfig.username = username;
            this.dataSourceConfig.password = password;
        }

        /**
         * 构造初始化方法
         *
         * @param dataSource 外部数据源实例
         */
        public Builder(DataSource dataSource) {
            this();
            this.dataSourceConfig.dataSource = dataSource;
            try {
                Connection conn = dataSource.getConnection();
                this.dataSourceConfig.url = conn.getMetaData().getURL();

                this.dataSourceConfig.connection = conn;
                this.dataSourceConfig.username = conn.getMetaData().getUserName();
            } catch (SQLException ex) {
                throw new RuntimeException("构建数据库配置对象失败!", ex);
            }
        }

        /**
         * 构建数据库配置
         *
         * @return 数据库配置
         */
        public DataSourceConfig build() {
            return this.dataSourceConfig;
        }
    }
}
