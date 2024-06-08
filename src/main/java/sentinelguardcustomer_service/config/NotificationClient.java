package sentinelguardcustomer_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import sentinelguardcustomer_service.dto.CustomerNotificationRequest;

@FeignClient(
        value = "notification",
        path = "api/v1/notification"
)
public interface NotificationClient {

    @PostMapping("/")
    void saveNotification(CustomerNotificationRequest notificationRequest);
}
