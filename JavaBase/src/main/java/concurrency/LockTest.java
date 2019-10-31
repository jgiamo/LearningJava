package concurrency;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        synchronized (LockTest.class) {
        }

    }
}
