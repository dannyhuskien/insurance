package com.insurance.models;

import com.insurance.utils.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/11/16.
 */
public class VehicleTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createNativeQuery("truncate table vehicles").executeUpdate();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 1").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testShouldCreateVehicleAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Vehicle vehicle = new Vehicle("Mazda", "3", 2011);
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, vehicle.getId());
    }

}