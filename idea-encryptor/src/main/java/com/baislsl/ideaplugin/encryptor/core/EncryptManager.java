package com.baislsl.ideaplugin.encryptor.core;

import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.baislsl.ideaplugin.encryptor.core.method.Encryptor;
import com.baislsl.ideaplugin.encryptor.ui.KeyLegalDetector;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EncryptManager implements KeyLegalDetector {
    private String key;
    private EncryptMethod method;
    private static List<Encryptor> encryptors;

    static {
        encryptors = Arrays.stream(EncryptMethod.values())
                .map(EncryptMethod::getNewInstance)
                .collect(Collectors.toList());
    }

    public EncryptManager() {
        this.method = EncryptMethod.AES;
    }

    public boolean setKey(String key) {
        this.key = key;
        if (getEncoder() == null) {
            return true;
        }
        return getEncoder().isLegalKey(key);
    }

    public void setEncodeMethod(EncryptMethod method) {
        Objects.requireNonNull(method);
        this.method = method;
    }

    public String encode(String s) {
        return getEncoder().encode(s, key);
    }

    public String decode(String s) {
        return getEncoder().decode(s, key);
    }

    public Encryptor getEncoder() {
        return encryptors.get(method.ordinal());
    }

    public EncryptMethod detect(@Nullable String s) {
        if(s == null) return null;
        for (Encryptor encryptor : encryptors) {
            if(encryptor.detect(s))
                return EncryptMethod.values()[encryptors.indexOf(encryptor)];
        }
        return null;
    }

    @Override
    public boolean isLegalKey(String key) {
        return getEncoder().isLegalKey(key);
    }

    @Nullable
    @Override
    public String getHint() {
        return getEncoder().getHint();
    }
}
