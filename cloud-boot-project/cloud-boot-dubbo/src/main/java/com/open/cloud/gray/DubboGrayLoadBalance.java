package com.open.cloud.gray;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @author Leijian
 * @date 2020/4/20
 * 灰度修改服务的loadBalance为
 * loadbalance
 */
public class DubboGrayLoadBalance implements LoadBalance,GrayApi {

	@Override
	public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
		//
		return null;
	}
}
