/** 任务目录 **/
DROP TABLE IF EXISTS DC_TASK_DIR;
CREATE TABLE DC_TASK_DIR
(
    TASK_DIR_ID          BIGINT(20)   NOT NULL,
    PARENT_DIR_ID        BIGINT(20),
    TASK_DIR_NAME        VARCHAR(100) NOT NULL,
    TASK_DIR_DESCRIPTION VARCHAR(100),
    TASK_ORDER           INT(6),
    CREATOR              VARCHAR(64) DEFAULT NULL,
    UPDATER              VARCHAR(64) DEFAULT NULL,
    CREATE_TIME          DATETIME    DEFAULT NULL,
    UPDATE_TIME          DATETIME    DEFAULT NULL,
    PRIMARY KEY (TASK_DIR_ID)
);

/** 任务批次信息表 **/
DROP TABLE IF EXISTS DC_BATCH_INFO;

CREATE TABLE DC_BATCH_INFO
(
    BATCH_ID          BIGINT(20)  NOT NULL,
    TASK_DIR_ID       BIGINT(20)   DEFAULT NULL,
    BATCH_NAME        VARCHAR(40) NOT NULL,
    BATCH_MSG         VARCHAR(400) DEFAULT NULL,
    BATCH_STATUS      INT(1)      NOT NULL,
    BATCH_RUN_STATUS  INT(2)      NOT NULL,
    TASK_RUNTIME_FLAG BIGINT(20),
    BATCH_CONCURRENT  INT(5)      NOT NULL,
    CREATOR           VARCHAR(64)  DEFAULT NULL,
    UPDATER           VARCHAR(64)  DEFAULT NULL,
    CREATE_TIME       DATETIME     DEFAULT NULL,
    UPDATE_TIME       DATETIME     DEFAULT NULL,
    PRIMARY KEY (BATCH_ID)
);

/** 任务主表 **/
DROP TABLE IF EXISTS DC_TASK_INFO;

CREATE TABLE DC_TASK_INFO
(
    TASK_ID     BIGINT(20)  NOT NULL,
    TASK_DIR_ID BIGINT(20)           DEFAULT NULL,
    TASK_NAME   VARCHAR(60) NOT NULL,
    TASK_TYPE   VARCHAR(20) NOT NULL,
    STATUS      INT(2)      NOT NULL DEFAULT '1',
    TASK_CFG    VARCHAR(1000)        DEFAULT NULL,
    TASK_RETRY  VARCHAR(10) NOT NULL,
    TASK_MSG    VARCHAR(400)         DEFAULT NULL,
    CREATOR     VARCHAR(64)          DEFAULT NULL,
    UPDATER     VARCHAR(64)          DEFAULT NULL,
    CREATE_TIME DATETIME             DEFAULT NULL,
    UPDATE_TIME DATETIME             DEFAULT NULL,
    PRIMARY KEY (TASK_ID)
);


/** 任务类型 **/
DROP TABLE IF EXISTS DC_TASK_TYPE;

CREATE TABLE DC_TASK_TYPE
(
    TYPE      VARCHAR(20) NOT NULL,
    TYPE_NAME VARCHAR(60) NOT NULL,
    TYPE_MSG  VARCHAR(10),
    TYPE_CFG  VARCHAR(1000),
    PRIMARY KEY (TYPE)
);


/** 批次跟任务的关联关系表 **/
DROP TABLE IF EXISTS DC_BATCH_TASK;
CREATE TABLE DC_BATCH_TASK
(
    BATCH_ID BIGINT(20) NOT NULL,
    TASK_ID  BIGINT(20) NOT NULL,
    CONSTRAINT PK_DC_BATCH_TASK_ID PRIMARY KEY (BATCH_ID, TASK_ID)
);

/** 任务关系依赖表 **/
DROP TABLE IF EXISTS DC_BATCH_TASK_DEPEND;
CREATE TABLE DC_BATCH_TASK_DEPEND
(
    BATCH_ID     BIGINT(20) NOT NULL,
    TASK_ID      BIGINT(20) NOT NULL,
    PREV_TASK_ID BIGINT(20) NOT NULL,
    CONSTRAINT PK_DC_BATCH_TASK_DEPEND_TASK_ID PRIMARY KEY (BATCH_ID, TASK_ID, PREV_TASK_ID)
);

/** 批次调度表 **/
DROP TABLE IF EXISTS DC_SCHEDULE_CRON;
CREATE TABLE DC_SCHEDULE_CRON
(
    TASK_ID        BIGINT(20)   NOT NULL,
    TASK_TYPE      INT(2)       NOT NULL,
    SCHEDULE_TYPE  VARCHAR(20),
    SCHEDULE_VALUE VARCHAR(200),
    SCHEDULE_CRON  VARCHAR(200) NOT NULL,
    UI_TIME        VARCHAR(20)  NOT NULL,
    CREATOR        VARCHAR(64) DEFAULT NULL,
    UPDATER        VARCHAR(64) DEFAULT NULL,
    CREATE_TIME    DATETIME    DEFAULT NULL,
    UPDATE_TIME    DATETIME    DEFAULT NULL,
    PRIMARY KEY (TASK_ID)
);


/** 批次日志状态表 **/
DROP TABLE IF EXISTS DC_BATCH_LOG;
CREATE TABLE DC_BATCH_LOG
(
    LOG_ID            BIGINT(20)  NOT NULL,
    BATCH_ID          BIGINT(20)  NOT NULL,
    BATCH_NAME        VARCHAR(40) NOT NULL,
    BATCH_RUN_STATUS      INT(2)      NOT NULL DEFAULT 0,
    BATCH_MSG         VARCHAR(500),
    BATCH_CONCURRENT  INT(3),
    BATCH_START_TIME  BIGINT(20),
    BATCH_FINISH_TIME BIGINT(20),
    TASK_RUNTIME_FLAG BIGINT(20),
    PRIMARY KEY (LOG_ID)
);

/** 任务日志表 **/
DROP TABLE IF EXISTS DC_TASK_LOG;
CREATE TABLE DC_TASK_LOG
(
    LOG_ID            BIGINT(20)  NOT NULL,
    BATCH_ID          BIGINT(20)  NOT NULL,
    TASK_ID           BIGINT(20)  NOT NULL,
    TASK_NAME         VARCHAR(40) NOT NULL,
    TASK_STATUS       INT(3)      NOT NULL,
    TASK_CFG          VARCHAR(1000),
    TASK_RUN_NUM      INT(5),
    TASK_MSG          VARCHAR(500),
    TASK_START_TIME   BIGINT(20)  NOT NULL,
    TASK_FINISH_TIME  BIGINT(20),
    TASK_ORDER        INT(8),
    TASK_RUNTIME_FLAG BIGINT(20)  NOT NULL,
    PRIMARY KEY (LOG_ID)
);
