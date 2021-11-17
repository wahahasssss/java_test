package com.hdu.zookeeper_distributelock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/1
 * @Time 下午7:59
 */
public class DistributeLock implements Lock, Watcher {

    private ZooKeeper zooKeeper = null;

    private String ROOT_LOCK = "/locks";
    private String lockName;
    private String WAIT_LOCK;
    private String CURRENT_LOCK;

    private CountDownLatch countDownLatch;
    private Integer sessionTimeout = 30000;
    private List<Exception> exceptionList = new ArrayList<Exception>();

    public DistributeLock(String config, String lockName) {
        this.lockName = lockName;
        String uuid = UUID.randomUUID().toString();
        try {
            zooKeeper = new ZooKeeper(config, sessionTimeout, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (stat == null) {
                zooKeeper.create(ROOT_LOCK, uuid.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        if (exceptionList.size() > 0) {
            throw new LockException(exceptionList.get(0));
        }
        try {
            if (this.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " " + lockName + "get the lock");
                return;
            } else {
                waitForLock(WAIT_LOCK, sessionTimeout);
            }
        } catch (InterruptedException e) {
            System.out.println(String.format("lock e" + e.getMessage()));
        } catch (KeeperException e) {
            System.out.println(String.format("lock e" + e.getMessage()));
        }
    }

    private boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK + "/" + prev, true);
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + " wait for lock" + ROOT_LOCK + "/" + prev);
            this.countDownLatch = new CountDownLatch(1);
            this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
            this.countDownLatch = null;
            System.out.println(Thread.currentThread().getName() + "等到了锁");
        }
        return true;
    }

    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)) {
                throw new LockException("锁名有误");
            }
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(CURRENT_LOCK + " 已经创建");
            List<String> subNodes = zooKeeper.getChildren(ROOT_LOCK, false);
            List<String> lockObjects = new ArrayList<>();
            for (String node : subNodes) {
                String _node = node.split(splitStr)[0];
                if (_node.equals(lockName)) {
                    lockObjects.add(node);
                }
            }
            Collections.sort(lockObjects);
            System.out.println(Thread.currentThread().getName() + " 的锁是 " + CURRENT_LOCK);
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
                return true;
            }
            String preNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, preNode) - 1);
        } catch (InterruptedException e) {
            System.out.println(String.format("try lock e " + e.getMessage()));
        } catch (KeeperException e) {
            System.out.println(String.format("try lock e " + e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("try lock e " + e.getMessage()));
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(WAIT_LOCK, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void unlock() {
        try {
            System.out.println("释放锁" + CURRENT_LOCK);
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            System.out.println(String.format("unlock e " + e.getMessage()));
        } catch (KeeperException e) {
            System.out.println(String.format("unlock e " + e.getMessage()));
        } catch (Exception e) {
            System.out.println("unlock e " + e.getMessage());
        }
    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent event) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    public static class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public LockException(String e) {
            super(e);
        }

        public LockException(Exception e) {
            super(e);
        }
    }
}
