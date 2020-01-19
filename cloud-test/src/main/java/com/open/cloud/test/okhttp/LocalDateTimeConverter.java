package com.open.cloud.test.okhttp;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;

import java.time.LocalDateTime;

public class LocalDateTimeConverter implements Converter<LocalDateTime> {
	@Override
	public Class supportJavaTypeKey() {
		return LocalDateTime.class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
			return LocalDateTimeUtils.
		} else {
			return DateUtils.parseDate(cellData.getStringValue(),
					contentProperty.getDateTimeFormatProperty().getFormat());
		}

	}

	@Override
	public CellData convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
		if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
			return new CellData(DateUtils.format(value, null));
		} else {
			return new CellData(DateUtils.format(value, contentProperty.getDateTimeFormatProperty().getFormat()));
		}
	}
}
}
