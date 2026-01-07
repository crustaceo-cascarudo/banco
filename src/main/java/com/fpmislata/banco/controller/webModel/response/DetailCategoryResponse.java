package com.fpmislata.banco.controller.webModel.response;

public record DetailCategoryResponse(
    Long id,
    String name,
    String slug,
    String description,
    Boolean estado
) {

}
