地址：http://rocketmq.apache.org/release_notes/release-notes-4.2.0/

ROCKETMQ_HOME=D:\work\rocketmq-all-4.2.0-bin-release
path=%ROCKETMQ_HOME%\bin;
start mqnamesrv.cmd

start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true

插件https://github.com/apache/rocketmq-externals.git
下载完成之后，进入‘rocketmq-externals\rocketmq-console\src\main\resources’文件夹，打开‘application.properties’进行配置。
进入‘\rocketmq-externals\rocketmq-console’文件夹，执行‘mvn clean package -Dmaven.test.skip=true’，编译生成。
编译成功之后，Cmd进入‘target’文件夹，执行‘java -jar rocketmq-console-ng-1.0.0.jar’，启动‘rocketmq-console-ng-1.0.0.jar’。


nohup ./bin/mqnamesrv &

nohup ./mqbroker -n 127.0.0.1:9876 autoCreateTopicEnable=true >mqbroker.out &

参考资料:https://www.jianshu.com/p/2838890f3284











































Producer：生产者，一个生产者发送业务应用系统生成的数据给Brokers（经纪人）。RocketMQ提供多范式发送：同步，异步，一站式。

Producer Group:生产者组，是将同样角色生产者的分组在一起。同一生产组的不同生产者实例都会被Broker经纪人联络告知提交或者回滚事务，以避免事务后源生产者崩溃。

注:考虑到提供的生产者在发送消息时足够给力时，一个生产者组仅允许一个生产者实例，以避免不必要的生产者实例初始化。

Consumer：消费者，从Brokers经纪人处拉取信息并填充到应用中。从用户应用的角度看，有两种类型的消费者：

PullConsumer：主动从brokers经纪人处拉取消息。一旦拉取到批量的数据，用户应用的消费进程初始化。
PushConsumer：封装消息拉取、消费进程和内部其他工作维护，留下一个回调接口让用户实现，当消息到达时即可执行用户实现逻辑。
Consumer Group：消费组，同前面提到的生产者组类似，把同样角色的消费乾分组到一起即消费者组。消费者组是实现负载均衡目标和容错目标的一个重要概念。就信息消费而言，超级easy。

注：一个消费组的所有消费者实例必须拥有相同的topic主题描述。

Topic：主题，是生产者发送的消息和消费者拉取的消息的规类。Topic与生产者和消费者都是非常松散的关系，一个topic可以有0个或者1个或者多个生产者向其发送消息，换句话说，一个生产者可以同时向不同和topic发送消息。从消费者的解度来说，一个topic可能被0个或者一个或者多个消费组订阅，类似的，一个消费组可以订阅一个或者多个主题只要这个消费组的实例保持他们的订阅一致。

Message：消息，要传输的信息。一个message必须有一个主题，主题可以看做是你的信件要邮寄的地址。一个消息也可以拥有一个可选的tag和额处的键值对。如你可能需要给你的message设置一个业务key和要boker服务上查找此message，以便在开发期间查找问题。

Message Queue：消息队列，一个主题被化分为一个或者多个子主题（sub-topics），“消息队列”.

Tag：标签，换而言之为子主题，为用户提供额外的灵活性。使用tag，同一业务模块不同目的的messages就可以用相同topic不同tag来标识。Tags有益于保持你的代码干净而条理清晰，同时促进使用RocketMQ提供的查询系统的效率。

Broker：经纪人，为RocketMQ系统的一个主要组件。它接收发送自生产者的messages，存储，然后准备处理来处消费者的拉取message请求。它也会存储message相关的元数据，包括消费组，消费进程偏移量和主题/队列相关信息。

Name Server：名字服务器，扮演远程信息提供者的角色。用于生产者消费者通过主题查找对应的broker列表。

Message Model：消息模型，两种：

Clustering：集群
Broadcasting：广播
Message Order：当使用DefaultMQPushConsumer时，你需要确定消费消息的方式：

Orderly：顺序地消费消息即表示消费的消息顺序同生产者发送的顺序一致。
Concurrently：并行消费。指定此方式消费，信息消费的最大并行数量仅受限于每个消费者客户端指定的线程池。
注：指定orderly时，最大并行消费数量由消费组订阅的消息队列的数量决定。