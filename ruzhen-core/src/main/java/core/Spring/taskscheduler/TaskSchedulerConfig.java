package core.Spring.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("core.Spring.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {
}
