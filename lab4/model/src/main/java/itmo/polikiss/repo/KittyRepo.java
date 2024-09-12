package itmo.polikiss.repo;

import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.KittyColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KittyRepo extends JpaRepository<Kitty, Long> {
    List<Kitty> findKittiesByName(String name);
    List<Kitty> findKittiesByBreed(String breed);
    List<Kitty> findKittiesByColor(KittyColor color);
    List<Kitty> findKittiesByNameAndBreedAndColor(String name, String breed, KittyColor color);
    List<Kitty> findKittiesByNameAndBreed(String name, String breed);
    List<Kitty> findKittiesByNameAndColor(String name, KittyColor color);
    List<Kitty> findKittiesByColorAndBreed(KittyColor color, String breed);
    List<Kitty> findKittiesByNameAndOwnerId(String name, Long ownerId);
    List<Kitty> findKittiesByBreedAndOwnerId(String breed, Long ownerId);
    List<Kitty> findKittiesByColorAndOwnerId(KittyColor color, Long ownerId);
    List<Kitty> findKittiesByNameAndBreedAndColorAndOwnerId(String name, String breed, KittyColor color, Long ownerId);
    List<Kitty> findKittiesByNameAndBreedAndOwnerId(String name, String breed, Long ownerId);
    List<Kitty> findKittiesByNameAndColorAndOwnerId(String name, KittyColor color, Long ownerId);
    List<Kitty> findKittiesByColorAndBreedAndOwnerId(KittyColor color, String breed, Long ownerId);
    List<Kitty> findKittiesByOwnerId(Long ownerId);

}
