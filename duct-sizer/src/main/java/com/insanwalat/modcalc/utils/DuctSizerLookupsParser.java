package com.insanwalat.modcalc.utils;

import com.insanwalat.modcalc.exceptions.ParsingLookupDataException;
import com.insanwalat.modcalc.module.lookup.DuctSizerLookup;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class DuctSizerLookupsParser {

    private URL ductSizerLookupDataPath = getClass().getResource("/duct-sizer-lookups.csv");
    private List<DuctSizerLookup> dataList = new ArrayList<>();

    public DuctSizerLookupsParser() {
        try {
            fillDataMap();
        } catch (Exception e) {
            throw new ParsingLookupDataException("Failed to parse Duct Sizer Lookup File");
        }
    }

    public List<DuctSizerLookup> getDataList() {
        return dataList;
    }

    private void fillDataMap() throws IOException, URISyntaxException {
        try (Reader reader = Files.newBufferedReader(Paths.get(ductSizerLookupDataPath.toURI()))) {
            CsvToBean<DuctSizerLookup> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(DuctSizerLookup.class)
                    .withSeparator('|')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<DuctSizerLookup> csvDuctSizerLookupIterator = csvToBean.iterator();
            while (csvDuctSizerLookupIterator.hasNext()) {
                dataList.add(csvDuctSizerLookupIterator.next());
            }
        }
    }
}
