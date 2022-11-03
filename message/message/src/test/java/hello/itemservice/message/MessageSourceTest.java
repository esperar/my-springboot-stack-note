package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        String result = ms.getMessage("hello", null, null);
        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        Assertions.assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String argumentMessage = ms.getMessage("안녕", new Object[]{"Spring"}, null);
        Assertions.assertThat(argumentMessage).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        Assertions.assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        Assertions.assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }
}
