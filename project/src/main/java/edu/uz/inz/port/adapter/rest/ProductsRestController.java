package edu.uz.inz.port.adapter.rest;

import edu.uz.inz.application.ProductsReadApplicationService;
import edu.uz.inz.application.ProductsWriteApplicationService;
import edu.uz.inz.application.error.ProductNotFoundException;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.ProductReadModel;
import edu.uz.inz.port.adapter.rest.request.CreateProductRequest;
import edu.uz.inz.port.adapter.rest.response.ErrorResponse;
import edu.uz.inz.port.adapter.rest.response.ObjectCreatedResponse;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsRestController {

    private final ProductsWriteApplicationService writeApplicationService;
    private final ProductsReadApplicationService readApplicationService;

    public ProductsRestController(
        final ProductsWriteApplicationService writeApplicationService,
        final ProductsReadApplicationService readApplicationService) {
        this.writeApplicationService = writeApplicationService;
        this.readApplicationService = readApplicationService;
    }


    @ApiOperation(value = "createProduct", notes = "Umożliwia dodanie nowego produktu do bazy danych sklepu.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ObjectCreatedResponse> createProduct(
        @RequestBody final CreateProductRequest request) {
        final UUID uuid = writeApplicationService.create(request.asCommand());

        return new ResponseEntity<>(new ObjectCreatedResponse(uuid), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProducts() {
        final List<ProductReadModel> products = readApplicationService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable final UUID uuid) {
        final ProductReadModel product = readApplicationService.getProduct(new GetByIdQuery(uuid));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ResponseEntity handle(final ProductNotFoundException exception) {
        return ErrorResponse
            .prepare("Product not found", exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable final UUID uuid) {
        return null;
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable final CreateProductRequest request) {
        return null;
    }
}
