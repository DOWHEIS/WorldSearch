package bsu.edu.cs222.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"pagination", "countries" })
public class CountryFull {
    public Pagination pagination;
    public List<Country> country;

}
