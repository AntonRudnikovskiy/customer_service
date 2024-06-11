package sentinelguardcustomer_service.config.client;

import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import sentinelguardcustomer_service.dto.CustomerNotificationRequest;

@FeignClient(
        value = "notification",
        path = "api/v1/notification"
)
public interface NotificationClient {

    @PostMapping("/")
    @Observed(
            name = "user.name",
            contextualName = "api/v1/notification/notificationRequest",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    void saveNotification(CustomerNotificationRequest notificationRequest);
}
