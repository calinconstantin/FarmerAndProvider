package ro.c.farmerandprovider.models;

import javax.persistence.*;

@Entity
@Table(name="farmerandprovider_farmer_statistics")
public class FarmerStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idFarmer;
    private long idProvider;
    private double totalHarvesting;
    private double totalTilling;
    private double totalSpraying;
    private double totalTransporting;

    public FarmerStatistics(long idFarmer,long idProvider){
        this.idFarmer=idFarmer;
        this.idProvider=idProvider;
    }

    public long getIdFarmer() {
        return idFarmer;
    }

    public void setIdFarmer(long idFarmer) {
        this.idFarmer = idFarmer;
    }

    public long getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(long idProvider) {
        this.idProvider = idProvider;
    }

    public double getTotalHarvesting() {
        return totalHarvesting;
    }

    public void setTotalHarvesting(double totalHarvesting) {
        this.totalHarvesting = totalHarvesting;
    }

    public double getTotalTilling() {
        return totalTilling;
    }

    public void setTotalTilling(double totalTilling) {
        this.totalTilling = totalTilling;
    }

    public double getTotalSpraying() {
        return totalSpraying;
    }

    public void setTotalSpraying(double totalSpraying) {
        this.totalSpraying = totalSpraying;
    }

    public double getTotalTransporting() {
        return totalTransporting;
    }

    public void setTotalTransporting(double totalTransporting) {
        this.totalTransporting = totalTransporting;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
