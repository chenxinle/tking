package com.me.tking.controller;

import com.me.tking.dao.ActivityInfoDao;
import com.me.tking.service.spider.TkingPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=BaseController.PREFIX + "/spider")
public class SpiderRestController {

    @Autowired
    TkingPageProcessor tkingPageProcessor;

    @Autowired
    ActivityInfoDao activityInfoDao;

    @RequestMapping(value="/start")
    public ResponseEntity startSpider() {
        tkingPageProcessor.startSpider();
        return ResponseEntity.ok("爬取成功");
    }

    @RequestMapping(value="/deleteAll")
    public ResponseEntity deleteAll() {
        activityInfoDao.deleteAll();
        return ResponseEntity.ok("删除成功");
    }
}
