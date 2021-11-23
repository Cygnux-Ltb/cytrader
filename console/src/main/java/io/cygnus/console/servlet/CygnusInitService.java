package io.cygnus.restful.service.servlet;

import static io.cygnus.restful.service.transport.OutboxPublisherGroup.GROUP_INSTANCE;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.CygInfoEntity;
import io.cygnus.restful.service.CygInfoService;
import io.cygnus.service.dto.pack.OutboxMessage;
import io.cygnus.service.dto.pack.OutboxTitle;
import io.mercury.common.datetime.pattern.spec.TimePattern;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;

@Component
public class CygnusInitService {

	private static final Logger log = CommonLoggerFactory.getLogger(CygnusInitService.class);

	@Resource
	private CygInfoService service;

	@PostConstruct
	public void init() throws ServletException {
		log.info("Cygnus Servlet init...");
		initEndTimeBinnerSender(new Date(), 1000 * 60);
	}

	public void destroy() throws IOException {
		// 关闭数据库连接
		CommonDaoFactory.closeSessionFactory();
		// 关闭OutboxPublisher连接
		var members = GROUP_INSTANCE.getKeys().toList().collect(GROUP_INSTANCE::getMember).toImmutable();
		for (Publisher<String> publisher : members) {
			publisher.close();
		}
	}

	private void initEndTimeBinnerSender(Date startTime, long period) {
		log.info("EndTimeBinnerSender init...");
		Timer endTimeBinnerSender = new Timer("EndTimeBinnerSender");
		log.info("Start Time : " + startTime);
		endTimeBinnerSender.schedule(new TimerTask() {

			private final DateFormat format = new SimpleDateFormat(TimePattern.HHMM.getPattern());

			@Override
			public void run() {
				String checkPoint = format.format(new Date());
				if (isTimeUp(checkPoint)) {
					sendEndTimeBinner();
				}
			}
		}, startTime, period);
	}

	private boolean isTimeUp(String checkPoint) {
		switch (checkPoint) {
		case "1504":
			return true;
		case "0234":
			return true;
		default:
			return false;
		}
	}

	private void sendEndTimeBinner() {

		List<CygInfoEntity> all = service.getAll();

		for (CygInfoEntity cyg : all) {
			var publisher = GROUP_INSTANCE.getMember(cyg.getCygId());

			String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.EndTimeBinner.name(), null));

			log.info("EndTimeBinner : " + msg);
			publisher.publish(msg);
		}

	}

}
