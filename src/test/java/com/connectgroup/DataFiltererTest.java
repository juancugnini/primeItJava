package com.connectgroup;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

public class DataFiltererTest {
    @Test
    public void shouldReturnEmptyCollection_WhenLogFileIsEmpty() throws FileNotFoundException {
        assertTrue(DataFilterer.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }
    @Test
    public void shouldReturnGB() throws FileNotFoundException {
        assertTrue(!DataFilterer.filterByCountry(openFile("src/test/resources/multi-lines"), "GB").isEmpty());
    }

    @Test
    public void shouldReturnUSAndAbove800() throws FileNotFoundException {
        assertTrue(!DataFilterer.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 800).isEmpty());
    }
}
