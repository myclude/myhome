package me.myclude.calculator.common;

import org.jasypt.encryption.StringEncryptor;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
class AppConfigTest {

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    private StringEncryptor jasStringEncryptor;

    @Test
    @DisplayName("property 암호화 테스트")
    public void encryptor_test()  {

        assertThat(jasStringEncryptor).isNotNull();

    }

    @Test
    @DisplayName("암호화 테스트")
    public void encryptor_encrypt() {

        //given
        String orgText = "sa";
        String encText = jasStringEncryptor.encrypt(orgText);

        System.out.println("encText = " + encText);

        //when
        String decText = jasStringEncryptor.decrypt(encText);
        System.out.println("decText = " + decText);

        //then
        assertThat(decText).isEqualTo(orgText);

    }

}