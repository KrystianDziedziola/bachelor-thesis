package edu.uz.inz.port.adapter.repository.write;

import edu.uz.inz.domain.model.Product;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductWriteRepository extends CrudRepository<Product, UUID> {

}
