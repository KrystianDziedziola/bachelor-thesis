package edu.uz.inz.port.adapter.repository.write;

import edu.uz.inz.domain.model.Order;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderWriteRepository extends CrudRepository<Order, UUID> {

}
