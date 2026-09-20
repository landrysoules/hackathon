package lu.ihub.hackathon.controller;

import jakarta.servlet.http.HttpSession;
import lu.ihub.hackathon.service.EntityScanService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pages/entity-scan")
public class EntityScanController {

    private final EntityScanService entityScanService;

    // Injection du service via le constructeur
    public EntityScanController(EntityScanService entityScanService) {
        this.entityScanService = entityScanService;
    }

    // 1. Appel du menu latéral HTMX : renvoie la vue de base de l'écran
    @GetMapping
    public String renderScreen(HttpSession session, Model model) {
        model.addAttribute("sessionId", session.getId());
        return "pages/entity-scan :: content";
    }

    // 2. Action HTMX (ex: clic sur le bouton de scan)
    @PostMapping("/scan")
    public String scanEntity(@RequestParam String entityName,
                             @AuthenticationPrincipal UserDetails userDetails,
                             Model model) {
        // Le contrôleur délègue au service
        var result = entityScanService.performScan(entityName, userDetails.getUsername());

        // On passe les données à la vue
        model.addAttribute("scanResult", result);

        // On renvoie juste le petit fragment du résultat mis à jour
        return "pages/entity-scan :: scan-result";
    }
}