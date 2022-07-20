package io.github.meta.ease.report.jasperreports;

import cn.hutool.core.bean.BeanUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>文件名称:  JasperPdfUtilTest</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/7/20</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
class JasperPdfUtilTest {

    @SneakyThrows
    @Test
    void viewReport() {
        String templatePath = "contract.jrxml";
        ContractEntity contract = new ContractEntity();
        contract.setContractCode("CON11123445567778888");
        contract.setContractName("马尔代夫海景房转让合同");
        contract.setContractOriginalCode("ORI555444333222111");
        contract.setOriginalCcyTaxIncludedAmt(new BigDecimal(123456789.12345666666)
                .setScale(6, BigDecimal.ROUND_DOWN));
        contract.setLocalCcyTaxIncludedAmt(new BigDecimal(987654321.123456));
        contract.setContractType("租赁合同");
        contract.setContractDetailType("房屋租赁合同");
        //contract.setEffectiveDate("123");
        contract.setExpiredDate(new Date());

        JasperPdfUtil.exportPdfFromXml(templatePath, BeanUtil.beanToMap(contract),"pdf.pdf");
    }

}