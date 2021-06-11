package com.aaron.base.page;

import com.aaron.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
public class Page<T> extends BaseEntity implements Pagination {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageNo = 1; // 当前页, 默认为第1页
    private int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数
    private long totalCount = -1; // 总记录数, 默认为-1, 表示需要查询
    private int totalPageCount = -1; // 总页数, 默认为-1, 表示需要计算

    // 用于分页查询时的条件设置
    private T param;
    private Map<String, Object> extraParam;

    private List<T> result; // 当前页记录List形式

    public Page() {
        super();
    }

    /**
     * @param pageNo   页码
     * @param pageSize 页面大小
     */
    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        computeTotalPage();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        computeTotalPage();
    }

    protected void computeTotalPage() {
        if (getPageSize() > 0 && getTotalCount() > -1) {
            this.totalPageCount = (int) (getTotalCount() % getPageSize() == 0 ? getTotalCount() / getPageSize()
                    : getTotalCount() / getPageSize() + 1);
        }
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public Map<String, Object> getExtraParam() {
        return extraParam;
    }

    public void setExtraParam(Map<String, Object> extraParam) {
        this.extraParam = extraParam;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * 为Redis分页设计，其他场景慎重使用
     *
     * @return start
     * @author 水墨
     */
    @JsonIgnore
    public int getStart() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 为Redis分页设计，其他场景慎重使用
     *
     * @return end
     * @author 水墨
     */
    @JsonIgnore
    public int getEnd() {
        return pageNo * pageSize - 1;
    }

    @Override
    public int getCurrentPageNo() {
        return pageNo;
    }

}
