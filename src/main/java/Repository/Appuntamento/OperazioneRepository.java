package Repository.Appuntamento;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Model.Operazione;

@Repository
public interface OperazioneRepository extends MongoRepository<Operazione, ObjectId>{

}
