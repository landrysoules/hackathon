package lu.ihub.hackathon.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    // Chargement initial complet du squelette
    @GetMapping({"/", "/entity-scan"})
    public String index(HttpSession session, Model model) {
        model.addAttribute("sessionId", session.getId());
        return "index";
    }


    // HTMX charge l'écran 2
    @GetMapping("/pages/pep-sanctions")
    public String getPepSanctionsFragment() {
        return "pages/pep-sanctions :: content";
    }

    // HTMX charge l'écran 3
    @GetMapping("/pages/transactions")
    public String getTransactionsFragment() {
        return "pages/transactions :: content";
    }

    // HTMX charge l'écran 4
    @GetMapping("/pages/audit-logs")
    public String getAuditLogsFragment() {
        return "pages/audit-logs :: content";
    }

    // Endpoint d'action métier KYC (inchangé)[cite: 1]
    @GetMapping("/api/kyc-status")
    @ResponseBody
    public String getKycStatus() throws InterruptedException {
        Thread.sleep(800);
        return "<article>" +
                "<header><strong>Statut : </strong> <span style='color: green;'>APPROUVÉ</span></header>" +
                "Aucune correspondance trouvée sur les listes de sanctions internationales (OFAC, ONU, UE)." +
                "</article>" +
                "<script>document.querySelector('[hx-get=\"/api/kyc-status\"]').setAttribute('aria-busy', 'false');</script>";
    }
}