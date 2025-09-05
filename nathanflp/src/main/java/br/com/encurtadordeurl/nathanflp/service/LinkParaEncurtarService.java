package br.com.encurtadordeurl.nathanflp.service;

import br.com.encurtadordeurl.nathanflp.controller.dto.*;
import br.com.encurtadordeurl.nathanflp.models.*;
import br.com.encurtadordeurl.nathanflp.repository.*;
import br.com.encurtadordeurl.nathanflp.utils.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.net.*;
import java.time.*;

@Service
public class LinkParaEncurtarService {

    private final linkParaEncurtarRepository repository;
    @Value("${link.expiration.days}")
    private Integer expirationTime;
    private final Logger log = LoggerFactory.getLogger(LinkParaEncurtarService.class);

    @Autowired
    public LinkParaEncurtarService(linkParaEncurtarRepository repository) {
        this.repository = repository;
    }

    public String cadastraLink(LinkEncurtadoRequest request, String baseUrl){
        String redirectedUrlId;

        log.info("Iniciando a geracao de id");
        do {
            redirectedUrlId = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (doesLinkAlreadyExist(redirectedUrlId));

        log.info("Id gerado: " + redirectedUrlId + ". Salvando no banco."  );
        repository.save(new LinkParaEncurtar(redirectedUrlId, request.url(), LocalDateTime.now().plusDays(expirationTime)));

        return baseUrl.replace("encurtar-url", redirectedUrlId);
    }

    public HttpHeaders redirecionaURL(String id ){
        log.info("Iniciando redirecionamento de id: " + id);
        LinkParaEncurtar url = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Link informado não existe nos nossos registros."));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getFullUrl()));

        log.info("Incrementando contagem de click e ultima vez clicada para id: " + url.getId());
        url.increaseClickCount();
        url.updateLastTimeClicked();

        repository.save(url);

        return headers;
    }

    public boolean doesLinkAlreadyExist(String id){
        return repository.existsById(id);
    }

    public LinkMetricsResponse metricsResponse(String id){
        log.info("Iniciando coleta de metrica para id: " + id);
        LinkParaEncurtar link = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Link informado não existe nos nossos registros."));

        log.info("Gerando novo linkMetricsResponse para id: " + link.getId());
        return new LinkMetricsResponse(
                link.getId(),
                link.getClickCount(),
                DateTimeFormatterUtil.format(link.getExpiresAt()));
    }

}
