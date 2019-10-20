package com.connectgroup;

import com.connectgroup.helper.FileReaderHelper;
import com.connectgroup.model.Request;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataFilterer {

    public static Collection<?> filterByCountry(Reader source, String country) {
        List<Request> requests = FileReaderHelper.fileReader(source);
        return requests.stream().filter(request -> request.getCountry().equals(country)).collect(Collectors.toList());
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        List<Request> requests = FileReaderHelper.fileReader(source);
        return requests.stream().filter(request -> request.getCountry().equals(country) && request.getRequestTime() > limit).collect(Collectors.toList());
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        List<Request> requests = FileReaderHelper.fileReader(source);
        BigDecimal average = new BigDecimal(requests.stream()
                .mapToInt((x) -> x.getRequestTime())
                .summaryStatistics().getAverage());
        return requests.stream().filter(request -> new BigDecimal(request.getRequestTime()).compareTo(average)>0).collect(Collectors.toList());
    }
}