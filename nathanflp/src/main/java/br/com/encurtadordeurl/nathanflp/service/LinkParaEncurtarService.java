package br.com.encurtadordeurl.nathanflp.service;

import br.com.encurtadordeurl.nathanflp.controller.dto.linkEncurtadoRequest;
import br.com.encurtadordeurl.nathanflp.models.linkParaEncurtar;
import br.com.encurtadordeurl.nathanflp.repository.linkParaEncurtarRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public record linkParaEncurtarService(linkParaEncurtarRepository repository) {


    public String cadastraLink(linkEncurtadoRequest request){

        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (doesLinkAlreadyExist(id));

        repository.save(new linkParaEncurtar(id, request.url(), LocalDateTime.now().plusDays(3)));

        return id;
    }

    public HttpHeaders redirecionaURL(String id ){

        Optional<linkParaEncurtar> url = repository.findById(id);

        if (url.isEmpty()) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return headers;
    }

    public boolean doesLinkAlreadyExist(String id){
        return repository.existsById(id);
    }

    public LinkMetricsResponse metricsResponse(String id){
        log.info("Buscando url por id: " + id);
        LinkParaEncurtar link = repository.findById(id).orElseThrow(() -> new RuntimeException("Link informado não existe nos nossos registros."));
        log.info("Url encontrada");
        log.info("Gerando novo linkMetricsReponse para id: " + link.getId());

        return new LinkMetricsResponse(link.getId(),
                link.getClickCount(),
                DateTimeFormatterUtil.format(link.getExpiresAt()));
    }

}
