package Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Dto.AppuntamentoDTO;
import Model.Appuntamento;
import Model.Operazione;
import Repository.Appuntamento.OperazioneRepository;
import Services.AppuntamentoService;

@RestController
@RequestMapping("/api/appuntamenti")
public class AppuntamentoController {

    @Autowired
    private AppuntamentoService appuntamentoService;

    @Autowired
    private OperazioneRepository operazioneRepository;
    
    @GetMapping
    public List<Appuntamento> getAllAppuntamenti() {
    	System.out.println("IN APPUNTAMENTO MICROSERVICE");
        return appuntamentoService.getAllAppuntamenti();
    }
    
    @GetMapping("/dentista/{dentistaId}")
    public List<Appuntamento> getAppuntamentiPerDentista(@PathVariable String dentistaId) {
        return appuntamentoService.getAppuntamentiByDentistaId(dentistaId);
    }



    @GetMapping("/{appuntamentoId}")
    public AppuntamentoDTO getAppuntamento(@PathVariable String appuntamentoId) {
    	Optional<Appuntamento> appuntamento= appuntamentoService.getAppuntamento(appuntamentoId);
    	 if (appuntamento.isPresent()) {
    		  // Converti gli ID delle operazioni in stringhe
    	        List<ObjectId> operazioniIds = appuntamento.get().getOperazioni();
    	        
    	        // Recupera tutte le operazioni associate usando gli ID convertiti
    	        List<Operazione> operazioni = operazioneRepository.findAllById(operazioniIds);
    	        
    	        // Restituisci un DTO con appuntamento e operazioni
    	        return appuntamentoService.convertToDTO(appuntamento.get(), operazioni);
    	 }
    	    throw new RuntimeException("Appuntamento non trovato");
    }
    @PostMapping
    public Appuntamento createAppuntamento(@RequestBody Appuntamento appuntamento) {
        return appuntamentoService.createAppuntamento(appuntamento);
    }

    @PutMapping("/{appuntamentoId}")
    public Appuntamento updateAppuntamento(@PathVariable String appuntamentoId, @RequestBody Appuntamento appuntamento) {
        return appuntamentoService.updateAppuntamento(appuntamentoId, appuntamento);
    }

    @DeleteMapping("/{appuntamentoId}")
    public void deleteAppuntamento(@PathVariable String appuntamentoId) {
        appuntamentoService.deleteAppuntamento(appuntamentoId);
    }

    @PutMapping("/{id}/operazioni")
    public Appuntamento addOperazione(@PathVariable String id, @RequestBody Operazione operazione) {
        return appuntamentoService.addOperazioneToAppuntamento(id, operazione);
    }
}
