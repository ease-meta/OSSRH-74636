package io.github.meta.ease.core.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * https://www.cnblogs.com/JavaWeiBianCheng/p/12016013.html
 * <p>
 * https://blog.csdn.net/java_xiaoo/article/details/108868714
 * <p>
 * 一个Flux<T>是一个标准的Publisher<T>，表示一个异步序列，可以发射0到N个元素，可以通过一个完成信号或错误信号终止。
 * <p>
 * 就像在响应式流规范里那样，这3种类型的信号转化为对一个下游订阅者的onNext，onComplete，onError3个方法的调用。
 * <p>
 * 这3个方法也可以理解为事件/回调，且它们都是可选的。
 * <p>
 * 如没有onNext但有onComplete，表示一个空的有限序列。既没有onNext也没有onComplete，表示一个空的无限序列（没有什么实际用途，可用于测试）。
 * <p>
 * 无限序列也没有必要是空的，如Flux.interval(Duration)产生一个Flux<Long> ，它是无限的，从钟表里发射出的规则的“嘀嗒”。
 *
 * @author leijian
 * @version 1.0
 * @date 2022/1/22 21:55
 */
public class FluxDemo {

    public static void main(String[] args) {
        allUsersFlux().subscribe(System.out::println);

    }

    /**
     * Flux
     * Flux 是一个发出(emit)0-N个元素组成的异步序列的Publisher<T>,可以被onComplete信号或者onError信号所终止。在响应流规范中存在三种给下游消费者调用的方法 onNext, onComplete, 和onError。下面这张图表示了Flux的抽象模型：
     */
    //传统数据处理
    public List<ClientUser> allUsersList() {
        return Arrays.asList(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }

    public Stream<ClientUser> allUsersStream() {
        return Stream.of(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }

    //在Reactor中我们又可以改写为Flux表示
    public static Flux<ClientUser> allUsersFlux() {
        return Flux.just(new ClientUser("felord.cn", "reactive"),
                new ClientUser("Felordcn", "Reactor"));
    }

    /**
     * Mono
     * Mono 是一个发出(emit)0-1个元素的Publisher<T>,可以被onComplete信号或者onError信号所终止。
     */
    public ClientUser currentUser() {
        return new ClientUser("felord.cn", "reactive");
    }

    public Optional<ClientUser> currentUserOptional() {
        return Optional.of(new ClientUser("felord.cn", "reactive"));
    }

    public Mono<ClientUser> currentUserMono() {
        return Mono.just(new ClientUser("felord.cn", "reactive"));
    }
}
