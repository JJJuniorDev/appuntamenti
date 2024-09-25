package Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Dto.AppuntamentoDTO;
import Dto.PazienteDTO;
import Model.Appuntamento;
import Model.Operazione;
import Repository.Appuntamento.AppuntamentoRepository;

@Service
public class AppuntamentoService {

	 @Value("${pazienti.service.url}")
	    private String pazientiServiceUrl;

	    private final RestTemplate restTemplate;
	    
	  private final KafkaTemplate<String, Object> kafkaTemplate;
	  
    @Autowired
    private AppuntamentoRepository appuntamentoRepository;

    @Autowired
    public AppuntamentoService(KafkaTemplate<String, Object> kafkaTemplate, AppuntamentoRepository appuntamentoRepository, RestTemplate restTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.appuntamentoRepository= appuntamentoRepository;
        this.restTemplate = restTemplate;
    }
    

    
    // Chiamata API al microservizio Pazienti
    public Optional<PazienteDTO> getPazienteById(String pazienteId) {
        try {
            PazienteDTO paziente = restTemplate.getForObject(
                pazientiServiceUrl + "/api/pazienti/" + pazienteId, PazienteDTO.class
            );
            return Optional.ofNullable(paziente);
        } catch (Exception e) {
            System.err.println("Errore nel recupero del paziente: " + e.getMessage());
            return Optional.empty();
        }
    }
   
    // Metodo per recuperare l'appuntamento con i dettagli del paziente
    public Optional<AppuntamentoDTO> getAppuntamentoConPaziente(String appuntamentoId) {
        Optional<Appuntamento> appuntamentoOpt = getAppuntamento(appuntamentoId);
        if (appuntamentoOpt.isPresent()) {
            Appuntamento appuntamento = appuntamentoOpt.get();
            Optional<PazienteDTO> pazienteOpt = getPazienteById(appuntamento.getPazienteId());
            
            if (pazienteOpt.isPresent()) {
                AppuntamentoDTO appuntamentoDTO = new AppuntamentoDTO(appuntamento, pazienteOpt.get());
                return Optional.of(appuntamentoDTO);
            }
        }
        return Optional.empty();
    }
    
    public Map<String, Object> getAppuntamentiStatistics(String dentistaId) {
        Map<String, Object> stats = new HashMap<>();

        // Numero totale di appuntamenti
        long totaleAppuntamenti = appuntamentoRepository.count();

        // Numero di appuntamenti per dentista loggato
        long appuntamentiPerDentista = appuntamentoRepository.findByDentistaId(dentistaId).size();

        // In futuro puoi implementare la logica per calcolare appuntamenti futuri e completati
    //    long appuntamentiFuturi = appuntamentoRepository.countByDataEOrarioAfter(new Date());
      //  long appuntamentiCompletati = appuntamentoRepository.countByDataEOrarioBefore(new Date());

        stats.put("totaleAppuntamenti", totaleAppuntamenti);
        stats.put("appuntamentiPerDentista", appuntamentiPerDentista);
        stats.put("appuntamentiCompletati", 60); //appuntamentiCompletati
        stats.put("appuntamentiFuturi", 12); //appuntamentiFuturi
      

        return stats;
    }
    
    public List<Appuntamento> getAppuntamentiByDentistaId(String dentistaId) {
        return appuntamentoRepository.findByDentistaId(dentistaId);
    }
    
    public List<AppuntamentoDTO> getAllAppuntamenti() {
        List<Appuntamento> appuntamenti = appuntamentoRepository.findAll();
        List<AppuntamentoDTO> appuntamentiDTO = new ArrayList<>();

        for (Appuntamento appuntamento : appuntamenti) {
            Optional<PazienteDTO> pazienteOpt = getPazienteById(appuntamento.getPazienteId());
            
            if (pazienteOpt.isPresent()) {
                AppuntamentoDTO appuntamentoDTO = new AppuntamentoDTO(appuntamento, pazienteOpt.get());
                appuntamentiDTO.add(appuntamentoDTO);
            } else {
                System.err.println("Paziente non trovato per l'appuntamento con ID: " + appuntamento.getId());
            }
        }

        return appuntamentiDTO;
    }

    
  /*  public List<Appuntamento> getAllAppuntamenti() {
    	System.out.println(appuntamentoRepository.count());
        return appuntamentoRepository.findAll();
    } */

    public Optional<Appuntamento> getAppuntamento(String id) {
        
        //if (id != null && id.length() == 24 && ObjectId.isValid(id)) {
    	System.out.println("LUNGHEZZA ID CHE DEVE ESSERE 24==="+id.length());
            ObjectId objectId = new ObjectId(id);
        return appuntamentoRepository.findById(objectId);
    }

    public Appuntamento createAppuntamento(Appuntamento appuntamento) {
    	  Appuntamento savedAppuntamento = appuntamentoRepository.save(appuntamento);

          // Solo se l'appuntamento è stato salvato correttamente, invia l'evento a Kafka
         // kafkaTemplate.send("appuntamento-creato", savedAppuntamento);

          return savedAppuntamento;
    }

    public void deleteAppuntamento(String id) {
        ObjectId objectId = new ObjectId(id);
        appuntamentoRepository.deleteById(objectId);
    }

    public Appuntamento updateAppuntamento(String id, Appuntamento newAppuntamento) {
        ObjectId objectId = new ObjectId(id);
        return appuntamentoRepository.findById(objectId)
            .map(appuntamento -> {
            	   try {
                       if (newAppuntamento.getDataEOrario() != null) {
                           appuntamento.setDataEOrario(newAppuntamento.getDataEOrario());
                       }
                       if (newAppuntamento.getCodiceFiscalePaziente() != null) {
                           appuntamento.setCodiceFiscalePaziente(newAppuntamento.getCodiceFiscalePaziente());
                       }
                       if (newAppuntamento.getTrattamento() != null) {
                           appuntamento.setTrattamento(newAppuntamento.getTrattamento());
                       }
                       if (newAppuntamento.getNote() != null) {
                           appuntamento.setNote(newAppuntamento.getNote());
                       }
                       if (newAppuntamento.getOperazioni() != null) {
                           appuntamento.setOperazioni(newAppuntamento.getOperazioni());
                       }
                       return appuntamentoRepository.save(appuntamento);
                   } catch (Exception e) {
                       System.out.printf("Error during mapping: {}", e.getMessage());
                       throw e; // Re-throw the exception after logging
                   }
               })
            .orElseGet(() -> {
                newAppuntamento.setId(id);
                return appuntamentoRepository.save(newAppuntamento);
            });
    }
        public Appuntamento addOperazioneToAppuntamento(String appuntamentoId, Operazione operazione) {
            ObjectId objectId = new ObjectId(appuntamentoId);
            Optional<Appuntamento> optionalAppuntamento = appuntamentoRepository.findById(objectId);
            if (optionalAppuntamento.isPresent()) {
                Appuntamento appuntamento = optionalAppuntamento.get();
                // Converte l'id dell'operazione in ObjectId prima di aggiungerlo
                ObjectId operazioneId = new ObjectId(operazione.getId());
                appuntamento.getOperazioni().add(operazioneId);
                return appuntamentoRepository.save(appuntamento);
            } else {
                throw new RuntimeException("Appuntamento non trovato con ID: " + appuntamentoId);
            }
        }
            
            public AppuntamentoDTO convertToDTO(Appuntamento appuntamento, List<Operazione> operazioni) {
                AppuntamentoDTO dto = new AppuntamentoDTO();
                dto.setId(appuntamento.getId().toString());
                dto.setDataEOrario(appuntamento.getDataEOrario());
                dto.setTrattamento(appuntamento.getTrattamento());
                dto.setNote(appuntamento.getNote());
               // dto.setOperazioniIds(appuntamento.getOperazioni()); // Imposta gli ID delle operazioni
                dto.setOperazioni(operazioni); // Imposta le operazioni complete
                return dto;
            }
            
       
        }
    

