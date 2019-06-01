package listener;

public class TestListener {

    public static void main(String[] args) {
        Person person = new Person();
        person.registerListener(new MyPersonListener());
        person.run();
        person.eat();
    }
}

//实现监听器接口中的方法
class MyPersonListener implements PersonListener {

    @Override
    public void dorun(Even even) {
        //拿到事件源
        Person person = even.getPerson();
        System.out.println("人在跑之前执行的动作");
    }

    @Override
    public void doeat(Even even) {
        System.out.println("人在吃之前执行的动作");
    }


}