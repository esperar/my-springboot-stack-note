package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodeResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        Assertions.assertThat(messageCodes).containsExactly("required.item" , "required");
    }

    @Test
    void messageCodeResolverField() {
        String[] messageCodes = codesResolver
                .resolveMessageCodes("required", "item", "itemName", String.class);
        Assertions.assertThat(messageCodes).containsExactly(
                "required.item.itemName", "required.itemName", "required.java.lang.String", "required"
        );
    }
}
