package edu.uz.inz.application;

import edu.uz.inz.application.error.ProductNotFoundException;
import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.ProductReadFromNoSqlRepository;
import edu.uz.inz.port.adapter.repository.read.ProductReadModel;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "read.source", havingValue = "nosql", matchIfMissing = true)
public class ProductsReadFromNoSqlApplicationService implements ProductsReadApplicationService {

    private final ProductReadFromNoSqlRepository productReadRepository;

    public ProductsReadFromNoSqlApplicationService(
        final ProductReadFromNoSqlRepository productReadRepository) {
        this.productReadRepository = productReadRepository;
    }

    @Override
    public List<ProductReadModel> getProducts() {
        return productReadRepository.findAll();
    }

    @Override
    public ProductReadModel getProduct(final GetByIdQuery query) {
        final UUID uuid = query.getUuid();
        final ProductReadModel product = productReadRepository.findOne(uuid.toString());

        if (Objects.isNull(product)) {
            throw new ProductNotFoundException(uuid);
        }

        return product;
    }
}
