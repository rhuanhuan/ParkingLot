package tw.aebp.passport;

import lombok.Getter;

import java.util.UUID;

public class BasePassport implements Passport {
    @Getter
    private String id;

    public BasePassport() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean verify() {
        return false;
    }
}
