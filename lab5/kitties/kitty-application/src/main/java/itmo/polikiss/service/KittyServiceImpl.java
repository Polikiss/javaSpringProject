package itmo.polikiss.service;

import itmo.polikiss.dto.FriendDto;
import itmo.polikiss.dto.KittyCreateDto;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.entity.Filter;
import itmo.polikiss.entity.Kitty;
import itmo.polikiss.dto.KittyColor;
import itmo.polikiss.repo.KittyRepo;
import lombok.experimental.ExtensionMethod;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional(readOnly = true)
@Service
@EnableRabbit
public class KittyServiceImpl implements KittyService {

    private final KittyRepo kittyRepo;

    @Autowired
    public KittyServiceImpl(KittyRepo kittyRepo) {
        this.kittyRepo = kittyRepo;
    }

    public KittyDto findKittyById(long id, long ownerId) {
        var kitty = kittyRepo.findById(id).orElseThrow(() -> new RuntimeException("Kitty is not found"));
        if(kitty.getOwnerId() == ownerId){
            return KittyMapper.mapToKittyDto(kitty);
        }
        throw new RuntimeException("Kitty is not found");
    }

    public KittyDto findKittyById(long id) {
        var kitty = kittyRepo.findById(id).orElseThrow(() -> new RuntimeException("Kitty is not found"));
        return KittyMapper.mapToKittyDto(kitty);
    }

    @RabbitListener(queues = "deleteKitty")
    @Transactional
    public void deleteKitty(long id) {
        var kitty = kittyRepo.findById(id).orElse(null);
        kittyRepo.delete(kitty);
    }

    @RabbitListener(queues = "createFriends")
    @Transactional
    public void makeFriend(FriendDto friendDto) {
        var firstKitty = kittyRepo.findById(friendDto.firstKittyId()).orElse(null);
        var kittyFriend = kittyRepo.findById(friendDto.kittyFriendId()).orElse(null);
        firstKitty.addKittyFriend(kittyFriend);
        kittyFriend.addKittyFriend(firstKitty);
        kittyRepo.save(firstKitty);
        kittyRepo.save(kittyFriend);
    }
    /*@RabbitListener(queues = "loose_friend")
    @Transactional
    public void looseFriend(FriendDto friendDto) {
        var kitty = kittyRepo.findById(friendDto.firstKittyId()).orElse(null);
        var kittyFriend = kittyRepo.findById(friendDto.kittyFriendId()).orElse(null);
        kitty.removeKittyFriend(kittyFriend);
        kittyFriend.removeKittyFriend(kitty);
        kittyRepo.save(kitty);
        kittyRepo.save(kittyFriend);
    }*/

    @RabbitListener(queues = "createKitty")
    @Transactional
    public KittyDto createKitty(KittyCreateDto kittyDto) {
        long ownerId = kittyDto.ownerId();
        Kitty kitty = new Kitty(
                ownerId,
                kittyDto.name(),
                kittyDto.bday(),
                kittyDto.color().toString(),
                kittyDto.breed()
        );
        kittyRepo.save(kitty);
        return KittyMapper.mapToKittyDto(kitty);
    }
    public List<KittyDto> findKittiesForAdmin(String name, String breed, String color) {
        KittyColor kittyColor = KittyColor.valueOf(color);
        List<Filter> filters = new ArrayList<>();
        if(name != null){
            filters.add(new Filter<>("name", name));
        }
        if(breed != null){
            filters.add(new Filter<>("breed", breed));
        }
        if(color != null){
            filters.add(new Filter<>("color", kittyColor));
        }

        List<Kitty> kitties;
        List<KittyDto> kittiesDto = new ArrayList<>();
        if(!filters.isEmpty()){
            var specification = getSpecificationFromFilters(filters);
            kitties = kittyRepo.findAll(specification);
        } else{
            kitties = kittyRepo.findAll();
        }


        for (Kitty kitty : kitties) {
            kittiesDto.add(KittyMapper.mapToKittyDto(kitty));
        }
        return kittiesDto;

    }


    public List<KittyDto> findKittiesForUser(String name, String breed, String color, Long id) {
        KittyColor kittyColor = KittyColor.valueOf(color);
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter<>("ownerId", id));
        if(name != null){
            filters.add(new Filter<>("name", name));
        }
        if(breed != null){
            filters.add(new Filter<>("breed", breed));
        }
        if(color != null){
            filters.add(new Filter<>("color", kittyColor));
        }
        List<Kitty> kitties;
        List<KittyDto> kittiesDto = new ArrayList<>();
        var specification = getSpecificationFromFilters(filters);
        kitties = kittyRepo.findAll(specification);
        for (Kitty kitty : kitties) {
            kittiesDto.add(KittyMapper.mapToKittyDto(kitty));
        }
        return kittiesDto;

    }

    private Specification<Kitty> createSpecification(Filter input) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(input.getField()),
                        "%"+input.getValue()+"%");
    }

    private Specification<Kitty> getSpecificationFromFilters(List<Filter> filter){
        Specification<Kitty> specification = createSpecification(filter.get(0));
        for (int i = 1; i < filter.size(); i++) {
            specification = specification.and(createSpecification(filter.get(i)));
        }
        return specification;
    }

}
