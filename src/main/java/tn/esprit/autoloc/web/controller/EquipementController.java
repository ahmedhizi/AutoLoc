
package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Equipements", description = "Gestion des équipements")
public class EquipementController {

    private final IEquipementService service;

    @Operation(summary = "Lister les équipements")
    @GetMapping
    public List<Equipement> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un équipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Équipement trouvé"),
            @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    })
    @GetMapping("/{id}")
    public Equipement findById(
            @Parameter(description = "Identifiant")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Créer un équipement")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement create(@RequestBody Equipement equipement) {
        return service.create(equipement);
    }

    @Operation(summary = "Modifier un équipement")
    @PutMapping("/{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return service.update(id, equipement);
    }

    @Operation(summary = "Supprimer un équipement")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
