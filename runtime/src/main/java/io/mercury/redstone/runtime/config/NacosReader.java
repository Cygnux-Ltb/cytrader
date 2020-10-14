package io.mercury.redstone.runtime.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import io.mercury.common.log.CommonLoggerFactory;
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

	private static final Logger log = CommonLoggerFactory.getLogger(NacosReader.class);

	public static final Properties readProperties(String serverAddr, String group, String dataId)
			throws NacosReadException {
		return connection(serverAddr).getProperties(group, dataId);
	}

	public static final Properties readProperties(String serverAddr, String namespace, String group, String dataId)
			throws NacosReadException {
		return connection(serverAddr, namespace).getProperties(group, dataId);
	}

	public static final String readSaved(String serverAddr, String group, String dataId) throws NacosReadException {
		return connection(serverAddr).getSaved(group, dataId);
	}

	public static final String readSaved(String serverAddr, String namespace, String group, String dataId)
			throws NacosReadException {
		return connection(serverAddr, namespace).getSaved(group, dataId);
	}

	public static final NacosReader connection(String serverAddr) throws NacosReadException {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
		return new NacosReader(properties);
	}

	public static final NacosReader connection(String serverAddr, String namespace) throws NacosReadException {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
		properties.put(PropertyKeyConst.NAMESPACE, namespace);
		return new NacosReader(properties);
	}

	private NacosReader(Properties nacosProperties) throws NacosReadException {
		try {
			log.info("Connection ConfigService -> serverAddr==[{}]", nacosProperties.get(PropertyKeyConst.SERVER_ADDR));
			log.info("Connection ConfigService -> namespace==[{}]", nacosProperties.get(PropertyKeyConst.NAMESPACE));
			this.configService = ConfigFactory.createConfigService(nacosProperties);
		} catch (NacosException e) {
			log.error("createConfigService error -> {}", e.getMessage(), e);
			throw new NacosReadException("ConfigFactory.createConfigService call error", e);
		}
	}

	public String getSaved(String group, String dataId) throws NacosReadException {
		try {
			log.info("Reading nacos ->  group==[{}], dataId==[{}]", group, dataId);
			String saved = configService.getConfig(dataId, group, 8000);
			if (StringUtil.isNullOrEmpty(saved)) {
				log.info("Read nacos config is null or empty");
				throw new NacosReadException("Read nacos config is null or empty");
			}
			return saved;
		} catch (NacosException e) {
			log.error("Read nacos error -> {}", e.getMessage());
			throw new NacosReadException("ConfigService.getConfig call error", e);
		}
	}

	public Properties getProperties(String group, String dataId) throws NacosReadException {
		try {
			log.info("Reading nacos ->  group==[{}], dataId==[{}]", group, dataId);
			String saved = configService.getConfig(dataId, group, 8000);
			if (StringUtil.isNullOrEmpty(saved)) {
				log.info("Read nacos config is null or empty");
				throw new NacosReadException("Read nacos config is null or empty");
			}
			try (ByteArrayInputStream inputStream = new ByteArrayInputStream(saved.getBytes())) {
				Properties properties = new Properties();
				properties.load(inputStream);
				properties.forEach((key, value) -> log.info("print nacos config : key -> {}, value -> {}", key, value));
				return properties;
			}
		} catch (NacosException e) {
			log.error("Read nacos error -> {}", e.getMessage());
			throw new NacosReadException("ConfigService.getConfig call error", e);
		} catch (IOException e) {
			log.error("Throw exception -> {}", e.getMessage());
			throw new NacosReadException(e);
		}
	}

	public static void main(String[] args) {

	}

}
