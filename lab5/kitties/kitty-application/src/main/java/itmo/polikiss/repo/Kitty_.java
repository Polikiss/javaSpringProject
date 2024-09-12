package itmo.polikiss.repo;

import itmo.polikiss.entity.Kitty;
import itmo.polikiss.dto.KittyColor;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Kitty.class)
public class Kitty_ {
    public static volatile SingularAttribute<Kitty, String> name;
    public static volatile SingularAttribute<Kitty, Long> id;
    public static volatile SingularAttribute<Kitty, String> breed;
    public static volatile SingularAttribute<Kitty, KittyColor> color;
    public static volatile SingularAttribute<Kitty, Long> ownerId;
    public static final String NAME = "name";
    public static final String ID = "id";
    public static final String BREED = "breed";
    public static final String COLOR = "color";
    public static final String OWNERID = "owner_id";
}
