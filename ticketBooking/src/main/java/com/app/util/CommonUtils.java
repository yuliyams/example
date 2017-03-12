package com.app.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    private static final String SDF = "yyyy-MM-dd";

    public static <E> List<E> getListUsingPagination(List<E> listObject, int pageSize, int pageNum) {
        if (pageSize < listObject.size()) {
            int startIndex = (pageNum - 1) * pageSize;
            int endIndex = (pageNum * pageSize);
            endIndex = endIndex > listObject.size() ? listObject.size() : endIndex;
            return listObject.subList(startIndex, endIndex);
        } else {
            return listObject;
        }
    }

    public static String convertFileToStringList(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return new String(Files.readAllBytes(path));
    }

    public static Date convertDate(String date) throws ParseException {
        return new SimpleDateFormat(SDF).parse(date);
    }

    public static String composeId(String entityName, long entityId) {
        return entityName + entityId;
    }
}
