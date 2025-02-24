package de.neuefische.java.nfjavaonlde270125spring.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {

    public String randomId()  {
        return UUID.randomUUID().toString();
    }
}
