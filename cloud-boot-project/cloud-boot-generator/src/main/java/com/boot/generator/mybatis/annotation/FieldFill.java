package com.boot.generator.mybatis.annotation;

/**
 * �ֶ�������ö����
 *
 * <p>
 * �ж�ע��� insert �� update �� sql �ű��Ƿ��ڶ�Ӧ����º��Ե��ֶε� if ��ǩ����
 * <if test="...">......</if>
 * �ж����ȼ��� {@link FieldStrategy} ��
 * </p>
 *
 * @author hubin
 * @since 2017-06-27
 */
public enum FieldFill {
    /**
     * Ĭ�ϲ�����
     */
    DEFAULT,
    /**
     * ����ʱ����ֶ�
     */
    INSERT,
    /**
     * ����ʱ����ֶ�
     */
    UPDATE,
    /**
     * ����͸���ʱ����ֶ�
     */
    INSERT_UPDATE
}
