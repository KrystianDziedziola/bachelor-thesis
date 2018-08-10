package edu.uz.inz.port.adapter.rest;

import edu.uz.inz.application.OrdersWriteApplicationService;
import edu.uz.inz.application.error.CustomerNotFoundException;
import edu.uz.inz.application.error.ProductNotFoundException;
import edu.uz.inz.port.adapter.rest.request.CreateOrderRequest;
import edu.uz.inz.port.adapter.rest.response.ErrorResponse;
import edu.uz.inz.port.adapter.rest.response.ObjectCreatedResponse;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersRestController {

    private final OrdersWriteApplicationService writeService;

    public OrdersRestController(final OrdersWriteApplicationService writeService) {
        this.writeService = writeService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody final CreateOrderRequest request) {
        final UUID uuid = writeService.create(request.asCommand());

        return new ResponseEntity<>(new ObjectCreatedResponse(uuid), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    public ResponseEntity handle(final CustomerNotFoundException exception) {
        return ErrorResponse
            .prepare("Customer not found", exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ResponseEntity handle(final ProductNotFoundException exception) {
        return ErrorResponse
            .prepare("Product not found", exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
