package io.redstone.engine.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

/**
 * Nacos信息读取
 * 
 * 使用Nacos读取properties配置文件
 * 
 * @author: yellow013
 */
public class NacosReader {

	private static Logger logger = CommonLoggerFactory.getLogger(NacosReader.class);

	private Properties properties;

	public static final Properties readWith(String... args) {
		return new NacosReader(args).properties;
	}

	private NacosReader(String[] args) {
		Assertor.requiredLength(args, 3, "main input args");
		String serverAddr = args[0];
		String group = args[1];
		String dataId = args[2];
		try {
			logger.info("Reading nacos -> serverAddr==[{}], group==[{}], dataId==[{}]", serverAddr, group, dataId);
			ConfigService configService = ConfigFactory.createConfigService(serverAddr);
			String config = configService.getConfig(dataId, group, 5000);
			if (StringUtil.isNullOrEmpty(config)) {
				logger.info("Read nacos config is null or empty");
				throw new RuntimeException("read nacos error");
			}
			ByteArrayInputStream inputStream = new ByteArrayInputStream(config.getBytes());
			properties = new Properties();
			properties.load(inputStream);
			properties.forEach((key, value) -> logger.info("nacos config : key -> {}, value -> {}", key, value));
		} catch (NacosException e) {
			logger.info("Read nacos error -> {}", e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.info("Load properties error -> {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		NacosReader.readWith("203.60.2.70:8848", "data");
	}

}
