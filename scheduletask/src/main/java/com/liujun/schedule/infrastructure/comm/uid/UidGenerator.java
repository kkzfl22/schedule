package com.liujun.schedule.infrastructure.comm.uid;

import com.ddd.common.infrastructure.utils.LocalSnowFlake;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @since 2021/7/30
 */
@Service
public class UidGenerator {


    /**
     * 获取序号
     *
     * @return
     */
    public long getUid() {
        return LocalSnowFlake.getDefaultInstance().nextId();
    }

}
