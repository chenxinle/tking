package com.me.tking.common;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class PageModel<T> implements Serializable {

    private static final long serialVersionUID = -8871167960445103162L;

    private static final int MAX_PAGE_SIZE = 100;

    long totalNum;
    int pageIndex;
    int pageSize;
    List<T> data = Lists.newArrayList();

    public PageModel(){}

    public Map<String,Object> toMap(String dataKey){
        Map<String,Object> map = new HashMap<>();
        map.put("pageSize", this.getPageSize());
        map.put("pageIndex", this.getPageIndex());
        map.put("total", this.getTotalNum());
        map.put("totalNum", this.getTotalNum());
        map.put("totalPage", (this.getTotalNum() - 1) / this.getPageSize() + 1);
        map.put(dataKey, this.getData());
        return map;
    }

    public <T2> PageModel<T2> cloneAndFill(List<T2> data){
        PageModel<T2> t2PageModel = new PageModel<>();
        t2PageModel.setPageIndex(pageIndex);
        t2PageModel.setPageSize(pageSize);
        t2PageModel.setTotalNum(totalNum);
        t2PageModel.setData(data);
        return t2PageModel;
    }

    public boolean isHasMore(){
        return pageIndex * pageSize < totalNum;
    }

    public RowBounds toRowBounds() {
        return new RowBounds((pageIndex - 1) * pageSize, pageSize);
    }

    public static <T> PageModel<T> from(int pageIndex, int pageSize, long totalNum,
            Function<RowBounds, List<T>> dataGetter) {
        PageModel<T> pageModel = new PageModel<T>();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalNum(totalNum);
        if (pageIndex<=0 || pageSize<=0 || pageSize > MAX_PAGE_SIZE){
            pageModel.setData(Collections.emptyList());
        }else if ((pageIndex-1) * pageSize >= totalNum){
            pageModel.setData(Collections.emptyList());
        }
        else {
            pageModel.setData(dataGetter.apply(pageModel.toRowBounds()));
        }
        return pageModel;
    }

}
