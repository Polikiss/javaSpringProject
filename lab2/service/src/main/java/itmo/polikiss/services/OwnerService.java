package itmo.polikiss.services;

import itmo.polikiss.dao.OwnerDao;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class OwnerService {
    private final OwnerDao ownerDao;
    private final KittyService kittyService;

    public OwnerService(OwnerDao ownerDao, KittyService kittyService) {
        this.ownerDao = ownerDao;
        this.kittyService = kittyService;
    }

    public OwnerDto findOwnerById(int id) {
        return MappingUtils.mapToOwnerDto(ownerDao.findById(id));
    }
    public OwnerDto findOwnerByName(String name){
        var list = findAllOwners();
        for (var owner : list) {
            if (owner.getName().equals(name)) {
                return MappingUtils.mapToOwnerDto(owner);
            }
        }
        throw new RuntimeException("owner not found");
    }

    public void saveOwner(Owner owner) {
        var session = ownerDao.getSession();
        Transaction tx1 = session.beginTransaction();
        ownerDao.save(owner, session);
        tx1.commit();
    }

    public void deleteOwner(Owner owner) {
        var session = ownerDao.getSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(owner);
        tx1.commit();
    }

    public void updateOwner(Owner owner) {
        var session = ownerDao.getSession();
        Transaction tx1 = session.beginTransaction();
        ownerDao.save(owner, session);
        tx1.commit();
    }

    public void getKitty(OwnerDto ownerDto, String kittyName, LocalDate bday, String breed, String color) {
        var owner = MappingUtils.mapToOwner(ownerDto);
        Kitty kitty = MappingUtils.mapToKitty(kittyService.createKitty(owner, kittyName, bday, breed, color));
        owner.addKitty(kitty);
        updateOwner(owner);
    }
    public void looseKitty(OwnerDto ownerDto, String kittyName){
        var owner = MappingUtils.mapToOwner(ownerDto);
        Kitty kitty = MappingUtils.mapToKitty(kittyService.findKittyByName(kittyName));
        kittyService.deleteKitty(kitty);
        owner.removeKitty(kitty);
        updateOwner(owner);
    }
    public void looseKitty(OwnerDto ownerDto, Kitty kitty){
        var owner = MappingUtils.mapToOwner(ownerDto);
        kittyService.deleteKitty(kitty);
        owner.removeKitty(kitty);
        updateOwner(owner);
    }
    public void looseKitty(Owner owner, String kittyName){
        Kitty kitty = MappingUtils.mapToKitty(kittyService.findKittyByName(kittyName));
        kittyService.deleteKitty(kitty);
        owner.removeKitty(kitty);
        updateOwner(owner);
    }
    public void looseKitty(Owner owner, Kitty kitty){
        kittyService.deleteKitty(kitty);
        owner.removeKitty(kitty);
        updateOwner(owner);
    }
    public void giveAwayKitty(OwnerDto ownerDto, String kittyName, String ownerName){
        var owner = MappingUtils.mapToOwner(ownerDto);
        Kitty kitty = MappingUtils.mapToKitty(kittyService.findKittyByName(kittyName, owner));
        Owner newOwner = MappingUtils.mapToOwner(findOwnerByName(ownerName));
        giveAwayKitty(owner, kitty, newOwner);
    }

    public void giveAwayKitty(Owner owner, Kitty kitty, Owner newOwner){
        kitty.setOwner(newOwner);
        owner.removeKitty(kitty);
        newOwner.addKitty(kitty);
        var session = ownerDao.getSession();
        Transaction tx1 = session.beginTransaction();
        ownerDao.save(owner, session);
        ownerDao.save(newOwner, session);
        tx1.commit();
        kittyService.updateKitty(kitty);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }


}
