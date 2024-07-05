package br.com.encurtadordeurl.nathanflp.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name ="tb_linkShortner")
public class linkParaEncurtar {


    @Id
    private String id;

    private String fullUrl;
    
    private LocalDateTime expiresAt;

    public  linkParaEncurtar() {
    }

    public  linkParaEncurtar(String id, String fullUrl, LocalDateTime expiresAt) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
