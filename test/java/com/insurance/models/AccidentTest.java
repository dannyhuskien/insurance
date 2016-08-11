package com.insurance.models;

import com.insurance.Enum.Severity;
import com.insurance.utils.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/11/16.
 */
public class AccidentTest {
    private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createNativeQuery("truncate table accidents").executeUpdate();
        session.createNativeQuery("truncate table vehicles").executeUpdate();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 1").executeUpdate();
        Vehicle v1 = new Vehicle("Ford", "Focus", 2012);
        Accident a1 = new Accident(simpleDate.parse("2011-12-12"), Severity.SERIOUS, 20000,v1);
        Accident a2 = new Accident(simpleDate.parse("2011-05-12"), Severity.TOTALED, 30000,v1);
        v1.getAccidents().add(a1);
        v1.getAccidents().add(a2);
        session.save(v1);
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testShouldCreateNewAccidentForAVehicle() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();

        Vehicle vehicleTest = session.get(Vehicle.class, 1);
        Accident a3 = new Accident(simpleDate.parse("2011-10-12"), Severity.LIGHT, 15000, vehicleTest);
        vehicleTest.getAccidents().add(a3);
        assertEquals(vehicleTest.getAccidents().size(), 3);
        assertEquals(vehicleTest.getAccidents().get(2).getSeverity(), Severity.LIGHT);

    }

}