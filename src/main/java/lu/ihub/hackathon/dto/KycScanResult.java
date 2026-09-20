package lu.ihub.hackathon.dto;

public record KycScanResult(
        String entityName,
        boolean sanctionsFound,
        int riskScore,
        String status
) {}