package io.github.meta.ease.report.jasperreports;

import cn.hutool.core.io.resource.ResourceUtil;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * <p>文件名称:  JasperPdfUtil</p>
 * <p>描述:     </p>
 * <p>创建时间:  2022/7/15</p>
 *
 * @author Abu
 * @version 22.0.1
 * @since 22.0.1
 */
public class JasperPdfUtil {

    private JasperPdfUtil() {
    }


    public static void viewReport(String templatePath, Map<String, Object> paramMap) throws JRException {
        JasperReport jasperReport = getJasperReportFromXml(templatePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
        JasperViewer.viewReport(jasperPrint, false);
    }

    /**
     * 导出 PDF
     *
     * @param templatePath jrxml 模板路径(base classpath)
     * @param paramMap     数据对象
     * @return
     * @throws net.sf.jasperreports.engine.JRException
     */
    public static void exportPdfFromXml(String templatePath, Map<String, Object> paramMap, String destFileName) throws JRException {
        JasperReport jasperReport = getJasperReportFromXml(templatePath);

        JasperPrint jasperPrint = getJasperPrint(jasperReport, paramMap);

      /*  JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFileName));
        SimplePdfReportConfiguration simplePdfReportConfiguration = new SimplePdfReportConfiguration();

        exporter.setConfiguration(simplePdfReportConfiguration);

        SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
        exporterConfiguration.setMetadataAuthor("abu");
        exporterConfiguration.setMetadataCreator("abu");
        exporter.setConfiguration(exporterConfiguration);*/

        JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
    }

    /**
     * 导出 PDF
     *
     * @param templatePath jasper 模板路径(base classpath)
     * @param paramMap     数据对象
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    public static byte[] exportPdfFromJasper(String templatePath, Map<String, Object> paramMap) throws FileNotFoundException, JRException {
        JasperReport jasperReport = getJasperReportFromJasper(templatePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private static JasperPrint getJasperPrint(JasperReport jasperReport, Map<String, Object> paramMap) throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap, new JREmptyDataSource());
        return jasperPrint;
    }

    private static JasperDesign getJasperDesign(String templatePath) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(ResourceUtil.getStream(templatePath));
        return jasperDesign;
    }

    /**
     *
     */
    @SneakyThrows
    public void sign(final InputStream is, final OutputStream os, byte imgb[], String reason, String location) {
        //下边的步骤都是固定的，照着写就行了，没啥要解释的
        // Creating the reader and the stamper，开始pdfreader
        PdfReader reader = new PdfReader(is);
        //创建签章工具PdfStamper ，最后一个boolean参数
        //false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
        //true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0', null, true);
        // 获取数字签章属性对象，设定数字签章的属性
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
        appearance.setVisibleSignature(new Rectangle(200, 200, 300, 300), 1, "sig1");
        //读取图章图片，这个image是itext包的image
        Image image = Image.getInstance(imgb);
        appearance.setSignatureGraphic(image);
        appearance.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
        appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
        // 摘要算法

        ExternalDigest digest = new BouncyCastleDigest();
        // 签名算法
        ExternalSignature signature = new DefaultExternalSignature();
        // 调用itext签名方法完成pdf签章
        MakeSignature.signDetached(appearance, digest, signature, null, null, null, null, 0, null);

    }
    /**
     * 获取导出对象,从 xml 中
     *
     * @param templatePath 模板路径(base classpath)
     * @return
     * @throws JRException
     */
    private static JasperReport getJasperReportFromXml(String templatePath) throws JRException {
        return JasperCompileManager.compileReport(getJasperDesign(templatePath));
    }


    /**
     * 获取导出对象,从 jasper 中
     * (jasper 为 jrxml 编译后生成的文件)
     *
     * @param templatePath 模板路径(base classpath)
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    private static JasperReport getJasperReportFromJasper(String templatePath) throws FileNotFoundException, JRException {
        return (JasperReport) JRLoader.loadObject(ResourceUtil.getStream(templatePath));
    }
}
