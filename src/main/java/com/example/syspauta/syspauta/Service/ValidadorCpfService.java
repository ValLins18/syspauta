package com.example.syspauta.syspauta.Service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class ValidadorCpfService {

    private final RestTemplate restTemplate;
    private final Environment environment;

    public ValidadorCpfService(Environment environment) {
        this.restTemplate = new RestTemplate();
        this.environment = environment;
    }

    public Boolean podeVotar(String cpf) {
        String url = String.format(environment.getProperty("url.valida.cpf"), cpf);

        var validationResponse = restTemplate.getForObject(url, Map.class);

        return validationResponse != null && "ABLE_TO_VOTE".equals(validationResponse.get("status"));
    }
}
