package core.multiThreads.ServiceInvokerDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin2.main.server.HeartbeatThread;

import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/12/5
 * @since 1.0.0
 */
public abstract class AbstractLoadBalancer implements LoadBalancer {
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractLoadBalancer.class);
    //使用volatile变量替代锁
    protected volatile Candidate candidate;
    protected final Random random;
    //心跳线程
    private Thread heartbeatThread;

    public AbstractLoadBalancer(Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invalid candidate " + candidate);
        }
        this.candidate = candidate;
        random = new Random();
    }

    public synchronized void init() throws Exception {
        if (null == heartbeatThread) {
            heartbeatThread = new Thread(new HeartbeatTask(), "LB_Heartbeat");
            heartbeatThread.setDaemon(true);
            heartbeatThread.start();
        }
    }

    public void updateCandidate(final Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invalid candidate " + candidate);
        }
        //更新volatile变量candidate
        this.candidate = candidate;
    }

    public abstract Endpoint nextEndpoint();

    protected void monitorEndpoints() {
        //读取volatile变量
        final Candidate currCandidate = candidate;
        boolean isTheEndpointOnline;

        //检测下游部件状态是否正常
        for (Endpoint endpoint : currCandidate) {
            isTheEndpointOnline = endpoint.isOnline();
            if (doDetect(endpoint) != isTheEndpointOnline) {
                endpoint.setOnline(!isTheEndpointOnline);
            }
        }
    }

    //检测指定的节点是否在线
    private boolean doDetect(Endpoint endpoint) {
        return true;
    }

    private class HeartbeatTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    //检测节点列表中的所有节点是否在线
                    monitorEndpoints();
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}