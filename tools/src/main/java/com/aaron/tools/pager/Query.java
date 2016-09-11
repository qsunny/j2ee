package com.aaron.tools.pager;


import java.io.Serializable;
import java.util.Map;

public class Query
  implements Serializable
{
  private static final long serialVersionUID = 817880730448759944L;
  private int pageNumber;
  private int pageSize = 15;
  private String orderby;
  private Map<String, ORDERBY> sqlOrderBy;
  private int pageIndex;
  private String page;
  private int rows;

  public Query()
  {
  }

  public Query(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageIndex() {
    if (this.pageIndex < 0)
      this.pageIndex = 1;
    else {
      this.pageIndex += 1;
    }
    return this.pageIndex;
  }

  public void setPageIndex(int pageIndex) {
    this.pageIndex = pageIndex;
  }

  public String getPage()
  {
    return this.page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public int getPageNumber() {
    return this.pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getRows() {
    return this.rows;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public String getOrderby() {
    return this.orderby;
  }

  public void setOrderby(String orderby) {
    this.orderby = orderby;
  }

  public Map<String, ORDERBY> getSqlOrderBy() {
    return this.sqlOrderBy;
  }

  public void setSqlOrderBy(Map<String, ORDERBY> sqlOrderBy) {
    this.sqlOrderBy = sqlOrderBy;
  }
}