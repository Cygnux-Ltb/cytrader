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

	private ConfigService configService;

	private Logger logger = CommonLoggerFactory.getLogger(NacosReader.class);

	public static final Properties readWith(String... args) {
		Assertor.requiredLength(args, 3, "main input args");
		return connection(args[0]).getProperties(args[1], args[2]);
	}

	public static final NacosReader connection(String serverAddr) {
		return new NacosReader(serverAddr);
	}

	private NacosReader(String serverAddr) {
		try {
			logger.info("createConfigService -> serverAddr==[{}]", serverAddr);
			this.configService = ConfigFactory.createConfigService(serverAddr);
		} catch (NacosException e) {
			logger.error("createConfigService error -> {}", e.getMessage(), e);
			throw new RuntimeException("createConfigService method error", e);
		}
	}

	public Properties getProperties(String group, String dataId) {
		try {
			logger.info("Reading nacos ->  group==[{}], dataId==[{}]", group, dataId);
			String config = configService.getConfig(dataId, group, 5000);
			if (StringUtil.isNullOrEmpty(config)) {
				logger.info("Read nacos config is null or empty");
				throw new RuntimeException("read nacos error");
			}
			ByteArrayInputStream inputStream = new ByteArrayInputStream(config.getBytes());
			Properties properties = new Properties();
			properties.load(inputStream);
			properties.forEach((key, value) -> logger.info("nacos config : key -> {}, value -> {}", key, value));
			return properties;
		} catch (NacosException e) {
			logger.error("Read nacos error -> {}", e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("Load properties error -> {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		NacosReader.readWith("203.60.2.70:8848", "data");
	}

}
