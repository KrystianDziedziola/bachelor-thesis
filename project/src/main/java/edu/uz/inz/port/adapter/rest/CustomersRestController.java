package edu.uz.inz.port.adapter.rest;

import edu.uz.inz.application.CustomersReadApplicationService;
import edu.uz.inz.application.CustomersWriteApplicationService;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.CustomerReadModel;
import edu.uz.inz.port.adapter.rest.request.CreateCustomerRequest;
import edu.uz.inz.port.adapter.rest.response.ObjectCreatedResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomersRestController {

    private final CustomersWriteApplicationService writeService;
    private final CustomersReadApplicationService readService;

    public CustomersRestController(
        final CustomersWriteApplicationService writeService,
        final CustomersReadApplicationService readService) {
        this.writeService = writeService;
        this.readService = readService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody final CreateCustomerRequest request) {
        final UUID uuid = writeService.create(request.asCommand());

        return new ResponseEntity<>(new ObjectCreatedResponse(uuid), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCustomers() {
        final List<CustomerReadModel> customers = readService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(
        @PathVariable final UUID uuid) {
        final CustomerReadModel customer = readService.getCustomer(new GetByIdQuery(uuid));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable final UUID uuid) {
        return null;
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable final CreateCustomerRequest request) {
        return null;
    }
}
