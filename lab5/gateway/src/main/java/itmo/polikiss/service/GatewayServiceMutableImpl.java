package itmo.polikiss.service;

import itmo.polikiss.dto.FriendDto;
import itmo.polikiss.dto.KittyCreateDto;
import itmo.polikiss.dto.OwnerCreateDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GatewayServiceMutableImpl {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public GatewayServiceMutableImpl(RabbitTemplate rabbitTemplate){

        this.rabbitTemplate = rabbitTemplate;
    }

    public void createKitty(KittyCreateDto kittyCreateDto) {

        rabbitTemplate.convertAndSend("createKitty", kittyCreateDto);
    }

    public void createOwner(OwnerCreateDto ownerCreateDto) {
        rabbitTemplate.convertAndSend("createOwner", ownerCreateDto);
    }

    public void deleteKitty(Long kittyId) {
        rabbitTemplate.convertAndSend("deleteKitty", kittyId);
    }

    public void deleteOwner(Long ownerId) {
        rabbitTemplate.convertAndSend("deleteOwner", ownerId);
    }

    public void kittyMakeFriend(FriendDto friendDto) {
        rabbitTemplate.convertAndSend("makeFriend", friendDto);
    }

    public void kittyLooseFriend(FriendDto friendDto) {
        rabbitTemplate.convertAndSend("makeLoose", friendDto);
    }

}
