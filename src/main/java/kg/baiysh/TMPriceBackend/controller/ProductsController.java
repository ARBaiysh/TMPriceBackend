package kg.baiysh.TMPriceBackend.controller;

import kg.baiysh.TMPriceBackend.rest.RestClientProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("/api/getProducts")
@RequiredArgsConstructor
public class ProductsController {
    private final RestClientProducts restClientProducts;

    @GetMapping()
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SALESMAN','DEALER')")
    public ResponseEntity<Object> getProducts(HttpServletRequest request) {
        ResponseEntity<Object> responseEntity = null;
        try {
            responseEntity = restClientProducts.get();
            log.info("server response status 1s code{}", responseEntity.getStatusCode());
            String addr = request.getRemoteAddr();
            log.info("ip client {}", addr);
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
    }

    @GetMapping("/check")
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER','SALESMAN','DEALER')")
    public ResponseEntity<String> check(HttpServletRequest request) {
        String addr = request.getRemoteAddr();
        log.info("ip client {}", addr);
        return new ResponseEntity<>("Server is running, your ip " + addr, HttpStatus.OK);
    }
}
