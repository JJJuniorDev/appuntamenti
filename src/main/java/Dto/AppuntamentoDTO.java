package Dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import Model.Appuntamento;
import Model.Operazione;

public class AppuntamentoDTO {
	 private String id;
	    private LocalDateTime dataEOrario;
	    private String trattamento;
	    private String note;
	    private List<String> operazioniIds;
	    private List<Operazione> operazioni; // Include le operazioni complete
	    private PazienteDTO paziente; // Contiene i dati del paziente
	    private String dentistaId;
	    
	    public AppuntamentoDTO() {}

	    // Costruttore che combina appuntamento e paziente
	    public AppuntamentoDTO(Appuntamento appuntamento, PazienteDTO paziente) {
	        this.id = appuntamento.getId();
	        this.dataEOrario = appuntamento.getDataEOrario();
	        this.trattamento = appuntamento.getTrattamento();
	       /* this.operazioniIds = appuntamento.getOperazioni().stream()
	                .map(ObjectId::toHexString)
	                .collect(Collectors.toList());*/
	            this.paziente = paziente;
	            this.dentistaId = appuntamento.getDentistaId();
	    }
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public LocalDateTime getDataEOrario() {
			return dataEOrario;
		}
		public void setDataEOrario(LocalDateTime localDateTime) {
			this.dataEOrario = localDateTime;
		}
	
		public String getTrattamento() {
			return trattamento;
		}
		public void setTrattamento(String trattamento) {
			this.trattamento = trattamento;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
			  public List<String> getOperazioniIds() {
			        return operazioniIds;
			    }

			    public void setOperazioniIds(List<String> operazioniIds) {
			        this.operazioniIds = operazioniIds;
			    }
		public List<Operazione> getOperazioni() {
			return operazioni;
		}
		public void setOperazioni(List<Operazione> operazioni) {
			this.operazioni = operazioni;
		}
	    
		 public PazienteDTO getPaziente() {
		        return paziente;
		    }

		    public void setPaziente(PazienteDTO paziente) {
		        this.paziente = paziente;
		    }

		    public String getDentistaId() {
		        return dentistaId;
		    }

		    public void setDentistaId(String dentistaId) {
		        this.dentistaId = dentistaId;
		    }
}
