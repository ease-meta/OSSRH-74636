package com.open.cloud.webssh;

import com.alibaba.excel.EasyExcel;
import com.open.cloud.webssh.model.InstallConfigMode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Leijian
 * @date 2021/3/11 17:20
 * @since
 */
public class MainRun {

	public static void main(String[] args) {
		DataHandleAnalysisEventListener dataHandleAnalysisEventListener = new DataHandleAnalysisEventListener();
		InputStream fis = null;
		try {
			fis = new FileInputStream("D:\\IdeaProjects\\open-cloud-platform\\cloud-boot-samples\\cloud-boot-ssh\\src\\main\\resources\\install-config.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//sheet(*)设置处理的工作簿，headRowNumber(*)设置从excel第几行开始处理
		EasyExcel.read(fis, InstallConfigMode.class, dataHandleAnalysisEventListener).sheet().headRowNumber(1).doRead();
	}
}
