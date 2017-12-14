package com.me.tking.service.spider;

import com.me.tking.dao.ActivityInfoDao;
import com.me.tking.entity.ActivityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class ActivityInfoPipeline implements Pipeline {
    @Autowired
    ActivityInfoDao activityInfoDao;

    private List<String> getResultByKey(Map<String, Object> result, String key) {
        return Optional.ofNullable((List<String>) result.get(key)).orElse(new ArrayList<>());
    }

    private int getNumberFromString(String string) {
        return Integer.parseInt(string.replaceAll("[^0-9]", ""));
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> result = resultItems.getAll();
        List<String> activityNames = getResultByKey(result, "activityName");
        List<String> scanTimes = getResultByKey(result, "scanTimes");
        List<String> likedTimes = getResultByKey(result, "likedTimes");
        int minSize = Math.min(Math.min(activityNames.size(), scanTimes.size()), likedTimes.size());
        List<ActivityInfo> activityInfos = new ArrayList<>();
        IntStream.range(0, minSize).forEach(i -> {
            ActivityInfo activityInfo = new ActivityInfo();
            activityInfo.setScanTimes(getNumberFromString(scanTimes.get(i)));
            activityInfo.setLikeTimes(getNumberFromString(likedTimes.get(i)));
            activityInfo.setActivityName(activityNames.get(i));
            activityInfos.add(activityInfo);
        });

        if (activityInfos.size() == 0) {
            return;
        }

        activityInfoDao.batchInsertActivityInfo(activityInfos);
    }
}
