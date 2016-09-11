package com.aaron.tools.pager;


public enum ORDERBY
{
  desc, asc;

  public ORDERBY reverse() { return this == asc ? desc : asc;
  }
}