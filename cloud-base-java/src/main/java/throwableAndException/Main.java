package throwableAndException;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/5 13:15
 **/
public class Main {

    public static void main(String[] args) {
        Tstep tstep = new Tstep();
        try {
            tstep.execute();
        } catch (Exception e) {
            System.out.println(123);
            e.printStackTrace();
        }
    }

    private void rethrow(Throwable t) {
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        else if (t instanceof Error) {
            throw (Error) t;
        }
        throw new IllegalStateException(t);
    }
}
