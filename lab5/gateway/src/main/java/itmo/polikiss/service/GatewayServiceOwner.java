package itmo.polikiss.service;

import itmo.polikiss.OwnerClient;
import itmo.polikiss.dto.OwnerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayServiceOwner {
    private final OwnerClient ownerClient;

    public GatewayServiceOwner(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }

    public OwnerDto findOwnerById(long id){
        return ownerClient.getOwner(id);
    }
    public List<OwnerDto> findAllOwners(){
        return ownerClient.getAll();
    }
}
