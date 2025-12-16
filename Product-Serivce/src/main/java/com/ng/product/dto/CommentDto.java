package com.ng.product.dto;

import java.time.LocalDate;

public record CommentDto(
        String user,
        Double stars,
        String comment,
        LocalDate date
) {}
