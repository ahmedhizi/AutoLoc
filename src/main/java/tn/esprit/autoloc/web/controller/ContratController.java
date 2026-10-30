
package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats")
public class ContratController {

    private final IContratService service;

    @Operation(summary = "Lister les contrats")
    @GetMapping
    public List<Contrat> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un contrat")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    @GetMapping("/{id}")
    public Contrat findById(
            @Parameter(description = "Identifiant")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Créer un contrat")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contrat create(@RequestBody Contrat contrat) {
        return service.create(contrat);
    }

    @Operation(summary = "Modifier un contrat")
    @PutMapping("/{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return service.update(id, contrat);
    }

    @Operation(summary = "Supprimer un contrat")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
