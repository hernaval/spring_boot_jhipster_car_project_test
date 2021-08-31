package com.hernaval.ctpn.web.rest.auth;

import com.hernaval.ctpn.security.jwt.TokenProvider;
import com.hernaval.ctpn.service.ClientService;
import com.hernaval.ctpn.service.dto.ClientDTO;
import com.hernaval.ctpn.web.rest.ClientResource;
import com.hernaval.ctpn.web.rest.errors.BadRequestAlertException;
import io.undertow.util.BadRequestException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);
    private final ClientService clientService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenProvider tokenProvider;

    public AuthResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginClient(@RequestBody ClientDTO clientDTO) {
        log.debug("received client dto for login {}", clientDTO);

        Optional<?> client = clientService.findByUsername(clientDTO.getUsername());

        if (!client.isPresent()) {
            throw new UsernameNotFoundException(String.format("No Client with username %s", clientDTO.getUsername()));
        }

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(clientDTO.getUsername(), clientDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = tokenProvider.createToken(auth, true);

        log.info(jwt);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<ClientDTO> signupClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException, BadRequestException {
        log.debug("REST request to save Client : {}", clientDTO);
        if (clientDTO.getId() != null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", "CLIENT", "id exists");
        }
        ClientDTO result = clientService.signup(clientDTO);
        return ResponseEntity.created(new URI("/api/signup/" + result.getId())).body(result);
    }
}
