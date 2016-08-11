package com.insurance.models;

import com.insurance.Enum.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Access(AccessType.PROPERTY)
public class Client {

    private int id;
    private String name;
    private Gender gender;
    private int age;
    private double ratevalue;
    private List<Vehicle> vehicles;

    public Client(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.ratevalue = 0.00;
        this.vehicles = new ArrayList<>();
    }

    public Client(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Basic
    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "ratevalue",precision=10, scale=2)
    public double getRatevalue() {
        return ratevalue;
    }

    public void setRatevalue(double ratevalue) {
        this.ratevalue = ratevalue;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
