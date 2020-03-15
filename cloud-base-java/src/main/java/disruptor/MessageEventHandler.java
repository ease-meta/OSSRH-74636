package disruptor;

import com.lmax.disruptor.WorkHandler;

public class MessageEventHandler implements WorkHandler<MessageEvent> {

    @Override
    public void onEvent(MessageEvent messageEvent) throws Exception {
        System.out.println(System.currentTimeMillis()+"------我是1号消费者----------"+messageEvent.getMessage());
    }
}