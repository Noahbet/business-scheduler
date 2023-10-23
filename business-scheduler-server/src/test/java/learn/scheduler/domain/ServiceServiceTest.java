package learn.scheduler.domain;

import learn.scheduler.data.BusinessRepository;
import learn.scheduler.data.ServiceRepository;
import learn.scheduler.models.Business;
import learn.scheduler.models.Category;
import learn.scheduler.models.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceServiceTest {

    @MockBean
    ServiceRepository repository;
    @MockBean
    BusinessRepository businessRepository;
    @Autowired
    ServiceService serviceService;

    @Test
    void shouldFindByBusinessId() {
        List<Service> expected = List.of(makeService(), makeService());
        when(repository.getServicesForBusiness(1)).thenReturn(expected);

        List<Service> actual = serviceService.getServicesForBusiness(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByServiceId() {
        Service expected = makeService();
        when(repository.getServiceById(1)).thenReturn(expected);

        Service actual = serviceService.getServiceById(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidService() {
        Service service = makeService();
        Service expected = makeService();

        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(repository.addService(service)).thenReturn(expected);

        Result<Service> actual = serviceService.addService(service);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddWithInvalidBusinessId() {
        Service service = makeService();

        when(businessRepository.searchById(1)).thenReturn(null);
        Result<Service> actual = serviceService.addService(service);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroBusinessId() {
        Service service = makeService();
        service.setBusinessId(0);

        Result<Service> actual = serviceService.addService(service);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithBlankName() {
        Service service = makeService();
        service.setServiceName("");

        Result<Service> actual = serviceService.addService(service);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroTimeLength() {
        Service service = makeService();
        service.setServiceId(0);

        Result<Service> actual = serviceService.addService(service);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddWithZeroCost() {
        Service service = makeService();
        service.setCost(new BigDecimal(0));

        Result<Service> actual = serviceService.addService(service);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldUpdateValidService() {
        Service service = makeService();
        service.setServiceId(1);
        service.setServiceName("Ello");

        when(businessRepository.searchById(1)).thenReturn(makeBusiness());
        when(repository.updateService(service)).thenReturn(true);

        Result<Service> actual = serviceService.updateService(service);

        assertTrue(actual.isSuccess());
    }

    @Test
    void shouldNotUpdateServiceWithNoId() {
        Service service = makeService();

        Result<Service> actual = serviceService.updateService(service);

        assertFalse(actual.isSuccess());
    }

    private Service makeService() {

        return new Service("Service C", 1, 30, new BigDecimal(90));
    }

    private Business makeBusiness() {

        return new Business(1, "Test", 1, Category.RESTAURANT);
    }
}
