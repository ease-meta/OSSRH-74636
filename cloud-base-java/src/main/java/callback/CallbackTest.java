package callback;

/**
 * 回调测试，原文出处http://www.cnblogs.com/xrq730/p/6424471.html
 */
public class CallbackTest {

    public static void main(String[] args) {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);

        teacher.askQuestion();
    }

}