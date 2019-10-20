package com.connectgroup.helper;

import com.connectgroup.model.Request;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileReaderHelper {
    private static String COMMA = ",";

    public static List<Request> fileReader(Reader source) {
        List<Request> inputList = new ArrayList<Request>();
        try{
            BufferedReader breader = new BufferedReader(source);
            inputList = breader.lines().skip(1).map(mapToItem).filter(request -> request!=null).collect(Collectors.toList());
            breader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException ioe) {

        }
        return inputList ;
    }

    private static Function<String, Request> mapToItem = (line) -> {
        String[] p = line.split(COMMA);
        Request item = new Request();
        if (!line.isEmpty()) {
            item.setDate(new Date(Long.valueOf(p[0])*1000));
            item.setCountry(p[1]);
            item.setRequestTime(Integer.valueOf(p[2]));
            return item;
        }
        return  null;
    };
}
