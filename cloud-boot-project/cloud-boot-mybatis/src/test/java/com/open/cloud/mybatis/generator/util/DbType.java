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
package com.open.cloud.mybatis.generator.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MybatisPlus ֧�ֵ����ݿ�����,��Ҫ���ڷ�ҳ����
 *
 * @author hubin
 * @since 2018-06-23
 */
@Getter
@AllArgsConstructor
public enum DbType {

    /**
     * MYSQL
     */
    MYSQL("mysql", "MySql���ݿ�"),
    /**
     * MARIADB
     */
    MARIADB("mariadb", "MariaDB���ݿ�"),
    /**
     * ORACLE
     */
    ORACLE("oracle", "Oracle11g���������ݿ�(�߰汾�Ƽ�ʹ��ORACLE_NEW)"),
    /**
     * oracle12c new pagination
     */
    ORACLE_12C("oracle12c", "Oracle12c+���ݿ�"),
    /**
     * DB2
     */
    DB2("db2", "DB2���ݿ�"),
    /**
     * H2
     */
    H2("h2", "H2���ݿ�"),
    /**
     * HSQL
     */
    HSQL("hsql", "HSQL���ݿ�"),
    /**
     * SQLITE
     */
    SQLITE("sqlite", "SQLite���ݿ�"),
    /**
     * POSTGRE
     */
    POSTGRE_SQL("postgresql", "Postgre���ݿ�"),
    /**
     * SQLSERVER2005
     */
    SQL_SERVER2005("sqlserver2005", "SQLServer2005���ݿ�"),
    /**
     * SQLSERVER
     */
    SQL_SERVER("sqlserver", "SQLServer���ݿ�"),
    /**
     * DM
     */
    DM("dm", "�������ݿ�"),
    /**
     * xugu
     */
    XU_GU("xugu", "������ݿ�"),
    /**
     * Kingbase
     */
    KINGBASE_ES("kingbasees", "�˴������ݿ�"),
    /**
     * Phoenix
     */
    PHOENIX("phoenix", "Phoenix HBase���ݿ�"),
    /**
     * Gauss
     */
    GAUSS("zenith", "Gauss ���ݿ�"),
    /**
     * ClickHouse
     */
    CLICK_HOUSE("clickhouse", "clickhouse ���ݿ�"),
    /**
     * GBase
     */
    GBASE("gbase", "�ϴ�ͨ��(����)���ݿ�"), GBASEDBT("gbasedbt", "�ϴ�ͨ�����ݿ�"),
    /**
     * Oscar
     */
    OSCAR("oscar", "��ͨ���ݿ�"),
    /**
     * Sybase
     */
    SYBASE("sybase", "Sybase ASE ���ݿ�"),
    /**
     * OceanBase
     */
    OCEAN_BASE("oceanbase", "OceanBase ���ݿ�"),
    /**
     * Firebird
     */
    FIREBIRD("Firebird", "Firebird ���ݿ�"),

    /**
     * HighGo
     */
    HIGH_GO("highgo", "嫸����ݿ�"),
    /**
     * CUBRID
     */
    CUBRID("cubrid", "CUBRID���ݿ�"),

    /**
     * GOLDILOCKS
     */
    GOLDILOCKS("goldilocks", "GOLDILOCKS���ݿ�"),
    /**
     * CSIIDB
     */
    CSIIDB("csiidb", "CSIIDB���ݿ�"),
    /**
     * UNKONWN DB
     */
    OTHER("other", "�������ݿ�");

    /**
     * ���ݿ�����
     */
    private final String db;
    /**
     * ����
     */
    private final String desc;

    /**
     * ��ȡ���ݿ�����
     *
     * @param dbType ���ݿ������ַ���
     */
    public static DbType getDbType(String dbType) {
        for (DbType type : DbType.values()) {
            if (type.db.equalsIgnoreCase(dbType)) {
                return type;
            }
        }
        return OTHER;
    }
}
