package com.hernaval.ctpn.service.impl;

import com.hernaval.ctpn.domain.Client;
import com.hernaval.ctpn.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    public UserDetailServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client =
            this.clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No client found with username %s", username)));

        return new UserDetailsImpl(client.getUsername(), client.getPassword());
    }
}
