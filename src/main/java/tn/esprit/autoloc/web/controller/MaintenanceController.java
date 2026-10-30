
package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
@Tag(name = "Maintenances", description = "Gestion des maintenances")
public class MaintenanceController {

    private final IMaintenanceService service;

    @Operation(summary = "Lister les maintenances")
    @GetMapping
    public List<Maintenance> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter une maintenance")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maintenance trouvée"),
            @ApiResponse(responseCode = "404", description = "Maintenance introuvable")
    })
    @GetMapping("/{id}")
    public Maintenance findById(
            @Parameter(description = "Identifiant")
            @PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Créer une maintenance")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return service.create(maintenance);
    }

    @Operation(summary = "Modifier une maintenance")
    @PutMapping("/{id}")
    public Maintenance update(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return service.update(id, maintenance);
    }

    @Operation(summary = "Supprimer une maintenance")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
