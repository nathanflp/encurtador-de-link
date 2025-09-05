package br.com.encurtadordeurl.nathanflp.controller.dto;

public record LinkMetricsResponse(String url,
                                  Integer clickCount,
                                  String lastTimeClicked) {

}
