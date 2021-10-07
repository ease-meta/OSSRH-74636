
package com.open.cloud.core.commons;

import com.open.cloud.core.domain.BaseRequest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于ThreadLocal的面向业务开发者使用的上下文传递对象
 */

public final class Context {

    /**
     * 线程上下文变量
     */
    protected static final ThreadLocal<Context> LOCAL = new ThreadLocal<Context>();

    /**
     * 自定义属性
     */
    protected ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();

    public static final String BATCH_THREAD_NAME = "GalaxyBatchTask";
    public static final String ADD_BATCH_KEY = "GalaxyBatchAddBatch";
    public static final String ADD_BATCH_INSERT_KEY = "addInsertBatchMap";
    public static final String ADD_BATCH_UPDATE_KEY = "addUpdateBatchMap";
    public static final String ADD_BATCH_DELETE_KEY = "addDeleteBatchMap";

    /**
     * 请求入参模型
     */
    private BaseRequest baseRequest;

    private String runDate;

    private String lastRunDate;

    private String nextRunDate;

    /**
     * 全局事务控制开关
     */
    private String dtpFlag;

    /**
     * Spring事务控制开关
     */
    private String txFlag;

    private boolean isBatch;

    /**
     * 批量提交事务
     */
    private boolean batchTransactional;

    /**
     * 业务节点类型
     */
    private String currentNodeType;

    /**
     * 百信项目要求，上下文增加数据路由关键字段 20170410
     */
    private String routerKey;

    /**
     * @fields properties
     */
    private Properties properties = new Properties();


    /**
     * 累加的交易发生额 add by Tim 2017/10/30
     *
     * @fields tranAmount
     */
    private Map<String, BigDecimal> acctBalance;

    /**
     * 得到上下文，没有则初始化
     *
     * @return 调用上下文
     */
    public static Context getContext() {
        Context context = LOCAL.get();
        if (context == null) {
            context = new Context();
            LOCAL.set(context);
        }
        return context;
    }


    public void initBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    /**
     * 查看上下文
     *
     * @return 调用上下文，可能为空
     */
    public static Context peekContext() {
        return LOCAL.get();
    }

    /**
     * 删除上下文
     */
    public static void removeContext() {
        LOCAL.remove();
    }

    /**
     * 设置上下文
     *
     * @param context 调用上下文
     */
    private static void setContext(Context context) {
        LOCAL.set(context);
    }

    /**
     * 设置一个调用上下文数据
     *
     * @param key   Key
     * @param value Value
     */
    public void put(String key, Object value) {
        if (key != null && value != null) {
            map.put(key, value);
        }
    }

    /**
     * 获取一个调用上下文数据
     *
     * @param key Key
     * @return 值
     */
    public Object get(String key) {
        if (key != null) {
            return map.get(key);
        }
        return null;
    }

    /**
     * 删除一个调用上下文数据
     *
     * @param key Key
     * @return 删除前的值
     */
    public Object remove(String key) {
        if (key != null) {
            return map.remove(key);
        }
        return null;
    }

  /*  public ISysHead getSysHead() {
        return sysHead;
    }

    public void setSysHead(ISysHead sysHead) {
        this.sysHead = sysHead;
    }

    public String getThreadNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getThreadNo();
    }

    public String getServiceCode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getServiceCode();
    }

    public String getTranMode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getTranMode();
    }

    public String getReference() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getReference();
    }

    public String getSourceType() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getSourceType();
    }

    public String getBranchId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getBranchId();
    }

    public String getUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getUserId();
    }

    public String getTranDate() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getTranDate();
    }

    public Date getTranDateParse() {
        String tranDate = getTranDate();
        if (null != tranDate) {
            return DateUtils.parse(tranDate, DateUtils.DEFAULT_DATE_FORMAT);
        }
        return null;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getRunDate() {
        return runDate;
    }

    public Date getRunDateParse() {
        String runDate = getRunDate();
        if (null == runDate) {
            return null;
        }
        return DateUtils.parse(runDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public void setNextRunDate(String nextRunDate) {
        this.nextRunDate = nextRunDate;
    }

    public String getNextRunDate() {
        return nextRunDate;
    }

    public Date getNextRunDateParse() {
        String nextRunDate = getNextRunDate();
        if (null == nextRunDate) {
            return null;
        }
        return DateUtils.parse(nextRunDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public void setLastRunDate(String lastRunDate) {
        this.lastRunDate = lastRunDate;
    }

    public String getLastRunDate() {
        return lastRunDate;
    }

    public Date getLastRunDateParse() {
        String lastRunDate = getLastRunDate();
        if (null == lastRunDate) {
            return null;
        }
        return DateUtils.parse(lastRunDate, DateUtils.DEFAULT_DATE_FORMAT);
    }

    public String getYesterday() {
        Date yesserday = getYesterdayParse();
        if (null == yesserday) {
            return null;
        }
        return DateUtils.getDateTime(getYesterdayParse(),
                DateUtils.DEFAULT_DATE_FORMAT);
    }

    public Date getYesterdayParse() {
        Date runDate = getRunDateParse();
        if (null == runDate) {
            return null;
        }
        return DateUtils.addDays(runDate, -1);
    }

    public String getTranTimestamp() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getTranTimestamp();
    }

    public String getUserLang() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getUserLang();
    }

    public String getSeqNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getSeqNo();
    }

    public String getProgramId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getProgramId();
    }

    public String getAuthUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getAuthUserId();
    }

    public String getAuthFlag() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getAuthFlag();
    }

    public String getApprUserId() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getApprUserId();
    }

    public String getApprFlag() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getApprFlag();
    }

    public String getSourceBranchNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getSourceBranchNo();
    }

    public String getDestBranchNo() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getDestBranchNo();
    }

    public String getMessageType() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getMessageType();
    }

    public String getMessageCode() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getMessageCode();
    }

    public String getFilePath() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getFilePath();
    }

    public String getMacValue() {
        ISysHead sysHead = getSysHead();
        if (sysHead == null) {
            return null;
        }
        return sysHead.getMacValue();
    }

    public String getPlatformId() {
        // 获取平台ID
        return platformId;
    }

    public void setPlatformId(String platformId) {
        // 获取平台ID
        this.platformId = platformId;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public Object getObject(String key) {
        return map.get(key);
    }

    public void setObject(String key, Object value) {
        map.put(key, value);
    }

    *//**
     * 获取IOC容器bean实例
     *
     * @param id
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2014年11月20日 上午9:29:31
     *//*
    @SuppressWarnings("unchecked")
    public <T> T getBean(String id) {
        T t = null;
        try {
            t = (T) SpringApplicationContext.getContext().getBean(id);
            //t = (T) SpringContainer.getContext().getBean(id);
        } catch (NoSuchBeanDefinitionException e) {
        }
        return t;
    }

    *//**
     * 清空上下文
     *
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年1月19日 下午3:20:27
     *//*
    public void cleanResource() {
        // 为了保持向下兼容性。对此方法调整为移除栈顶对象，并将新的对象压入栈顶；
        popContext();
        pushContext(new Context());

    }

    *//**
     * 获取全局事务控制开关
     *
     * @return
     *//*
    public String getDtpFlag() {
        return dtpFlag;
    }

    *//**
     * 设置全局事务控制开关
     *
     * @param dtpFlag
     *//*
    public void setDtpFlag(String dtpFlag) {
        this.dtpFlag = dtpFlag;
    }

    public boolean isBatch() {
        return isBatch;
    }

    public void setIsBatch(boolean isBatch) {
        this.isBatch = isBatch;
    }

    public String getCurrentNodeType() {
        return currentNodeType;
    }

    public void setCurrentNodeType(String currentNodeType) {
        this.currentNodeType = currentNodeType;
    }

    *//**
     * 将上下文对象序列化为JSON字符串
     *
     * @param context
     * @return
     *//*
    public static String serializeContext(Context context) {
        JSONObject contextJson = new JSONObject();
        putJSONObjectValue(contextJson, "sysHead", context.sysHead);
        putJSONObjectValue(contextJson, "appHead", context.appHead);
        putJSONObjectValue(contextJson, "dtpFlag", context.dtpFlag);
        putJSONObjectValue(contextJson, "currentNodeType", context.currentNodeType);
        putJSONObjectValue(contextJson, "isBatch", context.isBatch);
        putJSONObjectValue(contextJson, "runDate", context.runDate);
        putJSONObjectValue(contextJson, "lastRunDate", context.lastRunDate);
        putJSONObjectValue(contextJson, "nextRunDate", context.nextRunDate);
        putJSONObjectValue(contextJson, "platformId", context.platformId);
        // 百信项目要求，上下文增加数据路由关键字段 20170410
        putJSONObjectValue(contextJson, "routerKey", context.routerKey);
        return contextJson.toJSONString();
    }

    *//**
     * 将JSON字符串反序列化为上下文对象
     *
     * @param contextJson
     * @return
     *//*
    public static Context deserialize(String contextJson) {
        return JSON.parseObject(contextJson, Context.class);
    }

    private static void putJSONObjectValue(JSONObject jsonObject, String key, Object value) {
        if (value != null) {
            jsonObject.put(key, value);
        }
    }

    @Override
    public String toString() {
        return Context.serializeContext(this);
    }

    public IAppHead getAppHead() {
        return appHead;
    }

    public void setAppHead(IAppHead appHead) {
        this.appHead = appHead;
    }

    *//**
     * 百信项目要求，上下文增加数据路由关键字段 20170410
     *//*
    public String getRouterKey() {
        return routerKey;
    }

    *//**
     * 百信项目要求，上下文增加数据路由关键字段 20170410
     *//*
    public void setRouterKey(String routerKey) {
        this.routerKey = routerKey;
    }

    public String getTxFlag() {
        return txFlag;
    }

    public void setTxFlag(String txFlag) {
        this.txFlag = txFlag;
    }

    public boolean txIsOpen() {
        return (this.txFlag == null || "Y".equals(this.txFlag)) ? true : false;
    }

    *//**
     * 增加金额累计功能
     *
     * @param internalKey
     * @return
     *//*
    public void putAcctBal(String internalKey, BigDecimal balance) {
        if (batchTransactional) {
            if (null == acctBalance) {
                acctBalance = new HashMap<>(128);
            }
            acctBalance.put(internalKey, balance);
            if (logger.isInfoEnabled()) {
                logger.info("put internalKey [{}] ,acct balance [{}]", internalKey, balance);
            }
        }
    }

    *//**
     * 获取累加金额
     *
     * @param internalKey
     * @return
     *//*
    public BigDecimal getAcctBal(String internalKey) {
        if (batchTransactional) {
            if (null != acctBalance && acctBalance.containsKey(internalKey)) {
                if (logger.isInfoEnabled()) {
                    logger.info("get internalKey [{}] ,acct balance [{}]", internalKey, acctBalance.get(internalKey));
                }
                return acctBalance.get(internalKey);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    *//**
     * 批量事务提交
     *
     * @return
     *//*
    public boolean isBatchTransactional() {
        return this.batchTransactional;
    }

    *//**
     * 批量事务提交
     *
     * @param batchTransactional
     *//*
    public void setBatchTransactional(boolean batchTransactional) {
        this.batchTransactional = batchTransactional;
    }*/
}
