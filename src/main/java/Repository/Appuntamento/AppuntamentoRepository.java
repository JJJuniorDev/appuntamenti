package Repository.Appuntamento;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import Model.Appuntamento;

public interface AppuntamentoRepository extends MongoRepository<Appuntamento, ObjectId>{
	   // Metodo per cercare gli appuntamenti associati a un dentista specifico
    List<Appuntamento> findByDentistaId(String dentistaId);
}
