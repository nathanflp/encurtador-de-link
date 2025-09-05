package br.com.encurtadordeurl.nathanflp.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.*;

import java.time.*;
import java.util.*;


@Entity
@Table(name ="tb_link_shortner")
public class LinkParaEncurtar {

    @Id
    private String id;

    @Column(name = "full_url")
    private String fullUrl;

    @Column(name = "created_at", updatable = false, nullable = false)
    private  LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "click_count")
    private Integer clickCount = 0;

    @Column(name= "last_Time_Clicked")
    private LocalDateTime lastTimeClicked;

    public  LinkParaEncurtar() {
    }

    public  LinkParaEncurtar(String id, String fullUrl, LocalDateTime expiresAt) {
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

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getLastTimeClicked() {
        return lastTimeClicked;
    }

    public void setLastTimeClicked(LocalDateTime lastTimeClicked) {
        this.lastTimeClicked = lastTimeClicked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LinkParaEncurtar that = (LinkParaEncurtar) o;
        return Objects.equals(id, that.id) && Objects.equals(fullUrl, that.fullUrl) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullUrl, createdAt);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public void increaseClickCount() {
        this.clickCount++;
    }

    public void updateLastTimeClicked(){
        this.lastTimeClicked = LocalDateTime.now();
    }

}
