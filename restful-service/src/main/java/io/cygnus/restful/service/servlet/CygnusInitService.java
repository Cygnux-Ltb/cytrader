package io.cygnus.restful.service.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;

import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.service.CygInfoService;
import io.cygnus.restful.service.transport.OutboxPublisherGroup;
import io.cygnus.service.dto.pack.OutboxMessage;
import io.cygnus.service.dto.pack.OutboxTitle;
import io.mercury.common.character.Charsets;
import io.mercury.common.datetime.pattern.spec.TimePattern;
import io.mercury.common.log.CommonLoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import io.mercury.transport.api.Publisher;

@Component
public class CygnusInitService {

	private static final Logger log = CommonLoggerFactory.getLogger(CygnusInitService.class);

	@PostConstruct
	public void init() throws ServletException {
		log.info("Cygnus Servlet init...");
		initEndTimeBinnerSender(new Date(), 1000 * 60);
	}

	public void destroy() {
		// 关闭数据库连接
		CommonDaoFactory.closeSessionFactory();
		// 关闭OutboxPublisher连接
		ImmutableList<Publisher<byte[]>> members = OutboxPublisherGroup.INSTANCE.getMemberList();
		for (Publisher<byte[]> publisher : members) {
			publisher.destroy();
		}
	}

	private void initEndTimeBinnerSender(Date startTime, long period) {
		log.info("EndTimeBinnerSender init...");
		Timer endTimeBinnerSender = new Timer("EndTimeBinnerSender");
		log.info("Start Time : " + startTime);
		endTimeBinnerSender.schedule(new TimerTask() {
			DateFormat format = new SimpleDateFormat(TimePattern.HHMM.getPattern());

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

		CygInfoService dao = new CygInfoService();
		List<Integer> cygIdList = dao.getAllCygId();

		for (Integer cygId : cygIdList) {
			Publisher<byte[]> publisher = OutboxPublisherGroup.INSTANCE.acquireMember(cygId);

			String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.EndTimeBinner.name(), null));

			log.info("EndTimeBinner : " + msg);

			publisher.publish(msg.getBytes(Charsets.UTF8));
		}

	}

}
