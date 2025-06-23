package in.contactlet.service;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.contactlet.binding.Contact;
import in.contactlet.dto.ContactReq;
import in.contactlet.dto.ContactResp;
import in.contactlet.model.LinkPrecedence;
import in.contactlet.repo.ContactRepo;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepo contactRepository;
	@Override
	public ContactResp identifyContact(ContactReq request) {
        List<Contact> matchingContacts = contactRepository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber());

        if (matchingContacts.isEmpty()) {
            Contact newContact = Contact.builder()
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .linkPrecedence(LinkPrecedence.PRIMARY)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            contactRepository.save(newContact);
            return new ContactResp(newContact.getId(),
                    List.of(newContact.getEmail()),
                    List.of(newContact.getPhoneNumber()),
                    Collections.emptyList());
        }

        Contact primary = matchingContacts.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.PRIMARY)
                .min(Comparator.comparing(Contact::getCreatedAt))
                .orElse(matchingContacts.get(0));

        for (Contact c : matchingContacts) {
            if (c.getId().equals(primary.getId())) continue;
            if (c.getLinkPrecedence() == LinkPrecedence.PRIMARY) {
                c.setLinkPrecedence(LinkPrecedence.SECONDARY);
                c.setLinkedId(primary.getId());
                c.setUpdatedAt(LocalDateTime.now());
                contactRepository.save(c);
            }
        }

        boolean alreadyExists = matchingContacts.stream()
                .anyMatch(c -> Objects.equals(c.getEmail(), request.getEmail()) &&
                        Objects.equals(c.getPhoneNumber(), request.getPhoneNumber()));

        if (!alreadyExists) {
            Contact secondary = Contact.builder()
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .linkPrecedence(LinkPrecedence.SECONDARY)
                    .linkedId(primary.getId())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            contactRepository.save(secondary);
            matchingContacts.add(secondary);
        }

        Set<String> emails = matchingContacts.stream()
                .map(Contact::getEmail)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<String> phoneNumbers = matchingContacts.stream()
                .map(Contact::getPhoneNumber)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Long> secondaryIds = matchingContacts.stream()
                .filter(c -> c.getLinkPrecedence() == LinkPrecedence.SECONDARY)
                .map(Contact::getId)
                .collect(Collectors.toList());

        return new ContactResp(primary.getId(), new ArrayList<>(emails), new ArrayList<>(phoneNumbers), secondaryIds);
    }

}
