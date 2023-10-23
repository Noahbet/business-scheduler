package learn.scheduler.domain;

import learn.scheduler.data.BusinessRepository;
import learn.scheduler.models.Appointment;
import learn.scheduler.models.Availability;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BusinessServiceTest {

    @MockBean
    BusinessRepository repository;
    @Autowired
    BusinessService service;

    @Test
    void shouldFindBusinessesByQuery() {
        List<Business> expected = List.of(makeBusiness(), makeBusiness());
        when(repository.searchBusinesses("Store")).thenReturn(expected);

        List<Business> actual = service.searchBusinesses("Store");

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindBusinessesByCategory() {
        List<Business> expected = List.of(makeBusiness(), makeBusiness());
        when(repository.searchByCategory(Category.RESTAURANT)).thenReturn(expected);

        List<Business> actual = service.searchByCategory(Category.RESTAURANT);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidBusiness() {
        Business business = makeBusiness();
        Business expected = makeBusiness();
        expected.setBusinessId(1);

        when(repository.addBusiness(business)).thenReturn(expected);

        Result<Business> actual = service.addBusiness(business);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddWithSetBusinessId() {
        Business business = makeBusiness();
        business.setBusinessId(1);

        Result<Business> actual = service.addBusiness(business);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithEmptyName() {
        Business business = makeBusiness();
        business.setBusinessName("");

        Result<Business> actual = service.addBusiness(business);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroOwnerId() {
        Business business = makeBusiness();
        business.setOwnerId(0);

        Result<Business> actual = service.addBusiness(business);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddNull() {
        Result<Business> actual = service.addBusiness(null);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldUpdateValidBusiness() {
        Business business = makeBusiness();
        business.setBusinessId(1);
        business.setBusinessName("Ello");

        when(repository.updateBusinessName(1, "Ello")).thenReturn(true);

        Result<Business> actual = service.updateBusinessName(business);

        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateBusinessWithNoId() {
        Business business = makeBusiness();

        Result<Business> actual = service.updateBusinessName(business);

        assertFalse(actual.isSuccess());
    }

    private Business makeBusiness() {

        return new Business("Test", 1, Category.RESTAURANT);
    }
}
