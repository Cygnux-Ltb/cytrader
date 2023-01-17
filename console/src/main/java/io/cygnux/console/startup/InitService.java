package io.cygnux.console.startup;

import io.cygnux.console.service.ProductService;
import io.cygnux.console.transport.CommandDispatcher;
import io.cygnux.console.persistence.entity.ProductEntity;
import io.mercury.common.datetime.pattern.TimePattern;
import io.mercury.common.log.Log4j2LoggerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class InitService {

    private static final Logger log = Log4j2LoggerFactory.getLogger(InitService.class);

    @Resource
    private CommandDispatcher dispatcher;

    @Resource
    private ProductService service;

    @PostConstruct
    public void init() {
        log.info("Cygnus Servlet init...");
        initEndTimeBarsSender(new Date());
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


        List<ProductEntity> all = service.getAll();

//        for (ProductEntity cyg : all) {
//            var publisher = CommandDispatcher.getMember(cyg.getProductId());
//
//            String msg = JsonWrapper.toJson(new OutboxMessage<>(OutboxTitle.EndTimeBinner.name(), null));
//
//            log.info("End Time Bars : " + msg);
//            publisher.publish(msg);
//        }

    }

}
