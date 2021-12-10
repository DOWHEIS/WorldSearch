package bsu.edu.cs222.model;

public class Country {
    public String name;
    public String capitalCity;
    public Region region;
    public Income incomeLevel;
    public String value;
    public String date;
    public String iso2Code;

    public static class Region {
        public String value;
    }
    public static class Income {
        public String value;
    }

}
