package io.github.meta.ease.report.jasperreports;

import net.sf.jasperreports.engine.base.JRBaseStyle;

/**
 * <p>文件名称:  DefaultStyle</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/7/19</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class DefaultStyle extends JRBaseStyle {

    @Override
    public Boolean isBlankWhenNull() {
        return super.isBlankWhenNull;
    }
}
