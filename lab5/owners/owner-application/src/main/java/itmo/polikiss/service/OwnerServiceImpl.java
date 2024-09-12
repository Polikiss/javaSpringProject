package itmo.polikiss.service;

import itmo.polikiss.KittyClient;
import itmo.polikiss.dto.OwnerCreateDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.entity.Owner;
import itmo.polikiss.repo.OwnerRepo;
import lombok.experimental.ExtensionMethod;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
@EnableRabbit
@ExtensionMethod(OwnerMapper.class)
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepo ownerRepo;
    private final KittyClient kittyClient;
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    public OwnerServiceImpl(OwnerRepo ownerRepo, KittyClient kittyClient,  RabbitTemplate rabbitTemplate) {
        this.ownerRepo = ownerRepo;
        this.kittyClient = kittyClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "createOwner")
    @Transactional
    public void CreateOwner(OwnerCreateDto ownerCreateDto) {
        var owner = new Owner(ownerCreateDto.name(), ownerCreateDto.birthDate());
        ownerRepo.save(owner);
    }

    @RabbitListener(queues = "deleteOwner")
    @Transactional
    public void deleteOwner(Long id) {
        var owner = ownerRepo.findById(id).orElse(null);
        var kitties = kittyClient.getAll(id, null, null, null);
        for (var kitty: kitties){
            rabbitTemplate.convertAndSend("deleteKitty", kitty.id());
        }
        ownerRepo.delete(owner);
    }

    public OwnerDto findOwnerById(long id) {
        var owner = ownerRepo.findById(id).orElseThrow(() -> new RuntimeException("owner not found"));
        return OwnerMapper.mapToOwnerDto(owner);
    }

    public List<OwnerDto> findAllOwners() {
        var owners = ownerRepo.findAll();
        List<OwnerDto> ownersDto = new ArrayList<>();
        for (Owner owner : owners) {
            ownersDto.add(OwnerMapper.mapToOwnerDto(owner));
        }
        return ownersDto;
    }


}
