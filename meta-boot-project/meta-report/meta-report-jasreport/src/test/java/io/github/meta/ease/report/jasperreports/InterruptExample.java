package io.github.meta.ease.report.jasperreports;

public class InterruptExample {
    public static String test03(String s1, String s2, String s3) {
        String s = s1 + s2 + s3;
        return s;
    }

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isNodeReachable(String hostname) {
        try {
            System.out.println(hostname);
            Process exec = Runtime.getRuntime().exec("ping " + hostname);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        thread1.interrupt();
        System.out.println("Main run");
    }
}