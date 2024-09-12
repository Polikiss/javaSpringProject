package itmo.polikiss.service;


import itmo.polikiss.KittyClient;
import itmo.polikiss.dto.KittyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GatewayServiceKitty {
    private final KittyClient kittyClient;
    private final SecurityService securityService;

    public GatewayServiceKitty(KittyClient kittyClient, SecurityService securityService) {
        this.kittyClient = kittyClient;
        this.securityService = securityService;
    }

    public KittyDto findKittyByIdUser(long id){
        var ownerId = securityService.getOwnerId();
        return kittyClient.getKitty(id, ownerId);
    }

    public KittyDto findKittyByIdAdmin(long id){
        return kittyClient.getKittyAdmin(id);
    }

    public List<KittyDto> findKittiesForUser(String name, String breed, String color){
        var ownerId = securityService.getOwnerId();
        return kittyClient.getAll(ownerId, name, breed, color);

    }
    public List<KittyDto> findKittiesForAdmin(String name, String breed, String color){
        return kittyClient.getAllForAdmin(name, breed, color);
    }
}
