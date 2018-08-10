package edu.uz.inz.port.adapter.repository.write;

import edu.uz.inz.domain.model.Customer;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CustomerWriteRepository extends CrudRepository<Customer, UUID> {

}
