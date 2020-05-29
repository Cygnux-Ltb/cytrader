package io.mercury.ftdc.adaptor.consts;

import ctp.thostapi.thosttraderapiConstants;

/**
 * ///TFtdcForceCloseReasonType是一个强平原因类型<br>
 * <br>
 * ///非强平<br>
 * #define THOST_FTDC_FCC_NotForceClose '0'<br>
 * <br>
 * ///资金不足<br>
 * #define THOST_FTDC_FCC_LackDeposit '1'<br>
 * <br>
 * ///客户超仓<br>
 * #define THOST_FTDC_FCC_ClientOverPositionLimit '2'<br>
 * <br>
 * ///会员超仓<br>
 * #define THOST_FTDC_FCC_MemberOverPositionLimit '3'<br>
 * <br>
 * ///持仓非整数倍<br>
 * #define THOST_FTDC_FCC_NotMultiple '4'<br>
 * <br>
 * ///违规<br>
 * #define THOST_FTDC_FCC_Violation '5'<br>
 * <br>
 * ///其它<br>
 * #define THOST_FTDC_FCC_Other '6'<br>
 * <br>
 * ///自然人临近交割<br>
 * #define THOST_FTDC_FCC_PersonDeliv '7'<br>
 */

public interface FtdcForceCloseReason {

	/**
	 * 非强平
	 */
	char NotForceClose = thosttraderapiConstants.THOST_FTDC_FCC_NotForceClose;

}
