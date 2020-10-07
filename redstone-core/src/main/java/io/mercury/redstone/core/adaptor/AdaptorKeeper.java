package io.mercury.redstone.core.adaptor;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.serialization.Dumpable;
import io.mercury.redstone.core.account.Account;
import io.mercury.redstone.core.account.SubAccount;

/**
 * @topic 存储Adaptor和Mapping关系<br>
 * 
 *        1.以[accountId]查找Adaptor<br>
 *        2.以[subAccountId]查找Adaptor<br>
 * 
 * @author yellow013
 *
 * @TODO 修改为线程安全的管理器<br>
 * 
 *       如果程序运行中不修改Adaptor的引用则可以在多个线程中调用Get函数<br>
 *       如果运行中Adaptor崩溃, 重新创建创建Adaptor则需要重新Put<br>
 *       目前无法保证这一过程的访问安全
 */
@NotThreadSafe
public final class AdaptorKeeper implements Dumpable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1199809125474119945L;

	/**
	 * Logger
	 */
	private static final Logger log = CommonLoggerFactory.getLogger(AdaptorKeeper.class);

	/**
	 * 存储Adaptor, 使用accountId索引
	 */
	private static final MutableIntObjectMap<Adaptor> AccountAdaptorMap = MutableMaps.newIntObjectHashMap();

	/**
	 * 存储Adaptor, 使用subAccountId索引
	 */
	private static final MutableIntObjectMap<Adaptor> SubAccountAdaptorMap = MutableMaps.newIntObjectHashMap();

	private AdaptorKeeper() {
	}

	public static Adaptor getAdaptorByAccount(Account account) {
		return AccountAdaptorMap.get(account.accountId());
	}

	public static Adaptor getAdaptorByAccountId(int accountId) {
		return AccountAdaptorMap.get(accountId);
	}

	public static Adaptor getAdaptorBySubAccount(SubAccount subAccount) {
		return SubAccountAdaptorMap.get(subAccount.subAccountId());
	}

	public static Adaptor getAdaptorBySubAccountId(int subAccountId) {
		return SubAccountAdaptorMap.get(subAccountId);
	}

	public static void putAdaptor(@Nonnull Adaptor adaptor) {
		ImmutableList<Account> accounts = adaptor.accounts();
		accounts.each(account -> {
			AccountAdaptorMap.put(account.accountId(), adaptor);
			log.info(
					"Put adaptor to AccountAdaptorMap, accountId==[{}], remark==[{}], adaptorId==[{}], adaptorName==[{}]",
					account.accountId(), account.remark(), adaptor.adaptorId(), adaptor.adaptorName());
			account.subAccounts().each(subAccount -> {
				SubAccountAdaptorMap.put(subAccount.subAccountId(), adaptor);
				log.info(
						"Put adaptor to SubAccountAdaptorMap, subAccountId==[{}], subAccountName==[{}], adaptorId==[{}], adaptorName==[{}]",
						subAccount.subAccountId(), subAccount.subAccountName(), adaptor.adaptorId(),
						adaptor.adaptorName());
			});
		});
	}

	@Override
	public String dump() {
		return "";
	}

}
