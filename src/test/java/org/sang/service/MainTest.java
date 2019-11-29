package org.sang.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.sang.bean.Cenlendar;
import org.sang.mapper.CenlendarMapper;
import org.sang.utils.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DepartmentService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>11/29/2019</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Autowired
    CenlendarMapper cenlendarMapper;

    @Test
    @Rollback(false)
    public void main() throws Exception {
        initCenlendar("20180727");
    }

    void initCenlendar(String firstDate) throws InterruptedException {
        String dateStr = null;
        Calendar nowCalendar = Calendar.getInstance();
        String firstDayStr = firstDate;// 开始的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar firstCalendar = Calendar.getInstance();// 开始日期，并要累积加 一
        Calendar secondYearCalendar = Calendar.getInstance();// 结束的日期
        Date time = null;
        try {
            time = sdf.parse(firstDayStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        firstCalendar.setTime(time);
        secondYearCalendar.setTime(time);
        secondYearCalendar.add(Calendar.YEAR, 1);// 加上两个月
        Date first = firstCalendar.getTime();  //一年第一天
        Date last = secondYearCalendar.getTime();  //第二年最后一天

        while (first.getTime() < last.getTime()) { // 判断是否是节假日
            dateStr = sdf.format(first.getTime());
            String result = HolidayUtil.getStatusByBitefu(dateStr);

            Cenlendar cenlendar = new Cenlendar();
            cenlendar.setDate(first);
            cenlendar.setStatus(new Integer(result));

            cenlendarMapper.insert(cenlendar);

            firstCalendar.add(firstCalendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
            first = firstCalendar.getTime(); // 这个时间就是日期往后推一天的结果
            Thread.sleep(1000l);
        }
    }


} 
