package sentinelguardcustomer_service.dto;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
