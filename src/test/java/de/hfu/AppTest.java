package de.hfu;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test für Standard App.
 */
public class AppTest {
    /**
     * Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * Testet die Klasse Util auf richtige Funktionen
     */
    @Test
    public void utilTest() {
        assertTrue(Util.istErstesHalbjahr(6));
        assertTrue(Util.istErstesHalbjahr(1));
        assertFalse(Util.istErstesHalbjahr(12));
        assertFalse(Util.istErstesHalbjahr(7));

        try {
            boolean ignored = Util.istErstesHalbjahr(0);
            fail();
        } catch (Exception ignored) {
        }

        try {
            boolean ignored = Util.istErstesHalbjahr(13);
            fail();
        } catch (Exception ignored) {
        }

    }

    /**
     * Testet die Klasse Queue auf richtige Funktionen
     */
    @Test
    public void queueTest() {
        try {
            Queue q = new Queue(0);
            fail();
        } catch (Exception ignored) {
        }

        Queue queue = new Queue(3);

        queue.enqueue(2);

        assertEquals(2, queue.dequeue());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 4);

        try {
            queue.dequeue();
            fail();
        } catch (Exception ignored) {
        }

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);

        assertEquals(queue.dequeue(), 0);
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 7);

    }

    @Test
    public void residentRepositoryStubTest() {
        ResidentRepository stub = new ResidentRepository() {
            @Override
            public List<Resident> getResidents() {

                List<Resident> residentList = new ArrayList<>();

                residentList.add(new Resident("Max", "Mustermann", "Musterstrasse 1", "Musterbaum", new Date(2000, Calendar.JANUARY, 12)));
                residentList.add(new Resident("Marlene", "Maier", "Musterstrasse 2", "Musterbaum", new Date(2000, Calendar.JANUARY, 12)));
                residentList.add(new Resident("Mario", "Meyer", "Meyerstrasse 3", "Musterbaum", new Date(2000, Calendar.JANUARY, 12)));
                residentList.add(new Resident("Dieter", "Kinzel", "Musterstrasse 4", "Musterbaum", new Date(2000, Calendar.JANUARY, 13)));


                return residentList;
            }
        };

        BaseResidentService residentService = new BaseResidentService();
        residentService.setResidentRepository(stub);

        Resident filterResident = new Resident("Ma*", "M*", null, null, null);

        List l1 = residentService.getFilteredResidentsList(filterResident);

        filterResident.setGivenName(null);
        filterResident.setFamilyName(null);
        filterResident.setStreet("Musterstrasse*");

        List l2 = residentService.getFilteredResidentsList(filterResident);

        filterResident.setStreet("M*");
        List l3 = residentService.getFilteredResidentsList(filterResident);

        assertEquals(3, l1.size());

        assertEquals(3, l2.size());

        assertEquals(4, l3.size());


        try {
            residentService.getUniqueResident(filterResident);
            assert (false);
        } catch (ResidentServiceException e) {
        }

        filterResident.setGivenName("Dieter");
        filterResident.setStreet(null);
        try {
            residentService.getUniqueResident(filterResident);
        } catch (ResidentServiceException e) {
            fail();
            e.printStackTrace();
        }

        filterResident.setFamilyName("Kinzel");
        filterResident.setStreet("*");
        try {
            residentService.getUniqueResident(filterResident);
            assert (false);
        } catch (ResidentServiceException e) {
        }

        filterResident.setStreet("K*");
        List l4 = residentService.getFilteredResidentsList(filterResident);

        assertEquals(0, l4.size());

        filterResident.setStreet(null);
        filterResident.setDateOfBirth(new Date(2000, Calendar.JANUARY, 13));

        try {
            Resident resident = residentService.getUniqueResident(filterResident);
            assertEquals("Dieter", resident.getGivenName());
            assertEquals("Kinzel", resident.getFamilyName());
            assertEquals("Musterstrasse 4", resident.getStreet());
            assertEquals("Musterbaum", resident.getCity());
        } catch (ResidentServiceException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void residentRepositoryMockTest() {

        ResidentRepository mock = createMock(ResidentRepository.class);

        BaseResidentService residentService = new BaseResidentService();
        residentService.setResidentRepository(mock);

        Resident filterResident = new Resident("Ma*", "M*", null, null, null);

        List l1 = residentService.getFilteredResidentsList(filterResident);

        filterResident.setGivenName(null);
        filterResident.setFamilyName(null);
        filterResident.setStreet("Musterstrasse*");

        List l2 = residentService.getFilteredResidentsList(filterResident);

        filterResident.setStreet("M*");
        List l3 = residentService.getFilteredResidentsList(filterResident);

        assertEquals(3, l1.size());

        assertEquals(3, l2.size());

        assertEquals(4, l3.size());


        try {
            residentService.getUniqueResident(filterResident);
            assert (false);
        } catch (ResidentServiceException e) {
        }

        filterResident.setGivenName("Dieter");
        filterResident.setStreet(null);
        try {
            residentService.getUniqueResident(filterResident);
        } catch (ResidentServiceException e) {
            fail();
            e.printStackTrace();
        }

        filterResident.setFamilyName("Kinzel");
        filterResident.setStreet("*");
        try {
            residentService.getUniqueResident(filterResident);
            assert (false);
        } catch (ResidentServiceException e) {
        }

        filterResident.setStreet("K*");
        List l4 = residentService.getFilteredResidentsList(filterResident);

        assertEquals(0, l4.size());

        filterResident.setStreet(null);
        filterResident.setDateOfBirth(new Date(2000, Calendar.JANUARY, 13));

        try {
            Resident resident = residentService.getUniqueResident(filterResident);
            assertEquals("Dieter", resident.getGivenName());
            assertEquals("Kinzel", resident.getFamilyName());
            assertEquals("Musterstrasse 4", resident.getStreet());
            assertEquals("Musterbaum", resident.getCity());
        } catch (ResidentServiceException e) {
            fail();
            e.printStackTrace();
        }

    }

}

