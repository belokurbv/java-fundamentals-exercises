package com.bobocode.lesson_4.patterns.factory_method.creator;

import com.bobocode.lesson_4.patterns.factory_method.Connector;
import com.bobocode.lesson_4.patterns.factory_method.NoSQLConnector;

public class NoSQLConnectorCreator implements ConnectorCreator {

    @Override
    public Connector create() {
        return new NoSQLConnector();
    }
}
