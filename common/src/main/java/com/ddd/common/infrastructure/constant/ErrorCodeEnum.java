package com.ddd.common.infrastructure.constant;

/**
 * 错误码信息枚举
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/08/27
 */
public enum ErrorCodeEnum {
    /**
     * 成功的错误码信息
     */
    SUCCESS(0, "操作成功"),

    FAIL(-1, "操作失败,请联系管理员"),

    ERROR(-11, "系统错误,请联系管理员"),

    NOT_EXISTS(-100, "参数不存在"),

    TYPE_ERROR(-110, "参数类型错误"),

    NULL_ERROR(-120, "参数为空"),

    VALUE_ERROR(-130, "参数值错误"),

    BOOLEAN_ERROR(-131, "布尔类型值错误"),

    DECIMAL_ERROR(-132, "小数值错误"),

    VALUE_MINUS_ERROR(-133, "数值类型不能为负数"),

    SAVE_CLUSTER_ERROR(-200, "集群信息保存失败"),

    SAVE_CLUSTER_ENTITY_ERROR(-201, "集群信息保存失败"),

    SAVE_CLUSTER_USER_ERROR(-202, "集群和用户关系保存失败"),

    SAVE_CLUSTER_PROPERTY_VALUE_ERROR(-203, "集群参数信息设置失败"),

    QUERY_CLUSTER_ERROR(-204, "集群列表查询失败"),

    QUERY_CLUSTER_BUILD_PAGE_ERROR(-205, "构建集群分页信息失败"),

    QUERY_CLUSTER_INFO_ERROR(-206, "查看集群信息失败"),

    QUERY_CLUSTER_TYPE_LIST_ERROR(-207, "查看集群信息失败"),

    QUERY_ENTITY_USER_ERROR(-208, "查看实体用户失败"),

    QUERY_ENTITY_PAGE_LIST_ERROR(-209, "集群实体分页查询失败"),

    CLUSTER_NAME_EMPTY(-210, "集群名称不能为空"),

    CLUSTER_TYPE_EMPTY(-211, "集群类型不能为空"),

    CDH_CLUSTER_NAME_EMPTY(-212, "集群集群名称不能为空"),

    CM_URL_EMPTY(-213, "CM地址不能为空"),

    CM_USER_EMPTY(-214, "CM用户名不能为空"),

    CM_USER_PASSWORD_EMPTY(-215, "CM用户密码不能为空"),

    QUERY_ENTITY_ERROR(-250, "查询单个实体信息失败"),

    QUERY_ENTITY_LIST_BY_PARENT_ERROR(-251, "通过父级查询实体列表信息失败"),

    QUERY_ENTITY_TRUE_ERROR(-252, "查询实体树信息失败"),

    QUERY_ENTITY_LIST_ERROR(-253, "集群实体集合查询失败"),

    QUERY_NO_BINDING_ENTITY_LIST_ERROR(-254, "查询未绑定的实体列表信息失败"),

    QUERY_ENTITY_LIST_BY_ETTYPE_ERROR(-255, "通过实体类型查询实体列表信息失败"),

    QUERY_ENTITY_LIST_BY_IDS_ERROR(-256, "通过ID集合查询实体列表信息失败"),

    CHANGE_ENTITY_ERROR(-257, "选择实体信息失败"),

    CHANGE_TENANT_ERROR(-258, "选择集群信息失败"),

    CHANGE_CLUSTER_ERROR(-259, "选择租户信息失败"),

    CHANGE_PROJECT_ERROR(-260, "选择租户信息失败"),

    DDD_SYSTEM_USER_ERROR(-261, "添加操作系统用户失败"),

    MODIFY_SYSTEM_USER_ERROR(-262, "修改操作系统用户失败"),

    DELETE_SYSTEM_USER_ERROR(-263, "删除操作系统用户失败"),

    SAVE_USER_ERROR(-300, "用户信息保存失败"),

    SAVE_USER_ROLE_ERROR(-301, "用户和角色信息保存失败"),

    SAVE_ENTITY_USER_ERROR(-302, "实体和用户信息保存失败"),

    QUERY_NO_BINDING_USER_LIST_ERROR(-303, "查询未绑定的用户失败"),

    QUERY_USER_ERROR(-304, "查询用户信息失败"),

    QUERY_USER_ROLE_ERROR(-305, "查询用户角色信息失败"),

    CHANGE_ROLE_INFO_ERROR(-306, "请选择角色信息"),

    SAVE_USER_EXIST(-307, "该用户已存在！"),

    USER_CURRENTPASSWORD_ERROR(-308, "用户原始密码输入错误,请重新输入！"),

    SAVE_USERNAME_NULL(-309, "用户名为空！"),

    ROLE_NULL_ERROR(-400, "角色参数为空"),

    QUER_ROLE_ERROR(-401, "查询单个角色信息失败"),

    QUER_ROLE_LIST_ERROR(-402, "查询角色列表信息失败"),

    EXISTS_ROLE_ERROR(-403, "角色已经存在"),

    URL_PARAM_ERROR(-404, "地址格式不正确,请重新输入"),

    LOGIN_USER_PASSWORD_IS_NULL_ERROR(-600, "用户不存在"),

    LOGIN_USER_PASSWORD_ERROR(-601, "密码错误"),

    LOGIN_FAIL(-602, "登录失败,用户名或者密码错误"),

    QUERY_DATACONF_ERROR(-700, "数据源列表查询失败"),

    ADD_DATACONF_ERROR(-701, "添加数据源失败"),

    ADD_DATACONF_EXISTS(-701, "添加数据源名称已存在"),

    EDIT_DATACONF_ERROR(-702, "编辑数据源失败"),

    INFO_DATACONF_ERROR(-703, "查看数据源失败"),

    DELETE_DATACONF_ERROR(-704, "删除数据源失败"),

    DATACONF_EXISTS_JOBCONF_ERROR(-705, "当前数据源存在任务,请检查"),

    DATACONF_IMPORT_REMARK_INFO_ERROR(-706, "导入数据源表，类备注信息失败,请检查"),

    DATACONF_CHECK_DB_CONNECTION_ERROR(-707, "数据源测试连接失败，请检查"),

    DATACONF_IMPORT_DATA_IS_NULL_ERROR(-708, "导入文件格式或信息错误，请检查"),

    DATACONF_SYNCHING_ERROR(-709, "数据源在同步中,请稍后操作"),

    ADD_DATACONF_LINK_EXISTS(-710, "添加数据源链接已存在"),

    ADD_HDFS_SERVICE_EXISTS(-711, "添加HDFSSERVER服务名称已存在"),

    ADD_HDFS_SERVICE_ERROR(-712, "添加HDFSSERVICE服务失败"),

    EDIT_HDFS_SERVICE_ERROR(-713, "编辑HDFSSERVICE服务失败"),

    ADD_HDFS_SERVICE_CONNNECT_EXISTS(-714, "HDFS服务连接不上，请检查连接地址"),

    ADD_HBASE_SERVICE_CONNNECT_EXISTS(-715, "HBASE服务连接不上，请检查连接地址"),

    TARGET_DATASOURCE_EMPTY(-720, "数据源不能为空"),

    TARGET_SCHEMA_EMPTY(-721, "数据源Schema不能为空"),

    SRC_DATASOURCE_EMPTY(-722, "关联源不能为空"),

    SRC_SCHEMA_EMPTY(-723, "关联源Schema不能为空"),


    QUERY_JOBCONF_ERROR(-800, "任务配置列表查询失败"),

    ADD_JOBCONF_ERROR(-801, "添加任务配置失败"),

    ADD_JOBCONF_EXISTS(-801, "添加任务配置已存在"),

    EDIT_JOBCONF_ERROR(-802, "编辑任务配置失败"),

    INFO_JOBCONF_ERROR(-803, "查看任务配置失败"),

    DELETE_JOBCONF_ERROR(-804, "删除任务配置失败"),

    TASK_NOT_EXIST(-805, "任务不存在"),

    TASK_HAS_USED(-806, "任务在其它批次中被关联"),

    ENTITY_NOT_EXIST(-807, "编辑或删除的实体不存在，请确认"),

    NAME_EXIST(-808, "名字不能重复"),

    TASK_DIR_EMPTY(-809, "任务所属目录不能为空"),

    QUERY_BATCHCONF_ERROR(-900, "批次管理列表查询失败"),

    ADD_BATCHCONF_ERROR(-901, "添加批次管理失败"),

    ADD_BATCHCONF_EXISTS(-901, "添加批次管理已存在"),

    EDIT_BATCHCONF_ERROR(-902, "编辑批次管理失败"),

    INFO_BATCHCONF_ERROR(-903, "查看批次管理失败"),

    DELETE_BATCHCONF_ERROR(-904, "删除批次管理失败"),

    CHANGE_TABLE_ERROR(-905, "请选择需要创建的表名"),

    CRON_TIME_ERROR(-906, "时间格式错误"),

    CYCLE_EXISTS_ERROR(-907, "批次任务中存在环"),

    QUERY_BATCHSTATUS_LIST_ERROR(-908, "批次状态列表查询失败"),

    QUERY_TASK_LIST_ERROR(-909, "任务日志列表查询失败"),

    REMOVE_JOB_ERROR(-910, "job删除失败"),

    SYNCH_BATCHCONF_ERROR(-911, "同步批次任务失败"),

    DB_TYPE_ERROR(-1000, "数据库类型不支持"),
    JDBC_CON_ERROR(-1001, "数据库连接失败,请检查连接信息是否正确或者schema是否存在!"),
    CREATE_HIVE_TABLE_ERROR(-1002, "hive表创建失败"),
    ADD_USER_ERROR(-1003, "添加用户失败"),
    ORIGINAL_PASSWORD_NULL(-1004, "原密码不能为空"),
    NEW_PASSWORD_NULL(-1005, "新密码不能为空"),
    PASSWORD_NULL(-1006, "密码不能为空"),
    SESSION_USER_NULL(-1007, "Session中用户为空"),
    ORIGINAL_PASSWORD_ERROR(-1008, "原密码错误"),


    QUERY_METADATATABLE_ERROR(-1100, "元数据表管理列表查询失败"),
    ADD_METADATATABLE_ERROR(-1101, "添加元数据表管理失败"),
    EDIT_METADATATABLE_ERROR(-1102, "编辑元数据表管理失败"),
    INFO_METADATATABLE_ERROR(-1103, "查看元数据表管理失败"),
    DELETE_METADATATABLE_ERROR(-1104, "删除元数据表管理失败"),
    SYNCH_METADATATABLE_ERROR(-1105, "元数据表同步失败"),
    SYNCH_NOT_CONNECTION_ERROR(-1106, "数据源连接失败"),

    QUERY_METADATACOLUMN_ERROR(-1200, "元数据列管理列表查询失败"),
    ADD_METADATACOLUMN_ERROR(-1201, "添加元数据列管理失败"),
    EDIT_METADATACOLUMN_ERROR(-1202, "编辑元数据列管理失败"),
    INFO_METADATACOLUMN_ERROR(-1203, "查看元数据列管理失败"),
    DELETE_METADATACOLUMN_ERROR(-1204, "删除元数据列管理失败"),

    QUERY_GOVROLE_ERROR(-1300, "角色管理列表查询失败"),
    ADD_GOVROLE_ERROR(-1301, "添加角色管理失败"),
    EDIT_GOVROLE_ERROR(-1302, "编辑角色管理失败"),
    INFO_GOVROLE_ERROR(-1303, "查看角色管理失败"),
    DELETE_GOVROLE_ERROR(-1304, "删除角色管理失败"),
    GOVROLE_EXISTS_ERROR(-1305, "当前角色名称已经存在"),
    QUERY_USER_SENTRY_ERROR(-1306, "查看userSentry失败"),
    SYNCH_ROLE_SENTRY_PRIVILEGE_ERROR(-1307, "SENTRY权限同步中"),
    SYNCH_CONNECTION_ADDRESS_DOES_NOT_EXIST(-1308, "连接地址不存在"),
    METASTORE_NAME_EXIST(-1309, "metaStore信息已经存在,请检查"),

    QUERY_GOVPRIVILEGE_ERROR(-1400, "权限管理列表查询失败"),
    BINDING_GOVPRIVILEGE_ERROR(-1401, "表绑定角色失败"),
    SYNCH_GOVPRIVILEGE_ERROR(-1402, "表权限同步失败"),

    DELETE_GOV_ROLE_PRIVILEGE_ERROR(-1500, "删除sentry角色配置信息失败"),
    QUERY_GOV_ROLE_PRIVILEGE_ERROR(-1501, "查询sentry角色配置信息失败"),
    SAVE_GOV_ROLE_PRIVILEGE_ERROR(-1502, "保存sentry角色配置信息失败"),

    ADAPTATION_TABLE_DB_NOT_EXIST_ERROR(-1600, "数据源信息不存在"),
    ADAPTATION_TABLE_SCHEMA_NOT_EXIST_ERROR(-1601, "元数据库信息不存在"),
    ADAPTATION_TABLE_NOT_EXIST_ERROR(-1602, "适配表信息不存在"),
    ADAPTATION_TABLE_IMPROT_ERROR(-1603, "导入表信息失败"),
    ADAPTATION_TABLE_LIST_EXIST_ERROR(-1604, "接入的表在数据库已经存在"),

    ADAPTATION_SOURCE_DB_NOT_EXIST_ERROR(-1605, "源数据源不存在"),
    ADAPTATION_TARGET_DB_NOT_EXIST_ERROR(-1606, "目标数据源不存在"),
    ADAPTATION_SOURCE_SCHEMA_NOT_EXIST_ERROR(-1607, "源数据库不存在"),
    ADAPTATION_TARGET_SCHEMA_NOT_EXIST_ERROR(-1608, "目标数据库不存在"),

    ADD_SERVICE_EXIST_ERROR(-1609, "服务名已经存在"),

    ADD_SERVICE_ERROR(-1610, "添加服务错误"),
    EDIT_SERVICE_ERROR(-1611, "编辑服务错误"),
    POLICY_EXIST_ERROR(-1612, "策略已经存在,请修改策略名称"),
    SERVICE_NOT_EXIST(-1613, "服务信息不存在"),
    ADD_POLICY_ERROR(-1614, "策略添加失败"),
    EDIT_POLICY_ERROR(-1615, "策略编辑失败"),

    POLICY_NOT_EXISTS(-1616, "策略不存在"),
    POLICY_RESOURCES_NOT_EXISTS(-1617, "策略资源不存在"),
    POLICY_NAME_NOT_EXISTS(-1618, "策略名称不能为空"),
    POLICY_ITEMS_NOT_EXISTS(-1619, "策略明细不能为空"),
    POLICY_ITEMS_USER_ERROR(-1620, "策略明细用户组或用户为空"),
    POLICY_ITEMS_ACCESS_ERROR(-1621, "策略明细权限为空"),
    POLICY_QUERY_ERROR(-1622, "策略查询失败"),
    POLICY_HIVE_FILTER_SHCEMA_NAME_NOT_EXISTS(-1623, "数据库不能为空"),
    POLICY_HIVE_FILTER_TABLE_NAME_NOT_EXISTS(-1624, "表不能为空"),
    POLICY_HIVE_FILTER_COLUMN_NAME_NOT_EXISTS(-1627, "列不能为空"),
    POLICY_HIVE_FILTER_EXPR_NOT_EXISTS(-1625, "行过滤项表达式不能为空"),
    POLICY_ITEMS_ROWFILTER_ERROR(-1626, "行过滤策略明细为空"),


    HBASE_CONNECTION_ERROR(-5000, "hbase连接异常"),

    HBASE_SET_QUOTAS_ERROR(-5001, "hbase设置quotas异常"),

    HBASE_DELETE_QUOTAS_ERROR(-5002, "hbase删除quotas异常"),

    QUERY_HBASE_NAMESPACE_INFO_ERROR(-5003, "查询Hbase namespace信息错误"),

    QUERY_HBASE_TABLE_INFO_ERROR(-5004, "查询Hbase table信息错误"),


    ENTITY_INSERT_ERROR(-6000, "实体添加失败"),

    ENTITY_USER_INSERT_ERROR(-6010, "用户实体关联添加失败"),

    ENTITY_VALUE_DELETE_ERROR(-6020, "实体设置信息删除失败"),

    ENTITY_VALUE_INSERT_ERROR(-6030, "实体设置信息插入失败"),

    ENTITY_PARENT_VALUE_UPDATE_ERROR(-6140, "实体的父级使用值更新失败"),

    ENTITY_SET_CLUSTER_PHYSICS(-6200, "物理集群设置失败"),

    ENTITY_TENANT_ADD_ERROR(-6300, "租户添加失败"),

    ENTITY_TENANT_GET_ERROR(-6310, "租户查询失败"),

    CLUSTER_PARENT_EMPTY(-6311, "归属集群不能为空"),

    TENANT_NAME_EMPTY(-6312, "租户查询失败"),

    TENANT_PARENT_EMPTY(-6313, "归属租户不能为空"),

    PROJECT_NAME_EMPTY(-6314, "项目名称不能为空"),

    ENTITY_TENANT_DELETE_ERROR(-6400, "租户用户关联删除失败"),

    ENTITY_TENANT_EDIT_ERROR(-6500, "租户编辑失败"),

    ENTITY_TENANT_DETAIL_ERROR(-6600, "租户详细信息查看失败"),

    ENTITY_PROJECT_ADD_ERROR(-7000, "项目添加失败"),

    ENTITY_PROJECT_GET_ERROR(-7100, "项目列表查询失败"),

    ENTITY_PROJECT_EDIT_ERROR(-7200, "项目编辑失败"),

    ENTITY_PROJECT_DETAIL_ERROR(-7300, "项目详细信息查看失败"),

    MONITOR_PROPERTY_EMPTY(-8100, "监控属性为空"),

    KAFKA_SET_QUOTAS_ERROR(-9001, "kafka设置quotas异常"),

    KAFKA_DELETE_QUOTAS_ERROR(-9002, "kafka删除quotas异常"),

    YARN_CM_URL_ERROR(-10100, "yarn的cm连接信息错误"),

    HDFS_OPERATOR_ERROR(-11000, "hdfs操作失败"),

    CDH_SERVICE_NOT_AVAILABLE(-11100, "cdh中服务不可用"),

    DELET_CLUSTER_FAIL(-11200, "删除集群失败"),

    DELETE_ENITY_FAIL(-11210, "删除实体失败"),

    DELETE_USERROLE_FAIL(-11220, "删除用户与角色关联失败"),

    DELETE_ENTITYUSER_FAIL(-11230, "删除用户与实体关联失败"),

    DELETE_ENTITYPROPERTYVALUE_FAIL(-11240, "删除用户实体配制失败"),

    FLOW_ENTITY_USER_ALREADY_BIND(-12000, "当前用户已经被绑定，请选择其他用户！"),

    FLOW_ENTITY_NAME_ALREADY_EXISTS(-12010, "当前实体名称已经被占用，请重新输入"),

    FLOW_ENTITY_ADD_FAIL(-12020, "实体信息添加失败"),

    FLOW_ENTITY_PROPERTYVALUE_FAIL(-12030, "实体组件设置信息添加制作"),

    FLOW_ENTITY_USER_FAIL(-12040, "实体用户关联添加失败"),

    FLOW_ENTITY_VERSION_FAIL(-12050, "实体的版本信息表添加失败"),

    FLOW_ENTITY_PHYSICS_FAIL(-12060, "实体物理设置结果添加失败"),

    FLOW_ENTITY_USERROLE_FAIL(-12070, "用户角色添加失败"),

    FLOW_ENTITY_PHYSICS(-12080, "物理集群信息读取失败"),

    FLOW_ENTITY_CLUSTER_FAIL(-12090, "数据库集群信息添加失败"),

    FLOW_ENTITY_ENTITY_USE_FAIL(-12100, "数据库实体使用信息添加失败"),

    FLOW_ENTITY_CHILD_PROPERTYVALUE_EXISTS(-12110, "在子集的配制中存在组件已经被使用，不能删除"),

    FLOW_ENTITY_VERSTION_STATE_FAIL(-12120, "当前的实体已经在修改中，当前不能进行修改，待其他人修改完成后再操作"),

    FLOW_ENTITY_VERSTION_PARENT_STATE_FAIL(-12130, "当前的实体父级已经在修改中，当前不能进行修改，待其他人修改完成后再操作"),

    FLOW_ENTITY_DELETE_ENTITY_USER_FAIL(-13000, "数据库用户实体绑定关系删除失败"),

    FLOW_ENTITY_DELETE_USER_ROLE_FAIL(-13100, "数据库用户角色关联关系删除失败"),

    FLOW_ENTITY_UPDATE_INSERT_ENTITY_USER_FAIL(-12300, "数据库用户与实体的关系添加失败"),

    FLOW_ENTITY_UPDATE_INSERT_USER_ROLE_FAIL(-12310, "数据库用户与角色的关系添加失败"),

    FLOW_ENTITY_UPDATE_UPDATE_ETNAME_FAIL(-12320, "数据库实体用户名更新失败"),

    FLOW_ENTITY_UPDATE_DELETE_PROPERTYVALUE_FAIL(-12330, "数据库属性设置表删除失败"),

    FLOW_ENTITY_UPDATE_INSERT_PROPERTYVALUE_FAIL(-12340, "数据库属性设置表添加失败"),

    FLOW_ENTITY_DELETE_VERSTION_FAIL(-12350, "数据库版本信息删除失败"),

    FLOW_ENTITY_DELETE_PROPERTY_FAIL(-12355, "数据库删除操作属性信息失败"),

    FLOW_ENTITY_DELETE_PROPERTYVALUE_FAIL(-12360, "数据库删除操作属性设置表删除失败"),

    FLOW_ENTITY_DELETE_USE_FAIL(-12380, "数据库删除操作已使用信息删除失败"),

    FLOW_ENTITY_ADD_USE_FAIL(-12390, "数据库添加操作已使用信息添加失败"),

    FLOW_ENTITY_QUOTA_CHECK_FAIL(-12400, "配额不足"),

    FLOW_ENTITY_STATE_DOUBLE_THREAD(-12510, "出现流程错误"),

    FLOW_ENTITY_PARENT_STATE_DOUBLE_THREAD(-12520, "父级流程出现流程错误"),

    FLOW_ENTITY_PARENT_USE_UPDATE_FAIL(-12530, "数据库实体父级资源的使用信息更新失败"),

    FLOW_ENTITY_PROJECT_FAIL(-12540, "项目添加失败"),

    FLOW_ENTITY_DELETE_TABLE_PHYSICS_FAIL(-12550, "物理集群设置结果信息删除失败"),

    FLOW_ENTITY_UPDATE_QUOTA_USE_ERROR(-12660, "设置的新配额小于已经使用的配额"),

    UPLOAD_FILE_NOT_EXIST(-13000, "上传文件不存在"),

    DB_TYPE_NOT_SUPPORT(-13010, "数据库类型不支持"),

    DATASOURCE_NOT_EXIST(-13011, "数据源不存在"),

    SCHEMA_NOT_EXIST(-13020, "schema不存在"),

    TABLE_NOT_EXIST(-13021, "table不存在"),

    SCHEMA_HAS_LINKED(-13030, "已存在同样的源schema和目标schema被关联"),

    METADATA_COMPARE_FAIL_INT(-13040, "当前指定的元数据已经在对比中"),

    TEMPLATE_FILE_NOT_EXIST(-13050, "模板文件不存在"),

    TEMPLATE_FILE_FORMAT_ERROR(-13060, "模板文件格式错误"),

    TARGET_SCHEMA_HAS_LINKED(-13070, "已关联库,请先删除关联的库"),

    SCHEMA_SYNC_PROCESSING(-13080, "schema数据正在同步，不能删除"),

    CLUSTER_ADMIN_IS_NULL(-13090, "管理员不能为空"),

    DELETE_MODULE_CHILDREN_EXISTS(-13100, "当前实体存在子集，不能删除"),

    ROLE_NOT_EXIST(-13200, "角色在sentry中不存在，不能授权"),

    BATCH_TASK_ALREADY_RUN(-13300, "批次任务正在运行中"),
    ;

    private int code;

    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorCodeEnum{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
