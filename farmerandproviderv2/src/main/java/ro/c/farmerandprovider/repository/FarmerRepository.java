package ro.c.farmerandprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.c.farmerandprovider.models.Farmer;
import ro.c.farmerandprovider.models.FarmerStatistics;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
