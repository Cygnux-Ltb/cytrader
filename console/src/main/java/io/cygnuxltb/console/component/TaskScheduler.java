package io.cygnuxltb.console.component;

import io.cygnuxltb.console.persistence.entity.ProductEntity;
import io.cygnuxltb.console.service.ProductService;
import io.mercury.common.datetime.pattern.TimePattern;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class TaskScheduler {

    private static final Logger log = Log4j2LoggerFactory.getLogger(TaskScheduler.class);

    @Resource
    private CommandDispatcher dispatcher;

    @Resource
    private ProductService service;

    @Scheduled(fixedRate = 20000)
    public void task() {
        String checkPoint = TimePattern.HHMM.format(LocalTime.now());
        if (isTimeUp(checkPoint)) {
            sendEndTimeBars();
        }
    }

    public boolean isTimeUp(String checkPoint) {
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
