package com.fpmislata.banco.web.webModel.response;

public record ApiKeyResponse(
        String clientName,
        String apiKey,
        String message
) {
}
