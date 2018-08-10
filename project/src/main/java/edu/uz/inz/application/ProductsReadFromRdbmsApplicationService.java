package edu.uz.inz.application;

import edu.uz.inz.application.error.ProductNotFoundException;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.domain.model.Product;
import edu.uz.inz.port.adapter.repository.read.ProductReadFromRdbmsRepository;
import edu.uz.inz.port.adapter.repository.read.ProductReadModel;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "read.source", havingValue = "rdbms")
public class ProductsReadFromRdbmsApplicationService implements ProductsReadApplicationService {

    private final ProductReadFromRdbmsRepository productReadRepository;

    public ProductsReadFromRdbmsApplicationService(
        final ProductReadFromRdbmsRepository productReadRepository) {
        this.productReadRepository = productReadRepository;
    }

    @Override
    public List<ProductReadModel> getProducts() {
        final Iterable<Product> products = productReadRepository.findAll();
        return StreamSupport
            .stream(products.spliterator(), false)
            .map(this::toReadModel)
            .collect(Collectors.toList());
    }

    @Override
    public ProductReadModel getProduct(final GetByIdQuery query) {
        final UUID uuid = query.getUuid();
        final Product product = productReadRepository.findOne(uuid);

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(uuid);
        }

        return toReadModel(product);
    }

    private ProductReadModel toReadModel(final Product product) {
        return new ProductReadModel(product.getUuid().toString(), product.getName(),
            product.getPrice(), product.getDescription());
    }
}
