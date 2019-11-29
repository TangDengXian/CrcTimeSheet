package org.sang.mapper;

import org.sang.bean.Cenlendar;

import java.util.Date;

public interface CenlendarMapper {
    int deleteByPrimaryKey(Date date);

    int insert(Cenlendar record);

    Cenlendar selectByPrimaryKey(Date date);
}