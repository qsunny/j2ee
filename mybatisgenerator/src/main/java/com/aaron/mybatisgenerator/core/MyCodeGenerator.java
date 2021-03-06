package com.aaron.mybatisgenerator.core;

import com.aaron.mybatisgenerator.core.BaseInfo;
import com.aaron.mybatisgenerator.core.ColumnField;
import com.aaron.mybatisgenerator.core.FileList;
import com.aaron.mybatisgenerator.core.JdbcTypeNameTranslator;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;

import com.aaron.mybatisgenerator.util.*;
import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;


public class MyCodeGenerator
{
    public static void main(String[] args)
            throws Exception
    {
        //System.out.println("commandline params:"+Arrays.toString(args));
        MyCodeGenerator cg = new MyCodeGenerator();
        cg.generate();
        //BufferedInputStream bufferedInputStream = (BufferedInputStream)Resources.getResource("template/domain/domain.ftl").getContent();
//        File file = new File("src/main/resources/template/domain/domain.ftl");
//        InputStream in = new FileInputStream(file);
        //InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/template/domain/domain.ftl");
//        InputStreamReader isr = new InputStreamReader(in);
//        BufferedReader br = new BufferedReader(isr);
//        String str = br.readLine();
//        while(str!=null||!"".equals(str)) {
//            System.out.println(str);
//            str = br.readLine();
//            if(str==null) break;
//        }
//        br.close();
//        isr.close();
//        in.close();
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
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        try
        {
            String driver = propertiesUtil.getProperties("jdbc.driver");
            String url = propertiesUtil.getProperties("jdbc.url");
            String userName = propertiesUtil.getProperties("jdbc.userName");
            String password = propertiesUtil.getProperties("jdbc.password");
            String tableName = propertiesUtil.getProperties("tableName");
            String author = propertiesUtil.getProperties("author");
            String model = propertiesUtil.getProperties("model");
            String objectName = propertiesUtil.getProperties("objectName");
            String fLowerObjectName = propertiesUtil.getProperties("fLowerObjectName");

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