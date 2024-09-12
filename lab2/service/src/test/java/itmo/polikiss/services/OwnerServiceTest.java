package itmo.polikiss.services;

import itmo.polikiss.dao.KittyDao;
import itmo.polikiss.dao.OwnerDao;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceTest {
    /*@Test
    public void getFriendTest() {
        Owner owner = new Owner("Jon", LocalDate.parse("2000-01-01"));
        Kitty kitty = new Kitty(owner, "Garfild", LocalDate.parse("2020-01-01"), "gg", "grey");
        Kitty kitty1 = new Kitty(owner, "Matroskin", LocalDate.parse("2020-01-01"), "gg", "grey");
        KittyDao mockKittyDao = Mockito.mock(KittyDao.class);
        KittyService mockKittyService = new KittyService(mockKittyDao);
        mockKittyService.getFriend(MappingUtils.mapToKittyDto(kitty), MappingUtils.mapToKittyDto(kitty1));
        Assertions.assertTrue(kitty.getKittyFriends().contains(kitty1));
        Assertions.assertTrue(kitty1.getKittyFriends().contains(kitty));
    }
    @Test
    public void getKittyTest() {
        KittyDao mockKittyDao = Mockito.mock(KittyDao.class);
        KittyService mockKittyService = new KittyService(mockKittyDao);
        OwnerDao mockOwnerDao = Mockito.mock(OwnerDao.class);
        OwnerService mockOwnerService = new OwnerService(mockOwnerDao, mockKittyService);
        Owner owner = new Owner("Jon", LocalDate.parse("2000-01-01"));
        mockOwnerService.getKitty(MappingUtils.mapToOwnerDto(owner), "Garfild", LocalDate.parse("2020-01-01"), "gg", "grey");
        Kitty kitty = owner.getKitties().getFirst();
        assertEquals("Garfild", kitty.getName());
    }
    @Test
    public void looseKitty(){

        KittyDao mockKittyDao = Mockito.mock(KittyDao.class);
        KittyService mockKittyService = new KittyService(mockKittyDao);
        OwnerDao mockOwnerDao = Mockito.mock(OwnerDao.class);
        OwnerService mockOwnerService = new OwnerService(mockOwnerDao, mockKittyService);
        Owner owner = new Owner("Jon", LocalDate.parse("2000-01-01"));
        mockOwnerService.getKitty(MappingUtils.mapToOwnerDto(owner), "Garfild", LocalDate.parse("2020-01-01"), "gg", "grey");
        Kitty kitty = owner.getKitties().getFirst();
        Assertions.assertDoesNotThrow(() -> mockOwnerService.looseKitty(MappingUtils.mapToOwnerDto(owner), kitty));
        Assertions.assertEquals(0, owner.getKitties().size());

    }
    @Test
    public void giveAwayKitty(){

        KittyDao mockKittyDao = Mockito.mock(KittyDao.class);
        KittyService mockKittyService = new KittyService(mockKittyDao);
        OwnerDao mockOwnerDao = Mockito.mock(OwnerDao.class);
        OwnerService mockOwnerService = new OwnerService(mockOwnerDao, mockKittyService);
        Owner owner = new Owner("Jon", LocalDate.parse("2000-01-01"));
        Owner owner2 = new Owner("Jabe", LocalDate.parse("2000-01-01"));
        mockOwnerService.getKitty(MappingUtils.mapToOwnerDto(owner), "Garfild", LocalDate.parse("2020-01-01"), "gg", "grey");
        Kitty kitty = owner.getKitties().getFirst();
        mockOwnerService.giveAwayKitty(owner, kitty, owner2);
        Assertions.assertEquals(0, owner.getKitties().size());
        Assertions.assertEquals(1, owner2.getKitties().size());
    }*/
}