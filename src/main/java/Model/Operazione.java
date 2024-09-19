package Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "OPERAZIONI")
public class Operazione {

    @Id
    private String id;

    @Field("NOME")
    private String nome;

    @Field("PREZZO")
    private int prezzo;

    @Field("CATEGORIA")
    private String categoria;

    @Field("DURATA")
    private Integer durata;

    @DBRef
	@Field("MATERIALI_NECESSARI")
    private List<Materiale> materialiNecessari;

    @Field("NOTE")
    private String note;

    @Field("RESPONSABILE")
    private String responsabile;

    @Field("DATA")
    private Date data;

    @Field("STATO")
    private String stato;

    @Field("PAZIENTE_ID")
    private int pazienteId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getDurata() {
		return durata;
	}

	public void setDurata(Integer durata) {
		this.durata = durata;
	}

	public List<Materiale> getMaterialiNecessari() {
		return materialiNecessari;
	}

	public void setMaterialiNecessari(List<Materiale> materialiNecessari) {
		this.materialiNecessari = materialiNecessari;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(String responsabile) {
		this.responsabile = responsabile;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getPazienteId() {
		return pazienteId;
	}

	public void setPazienteId(int pazienteId) {
		this.pazienteId = pazienteId;
	}


}

