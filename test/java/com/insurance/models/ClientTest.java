package com.insurance.models;

import com.insurance.Enum.Gender;
import com.insurance.utils.Mysql;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 8/11/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createNativeQuery("truncate table vehicles").executeUpdate();
        session.createNativeQuery("truncate table clients").executeUpdate();
        session.createNativeQuery("set FOREIGN_KEY_CHECKS = 1").executeUpdate();
        Client client = new Client("Joe", Gender.M, 25);
        Vehicle v1 = new Vehicle("Mazda", "S", 2005, client);
        Vehicle v2 = new Vehicle("Honda", "CRV", 2010, client);
        client.getVehicles().add(v1);
        client.getVehicles().add(v2);
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShouldCreateNewClientAndSave() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();
        Client client = new Client("Henry", Gender.F, 45);
        session.save(client);
        session.getTransaction().commit();
        session.close();
        assertEquals(1, client.getId());
    }

    // ************************
    // *** PROOF OF CONCEPT ***
    // ************************

    @Test
    public void testShouldGetRecordAndUpdateItAndRemoveARecordAndAddAVehicleReocrd() throws Exception {
        Session session = Mysql.getSession();
        session.beginTransaction();

        //Animal animal = session.get(Animal.class, 1);
        Client client = session.get(Client.class, 1);
        client.setName("Molly");
        Optional<Vehicle> mazda = client.getVehicles().stream().filter(v -> v.getMake().equals("Mazda")).findFirst();
        mazda.ifPresent(m -> {
            m.setMake("Toyota");
        });

        Optional<Vehicle> honda = client.getVehicles().stream().filter(v -> v.getMake().equals("Honda")).findFirst();
        honda.ifPresent(h -> {
            client.getVehicles().remove(h);
            session.delete(h);
        });

        Vehicle v3 = new Vehicle("Ford", "S", 2005, client);
        client.getVehicles().add(v3);

        session.getTransaction().commit();
        session.close();
    }
}