package com.ng.orderservice.stubs;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClicentStubs {

    public static void stubInventoryCalls(String skuCode, Integer quantity) {
        String url = String.format("/api/inventory?skuCode=%s&quantity=%d", skuCode, quantity);

        stubFor(
                get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("IN STOCK"))
        );

    }

    public static void stubInventoryOutOfStockCalls(String skuCode, Integer quantity) {
        String url = String.format("/api/inventory?skuCode=%s&quantity=%d", skuCode, quantity);

        stubFor(
                get(urlEqualTo("/api/inventory?skuCode=" + skuCode + "&quantity=" + quantity))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("OUT OF STOCK"))
        );

    }
}
