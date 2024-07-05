package br.com.encurtadordeurl.nathanflp.controller;

import br.com.encurtadordeurl.nathanflp.controller.dto.linkEncurtadoRequest;
import br.com.encurtadordeurl.nathanflp.controller.dto.linkParaEncurtarResponse;
import br.com.encurtadordeurl.nathanflp.service.linkParaEncurtarService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class linkParaEncurtarController {

    @Autowired
    private linkParaEncurtarService service;

    @PostMapping(value = "/encurtar-url")
    public ResponseEntity<linkParaEncurtarResponse> shortenUrl(@RequestBody linkEncurtadoRequest request, HttpServletRequest servletRequest) {

        var redirectUrl = servletRequest.getRequestURL().toString().replace("encurtar-url", service.cadastraLink(request));
        return ResponseEntity.ok(new linkParaEncurtarResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        return ResponseEntity.status(HttpStatus.FOUND).headers(service.redirecionaURL(id)).build();
    }

}