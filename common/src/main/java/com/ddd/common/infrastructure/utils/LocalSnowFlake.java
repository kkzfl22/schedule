package com.ddd.common.infrastructure.utils;

/**
 * twitter的snowflake算法 -- java实现
 * <p>
 * 仅供本地测试使用
 *
 * @author beyond
 * @date 2016/11/26
 */
public class LocalSnowFlake {

    /**
     * 起始的时间戳
     */
    private static final long START_TIMESTAMP = 1480166465631L;

    /**
     * 每一部分占用的位数 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12;

    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private static final long DATA_CENTER_BIT = 5;

    /**
     * 数据中心的最大值
     */
    public static final long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);

    /**
     * 机器编号的最大值
     */
    public static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);

    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;

    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据中心
     */
    private long dataCenterId;
    /**
     * 机器标识
     */
    private long machineId;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 数据中心的id，默认使用1，当分页式环境时，需要使用配制
     */
    private static final long DATA_CENTER_ID = 1;

    /**
     * 机器的id，默认使用1
     */
    private static final long MACHINE_ID = 1;

    /**
     * 实例信息
     */
    private static final LocalSnowFlake INSTANCE = new LocalSnowFlake(DATA_CENTER_ID, MACHINE_ID);

    /**
     * 默认的实体
     *
     * @return
     */
    public static LocalSnowFlake getDefaultInstance() {
        return INSTANCE;
    }

    public LocalSnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public String nextIdStr() {
        return String.valueOf(this.nextId());
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewTimestamp();
        if (currStmp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastTimestamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimestamp = currStmp;

        // 时间戳部分 数据中心部分 机器标识部分 序列号部分
        return (currStmp - START_TIMESTAMP) << TIMESTAMP_LEFT | dataCenterId << DATA_CENTER_LEFT | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewTimestamp();
        while (mill <= lastTimestamp) {
            mill = getNewTimestamp();
        }
        return mill;
    }

    private long getNewTimestamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        LocalSnowFlake testLocalSnowFlake = new LocalSnowFlake(1, 1);

        int maxValue = 100;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maxValue; i++) {
            System.out.println(testLocalSnowFlake.nextId());
        }
        System.out.println(testLocalSnowFlake.nextId());
        long end = System.currentTimeMillis();
        System.out.println("生成:" + maxValue + "用时:" + (end - startTime));

        System.out.println(System.currentTimeMillis());
    }
}
