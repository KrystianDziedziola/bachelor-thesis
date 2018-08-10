package edu.uz.inz.port.adapter.repository.read;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductReadModelUpdateRepository extends
    MongoRepository<ProductReadModel, String> {

}
