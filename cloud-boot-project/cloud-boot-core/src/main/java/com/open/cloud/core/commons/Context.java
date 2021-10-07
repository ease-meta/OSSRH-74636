
package com.open.cloud.core.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;

/**
 * @author Tim
 * @version V1.0
 * @description 基于统一缓存实现的交易全局上下文
 * @update 2014年11月13日 下午4:36:49
 * @modify 2016年09月20日 下午13:07:22 修改本地缓存存储上下文
 */

public class Context implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(Context.class);

    private static final long serialVersionUID = -1L;

    public static final String BATCH_THREAD_NAME = "GalaxyBatchTask";
    public static final String ADD_BATCH_KEY = "GalaxyBatchAddBatch";
    public static final String ADD_BATCH_INSERT_KEY = "addInsertBatchMap";
    public static final String ADD_BATCH_UPDATE_KEY = "addUpdateBatchMap";
    public static final String ADD_BATCH_DELETE_KEY = "addDeleteBatchMap";


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
     * @fields platformId
     */
    private String platformId = ThreadLocalManager.getUID();

    /**
     * @fields properties
     */
    private Properties properties = new Properties();

    /**
     * @fields map
     */
    private Map<String, Object> map = new HashMap<>();

    /**
     * 累加的交易发生额 add by Tim 2017/10/30
     *
     * @fields tranAmount
     */
    private Map<String, BigDecimal> acctBalance;

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getInstance() {
        Stack<Context> contexts = getContextStack();
        if (contexts.empty()) {
            contexts.push(new Context());
            logger.info("Pressing context into context stack!");
        }
        return contexts.peek();
    }

    /**
     * 获取上下文栈对象
     *
     * @return
     */
    private static Stack<Context> getContextStack() {
        Stack<Context> contexts = ThreadLocalManager.getTranContext();
        if (null == contexts) {
            contexts = new Stack<>();
            ThreadLocalManager.setTranContext(contexts);
            logger.info("Create a stack and fill in ThreadLocal!");
        }
        return contexts;
    }

    /**
     * 将上下文压入栈顶
     *
     * @param context
     */
    public static Context pushContext(Context context) {
        logger.info("Press the context into the top of the stack!");
        Stack<Context> contexts = getContextStack();
        return contexts.push(context);
    }

    /**
     * 移除栈顶对象
     *
     * @return
     */
    public static Context popContext() {
        Stack<Context> contexts = getContextStack();
        if (contexts.size() == 0) {
            return null;
        }
        return contexts.pop();
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
