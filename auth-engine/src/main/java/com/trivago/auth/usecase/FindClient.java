package com.trivago.auth.usecase;

import com.trivago.auth.domain.Client;
import com.trivago.auth.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class FindClient {

  private final ClientRepository clientRepository;

  public FindClient(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Client execute(String id) {
    return clientRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Client not found"));
  }
}
