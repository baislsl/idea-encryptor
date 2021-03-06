package com.baislsl.ideaplugin.encryptor.core;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;

public class EncryptExecutor extends BaseTransferExecutor {

    public EncryptExecutor(Project project, String text, Runnable executor) {
        super(project, text, executor);
    }

    @Override
    protected String conductTransfer() {
        EncryptManager encryptManager = new EncryptManager();
        encryptManager.setEncodeMethod(method);
        encryptManager.setKey(key);
        return encryptManager.encode(text);
    }
}
