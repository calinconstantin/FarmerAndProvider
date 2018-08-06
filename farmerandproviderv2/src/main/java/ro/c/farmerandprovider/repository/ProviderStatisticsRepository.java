package ro.c.farmerandprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.c.farmerandprovider.models.ProviderStatistics;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProviderStatisticsRepository extends JpaRepository<ProviderStatistics, Long> {
    @Query("select providerStatistics from ProviderStatistics providerStatistics where" +
            " providerStatistics.idFarmer = :idFarmer, providerStatistics.idProvider = :idProvider and providerStatistics.typeUtility = :type")
    ProviderStatistics getStatsThatAlreadyExists(@Param("idFarmer") Long idFarmer,
                                                 @Param("idProvider") Long idProvider,
                                                 @Param("type") String type);
    @Query("select providerStatistics from ProviderStatistics providerStatistics where providerStatistics.idProvider = :idProvider")
    List<ProviderStatistics> getByProviderId(@Param("idProvider") Long idProvider);
}
