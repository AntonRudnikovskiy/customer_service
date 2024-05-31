package sentinelguardcustomer_service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sentinelguardcustomer_service.dto.CustomerRegistrationRequest;
import sentinelguardcustomer_service.entity.Customer;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    Customer toEntity(CustomerRegistrationRequest customerRegistrationRequest);
}
