package org.lpoo.g55.commands;

import java.io.IOException;

public interface Command {
    void execute() throws IOException;

    String getText();
}
