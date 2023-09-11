package edu.hnu.trade.scheduled;

import edu.hnu.trade.service.LedgerService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class MyTask implements SchedulingConfigurer {

  @Autowired
  LedgerService ledgerService;

  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
    scheduledTaskRegistrar.addTriggerTask(() -> process(),
        triggerContext -> {
          return new CronTrigger("0 0 0 * * ?").nextExecutionTime(triggerContext);
        });
  }

  private void process() {
    ledgerService.updateData(LocalDateTime.now());
  }
}
