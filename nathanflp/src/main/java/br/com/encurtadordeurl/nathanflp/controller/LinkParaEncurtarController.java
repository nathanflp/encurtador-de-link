package br.com.encurtadordeurl.nathanflp.controller;

import br.com.encurtadordeurl.nathanflp.controller.dto.*;
import br.com.encurtadordeurl.nathanflp.service.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1")
public class LinkParaEncurtarController {

    private final LinkParaEncurtarService service;

    @Autowired
    public LinkParaEncurtarController(LinkParaEncurtarService service) {
        this.service = service;
    }

    @PostMapping(value = "/encurtar-url")
    public ResponseEntity<LinkParaEncurtarResponse> shortenUrl(@RequestBody LinkEncurtadoRequest request, HttpServletRequest servletRequest) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.cadastraLink(request, servletRequest.getRequestURL().toString()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(service.redirecionaURL(id))
                .build();
    }

    @GetMapping("/metrics/{id}")
    public ResponseEntity<LinkMetricsResponse> metrics(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(service.metricsResponse(id));
    }

}