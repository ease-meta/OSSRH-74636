package com.moc.cloud.workflow.framework.serviceImpl;

import com.moc.cloud.workflow.common.exception.BusinessException;
import com.moc.cloud.workflow.common.exception.FlowNoGenException;
import com.moc.cloud.workflow.common.utils.ToolsStrBusi;
import com.moc.cloud.workflow.framework.entity.SeqNoPara;
import com.moc.cloud.workflow.framework.mapper.SeqNoParaMapper;
import com.moc.cloud.workflow.framework.service.SeqNoParaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-12
 */
@Service
@Slf4j
@Transactional
@Lazy
public class SeqNoParaServiceImpl implements SeqNoParaService {

	@Resource
	private DataSource dataSource;

	@Resource
	private SeqNoParaMapper seqNoParaMapper;

	public int save(SeqNoPara seqNoPara) {
		return seqNoParaMapper.insert(seqNoPara);
	}

	/**
	 * 获取指定长度的流水号,不足补0
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @return String
	 */
	public String getFlowNoByLength(String flowType, int length) {
		Long[] flowNo = getFlowNoFromDb(flowType, 1);
		return convertLong2StrFlowNo(flowNo[0], length);
	}

	/**
	 * 获取指定长度的流水号,前八位平台日期
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @return String
	 */
	public String getFlowNo(String flowType, int length) {
		Long[] flowNo = getFlowNoFromDb(flowType, 1);
		String sysdate = ToolsStrBusi.getSysDate("yyyyMMdd");
		return convertLong2StrFlowNoByDate(flowNo[0], length, sysdate);
	}

	/**
	 * 获取指定长度的流水号,前八位平台日期
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @param date
	 *            日期yyyyMMdd
	 * @return String
	 */
	public String getFlowNo(String flowType, int length, String date) {
		Long[] flowNo = getFlowNoFromDb(flowType, 1);
		return convertLong2StrFlowNoByDate(flowNo[0], length, date);
	}

	/**
	 * 获取若干个指定长度的流水号
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @param num
	 *            流水号个数
	 * @return String[]
	 */
	public String[] getFlowNo(String flowType, int length, int num) {
		Long[] flowNo = getFlowNoFromDb(flowType, num);
		String[] ret = new String[flowNo.length];

		for (int i = 0; i < flowNo.length; i++) {
			ret[i] = convertLong2StrFlowNo(flowNo[i], length);
		}
		return ret;
	}

	/**
	 * 获取若干个指定长度的流水号，前八位平台日期
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @param num
	 *            流水号个数
	 * @return String[]
	 */
	public String[] getFlowNoByDate(String flowType, int length, int num) {
		Long[] flowNo = getFlowNoFromDb(flowType, num);
		String[] ret = new String[flowNo.length];
		String sysdate = ToolsStrBusi.getSysDate("yyyyMMdd");
		for (int i = 0; i < flowNo.length; i++) {
			ret[i] = convertLong2StrFlowNoByDate(flowNo[i], length, sysdate);
		}
		return ret;
	}

	/**
	 * 获取若干个指定长度的流水号，前八位平台日期
	 *
	 * @param flowType
	 *            流水号类型
	 * @param length
	 *            流水号长度
	 * @param num
	 *            流水号个数
	 * @param date
	 *            日期yyyyMMdd
	 * @return String[]
	 */
	public String[] getFlowNoByDate(String flowType, int length, int num, String date) {
		Long[] flowNo = getFlowNoFromDb(flowType, num);
		String[] ret = new String[flowNo.length];
		for (int i = 0; i < flowNo.length; i++) {
			ret[i] = convertLong2StrFlowNoByDate(flowNo[i], length, date);
		}
		return ret;
	}

	private String convertLong2StrFlowNoByDate(long flowNo, int length, String sysdate) {
		String ret = String.valueOf(flowNo);
		while (ret.length() < length - 8) {
			ret = "0" + ret;
		}
		int _len = ret.length() + sysdate.length();
		if (length <= _len) {
			ret = (sysdate + ret).substring(0, length);
		} else {
			ret = sysdate + ret;
		}
		return ret;
	}

	private static String convertLong2StrFlowNo(long flowNo, int length) {
		String ret = String.valueOf(flowNo);
		String zero_array = "00000000000000000000";
		int _len = ret.length() + zero_array.length();
		if (length <= _len) {
			ret = (zero_array + ret).substring(_len - length, _len);
		} else {
			ret = zero_array + ret;
		}
		return ret;
	}

	private Long[] getFlowNoFromDb(String flowType, int num) {
		if (flowType == null) {
			throw new FlowNoGenException("生成流水号异常:flowType不能为空.");
		}

		if (num < 1) {
			throw new FlowNoGenException("生成流水号异常: 取流水号个数\"num\"不能小于1.");
		}

		Long[] ret = new Long[num];
		Connection con = getnostateConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		// try (Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		// ResultSet.CONCUR_UPDATABLE); ResultSet rs = st.executeQuery("select MIN_SEQ,
		// MAX_SEQ, CURR_SEQ, COUNT_SEQ,SEQ_TYPE from dc_act_seq_no_para where SEQ_TYPE
		// = '" + flowType + "' for update")) {
		try {
			st = con.prepareStatement("select MIN_SEQ, MAX_SEQ, CURR_SEQ, COUNT_SEQ,SEQ_TYPE from dc_act_seq_no_para where SEQ_TYPE = ? for update",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setString(1, flowType);
			rs = st.executeQuery();
			if (rs.next()) {
				long lowerLimit = Long.parseLong(rs.getString("MIN_SEQ"));
				long upperLimit = Long.parseLong(rs.getString("MAX_SEQ"));
				long step = Long.parseLong(rs.getString("COUNT_SEQ"));
				long index = Long.parseLong(rs.getString("CURR_SEQ"));

				for (int i = 0; i < num; i++) {
					if (index + step > upperLimit) {
						index = lowerLimit + step;
					} else {
						index += step;
					}
					ret[i] = new Long(index);
				}

				rs.updateString("CURR_SEQ", String.valueOf(index));
				rs.updateRow();
				log.info("生成流水号成功，生成的流水号是[{}]",ret[0]);
			} else {
				throw new FlowNoGenException("生成流水号异常：未找到" + flowType + "相关的记录");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				log.error("数据库操作异常", e);
			}
			log.error("数据库操作异常", e, e.getSuppressed());
			throw new FlowNoGenException("获取序列号异常");
		} catch (Exception e) {
			throw new BusinessException("数据库操作异常");
		} finally {
			try {
				if (Objects.nonNull(rs)) {
					rs.close();
				}
				if (Objects.nonNull(st)) {
					st.close();
				}
				if (Objects.nonNull(con)) {
					con.close();
				}
			} catch (SQLException e) {
				log.error("数据库操作异常", e, e.getSuppressed());
			}
		}

		return ret;
	}

	private Connection getnostateConnection() {
		try {
			Connection con = dataSource.getConnection();
			con.setAutoCommit(false);
			return con;
		} catch (Exception e) {
			log.error("数据库操作异常", e);
			throw new BusinessException("数据库操作异常");

		}
	}

}
