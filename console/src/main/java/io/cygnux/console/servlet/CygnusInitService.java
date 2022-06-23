package io.cygnux.console.servlet;

import io.cygnux.console.service.ProductService;
import io.cygnux.console.service.dto.pack.OutboxMessage;
import io.cygnux.console.service.dto.pack.OutboxTitle;
import io.cygnux.console.transport.OutboxPublisherGroup;
import io.cygnux.repository.db.CommonDaoFactory;
import io.cygnux.repository.entities.ItProduct;
import io.mercury.common.datetime.pattern.TimePattern;
import io.mercury.common.log.Log4j2LoggerFactory;
import io.mercury.serialization.json.JsonWrapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class CygnusInitService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(CygnusInitService.class);

    @Resource
    private ProductService service;

    @PostConstruct
    public void init() {
        log.info("Cygnus Servlet init...");
        initEndTimeBarsSender(new Date());
    }

    public void destroy() throws IOException {
        // 关闭数据库连接
        CommonDaoFactory.closeSessionFactory();
        // 关闭OutboxPublisher连接
        var members = OutboxPublisherGroup.GROUP_INSTANCE.getKeys().toList().collect(OutboxPublisherGroup.GROUP_INSTANCE::getMember).toImmutable();
        for (var member : members) {
            member.close();
        }
    }

    private void initEndTimeBarsSender(Date startTime) {
        log.info("EndTimeBarsSender init...");
        var timer = new Timer("EndTimeBarsSender");
        log.info("Start Time : " + startTime);
        timer.schedule(new TimerTask() {

            private final DateFormat format = new SimpleDateFormat(TimePattern.HHMM.getPattern());

            @Override
            public void run() {
                String checkPoint = format.format(new Date());
                if (isTimeUp(checkPoint)) {
                    sendEndTimeBars();
                }
            }

        }, startTime, 1000 * 60);
    }

    private boolean isTimeUp(String checkPoint) {
        return switch (checkPoint) {
            case "1504", "0234" -> true;
            default -> false;
        };
    }

    private void sendEndTimeBars() {

        List<ItProduct> all = service.getAll();

        for (ItProduct cyg : all) {
            var publisher = OutboxPublisherGroup.GROUP_INSTANCE.getMember(cyg.getProductId());

            String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.EndTimeBinner.name(), null));

            log.info("End Time Bars : " + msg);
            publisher.publish(msg);
        }

    }

}
