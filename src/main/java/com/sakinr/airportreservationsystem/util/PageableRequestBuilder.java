package com.sakinr.airportreservationsystem.util;

import com.sakinr.airportreservationsystem.model.PageableQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageableRequestBuilder {
    private PageableRequestBuilder() {
    }

    public static PageRequest build(PageableQuery query) {
        if (query.getSortDirection() == null || query.getSortBy() == null) {
            return PageRequest.of(
                    Math.max(0, query.getPage() - 1),
                    Math.max(0, query.getPageSize())
            );
        }

        return PageRequest.of(
                Math.max(0, query.getPage() - 1),
                Math.max(0, query.getPageSize()),
                Sort.Direction.valueOf(query.getSortDirection().toUpperCase()),
                query.getSortBy()
        );
    }
}
