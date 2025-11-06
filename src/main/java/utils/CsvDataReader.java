package utils;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvDataReader {

    public static Collection<Object[]> getTestData(String fileName) {
        URL resource = CsvDataReader.class.getClassLoader().getResource("data/" + fileName);

        if (resource == null) {
            throw new IllegalArgumentException("Fajl sa podacima nije pronadjen: " + fileName);
        }

        List<CityData> cities = new ArrayList<>();

        try (InputStreamReader reader = new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8)) {

            cities = new CsvToBeanBuilder<CityData>(reader)
                    .withType(CityData.class)
                    .withSeparator(',')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

        } catch (Exception e) {
            e.printStackTrace();
//            Assert.fail("Greska pri citanju CSV fajla: " + e.getMessage());
        }

        Collection<Object[]> testData = new ArrayList<>();
        for (CityData city : cities) {
            testData.add(new Object[]{city});
        }

        return testData;
    }
}