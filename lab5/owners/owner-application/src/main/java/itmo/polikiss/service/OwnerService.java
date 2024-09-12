package itmo.polikiss.service;

import itmo.polikiss.dto.OwnerCreateDto;
import itmo.polikiss.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    OwnerDto findOwnerById(long id);
    void CreateOwner(OwnerCreateDto ownerCreateDto);
    void deleteOwner(Long id);
    List<OwnerDto> findAllOwners();

}
