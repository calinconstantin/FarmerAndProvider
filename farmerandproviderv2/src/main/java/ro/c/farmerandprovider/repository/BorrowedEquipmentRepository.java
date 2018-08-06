package ro.c.farmerandprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.c.farmerandprovider.models.BorrowedEquipment;

@Repository
public interface BorrowedEquipmentRepository extends JpaRepository<BorrowedEquipment, Long> {
    @Query("select borrowedEquipment from BorrowedEquipment borrowedEquipment where borrowedEquipment.idFarmer = :idFarmer")
    BorrowedEquipment getByFarmerId(@Param("idFarmer") Long idFarmer);
}
