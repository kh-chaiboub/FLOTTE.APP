package com.reforgan.reforgan.repository;
import com.reforgan.reforgan.domain.RefOrgan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@SuppressWarnings("unused")
@Repository
public interface RefOrganRepository extends MongoRepository<RefOrgan, String> {

   List<RefOrgan> findByPrefOrgan(String p_ref);
   List<RefOrgan> findByIdIn(List<String> prefs);
}
