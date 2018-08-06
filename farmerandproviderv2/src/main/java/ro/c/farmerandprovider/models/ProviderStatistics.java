package ro.c.farmerandprovider.models;

import javax.persistence.*;

@Entity
@Table(name="farmerandprovider_provider_statistics")
public class ProviderStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String serviceType;
    private long idProvider;
    private long idFarmer;
    private double totalMoneyAccumulated;

    public ProviderStatistics(long newIdProvider, long newIdFarmer, String type){
        idProvider=newIdProvider;
        idFarmer=newIdFarmer;
        serviceType=type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    public long getIdFarmer() {
        return idFarmer;
    }

    public void setIdFarmer(long idFarmer) {
        this.idFarmer = idFarmer;
    }

    public double getTotalMoneyAccumulated() {
        return totalMoneyAccumulated;
    }

    public void setTotalMoneyAccumulated(double totalMoneyAccumulated) {
        this.totalMoneyAccumulated = totalMoneyAccumulated;
    }
}
