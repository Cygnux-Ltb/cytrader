package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;
import io.mercury.common.util.StringUtil;

/**
 * ///TFtdcHedgeFlagType是一个投机套保标志类型<br>
 * <br>
 * ///投机<br>
 * #define THOST_FTDC_HF_Speculation '1'<br>
 * <br>
 * ///套利<br>
 * #define THOST_FTDC_HF_Arbitrage '2'<br>
 * <br>
 * ///套保<br>
 * #define THOST_FTDC_HF_Hedge '3'<br>
 * <br>
 * ///做市商<br>
 * #define THOST_FTDC_HF_MarketMaker '5'<br>
 * <br>
 * ///第一腿投机第二腿套保 大商所专用<br>
 * #define THOST_FTDC_HF_SpecHedge '6'<br>
 * <br>
 * ///第一腿套保第二腿投机 大商所专用<br>
 * #define THOST_FTDC_HF_HedgeSpec '7'<br>
 */

public interface FtdcHedgeFlag {

	/**
	 * 组合投机套保标识, 投机, [char]
	 */
	char Speculation = thosttraderapiConstants.THOST_FTDC_HF_Speculation;

	/**
	 * 组合投机套保标识, 投机, [String]
	 */
	String SpeculationStr = StringUtil.toString(thosttraderapiConstants.THOST_FTDC_HF_Speculation);

}
