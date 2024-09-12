package itmo.polikiss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import itmo.polikiss.dto.KittyDto;
import itmo.polikiss.dto.OwnerDto;
import itmo.polikiss.models.KittyColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class KittyTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;
    private ObjectWriter ow;
    private KittyDto kittyDto1;
    private KittyDto kittyDto2;

    private OwnerDto owner;


    @BeforeEach
    public void init() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = mapper.writer().withDefaultPrettyPrinter();
        long id = 1;
        owner = new OwnerDto(id, "Den", Date.from(Instant.now()));
        kittyDto1 = new KittyDto(id, "Garfild", Date.from(Instant.now()), KittyColor.GINGER, "ylichniy", id);
        kittyDto2 = new KittyDto((id+1), "Angela", Date.from(Instant.now()), KittyColor.BLACK, "idk", id);
    }

    @Test
    @Sql("clean.sql")
    @WithMockUser(username="admin",roles={"ADMIN"})
    public void findKittyTest() throws Exception {
        var result = mockMvc.perform(get("/kitties/1")).andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("{kittyId: 1, name: \"Garfild\", breed: \"ylichniy\", color: \"GINGER\", ownerId: 1}",
                result, JSONCompareMode.LENIENT);

    }

    @Test
    @Sql("clean.sql")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void findByNameCatsTest() throws Exception {
        var result = mockMvc.perform(get("/kitties")
                        .param("name","Angela" )
                        .param("breed", "idk")
                        .param("color", "BLACK"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONAssert.assertEquals("[{kittyId: 2, name: \"Angela\", breed: \"idk\", color: \"BLACK\", ownerId: 1}]", result, JSONCompareMode.LENIENT);
    }
}

