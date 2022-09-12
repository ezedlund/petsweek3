/**
 * @author Elijah Edlund - ezedlund
 * CIS175 - Fall 2021
 * Sep 12, 2022
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="items")
public class PetItem {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int ID;
	@Column(name="OWNER")
	private String owner;
	@Column(name="NAME")
	private String name;
	@Column(name="TYPE")
	private String type;
	
	public PetItem() {
		super();
	}
	
	public PetItem(String owner, String name, String type) {
		super();
		this.owner = owner;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return ID;
	}

	public void setId(int iD) {
		ID = iD;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String returnItemDetails() {
		return "Name: " + this.name + ", Type: " + this.type + ", Owner: " + this.owner;
	}
}
