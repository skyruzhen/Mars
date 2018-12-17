package core.multiThreads.ServiceInvokerDemo;

import jdk.nashorn.internal.ir.RuntimeNode;

/**
 * 〈一句话功能简述〉<br>
 * 〈负载均衡算法〉
 *
 * @author lizhen
 * @create 2018/12/5
 * @since 1.0.0
 */
public class ServiceInvoker {
    private static final ServiceInvoker INSTANCE=new ServiceInvoker();

    private volatile LoadBalancer loadBalancer;

    private ServiceInvoker(){

    }

    public static ServiceInvoker getInstance(){
        return INSTANCE;
    }

    public void dispatchRequest(RuntimeNode.Request request){};

    void updateCandidate(final Candidate candidate){};
    Endpoint nextEndpoint(){return null;};
}