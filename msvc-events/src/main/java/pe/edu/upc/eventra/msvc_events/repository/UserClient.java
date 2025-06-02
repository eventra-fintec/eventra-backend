package pe.edu.upc.eventra.msvc_events.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.upc.eventra.msvc_events.model.dtos.auxs.UserResponse;

@FeignClient(name = "msvc-users")
public interface UserClient {
    @GetMapping("api/users/{id}")
    UserResponse getUserById(@PathVariable("id") long id);
}

