package br.com.fiap.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "netflix", type = "midia")
public class Item {
     
	@Id()
    private String id;
    private String name;
    private String description;
    private long codProduto;
    
    public Item() {
    	
    }
	public Item(String name, String description, long codProduto) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(long codProduto) {
		this.codProduto = codProduto;
	}
	
	
}
