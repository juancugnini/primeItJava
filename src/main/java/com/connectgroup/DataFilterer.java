package com.connectgroup;

import com.connectgroup.model.Request;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataFilterer {

    private static String COMMA = ",";

    public static Collection<?> filterByCountry(Reader source, String country) {
        List<Request> requests = fileReader(source);
        return requests.stream().filter(request -> request.getCountry().equals(country)).collect(Collectors.toList());
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        List<Request> requests = fileReader(source);
        return requests.stream().filter(request -> request.getCountry().equals(country) && request.getRequestTime() > limit).collect(Collectors.toList());
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        return Collections.emptyList();
    }

    public static List<Request> fileReader(Reader source) {
            List<Request> inputList = new ArrayList<Request>();
            try{
                BufferedReader breader = new BufferedReader(source);
                inputList = breader.lines().skip(1).map(mapToItem).collect(Collectors.toList());
                breader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException ioe) {

            }
            return inputList ;
        }
    private static Function<String, Request> mapToItem = (line) -> {
        String[] p = line.split(COMMA);
        Request item = new Request();
        item.setDate(new Date(Long.valueOf(p[0])*1000));
        item.setCountry(p[1]);
        item.setRequestTime(Long.valueOf(p[2]));
        return item;
    };
}