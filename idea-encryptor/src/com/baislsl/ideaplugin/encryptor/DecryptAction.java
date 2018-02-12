package com.baislsl.ideaplugin.encryptor;

import com.baislsl.ideaplugin.encryptor.core.EncryptManager;
import com.baislsl.ideaplugin.encryptor.core.method.EncryptMethod;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class DecryptAction extends AnAction {
    private final static Logger LOG = LoggerFactory.getLogger(EncryptAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Objects.requireNonNull(project);
        Document document = e.getData(PlatformDataKeys.EDITOR).getDocument();
        LOG.info(document.getText());

        EncryptManager encryptManager = new EncryptManager();
        encryptManager.setEncodeMethod(EncryptMethod.NATIVE_ENCODER);
        document.setText(encryptManager.decode(document.getText()));
    }
}
