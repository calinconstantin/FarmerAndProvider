package ro.c.farmerandprovider.models;

import javax.persistence.*;

@Entity
@Table(name = "farmerandprovider_provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private int priceTilling;
    private int equipmentTilling;
    private int priceHarvesting;
    private int equipmentHarvesting;
    private int priceSpraying;
    private int equipmentSpraying;
    private int priceTransporting;
    private int equipmentTransporting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriceTilling() {
        return priceTilling;
    }

    public void setPriceTilling(int priceTilling) {
        this.priceTilling = priceTilling;
    }

    public int getEquipmentTilling() {
        return equipmentTilling;
    }

    public void setEquipmentTilling(int equipmentTilling) {
        this.equipmentTilling = equipmentTilling;
    }

    public int getPriceHarvesting() {
        return priceHarvesting;
    }

    public void setPriceHarvesting(int priceHarvesting) {
        this.priceHarvesting = priceHarvesting;
    }

    public int getEquipmentHarvesting() {
        return equipmentHarvesting;
    }

    public void setEquipmentHarvesting(int equipmentHarvesting) {
        this.equipmentHarvesting = equipmentHarvesting;
    }

    public int getPriceSpraying() {
        return priceSpraying;
    }

    public void setPriceSpraying(int priceSpraying) {
        this.priceSpraying = priceSpraying;
    }

    public int getEquipmentSpraying() {
        return equipmentSpraying;
    }

    public void setEquipmentSpraying(int equipmentSpraying) {
        this.equipmentSpraying = equipmentSpraying;
    }

    public int getPriceTransporting() {
        return priceTransporting;
    }

    public void setPriceTransporting(int priceTransporting) {
        this.priceTransporting = priceTransporting;
    }

    public int getEquipmentTransporting() {
        return equipmentTransporting;
    }

    public void setEquipmentTransporting(int equipmentTransporting) {
        this.equipmentTransporting = equipmentTransporting;
    }
}
