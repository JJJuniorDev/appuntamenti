package Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.Operazione;
import Services.OperazioneService;

@RestController
@RequestMapping("/api/operazioni")
public class OperazioneController {

    @Autowired
    private OperazioneService operazioneService;

    @GetMapping
    public List<Operazione> getAllOperazioni() {
        return operazioneService.getAllOperazioni();
    }

    @GetMapping("/{id}")
    public Operazione getOperazioneById(@PathVariable String id) {
        return operazioneService.getOperazioneById(id);
    }

    @PostMapping
    public Operazione createOperazione(@RequestBody Operazione operazione) {
        return operazioneService.createOperazione(operazione);
    }

    @DeleteMapping("/{id}")
    public void deleteOperazione(@PathVariable String id) {
        operazioneService.deleteOperazione(id);
    }
}
