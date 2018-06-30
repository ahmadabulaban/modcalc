package com.insanwalat.modcalc.fanesp.utils;

import com.insanwalat.modcalc.fanesp.exceptions.ParsingLookupDataException;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientDataLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspCoefficientLookup;
import com.insanwalat.modcalc.fanesp.module.lookup.FanEspLookup;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
import java.util.stream.Collectors;

@Component
public class FanEspLookupsParser {

    private URL fanEspLookupDataPath = getClass().getClassLoader().getResource("fan-esp-resources/fan-esp-lookups.csv");
    private URL fanEspCoefficientLookupDataPath = getClass().getClassLoader().getResource("fan-esp-resources/fan-esp-coefficient-lookups.csv");

    private List<FanEspLookup> dataList = new ArrayList<>();
    private List<FanEspCoefficientLookup> dataCoefficientList = new ArrayList<>();
    private Map<String, FanEspCoefficientDataLookup> coefficientDataLookupMap = new HashMap<>();

//    public static void main(String[] args) {
//        new FanEspLookupsParser();
//    }

    public FanEspLookupsParser() {
        try {
            fillDataList();
            fillCoefficientDataList();
            fillCoefficientLookupsMap();
            System.out.println("pepsi");
        } catch (Exception e) {
            throw new ParsingLookupDataException("Failed to parse Fan Esp Lookup Files");
        }
    }

    public List<FanEspLookup> getDataList() {
        return dataList;
    }

    public List<FanEspCoefficientLookup> getDataCoefficientList() {
        return dataCoefficientList;
    }

    public List<FanEspCoefficientLookup> getDataCoefficientList(String documentRelated) {
        List<FanEspCoefficientLookup> list = new ArrayList<>();
        for (FanEspCoefficientLookup coefficientLookup : dataCoefficientList) {
            if (coefficientLookup.getDocumentRelated().equalsIgnoreCase(documentRelated))
                list.add(coefficientLookup);
        }
        return list;
    }

    public List<FanEspCoefficientDataLookup> getFanEspCoefficientDataLookupList() {
        return coefficientDataLookupMap.values().stream().collect(Collectors.toList());
    }

    public FanEspCoefficientDataLookup getFanEspCoefficientDataLookup(String typeName) {
        for (FanEspCoefficientLookup coefficientLookup : dataCoefficientList) {
            if (coefficientLookup.getTypeName().equalsIgnoreCase(typeName))
                return coefficientDataLookupMap.get(coefficientLookup.getName());
        }
        return null;
    }


    private void fillDataList() throws IOException, URISyntaxException {
        try (Reader reader = Files.newBufferedReader(Paths.get(fanEspLookupDataPath.toURI()))) {
            CsvToBean<FanEspLookup> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(FanEspLookup.class)
                    .withSeparator('|')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<FanEspLookup> csvFanEspLookupIterator = csvToBean.iterator();
            while (csvFanEspLookupIterator.hasNext()) {
                dataList.add(csvFanEspLookupIterator.next());
            }
        }
    }

    private void fillCoefficientDataList() throws IOException, URISyntaxException {
        try (Reader reader = Files.newBufferedReader(Paths.get(fanEspCoefficientLookupDataPath.toURI()))) {
            CsvToBean<FanEspCoefficientLookup> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(FanEspCoefficientLookup.class)
                    .withSeparator('|')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<FanEspCoefficientLookup> csvFanEspCoefficientLookupIterator = csvToBean.iterator();
            while (csvFanEspCoefficientLookupIterator.hasNext()) {
                dataCoefficientList.add(csvFanEspCoefficientLookupIterator.next());
            }
        }
    }

    private void fillCoefficientLookupsMap() throws IOException, URISyntaxException {
        for (FanEspCoefficientLookup fanEspCoefficientLookup : dataCoefficientList) {
            String name = fanEspCoefficientLookup.getName();
            String documentPath = fanEspCoefficientLookup.getDocument();
            String coefficientTable[][] = new String[fanEspCoefficientLookup.getHeight()][fanEspCoefficientLookup.getWidth()];
            List<Double> coefficientValues = new ArrayList<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(getClass().getClassLoader().getResource(documentPath).toURI()))) {
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withCSVParser(new CSVParserBuilder().withSeparator('|').build())
                        .build();
                for (int i = 0; i < coefficientTable.length; i++) {
                    String[] record = csvReader.readNext();
                    for (int j = 0; j < coefficientTable[i].length; j++) {
                        String rowRecord = record[j];
                        coefficientTable[i][j] = rowRecord;
                        if (rowRecord.endsWith("v")) {
                            Double v = Double.valueOf(rowRecord.replace("v", ""));
                            if (!coefficientValues.contains(v))
                                coefficientValues.add(v);
                        }
                    }
//                    System.arraycopy(record, 0, coefficientTable[i], 0, record.length);
                }
            }
            coefficientDataLookupMap.put(name, new FanEspCoefficientDataLookup(name, coefficientTable, coefficientValues));
        }
    }
}
