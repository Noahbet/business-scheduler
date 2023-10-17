package learn.scheduler.models;

import java.math.BigDecimal;

public class Service {

    private int serviceId;
    private String serviceName;
    private int totalTimeLength;
    private BigDecimal cost;

    public Service() {
    }

    public Service(int serviceId, String serviceName, int totalTimeLength, BigDecimal cost) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.totalTimeLength = totalTimeLength;
        this.cost = cost;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getTotalTimeLength() {
        return totalTimeLength;
    }

    public void setTotalTimeLength(int totalTimeLength) {
        this.totalTimeLength = totalTimeLength;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
