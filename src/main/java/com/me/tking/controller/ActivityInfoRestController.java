package com.me.tking.controller;

import com.me.tking.common.PageModel;
import com.me.tking.dao.ActivityInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=BaseController.PREFIX + "/activity")
public class ActivityInfoRestController extends BaseController{
    @Autowired
    ActivityInfoDao activityInfoDao;

    @RequestMapping(value="")
    public ResponseEntity getAll(
        @RequestParam(defaultValue = "1") int pageIndex,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") int orderByType
    ) {
        return ResponseEntity.ok(
                PageModel.from(pageIndex, pageSize, activityInfoDao.countAll(keyword, orderByType),
                    rowBounds -> activityInfoDao.getAll(rowBounds, keyword, orderByType)
                )
        );
    }
}

