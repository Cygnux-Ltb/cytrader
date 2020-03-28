package io.redstone.engine.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.common.util.Assertor;
import io.mercury.common.util.StringUtil;

public class NacosClient {

	private Properties properties;
	
	private Logger log = CommonLoggerFactory.getLogger(NacosReader.class);

	public static final NacosClient readWith(String... args) {
		Assertor.requiredLength(args, 2, "main input args");
		return new NacosClient(args[0], "flink", args[1], "data-stream");
	}

	public static final NacosClient readWithNamespace(String... args) {
		Assertor.requiredLength(args, 3, "main input args");
		return new NacosClient(args[0], args[1], args[2], "data-stream");
	}

	public static final NacosClient readWithSpecific(String... args) {
		Assertor.requiredLength(args, 4, "main input args");
		return new NacosClient(args[0], args[1], args[2], args[3]);
	}

	private NacosClient(String serverAddr, String namespace, String group, String dataId) {
		try {
			log.info("Reading nacos -> serverAddr==[{}], namespace==[{}], group==[{}], dataId==[{}]", serverAddr, group,
					dataId);
			Properties nacosParam = new Properties();
			nacosParam.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
			nacosParam.put(PropertyKeyConst.NAMESPACE, namespace);
			ConfigService configService = ConfigFactory.createConfigService(nacosParam);
			String config = configService.getConfig(dataId, group, 5000);
			if (StringUtil.isNullOrEmpty(config)) {
				log.info("Read nacos config is null or empty");
				throw new RuntimeException("read nacos error");
			}
			ByteArrayInputStream inputStream = new ByteArrayInputStream(config.getBytes());
			properties = new Properties();
			properties.load(inputStream);
			properties.forEach((key, value) -> log.info("read properties : key -> {}, value -> {}", key, value));
		} catch (NacosException e) {
			log.info("Read nacos error -> {}", e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.info("Load properties error -> {}", e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public static void main(String[] args) {
		NacosClient.readWith("203.60.2.70:8848", "data");
	}

}
