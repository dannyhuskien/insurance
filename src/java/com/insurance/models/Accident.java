package com.insurance.models;

import com.insurance.Enum.Severity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by localadmin on 8/11/16.
 */

@Entity
@Table(name = "accidents")
@Access(AccessType.PROPERTY)
public class Accident {

    private int id;
    private Date incidentdate;
    private Severity severity;
    private int totalcost;
    private Vehicle vehicle;

    public Accident() {
    }

    public Accident(Date incidentdate, Severity severity, int totalcost) {
        this(incidentdate, severity, totalcost, null);

    }

    public Accident(Date incidentdate, Severity severity, int totalcost, Vehicle vehicle) {
        this.incidentdate = incidentdate;
        this.severity = severity;
        this.totalcost = totalcost;
        this.vehicle = vehicle;
    }

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

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
    @Temporal(TemporalType.DATE)
    @Column(name = "incidentdate")
    public Date getIncidentdate() {
        return incidentdate;
    }

    public void setIncidentdate(Date incidentdate) {
        this.incidentdate = incidentdate;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    @Basic
    @Column(name = "totalcost")
    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }
}
