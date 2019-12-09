package org.sang.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.sang.bean.Cenlendar;
import org.sang.bean.User;
import org.sang.mapper.CenlendarMapper;
import org.sang.mapper.UserMapper;
import org.sang.utils.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //initCenlendar("20180727");
        User user = new User();
        user.setName("曹绪才");
        user.setPassword("handhand");
        user.setPhone("");
        user.setEnabled(true);
        user.setUsername("4821");
        user.setUserface("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg");
        register(user);
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

    @Autowired
    UserMapper userMapper;

    void register(User user) {
        User user2 = userMapper.loadUserByUsername(user.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        if (user2 != null) {
            //修改密码
            user.setId(user2.getId());
            userMapper.updateHr(user);
            return;
        }
        userMapper.hrReg2(user);
    }


} 
