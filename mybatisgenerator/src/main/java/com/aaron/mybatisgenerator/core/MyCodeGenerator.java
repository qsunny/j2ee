package com.aaron.mybatisgenerator.core;

import com.aaron.mybatisgenerator.core.BaseInfo;
import com.aaron.mybatisgenerator.core.ColumnField;
import com.aaron.mybatisgenerator.core.FileList;
import com.aaron.mybatisgenerator.core.JdbcTypeNameTranslator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aaron.mybatisgenerator.util.*;
import org.apache.commons.lang.StringUtils;

public class MyCodeGenerator
{
    public static void main(String[] args)
            throws Exception
    {
        MyCodeGenerator cg = new MyCodeGenerator();
        cg.generate();
    }

    private String getField(String column)
    {
        String[] columnSplit = column.split("_");

        StringBuffer field = new StringBuffer();

        for (int i = 0; i < columnSplit.length; i++) {
            if (i != 0)
            {
                field.append(StringUtils.capitalize(StringUtils.lowerCase(columnSplit[i])));
            }
            else
            {
                field.append(StringUtils.uncapitalize(StringUtils.lowerCase(columnSplit[i])));
            }
        }

        return field.toString();
    }

    public BaseInfo getBaseInfo()
            throws Exception
    {
        BaseInfo baseInfo = new BaseInfo();
        Connection connection = null;
        try
        {
            String driver = PropertiesUtil.getProperties("jdbc.driver");
            String url = PropertiesUtil.getProperties("jdbc.url");
            String userName = PropertiesUtil.getProperties("jdbc.userName");
            String password = PropertiesUtil.getProperties("jdbc.password");
            String tableName = PropertiesUtil.getProperties("tableName");
            String author = PropertiesUtil.getProperties("author");
            String model = PropertiesUtil.getProperties("model");
            String objectName = PropertiesUtil.getProperties("objectName");
            String fLowerObjectName = PropertiesUtil.getProperties("fLowerObjectName");

            connection = JdbcUtil.getConnection(driver, url, userName, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from " + tableName + " where 1=2");

            Map columnCommentMap = getColumnComment(connection, tableName);

            ResultSetMetaData rsmd = rs.getMetaData();

            List columnFieldList = new ArrayList();

            int count = rsmd.getColumnCount();

            for (int i = 1; i <= count; i++) {
                String columnName = rsmd.getColumnName(i);
                int jdbcType = rsmd.getColumnType(i);
                ColumnField columnField = new ColumnField();
                String field = getField(columnName);
                String cField = StringUtils.capitalize(field);

                columnField.setColumn(columnName);
                columnField.setField(field);
                columnField.setcField(cField);
                columnField.setJdbcType(JdbcTypeNameTranslator.getJdbcTypeName(jdbcType));
                columnField.setJavaType(JdbcTypeNameTranslator.getJavaType(jdbcType));
                columnField.setComment((String)columnCommentMap.get(columnName));

                columnFieldList.add(columnField);
            }
            baseInfo.setColumnFieldList(columnFieldList);
            baseInfo.setAuthor(author);
            baseInfo.setModel(model);
            baseInfo.setTableName(tableName);
            baseInfo.setObjectName(objectName);
            baseInfo.setCreateTime(DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
            baseInfo.setfLowerObjectName(fLowerObjectName);
        }
        catch (Exception localException)
        {
        }
        finally
        {
            if (connection != null)
            {
                connection.close();
            }

            if (connection == null) return baseInfo;
        }
        connection.close();

        return baseInfo;
    }

    public Map<String, String> getColumnComment(Connection connection, String tableName) throws Exception
    {
        Map map = new HashMap();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT column_name,COLUMN_COMMENT FROM information_schema.COLUMNS WHERE UPPER(table_name) = UPPER('" + tableName + "')");
        while (rs.next())
        {
            String column = rs.getString(1);
            String comments = rs.getString(2);
            map.put(column, comments);
        }

        return map;
    }

    public void generate() throws Exception
    {
        try {
            BaseInfo baseInfo = getBaseInfo();
            Map<String, String> map = FileList.getFileToPath(baseInfo.getModel(), baseInfo.getObjectName());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String context = TemplateUtil.getText((String) entry.getKey(), baseInfo);
                String filePath = FileUtil.getClassPath() + "target/" + (String) entry.getValue();
                FileUtil.string2File(context, filePath);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}