package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.User;
import org.sang.bean.MsgContent;
import org.sang.bean.SysMsg;

import java.util.List;

/**
 * Created by sang on 2018/2/2.
 */
public interface SysMsgMapper {

    int sendMsg(MsgContent msg);

    int addMsg2AllHr(@Param("users") List<User> users, @Param("mid") Long mid);

    List<SysMsg> getSysMsg(@Param("start") int start, @Param("size") Integer size,@Param("hrid") Long hrid);

    int markRead(@Param("flag") Long flag, @Param("hrid") Long hrid);
}
