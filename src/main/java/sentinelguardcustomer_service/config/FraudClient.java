package sentinelguardcustomer_service.config;

import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sentinelguardcustomer_service.dto.FraudCheckResponse;

@FeignClient(
        value = "fraud",
        path = "api/v1/fraud-check"
)
public interface FraudClient {

    @GetMapping(path = "{customerId}")
    @Observed(
            name = "user.name",
            contextualName = "api/v1/fraud-check/fraud",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    FraudCheckResponse isFraudster(@PathVariable("customerId") long customerID);
}
