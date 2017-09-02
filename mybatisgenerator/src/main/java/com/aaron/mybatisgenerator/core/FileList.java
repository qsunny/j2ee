package com.aaron.mybatisgenerator.core;

import java.util.HashMap;
import java.util.Map;

public class FileList
{
    public static Map<String, String> getFileToPath(String model, String objectName)
    {
        Map<String,String> fileToPath = new HashMap<String,String>();

        fileToPath.put("/dao/mapper/mapper.ftl", "mybatis/mapper/" + model + "/" + objectName + "Mapper.xml");
        fileToPath.put("/domain/domain.ftl", "com/allchips/" + model + "/common/" + objectName + ".java");
        fileToPath.put("/dao/dao.ftl", "com/allchips/" + model + "/core/dao/" + "I" + objectName + "Dao" + ".java");
        //fileToPath.put("/dao/impl/daoimpl.ftl", "com/allchips/" + model + "/core/dao/impl/" + objectName + "DaoImpl" + ".java");
        fileToPath.put("/service/service.ftl", "com/allchips/" + model + "/core/service/" + "I" + objectName + "Service" + ".java");
        fileToPath.put("/service/impl/serviceimpl.ftl", "com/allchips/" + model + "/core/service/impl/" + objectName + "ServiceImpl" + ".java");

        return fileToPath;
    }
}
