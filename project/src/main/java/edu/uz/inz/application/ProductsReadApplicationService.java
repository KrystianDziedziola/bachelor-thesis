package edu.uz.inz.application;

import edu.uz.inz.application.query.GetByIdQuery;
import edu.uz.inz.port.adapter.repository.read.ProductReadModel;
import java.util.List;

public interface ProductsReadApplicationService {

    List<ProductReadModel> getProducts();

    ProductReadModel getProduct(GetByIdQuery query);
}
