package kg.baiysh.TMPriceBackend.rest;

import kg.baiysh.TMPriceBackend.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Service
@FeignClient(value = "RestClientUsers", url = "${base1c.url}", configuration = RestConfig.class)
public interface RestClientUsers {

    @RequestMapping(method = RequestMethod.GET, value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> getUsers();
}
