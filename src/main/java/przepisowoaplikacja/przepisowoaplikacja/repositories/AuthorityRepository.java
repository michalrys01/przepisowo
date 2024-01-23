package przepisowoaplikacja.przepisowoaplikacja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import przepisowoaplikacja.przepisowoaplikacja.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
