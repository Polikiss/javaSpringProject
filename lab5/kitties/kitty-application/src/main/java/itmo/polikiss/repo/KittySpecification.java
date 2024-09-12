package itmo.polikiss.repo;

import itmo.polikiss.entity.Kitty;
import itmo.polikiss.dto.KittyColor;
import org.springframework.data.jpa.domain.Specification;

public class KittySpecification {
    private Specification<Kitty> nameLike(String name){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Kitty_.NAME), "%"+name+"%");
    }

    private Specification<Kitty> colorLike(KittyColor color){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Kitty_.COLOR), "%"+color+"%");
    }

    private Specification<Kitty> breedLike(String breed){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Kitty_.NAME), "%"+breed+"%");
    }

    private Specification<Kitty> idLike(Long id){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Kitty_.ID), "%"+id+"%");
    }

    private Specification<Kitty> ownerIdLike(Long ownerId){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Kitty_.OWNERID), "%"+ownerId+"%");
    }

}
