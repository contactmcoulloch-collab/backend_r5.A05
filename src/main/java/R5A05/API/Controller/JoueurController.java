package R5A05.API.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

import R5A05.API.ApiApplication;
import R5A05.API.Modele.Joueur;
import R5A05.API.Repository.JoueurRepository;

@RestController
@RequestMapping("/joueurs")
public class JoueurController {

    private final JoueurRepository joueurRepository;

    public JoueurController(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @GetMapping
    public ResponseEntity<List<Joueur>> getAllJoueurs() {
       List<Joueur> listeJoeurs = joueurRepository.findAll();
       return ResponseEntity.status(HttpStatus.OK).body(listeJoeurs);
    }

     @GetMapping("/{id}")
    public Optional<Joueur> getOneJoueur(@PathVariable Integer id) {
        return joueurRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Joueur> ajoutJoueur(@RequestBody Joueur nouveauJoueur) {
    Joueur joueurEnregistre = joueurRepository.save(nouveauJoueur);
    return ResponseEntity.status(HttpStatus.CREATED).body(joueurEnregistre);
}


     @DeleteMapping("/{id}")
public ResponseEntity<Void> delOneJoueur(@PathVariable Integer id) {
    joueurRepository.deleteById(id);
    return ResponseEntity.ok().build();
}

    @PutMapping("/{id}")
    public ResponseEntity<Joueur> updateOneJoueur(@PathVariable Integer id, @RequestBody Joueur données) {
       Joueur joueurUpdate = joueurRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Joueur introuvable"
            ));;
        joueurUpdate.setDate_naissance(données.getDate_naissance());
        joueurUpdate.setNom(données.getNom());
        joueurUpdate.setPrenom(données.getPrenom());
        joueurUpdate.setNumero_licence(données.getNumero_licence());
        joueurUpdate.setPoids(données.getPoids());
        joueurUpdate.setTaille(données.getTaille());
        joueurUpdate.setStatut(données.getStatut());
        Joueur joueurEnregistre = joueurRepository.save(joueurUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(joueurEnregistre);
    }
}

