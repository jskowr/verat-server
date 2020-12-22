package api.projekt.pl.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "priorities")
public class Priority {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public enum Priorities{
		NORMALNY, NISKI, WYSOKI, PILNY, ZAKOŃCZONE
	}
}
