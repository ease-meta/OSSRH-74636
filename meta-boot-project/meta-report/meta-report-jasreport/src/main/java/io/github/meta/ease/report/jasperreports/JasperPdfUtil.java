package io.github.meta.ease.report.jasperreports;

import cn.hutool.core.io.resource.ResourceUtil;
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

        JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFileName));
        SimplePdfReportConfiguration simplePdfReportConfiguration = new SimplePdfReportConfiguration();

        exporter.setConfiguration(simplePdfReportConfiguration);

        SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
        exporterConfiguration.setMetadataAuthor("abu");
        exporterConfiguration.setMetadataCreator("abu");
        exporter.setConfiguration(exporterConfiguration);
        //exporter.exportReport();

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
        jasperDesign.addStyle(new DefaultStyle());
        return jasperDesign;
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
