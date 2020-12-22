package api.projekt.pl.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Lob
    @Column(columnDefinition = "text")
    private String description;
    
    @Column(name = "finish_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date finishDate;
    
    @ManyToOne
    @JoinColumn(name="list_of_tasks_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ListOfTasks listOfTasks;
    
    @ManyToOne 
    @JoinColumn(name="priority_id")
    private Priority priority;
    
    
	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public ListOfTasks getListOfTasks() {
		return listOfTasks;
	}

	public void setListOfTasks(ListOfTasks listOfTasks) {
		this.listOfTasks = listOfTasks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
