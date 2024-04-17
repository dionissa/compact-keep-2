package com.dionissa.compactkeep2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dionissa.compactkeep2.model.Client;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

    List<Client> findByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndNotesContainingIgnoreCase(
            String name, String phoneNumber, String notes);
}
