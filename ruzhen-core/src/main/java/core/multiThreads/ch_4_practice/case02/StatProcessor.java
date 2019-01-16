package core.multiThreads.ch_4_practice.case02;

import java.util.Map;

public interface StatProcessor {
    void process(String record);
    Map<Long, DelayItem> getResult();
}
