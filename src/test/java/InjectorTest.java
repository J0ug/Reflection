import org.example.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InjectorTest {

    @Test
    void testOrderServiceInjection() throws Exception {
        Injector injector = new Injector("src/main/resources/config.properties");
        OrderService orderService = injector.inject(new OrderService());

        assertNotNull(orderService.getPaymentProcessor(), "PaymentProcessor should be injected");
        assertNotNull(orderService.getNotificationSender(), "NotificationSender should be injected");

        // Дополнительные проверки
        orderService.placeOrder(100.0, "Order placed successfully!");
    }
}
