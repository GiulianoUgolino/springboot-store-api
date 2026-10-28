package com.giuli.tienda.dto;

import java.util.List;

public record OrderRequest(
        List<OrderDetailRequest> details
) {
}
