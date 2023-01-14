package kg.baiysh.TMPriceBackend.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.baiysh.TMPriceBackend.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepo {

    public List<ProductDTO> getProductDTO() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("response.json");
        ProductDTO[] productDTOS = mapper.readValue(resource.getFile(), ProductDTO[].class);
        return new ArrayList<>(List.of(productDTOS));
    }
}
