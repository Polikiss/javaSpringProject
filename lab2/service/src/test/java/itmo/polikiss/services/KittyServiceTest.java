package itmo.polikiss.services;

import itmo.polikiss.dao.KittyDao;
import itmo.polikiss.dto.MappingUtils;
import itmo.polikiss.models.Kitty;
import itmo.polikiss.models.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class KittyServiceTest {
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
    public void looseFriendTest() {
        Owner owner = new Owner("Jon", LocalDate.parse("2000-01-01"));
        Kitty kitty = new Kitty(owner, "Garfild", LocalDate.parse("2020-01-01"), "gg", "grey");
        KittyDao mockKittyDao = Mockito.mock(KittyDao.class);
        KittyService mockKittyService = new KittyService(mockKittyDao);
        Kitty kitty1 = new Kitty(owner, "Matroskin", LocalDate.parse("2020-01-01"), "gg", "grey");
        mockKittyService.getFriend(MappingUtils.mapToKittyDto(kitty), MappingUtils.mapToKittyDto(kitty1));
        mockKittyService.looseFriend(MappingUtils.mapToKittyDto(kitty), MappingUtils.mapToKittyDto(kitty1));
        Assertions.assertFalse(kitty.getKittyFriends().contains(kitty1));
        Assertions.assertFalse(kitty1.getKittyFriends().contains(kitty));
    }*/

}