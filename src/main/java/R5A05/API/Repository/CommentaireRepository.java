package R5A05.API.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import R5A05.API.Modele.Commentaire;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    default List<Commentaire> findByJoueurId(Integer idJoueur){
        List<Commentaire> allComm = findAll();
        List<Commentaire> CommJoueur = new ArrayList<>();
        for (Commentaire commentaire : allComm){
            if (commentaire.getJoueur_id() == idJoueur){
                CommJoueur.add(commentaire);
            }
        }
        return CommJoueur;
    }

}