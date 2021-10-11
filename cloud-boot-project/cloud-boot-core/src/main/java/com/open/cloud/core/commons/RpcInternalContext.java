/*

package com.open.cloud.core.commons;


import org.springframework.util.StopWatch;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

*/
/**
 * 基于ThreadLocal的内部使用的上下文传递。一般存在于：客户端请求线程、服务端业务线程池、客户端异步线程<br>
 *
 * @author <a href=mailto:zhanggeng.zg@antfin.com>GengZhang</a>
 * <p>
 * The constant LOCAL.
 * <p>
 * The constant DEQUE_LOCAL.
 * <p>
 * 设置上下文
 * @param context RPC内置上下文
 * <p>
 * 得到上下文，为空则自动创建
 * @return RPC内置上下文
 * <p>
 * 查看上下文，为空不自动创建
 * @return RPC内置上下文
 * <p>
 * 清理上下文
 * <p>
 * 上下文往下放一层（例如服务端B接到A的请求后再作为C的客户端调用，调用前这里就先把放A-B的上下文存起来）
 * <p>
 * 上下文往上取一层（例如服务端B接到A的请求后再作为C的客户端调用，调用完毕后这里就先把放A-B的上下文取起来）
 * <p>
 * 清理全部上下文
 * <p>
 * Instantiates a new Rpc context.
 * <p>
 * The Local address.
 * <p>
 * The Remote address.
 * <p>
 * 附带属性功能，遵循谁使用谁清理的原则。Key必须为 "_" 和 "."开头<br>
 * 如果关闭了 {@link #ATTACHMENT_ENABLE} 功能，"_" 开头的Key将不被保持和传递。
 * @return the boolean
 * <p>
 * Sets provider side.
 * @param isProviderSide the is provider side
 * @return the provider side
 * <p>
 * Is consumer side.
 * @return the boolean
 * <p>
 * set local address.
 * @param address the address
 * @return context local address
 * <p>
 * 本地地址InetSocketAddress
 * @return local address
 * <p>
 * set remote address.
 * @param address the address
 * @return context remote address
 * <p>
 * set remote address.
 * @param host the host
 * @param port the port
 * @return context remote address
 * <p>
 * 远程地址InetSocketAddress
 * @return remote address
 * <p>
 * get attachment.
 * @param key the key
 * @return attachment attachment
 * <p>
 * remove attachment.
 * @param key the key
 * @return Old value
 * <p>
 * get attachments.
 * @return attachments attachments
 * <p>
 * Clear attachments.
 * @return the rpc internal context
 * <p>
 * Gets stop watch.
 * @return the stop watch
 * @param context RPC内置上下文
 * <p>
 * 得到上下文，为空则自动创建
 * @return RPC内置上下文
 * <p>
 * 查看上下文，为空不自动创建
 * @return RPC内置上下文
 * <p>
 * 清理上下文
 * <p>
 * 上下文往下放一层（例如服务端B接到A的请求后再作为C的客户端调用，调用前这里就先把放A-B的上下文存起来）
 * <p>
 * 上下文往上取一层（例如服务端B接到A的请求后再作为C的客户端调用，调用完毕后这里就先把放A-B的上下文取起来）
 * <p>
 * 清理全部上下文
 * <p>
 * Instantiates a new Rpc context.
 * <p>
 * The Local address.
 * <p>
 * The Remote address.
 * <p>
 * 附带属性功能，遵循谁使用谁清理的原则。Key必须为 "_" 和 "."开头<br>
 * 如果关闭了 {@link #ATTACHMENT_ENABLE} 功能，"_" 开头的Key将不被保持和传递。
 * @return the boolean
 * <p>
 * Sets provider side.
 * @param isProviderSide the is provider side
 * @return the provider side
 * <p>
 * Is consumer side.
 * @return the boolean
 * <p>
 * set local address.
 * @param address the address
 * @return context local address
 * <p>
 * 本地地址InetSocketAddress
 * @return local address
 * <p>
 * set remote address.
 * @param address the address
 * @return context remote address
 * <p>
 * set remote address.
 * @param host the host
 * @param port the port
 * @return context remote address
 * <p>
 * 远程地址InetSocketAddress
 * @return remote address
 * <p>
 * get attachment.
 * @param key the key
 * @return attachment attachment
 * <p>
 * remove attachment.
 * @param key the key
 * @return Old value
 * <p>
 * get attachments.
 * @return attachments attachments
 * <p>
 * Clear attachments.
 * @return the rpc internal context
 * <p>
 * Gets stop watch.
 * @return the stop watch
 * @param context RPC内置上下文
 * <p>
 * 得到上下文，为空则自动创建
 * @return RPC内置上下文
 * <p>
 * 查看上下文，为空不自动创建
 * @return RPC内置上下文
 * <p>
 * 清理上下文
 * <p>
 * 上下文往下放一层（例如服务端B接到A的请求后再作为C的客户端调用，调用前这里就先把放A-B的上下文存起来）
 * <p>
 * 上下文往上取一层（例如服务端B接到A的请求后再作为C的客户端调用，调用完毕后这里就先把放A-B的上下文取起来）
 * <p>
 * 清理全部上下文
 * <p>
 * Instantiates a new Rpc context.
 * <p>
 * The Local address.
 * <p>
 * The Remote address.
 * <p>
 * 附带属性功能，遵循谁使用谁清理的原则。Key必须为 "_" 和 "."开头<br>
 * 如果关闭了 {@link #ATTACHMENT_ENABLE} 功能，"_" 开头的Key将不被保持和传递。
 * @return the boolean
 * <p>
 * Sets provider side.
 * @param isProviderSide the is provider side
 * @return the provider side
 * <p>
 * Is consumer side.
 * @return the boolean
 * <p>
 * set local address.
 * @param address the address
 * @return context local address
 * <p>
 * 本地地址InetSocketAddress
 * @return local address
 * <p>
 * set remote address.
 * @param address the address
 * @return context remote address
 * <p>
 * set remote address.
 * @param host the host
 * @param port the port
 * @return context remote address
 * <p>
 * 远程地址InetSocketAddress
 * @return remote address
 * <p>
 * get attachment.
 * @param key the key
 * @return attachment attachment
 * <p>
 * remove attachment.
 * @param key the key
 * @return Old value
 * <p>
 * get attachments.
 * @return attachments attachments
 * <p>
 * Clear attachments.
 * @return the rpc internal context
 * <p>
 * Gets stop watch.
 * @return the stop watch
 * @see #ATTACHMENT_ENABLE
 * <p>
 * The Stopwatch
 * <p>
 * The Provider side.
 * <p>
 * Is provider side.
 * <p>
 * The constant LOCAL.
 * <p>
 * The constant DEQUE_LOCAL.
 * <p>
 * 设置上下文
 * @see #ATTACHMENT_ENABLE
 * <p>
 * The Stopwatch
 * <p>
 * The Provider side.
 * <p>
 * Is provider side.
 * <p>
 * The constant LOCAL.
 * <p>
 * The constant DEQUE_LOCAL.
 * <p>
 * 设置上下文
 * @see #ATTACHMENT_ENABLE
 * <p>
 * The Stopwatch
 * <p>
 * The Provider side.
 * <p>
 * Is provider side.
 *//*

public class RpcInternalContext implements Cloneable {


    */
/**
 * The constant LOCAL.
 *//*

    private static final ThreadLocal<RpcInternalContext>        LOCAL             = new ThreadLocal<RpcInternalContext>();

    */
/**
 * The constant DEQUE_LOCAL.
 *//*

    private static final ThreadLocal<Deque<RpcInternalContext>> DEQUE_LOCAL       = new ThreadLocal<Deque<RpcInternalContext>>();

    */
/**
 * 设置上下文
 *
 * @param context RPC内置上下文
 *//*

    public static void setContext(RpcInternalContext context) {
        LOCAL.set(context);
    }

    */
/**
 * 得到上下文，为空则自动创建
 *
 * @return RPC内置上下文
 *//*

    public static RpcInternalContext getContext() {
        RpcInternalContext context = LOCAL.get();
        if (context == null) {
            context = new RpcInternalContext();
            LOCAL.set(context);
        }
        return context;
    }

    */
/**
 * 查看上下文，为空不自动创建
 *
 * @return RPC内置上下文
 *//*

    public static RpcInternalContext peekContext() {
        return LOCAL.get();
    }

    */
/**
 * 清理上下文
 *//*

    public static void removeContext() {
        LOCAL.remove();
    }

    */
/**
 * 上下文往下放一层（例如服务端B接到A的请求后再作为C的客户端调用，调用前这里就先把放A-B的上下文存起来）
 *//*

    public static void pushContext() {
        RpcInternalContext context = LOCAL.get();
        if (context != null) {
            Deque<RpcInternalContext> deque = DEQUE_LOCAL.get();
            if (deque == null) {
                deque = new ArrayDeque<RpcInternalContext>();
                DEQUE_LOCAL.set(deque);
            }
            deque.push(context);
            LOCAL.set(null);
        }
    }

    */
/**
 * 上下文往上取一层（例如服务端B接到A的请求后再作为C的客户端调用，调用完毕后这里就先把放A-B的上下文取起来）
 *//*

    public static void popContext() {
        Deque<RpcInternalContext> deque = DEQUE_LOCAL.get();
        if (deque != null) {
            RpcInternalContext context = deque.peek();
            if (context != null) {
                LOCAL.set(deque.pop());
            }
        }
    }

    */
/**
 * 清理全部上下文
 *//*

    public static void removeAllContext() {
        LOCAL.remove();
        DEQUE_LOCAL.remove();
    }


    */
/**
 * Instantiates a new Rpc context.
 *//*

    protected RpcInternalContext() {
    }



    */
/**
 * The Local address.
 *//*

    private InetSocketAddress   localAddress;

    */
/**
 * The Remote address.
 *//*

    private InetSocketAddress   remoteAddress;

    */
/**
 * 附带属性功能，遵循谁使用谁清理的原则。Key必须为 "_" 和 "."开头<br>
 * 如果关闭了 {@link #ATTACHMENT_ENABLE} 功能，"_" 开头的Key将不被保持和传递。
 *
 * @see #ATTACHMENT_ENABLE
 *//*

    private Map<String, Object> attachments = new ConcurrentHashMap<String, Object>();

    */
/**
 * The Stopwatch
 *//*

    private StopWatch stopWatch   = new StopWatch();

    */
/**
 * The Provider side.
 *//*

    private Boolean             providerSide;



    */
/**
 * Is provider side.
 *
 * @return the boolean
 *//*

    public boolean isProviderSide() {
        return providerSide != null && providerSide;
    }

    */
/**
 * Sets provider side.
 *
 * @param isProviderSide the is provider side
 * @return the provider side
 *//*

    public RpcInternalContext setProviderSide(Boolean isProviderSide) {
        this.providerSide = isProviderSide;
        return this;
    }

    */
/**
 * Is consumer side.
 *
 * @return the boolean
 *//*

    public boolean isConsumerSide() {
        return providerSide != null && !providerSide;
    }





    */
/**
 * set local address.
 *
 * @param address the address
 * @return context local address
 *//*

    public RpcInternalContext setLocalAddress(InetSocketAddress address) {
        this.localAddress = address;
        return this;
    }



    */
/**
 * 本地地址InetSocketAddress
 *
 * @return local address
 *//*

    public InetSocketAddress getLocalAddress() {
        return localAddress;
    }

    */
/**
 * set remote address.
 *
 * @param address the address
 * @return context remote address
 *//*

    public RpcInternalContext setRemoteAddress(InetSocketAddress address) {
        this.remoteAddress = address;
        return this;
    }

    */
/**
 * set remote address.
 *
 * @param host the host
 * @param port the port
 * @return context remote address
 *//*

    public RpcInternalContext setRemoteAddress(String host, int port) {
        if (host == null) {
            return this;
        }
        if (port < 0 || port > 0xFFFF) {
            port = 0;
        }
        // 提前检查是否为空，防止createUnresolved抛出异常，损耗性能
        this.remoteAddress = InetSocketAddress.createUnresolved(host, port);
        return this;
    }

    */
/**
 * 远程地址InetSocketAddress
 *
 * @return remote address
 *//*

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }


    */
/**
 * get attachment.
 *
 * @param key the key
 * @return attachment attachment
 *//*

    public Object getAttachment(String key) {
        return key == null ? null : attachments.get(key);
    }



    */
/**
 * remove attachment.
 *
 * @param key the key
 * @return Old value
 *//*

    public Object removeAttachment(String key) {
        return attachments.remove(key);
    }

    */
/**
 * get attachments.
 *
 * @return attachments attachments
 *//*

    public Map<String, Object> getAttachments() {
        return attachments;
    }



    */
/**
 * Clear attachments.
 *
 * @return the rpc internal context
 *//*

    public RpcInternalContext clearAttachments() {
        if (attachments != null && attachments.size() > 0) {
            attachments.clear();
        }
        return this;
    }

    */
/**
 * Gets stop watch.
 *
 * @return the stop watch
 *//*

    public StopWatch getStopWatch() {
        return stopWatch;
    }







    @Override
    public RpcInternalContext clone() {
        try {
            return (RpcInternalContext) super.clone();
        } catch (Exception e) {
            RpcInternalContext context = new RpcInternalContext();

            context.localAddress = this.localAddress;
            context.remoteAddress = this.remoteAddress;
            context.stopWatch = this.stopWatch.clone();
            context.providerSide = this.providerSide;

            context.attachments.putAll(this.attachments);
            return context;
        }
    }


}*/
