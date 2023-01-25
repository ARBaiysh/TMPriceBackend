package kg.baiysh.TMPriceBackend.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Service
@FeignClient(value = "RestClientProducts", url = "${base1c.url}", configuration = RestConfig.class)
public interface RestClientProducts {

    @RequestMapping(method = RequestMethod.GET, value = "/getProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> get();
}
