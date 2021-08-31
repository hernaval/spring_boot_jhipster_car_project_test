package com.hernaval.ctpn.service.impl;

import com.hernaval.ctpn.domain.Client;
import com.hernaval.ctpn.domain.Role;
import com.hernaval.ctpn.domain.enumeration.ERole;
import com.hernaval.ctpn.repository.ClientRepository;
import com.hernaval.ctpn.service.ClientService;
import com.hernaval.ctpn.service.dto.ClientDTO;
import com.hernaval.ctpn.service.mapper.ClientMapper;
import io.undertow.util.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    PasswordEncoder encoder;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO save(ClientDTO clientDTO) {
        log.debug("Request to update Client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);

        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO signup(ClientDTO clientDTO) throws BadRequestException {
        clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
        log.debug("Request to save Client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        if (clientRepository.existsByUsername(clientDTO.getUsername()) || clientRepository.existsByEmail(clientDTO.getEmail())) {
            throw new BadRequestException("Client with username or email is already exist");
        }

        Set<Role> roles = new HashSet<>();
        Role roleAdmin = new Role();
        roleAdmin.setName(ERole.ROLE_ADMIN);
        roles.add(roleAdmin);
        client.setRoles(roles);

        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public Optional<ClientDTO> partialUpdate(ClientDTO clientDTO) {
        log.debug("Request to partially update Client : {}", clientDTO);

        return clientRepository
            .findById(clientDTO.getId())
            .map(
                existingClient -> {
                    clientMapper.partialUpdate(existingClient, clientDTO);

                    return existingClient;
                }
            )
            .map(clientRepository::save)
            .map(clientMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> findAll() {
        log.debug("Request to get all Clients");
        return clientRepository
            .findAllWithEagerRelationships()
            .stream()
            .map(clientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ClientDTO> findAllWithEagerRelationships(Pageable pageable) {
        return clientRepository.findAllWithEagerRelationships(pageable).map(clientMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDTO> findOne(Long id) {
        log.debug("Request to get Client : {}", id);
        return clientRepository.findOneWithEagerRelationships(id).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDTO> findByUsername(String username) {
        return clientRepository.findByUsername(username).map(clientMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Client : {}", id);
        clientRepository.deleteById(id);
    }
}
