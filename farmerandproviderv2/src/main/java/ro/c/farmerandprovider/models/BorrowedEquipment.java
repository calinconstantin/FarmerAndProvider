package ro.c.farmerandprovider.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name ="farmerandprovider_borrowedEquipment")
public class BorrowedEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private long idFarmer;
    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private long idProvider;

    private int forTilling=0;
    private int forSpraying=0;
    private int forHarvesting=0;
    private int forTransporting=0;

    public BorrowedEquipment(long idFarmer,long idProvider){
        this.idFarmer=idFarmer;
        this.idProvider=idProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getForTilling() {
        return forTilling;
    }

    public void setForTilling(int forTilling) {
        this.forTilling = forTilling;
    }

    public int getForSpraying() {
        return forSpraying;
    }

    public void setForSpraying(int forSpraying) {
        this.forSpraying = forSpraying;
    }

    public int getForHarvesting() {
        return forHarvesting;
    }

    public void setForHarvesting(int forHarvesting) {
        this.forHarvesting = forHarvesting;
    }

    public int getForTransporting() {
        return forTransporting;
    }

    public void setForTransporting(int forTransporting) {
        this.forTransporting = forTransporting;
    }
}
