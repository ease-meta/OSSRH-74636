package cn.iocoder.yudao.module.system.dal.dataobject.social;


import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.meta.ease.mybatis.mybatis.core.dataobject.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 社交用户的绑定
 * 即 {@link cn.iocoder.yudao.module.system.dal.dataobject.social.SocialUserDO} 与 UserDO 的关联表
 *
 * @author 芋道源码
 */
@TableName(value = "system_social_user_bind", autoResultMap = true)
@KeySequence("system_social_user_bind_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserBindDO extends BaseDO {

    /**
     * 关联的用户编号
     * <p>
     * 关联 UserDO 的编号
     */
    private Long userId;
    /**
     * 用户类型
     * <p>
     * 枚举 {@link io.github.meta.ease.common.enums.UserTypeEnum}
     */
    private Integer userType;

    /**
     * 社交平台的用户编号
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.system.dal.dataobject.social.SocialUserDO#getId()}
     */
    private Long socialUserId;
    /**
     * 社交平台的类型
     * <p>
     * 冗余 {@link cn.iocoder.yudao.module.system.dal.dataobject.social.SocialUserDO#getType()}
     */
    private Integer socialType;

}
