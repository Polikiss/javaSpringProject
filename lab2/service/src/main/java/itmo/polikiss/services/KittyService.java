package itmo.polikiss.services;

import itmo.polikiss.dao.KittyDao;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class KittyService {
    private final KittyDao kittyDao ;

    public KittyService(KittyDao kittyDao) {

        this.kittyDao = kittyDao;
    }

    public KittyDto findKittyById(int id) {
        return MappingUtils.mapToKittyDto(kittyDao.findById(id));
    }
    public KittyDto findKittyByName(String name){
        List<Kitty> list = findAllKitties();
        for (Kitty kitty : list) {
            String curName = kitty.getName();
            if (curName.equals(name)) {
                return MappingUtils.mapToKittyDto(kitty);
            }
        }
        throw new RuntimeException("Kitty is not found");
    }

    public KittyDto findKittyByName(String name, Owner owner){
        List<Kitty> list = findAllKitties();
        for (Kitty kitty : list) {
            if (kitty.getName().equals(name) && kitty.getOwner().equals(owner)) {
                return MappingUtils.mapToKittyDto(kitty);
            }
        }
        throw new RuntimeException("Kitty is not found");
    }

    public void saveKitty(Kitty kitty) {

        var session = kittyDao.getSession();
        Transaction tx1 = session.beginTransaction();
        kittyDao.save(kitty, session);
        tx1.commit();
    }

    public void deleteKitty(Kitty kitty) {
        var session = kittyDao.getSession();
        Transaction tx1 = session.beginTransaction();
        kittyDao.delete(kitty, session);
        tx1.commit();
    }
    public void getFriend(KittyDto firstKittyDto, KittyDto kittyFriendDto){
        var firstKitty = MappingUtils.mapToKitty(firstKittyDto);
        var kittyFriend = MappingUtils.mapToKitty(kittyFriendDto);
        firstKitty.addKittyFriend(kittyFriend);
        kittyFriend.addKittyFriend(firstKitty);
        var session = kittyDao.getSession();
        Transaction tx1 = session.beginTransaction();
        kittyDao.update(firstKitty, session);
        kittyDao.update(kittyFriend, session);
        tx1.commit();
    }

    public void getFriend(Kitty firstKitty, Kitty kittyFriend){
        firstKitty.addKittyFriend(kittyFriend);
        kittyFriend.addKittyFriend(firstKitty);
    }

    public void looseFriend(Kitty kitty,  Kitty kittyFriend){
        kitty.removeKittyFriend(kittyFriend);
        kittyFriend.removeKittyFriend(kitty);
    }

    public void looseFriend(KittyDto kittyDto,  KittyDto kittyFriendDto){
        var kitty = MappingUtils.mapToKitty(kittyDto);
        var kittyFriend = MappingUtils.mapToKitty(kittyFriendDto);
        kitty.removeKittyFriend(kittyFriend);
        kittyFriend.removeKittyFriend(kitty);
        var session = kittyDao.getSession();
        Transaction tx1 = session.beginTransaction();
        kittyDao.update(kitty, session);
        kittyDao.update(kittyFriend, session);
        tx1.commit();
    }
    public KittyDto createKitty(Owner owner, String name, LocalDate bday, String breed, String color){
        Kitty kitty = new Kitty(owner, name, bday, color, breed);
        saveKitty(kitty);
        return MappingUtils.mapToKittyDto(kitty);
    }

    public void updateKitty(Kitty kitty) {

        var session = kittyDao.getSession();
        Transaction tx1 = session.beginTransaction();
        kittyDao.update(kitty, session);
        tx1.commit();
    }

    public List<Kitty> findAllKitties() {
        return kittyDao.findAll();
    }
}
