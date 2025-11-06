package utils;

import com.opencsv.bean.CsvBindByName;

public class CityData {

    @CsvBindByName(column = "city_name")
    private String cityName;

    @CsvBindByName(column = "expected_title_part")
    private String expectedTitlePart;

    @CsvBindByName(column = "expected_url_part")
    private String expectedUrlPart;

    public CityData() {}

    public String getCityName() { return cityName; }
    public String getExpectedTitlePart() { return expectedTitlePart; }
    public String getExpectedUrlPart() { return expectedUrlPart; }

    @Override
    public String toString() {
        return cityName;
    }

}
