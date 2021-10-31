/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.mybatis.generator.config;

import com.open.cloud.mybatis.generator.util.DbType;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�����
 *
 * @author YangHu, hcl, hubin
 * @since 2016/8/30
 */
public class DataSourceConfig {

    public SqlSession getSqlSessionFactory;
    /**
     * �������ӵ�URL
     */
    private String url;
    /**
     * ���ݿ������û���
     */
    private String username;
    /**
     * ���ݿ���������
     */
    private String password;
    /**
     * ����Դʵ��
     *
     * @since 3.5.0
     */
    private DataSource dataSource;
    /**
     * ���ݿ�����
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
     * �������ݿ����Ӷ���
     * �ⷽ������ֻ����һ�Σ��Ͼ�ֻ�Ǵ������ɣ���һ�����Ӿ��С�
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
     * ���ݿ����ù�����
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
         * �����ʼ������
         *
         * @param url      ���ݿ����ӵ�ַ
         * @param username ���ݿ��˺�
         * @param password ���ݿ�����
         */
        public Builder(String url, String username, String password) {
            this();
            if (StringUtils.isBlank(url)) {
                throw new RuntimeException("�޷������ļ�������ȷ���� url ������Ϣ��");
            }
            this.dataSourceConfig.url = url;
            this.dataSourceConfig.username = username;
            this.dataSourceConfig.password = password;
        }

        /**
         * �����ʼ������
         *
         * @param dataSource �ⲿ����Դʵ��
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
                throw new RuntimeException("�������ݿ����ö���ʧ��!", ex);
            }
        }

        /**
         * �������ݿ�����
         *
         * @return ���ݿ�����
         */
        public DataSourceConfig build() {
            return this.dataSourceConfig;
        }
    }
}
