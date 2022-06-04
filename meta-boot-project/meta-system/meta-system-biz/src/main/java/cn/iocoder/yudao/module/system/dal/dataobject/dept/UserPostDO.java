package cn.iocoder.yudao.module.system.dal.dataobject.dept;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.meta.ease.mybatis.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户和岗位关联
 *
 * @author ruoyi
 */
@TableName("system_user_post")
@KeySequence("system_user_post_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPostDO extends BaseDO {

    /**
     * 自增主键
     */
    @TableId
    private Long id;
    /**
     * 用户 ID
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO#getId()}
     */
    private Long userId;
    /**
     * 角色 ID
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.system.dal.dataobject.dept.PostDO#getId()}
     */
    private Long postId;

}
