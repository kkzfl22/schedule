INSERT INTO DC_TASK_DIR(TASK_DIR_ID, PARENT_DIR_ID, TASK_DIR_NAME, TASK_DIR_DESCRIPTION, TASK_ORDER,
                        CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES ('1000', '0', '测试工程', '这是一个测试的批次任务', '0', 'admin', '', NULL, NULL);



INSERT INTO DC_BATCH_INFO(BATCH_ID, TASK_DIR_ID, BATCH_NAME, BATCH_MSG, BATCH_STATUS, BATCH_RUN_STATUS,
                          TASK_RUNTIME_FLAG, BATCH_CONCURRENT, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1001, 1000, '测试批次1', '测试批次信息', 1, 0, NULL, 2, 'admin', NULL, NULL, NULL);



INSERT INTO DC_TASK_TYPE(TYPE, TYPE_NAME, TYPE_MSG, TYPE_CFG)
VALUES ('test', '测试的任务', 'testCfg', '{testCfg:baseInfo}');



INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1001, 1000, '任务执行A', 'test', 1, '{testA:test}', '2', '重试3次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1002, 1000, '任务执行B', 'test', 1, '{testB:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1003, 1000, '任务执行C', 'test', 1, '{testC:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1004, 1000, '任务执行D', 'test', 1, '{testD:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1005, 1000, '任务执行E', 'test', 1, '{testE:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1006, 1000, '任务执行F', 'test', 1, '{testF:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1007, 1000, '任务执行G', 'test', 1, '{testG:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);
INSERT INTO DC_TASK_INFO(TASK_ID, TASK_DIR_ID, TASK_NAME, TASK_TYPE, STATUS, TASK_CFG,
                         TASK_RETRY, TASK_MSG, CREATOR, UPDATER, CREATE_TIME, UPDATE_TIME)
VALUES (1008, 1000, '任务执行H', 'test', 1, '{testH:test3}', '2', '重试2次的任务', 'admin', NULL, NULL, NULL);



INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1001');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1002');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1003');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1004');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1005');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1006');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1007');
INSERT INTO DC_BATCH_TASK(BATCH_ID, TASK_ID)
VALUES ('1001', '1008');



INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1001, 1002);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1001, 1003);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1001, 1004);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1002, 1006);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1003, 1005);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1004, 1005);
INSERT INTO DC_BATCH_TASK_DEPEND(BATCH_ID, TASK_ID, PREV_TASK_ID)
VALUES (1001, 1005, 1006);
