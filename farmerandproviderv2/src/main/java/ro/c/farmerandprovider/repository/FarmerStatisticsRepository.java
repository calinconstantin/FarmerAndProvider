package ro.c.farmerandprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.c.farmerandprovider.models.FarmerStatistics;

import java.util.List;

@Repository
public interface FarmerStatisticsRepository extends JpaRepository<FarmerStatistics, Long> {
    @Query("select farmerStatistics from FarmerStatistics farmerStatistics where" +
            " farmerStatistics.idFarmer = :idFarmer and farmerStatistics.idProvider = :idProvider")
    FarmerStatistics getByIdFarmerAndIdProvider(@Param("idFarmer") Long idFarmer,
                                                @Param("idProvider") Long idProvider);
    @Query("select farmerStatistics from FarmerStatistics farmerStatistics where" +
            " farmerStatistics.idFarmer = :idFarmer ")
    List<FarmerStatistics> getByIdFarmer(@Param("idFarmer") Long idFarmer);
}
