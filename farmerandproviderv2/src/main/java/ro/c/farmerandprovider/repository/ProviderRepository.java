package ro.c.farmerandprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.c.farmerandprovider.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
