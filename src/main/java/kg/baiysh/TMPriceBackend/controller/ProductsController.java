package kg.baiysh.TMPriceBackend.controller;

import kg.baiysh.TMPriceBackend.dto.ProductDTO;
import kg.baiysh.TMPriceBackend.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("/api/getProducts")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductRepo productRepo;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProducts() throws IOException {
        log.info("Start getProducts");
        List<ProductDTO> productDTO = productRepo.getProductDTO();
        log.info("Finish getProducts size - {}", productDTO.size());
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        log.info("ip client {}", addr);
        return new ResponseEntity<>("Server is running, your ip " + addr, HttpStatus.OK);
    }
}
