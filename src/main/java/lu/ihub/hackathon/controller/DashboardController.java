package lu.ihub.hackathon.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // On récupère le nom de l'utilisateur connecté via Spring Security
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        // La persistance de session HTTP reste gérée par le conteneur Redis en fond
        return "index";
    }

    // Endpoint fragment HTMX (Retourne un composant HTML réactif partiel)
    @GetMapping("/api/kyc-check")
    @ResponseBody
    public String performKycCheck(@RequestParam(defaultValue = "ENT-9842") String entityId)
            throws InterruptedException {

        // Simulation du temps de réponse d'un scan de sanctions (OFAC, ONU)
        Thread.sleep(1200);

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        return """



                        ✓ Entité Validée - Conformité Conforme

                    %s


                    Aucun match trouvé pour %s sur les listes de gel des avoirs (OFAC, UE, PEP).


                    Score Risque: 0.02
                    Statut: ACTIF


            """.formatted(now, entityId);
    }
}