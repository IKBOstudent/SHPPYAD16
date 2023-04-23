package com.example.shppyad16.PostOffice;

import com.example.shppyad16.Departure.Departure;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post_offices_table_new")
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "postOffice", cascade = CascadeType.ALL)
    private List<Departure> departureList = new ArrayList<>();

    public PostOffice() {}

    public PostOffice(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Departure> getDepartureList() {
        return departureList;
    }

    public void setDepartureList(List<Departure> departureList) {
        this.departureList = departureList;
    }

    public void addDeparture(Departure departure) {
        this.departureList.add(departure);
    }
}
