package itmo.polikiss;

import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.KittyListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class KittyClient {
    @Value("${upload.kitty.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public KittyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<KittyDto> getAll(Long ownerId, String name, String breed, String color){
        KittyListDto kittyListDto = restTemplate.getForObject(baseUrl, KittyListDto.class, name, breed, color, ownerId);
        return kittyListDto != null ? kittyListDto.kitties() : null;
    }

    public List<KittyDto> getAllForAdmin(String name, String breed, String color){
        KittyListDto kittyListDto = restTemplate.getForObject(baseUrl, KittyListDto.class, name, breed, color);
        return kittyListDto != null ? kittyListDto.kitties() : null;
    }

    public KittyDto getKitty(Long id, Long ownerId){
        String resourceUrl = baseUrl + "/%s".formatted(id);
        return restTemplate.getForObject(resourceUrl, KittyDto.class, ownerId);
    }

    public KittyDto getKittyAdmin(Long id){
        String resourceUrl = baseUrl + "/%s".formatted(id);
        return restTemplate.getForObject(resourceUrl, KittyDto.class);
    }
}
