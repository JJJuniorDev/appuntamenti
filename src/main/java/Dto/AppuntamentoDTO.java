package Dto;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;

import Model.Operazione;

public class AppuntamentoDTO {
	 private String id;
	    private LocalDateTime dataEOrario;
	    private String codiceFiscalePaziente;
	    private String trattamento;
	    private String note;
	    private List<ObjectId> operazioniIds;
	    private List<Operazione> operazioni; // Include le operazioni complete
	    
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
		public String getCodiceFiscalePaziente() {
			return codiceFiscalePaziente;
		}
		public void setCodiceFiscalePaziente(String codiceFiscalePaziente) {
			this.codiceFiscalePaziente = codiceFiscalePaziente;
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
		public List<ObjectId> getOperazioniIds() {
			return operazioniIds;
		}
		public void setOperazioniIds(List<ObjectId> operazioniIds) {
			this.operazioniIds = operazioniIds;
		}
		public List<Operazione> getOperazioni() {
			return operazioni;
		}
		public void setOperazioni(List<Operazione> operazioni) {
			this.operazioni = operazioni;
		}
	    
	    
}
