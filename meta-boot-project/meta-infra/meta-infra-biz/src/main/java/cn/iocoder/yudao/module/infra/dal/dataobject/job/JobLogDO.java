package cn.iocoder.yudao.module.infra.dal.dataobject.job;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.meta.ease.mybatis.mybatis.core.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 定时任务的执行日志
 *
 * @author 芋道源码
 */
@TableName("infra_job_log")
@KeySequence("infra_job_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobLogDO extends BaseDO {

    /**
     * 日志编号
     */
    private Long id;
    /**
     * 任务编号
     * <p>
     * 关联 {@link JobDO#getId()}
     */
    private Long jobId;
    /**
     * 处理器的名字
     * <p>
     * 冗余字段 {@link JobDO#getHandlerName()}
     */
    private String handlerName;
    /**
     * 处理器的参数
     * <p>
     * 冗余字段 {@link JobDO#getHandlerParam()}
     */
    private String handlerParam;
    /**
     * 第几次执行
     * <p>
     * 用于区分是不是重试执行。如果是重试执行，则 index 大于 1
     */
    private Integer executeIndex;

    /**
     * 开始执行时间
     */
    private Date beginTime;
    /**
     * 结束执行时间
     */
    private Date endTime;
    /**
     * 执行时长，单位：毫秒
     */
    private Integer duration;
    /**
     * 状态
     * <p>
     * 枚举 {@link cn.iocoder.yudao.module.infra.enums.job.JobLogStatusEnum}
     */
    private Integer status;
    /**
     * 结果数据
     * <p>
     * 成功时，使用 {@link JobHandler#execute(String)} 的结果
     * 失败时，使用 {@link JobHandler#execute(String)} 的异常堆栈
     */
    private String result;

}
