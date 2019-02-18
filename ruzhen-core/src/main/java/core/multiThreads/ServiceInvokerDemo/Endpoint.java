package core.multiThreads.ServiceInvokerDemo;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/12/5
 * @since 1.0.0
 */
public class Endpoint {
    public final String host;
    public final int port;
    public final int weight;
    private volatile boolean online=true;

    public Endpoint(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}