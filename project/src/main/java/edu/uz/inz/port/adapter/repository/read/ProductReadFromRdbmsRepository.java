package edu.uz.inz.port.adapter.repository.read;

import edu.uz.inz.domain.model.Product;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProductReadFromRdbmsRepository extends CrudRepository<Product, UUID> {

}
