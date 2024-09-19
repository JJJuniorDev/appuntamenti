package Services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Operazione;
import Repository.Appuntamento.OperazioneRepository;


@Service
public class OperazioneService {

    @Autowired
    private OperazioneRepository operazioneRepository;

    public List<Operazione> getAllOperazioni() {
        return operazioneRepository.findAll();
    }

    public Operazione getOperazioneById(String id) {
    	  ObjectId objectId = new ObjectId(id);
        return operazioneRepository.findById(objectId).orElse(null);
    }

    public Operazione createOperazione(Operazione operazione) {
        return operazioneRepository.save(operazione);
    }

    public void deleteOperazione(String id) {
    	ObjectId objectId = new ObjectId(id);
        operazioneRepository.deleteById(objectId);
    }
}
