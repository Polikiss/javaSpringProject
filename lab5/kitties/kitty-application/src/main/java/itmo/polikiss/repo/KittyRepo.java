package itmo.polikiss.repo;


import itmo.polikiss.entity.Kitty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KittyRepo extends JpaRepository<Kitty, Long>, JpaSpecificationExecutor<Kitty> {
    List<Kitty> findAll(Specification<Kitty> spec);
}
