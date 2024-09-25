package Model;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;




@Document(collection = "APPUNTAMENTO")
public class Appuntamento {

	@Id
	 private ObjectId id;

	@Field("DATA_E_ORARIO")
    private LocalDateTime dataEOrario;

	@Field("CODICE_FISCALE_PAZIENTE")
    private String codiceFiscalePaziente;


    @Field("TRATTAMENTO")
    private String trattamento;


    @Field("NOTE")
    private String note;

    @DBRef
    @Field("OPERAZIONI")
    private List<ObjectId> operazioni;

    @DBRef
    @Field("PAZIENTE_ID")
    private ObjectId pazienteId;
    
    @Field("DENTISTA_ID") // Nuovo campo per salvare il riferimento al dentista
    private ObjectId dentistaId;
    
    public String getId() {
        return id.toHexString();
    }


    public void setId(String id) {
        this.id = new ObjectId(id);
    }

	public LocalDateTime getDataEOrario() {
		return dataEOrario;
	}

	public void setDataEOrario(LocalDateTime dataEOrario) {
		this.dataEOrario = dataEOrario;
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


	public List<ObjectId> getOperazioni() {
		return operazioni;
	}


	public void setOperazioni(List<ObjectId> operazioni) {
		this.operazioni = operazioni;
	}

	   public String getPazienteId() {
	        return pazienteId != null ? pazienteId.toHexString() : null;
	    }
	   
	  public void setPazienteId(String pazienteId) {
	        this.pazienteId = new ObjectId(pazienteId);
	    }

	public void setId(ObjectId id) {
		this.id = id;
	}


	  public String getDentistaId() {
	        return dentistaId != null ? dentistaId.toHexString() : null;
	    }

	    public void setDentistaId(String dentistaId) {
	        this.dentistaId = new ObjectId(dentistaId);
	    }


	


}
