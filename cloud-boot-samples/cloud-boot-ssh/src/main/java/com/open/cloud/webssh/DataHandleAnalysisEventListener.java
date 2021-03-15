package com.open.cloud.webssh;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.open.cloud.webssh.model.InstallConfigMode;
import com.open.cloud.webssh.model.SSHConfigModeConverter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataHandleAnalysisEventListener extends AnalysisEventListener {

	private static final Logger log = LoggerFactory.getLogger(DataHandleAnalysisEventListener.class);

	private static List<InstallConfigMode> excelModelList = Collections.synchronizedList(new ArrayList<>());

	@SneakyThrows
	@Override
	public void invoke(Object data, AnalysisContext context) {
		InstallConfigMode installConfigMode = (InstallConfigMode) data;
		if (StringUtils.isAnyEmpty(installConfigMode.getHostname(), installConfigMode.getPassword(), installConfigMode.getRootpassword(), installConfigMode.getUsername())) {
			log.warn("数据有空，不进行处理");
		} else {
			ExecuteShellUtil.getInstance().init(SSHConfigModeConverter.INSTANCE.to(installConfigMode));
			excelModelList.add(installConfigMode);
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		System.out.println("开始处理数据");

	}
}