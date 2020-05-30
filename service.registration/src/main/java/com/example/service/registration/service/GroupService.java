package com.example.service.registration.service;

import com.example.service.registration.entity.Group;
import com.example.service.registration.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group get(long id) {
        return groupRepository.get(id);
    }
}
