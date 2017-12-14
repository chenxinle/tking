package com.me.tking;

import com.me.tking.dao.ActivityInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TkingApplicationTests {

    @Autowired
    ActivityInfoDao activityInfoDao;

	@Test
	public void contextLoads() {
//        activityInfoDao.getAll();
	}

	@Test
    public void deleteAll() {
	    activityInfoDao.deleteAll();
    }

}
