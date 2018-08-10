package edu.uz.inz.port.adapter.rest;

import edu.uz.inz.application.CustomersWriteApplicationService;
import edu.uz.inz.application.OrdersWriteApplicationService;
import edu.uz.inz.application.ProductsWriteApplicationService;
import edu.uz.inz.application.command.CreateCustomerCommand;
import edu.uz.inz.application.command.CreateOrderCommand;
import edu.uz.inz.application.command.CreateProductCommand;
import edu.uz.inz.domain.model.Address;
import edu.uz.inz.domain.model.PaymentType;
import edu.uz.inz.port.adapter.rest.request.OrderItemRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/init", produces = MediaType.APPLICATION_JSON_VALUE)
public class InitiatorRestController {

    private static final Random RANDOM = new Random();
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    private static final List<UUID> PRODUCT_UUIDS = new ArrayList<>();

    private final ProductsWriteApplicationService productsService;
    private final CustomersWriteApplicationService customersService;
    private final OrdersWriteApplicationService ordersService;

    public InitiatorRestController(
        final ProductsWriteApplicationService productsService,
        final CustomersWriteApplicationService customersService,
        final OrdersWriteApplicationService ordersService) {
        this.productsService = productsService;
        this.customersService = customersService;
        this.ordersService = ordersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> init(final Long productsCount, final Long customersCount)
        throws IOException {
        initProducts(productsCount);
        initCustomers(customersCount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void initProducts(final Long count) throws IOException {
        final Path path = Paths.get("productsUuids");
        try (final BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
            for (long productNumber = 1; productNumber <= count; productNumber++) {
                final UUID uuid = productsService.create(createProductCommand(productNumber));
                writer.write(uuid.toString());
                writer.newLine();
                System.out.println("Created PRODUCT number " + productNumber);
                PRODUCT_UUIDS.add(uuid);
            }
        }
    }

    private void initCustomers(final Long count) throws IOException {
        final Path path = Paths.get("customersUuids");
        try (final BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
            for (long customerNumber = 1; customerNumber <= count; customerNumber++) {
                final UUID uuid = customersService.create(createCustomerCommand(customerNumber));
                createOrders(uuid);
                writer.write(uuid.toString());
                writer.newLine();
                System.out.println("Created CUSTOMER number " + customerNumber);
            }
        }
    }

    private void createOrders(final UUID cusotmerUuid) {
        final int numberOfOrders = RANDOM.nextInt(3) + 1;
        for (int orderNumber = 1; orderNumber <= numberOfOrders; orderNumber++) {
            ordersService.create(
                new CreateOrderCommand(cusotmerUuid, createOrderItems(),
                    PaymentType.CREDIT));
        }
    }

    private ArrayList<OrderItemRequest> createOrderItems() {
        final ArrayList<OrderItemRequest> orderItems = new ArrayList<>();

        final int numberOfItems = RANDOM.nextInt(3) + 1;
        for (int itemNumber = 1; itemNumber <= numberOfItems; itemNumber++) {
            final int quantity = RANDOM.nextInt(10) + 1;
            final OrderItemRequest item = new OrderItemRequest(getRandomProductId(), quantity);
            orderItems.add(item);
        }

        return orderItems;
    }

    private UUID getRandomProductId() {
        final int productIndex = RANDOM.nextInt(PRODUCT_UUIDS.size());
        return PRODUCT_UUIDS.get(productIndex);
    }

    private CreateProductCommand createProductCommand(final long productNumber) {
        return new CreateProductCommand(
            "Product description number " + productNumber,
            BigDecimal.valueOf(RANDOM.nextInt(10_000)),
            "Product " + productNumber
        );
    }

    private CreateCustomerCommand createCustomerCommand(final long customerNumber) {
        final Address address = new Address(
            "Street " + customerNumber,
            "City " + customerNumber,
            RANDOM.nextInt(99) + "-" + RANDOM.nextInt(100)
        );

        return new CreateCustomerCommand(
            "Customer name " + customerNumber,
            address,
            BigDecimal.valueOf(RANDOM.nextInt(10_000))
        );
    }
}
