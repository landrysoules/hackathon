package lu.ihub.hackathon.service;

import lu.ihub.hackathon.dto.KycScanResult;
import org.springframework.stereotype.Service;

@Service
public class EntityScanService {

    // Logique métier : calcul de risque, requêtes PostgreSQL, etc.
    public KycScanResult performScan(String entityName, String operator) {
        // Traitement métier simulé
        boolean sanctionsFound = false;
        int riskScore = 15; // Risque faible

        return new KycScanResult(entityName, sanctionsFound, riskScore, "APPROUVÉ");
    }
}