package R5.A05.API.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
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

import R5.A05.API.ApiApplication;
import  R5.A05.API.Modele.Joueur;

@RestController
@RequestMapping("/joueurs")
public class JoueurController {

    private final JoueurRepository joueurRepository;

    public JoueurController(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @GetMapping
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

     @GetMapping("/{id}")
    public Optional<Joueur> getOneJoueur(@PathVariable Integer id) {
        return joueurRepository.findById(id);
    }

    @PostMapping
    public Joueur ajoutJoueur(@RequestBody Joueur nouveaJoueur){
        return joueurRepository.save(nouveaJoueur);
    }

     @DeleteMapping ("/{id}")
    public void delOneJoueur(@PathVariable Integer id) {
        joueurRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Joueur updateOneJoueur(@PathVariable Integer id, @RequestBody Joueur données) {
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
        return joueurRepository.save(joueurUpdate);
    }
}

