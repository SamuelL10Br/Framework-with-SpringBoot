package com.samuel.frameblog.controller;

import com.samuel.frameblog.dto.CepResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CepController {

    @GetMapping("/cep/{cep}")
    public CepResponse buscarCep(@PathVariable String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, CepResponse.class);
    }
}