package itmo.polikiss;

import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.dto.OwnerDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OwnerClient {
    @Value("${upload.owner.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public OwnerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<OwnerDto> getAll(){
        OwnerDtoList ownerDtoList = restTemplate.getForObject(baseUrl, OwnerDtoList.class);
        return ownerDtoList != null ? ownerDtoList.owners() : null;
    }


    public OwnerDto getOwner(Long id){
        String resourceUrl = baseUrl + "/%s".formatted(id);
        return restTemplate.getForObject(resourceUrl, OwnerDto.class);
    }
}
