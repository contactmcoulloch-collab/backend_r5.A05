package R5.A05.API.Modele;

import java.sql.Date;

import org.jspecify.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table (name = "commentaire")
public class Commentaire {
  
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentaire_id")
    private Integer id;

    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date")
    @JsonProperty("date")
    private Date date;

    @Column(name = "joueur_id")
    private int joueur_id;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getContenu() {
    return contenu;
  }
  public void setContenu(String contenu) {
    this.contenu = contenu;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public int getJoueur_id() {
    return joueur_id;
  }
  public void setJoueur_id(int joueur_id) {
    this.joueur_id = joueur_id;
  }

  
}
