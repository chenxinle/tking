package com.me.tking.service.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TkingPageProcessor implements PageProcessor {

    @Autowired
    ActivityInfoPipeline activityInfoPipeline;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8");

    @Override
    public void process(Page page) {

        List<String> pages = page.getHtml().css("li.pagination-page a", "pagination").regex("offset=.*").all();
        List<String> formatPages = pages.stream().map(p -> "https://www.tking.cn/list/?" + p).collect(Collectors.toList());
        page.addTargetRequests(formatPages);
        page.putField("scanTimes", page.getHtml().css(".info-one:nth-child(odd)", "text").all());
        page.putField("likedTimes", page.getHtml().css(".info-one:nth-child(even)", "text").all());
        page.putField("activityName",page.getHtml().css(".show-name", "text").all());

        if (page.getResultItems().get("activityName") == null) {
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void startSpider() {
        Spider.create(new TkingPageProcessor())
                .addUrl("https://www.tking.cn/list/")
                .addPipeline(activityInfoPipeline)
                .thread(5)
                .run();
    }
}