package R5A05.API.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import R5A05.API.Modele.Commentaire;
import R5A05.API.Modele.Joueur;
import R5A05.API.Repository.CommentaireRepository;
import R5A05.API.Repository.JoueurRepository;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {

    private final CommentaireRepository commentaireRepository;

    public CommentaireController(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    @GetMapping
    public ResponseEntity<List<Commentaire>> getAllCommentaires() {
        return ResponseEntity.ok(commentaireRepository.findAll());
    }

    @GetMapping("/joueur/{joueurId}")
    public ResponseEntity<List<Commentaire>> getCommentairesJoueur(
            @PathVariable Integer joueurId) {
        return ResponseEntity.ok(commentaireRepository.findByJoueurId(joueurId));
    }

   @PostMapping
public ResponseEntity<Commentaire> ajoutCommentaire(
        @RequestBody Commentaire nouveauCommentaire) {
    Commentaire commentaireEnregistre = commentaireRepository.save(nouveauCommentaire);
    return ResponseEntity.status(HttpStatus.OK).body(commentaireEnregistre);

}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delOneCommentaire(@PathVariable Integer id) {
        if (!commentaireRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Commentaire introuvable");
        }

        commentaireRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
