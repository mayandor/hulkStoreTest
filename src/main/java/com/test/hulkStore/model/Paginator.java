package com.test.hulkStore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Paginator {

    private int page=0;
    @JsonProperty(value = "per_page")
    private int perPage = 0;

    @JsonProperty(value = "total_pages")
    private int totalPages = 0;

    @JsonProperty(value = "total_registers")
    private int totalRegisters = 0;

    public Paginator(int page, int perPage) {
        this.page = page;
        this.perPage = perPage;
    }
}
