package com.mes.kanban.utils;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程并行执行器
 */
public final class ParallelRunner {
    private static final Logger logger = LoggerFactory.getLogger(ParallelRunner.class);

    /**
     * 线程池（一个线程空闲60分钟回收）
     */
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    /**
     * 创建多线程并行执行器
     *
     * @param taskCount 任务数
     */
    public static ParallelRunner create(int taskCount) {
        return new ParallelRunner(taskCount);
    }

    /**
     * 线程任务数执行计数器
     */
    private final CountDownLatch countDownLatch;

    /**
     * 创建多线程并行执行器
     *
     * @param taskCount 任务数
     */
    private ParallelRunner(int taskCount) {
        this.countDownLatch = new CountDownLatch(taskCount);
    }

    /**
     * 切换数据源执行任务（如果多线程不切换数据源会默认使用master数据源）
     *
     * @param DS       数据源
     * @param runnable 多线程任务
     */
    public void execute(String DS, Runnable runnable) {
        EXECUTOR_SERVICE.execute(() -> {
            try {
                DynamicDataSourceContextHolder.push(DS);
                runnable.run();
            } catch (Exception e) {
                logger.error("parallel task run error", e);
            } finally {
                //执行结束任务计数器减一
                countDownLatch.countDown();
            }
        });
    }

    /**
     * 当前线程执行任务等待所有任务执行完
     *
     * @param runnable 多线程任务
     */
    public void executeAndAwait(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            logger.error("parallel task run error", e);
        } finally {
            //执行结束任务计数器减一
            countDownLatch.countDown();
        }
        //等待
        await();
    }

    /**
     * 等待所有任务执行完（任务计数器减到0）
     */
    private void await() {
        try {
            countDownLatch.await();
        } catch (Exception e) {
            logger.error("parallel task await error", e);
        }
    }
}
