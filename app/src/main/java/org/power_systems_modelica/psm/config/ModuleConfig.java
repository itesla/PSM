package org.power_systems_modelica.psm.config;

import java.nio.file.Path;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public interface ModuleConfig {

    String getStringProperty(String name);

    String getStringProperty(String name, String defaultValue);

    List<String> getStringListProperty(String name);

    List<String> getStringListProperty(String name, List<String> defaultValue);

    <E extends Enum<E>> E getEnumProperty(String name, Class<E> clazz);

    <E extends Enum<E>> E getEnumProperty(String name, Class<E> clazz, E defaultValue);

    <E extends Enum<E>> List<E> getEnumListProperty(String name, Class<E> clazz);

    <E extends Enum<E>> List<E> getEnumListProperty(String name, Class<E> clazz, List<E> defaultValue);

    int getIntProperty(String name);

    Integer getOptionalIntProperty(String name);

    int getIntProperty(String name, int defaultValue);

    float getFloatProperty(String name);

    float getFloatProperty(String name, float defaultValue);

    double getDoubleProperty(String name);

    double getDoubleProperty(String name, double defaultValue);

    boolean getBooleanProperty(String name);

    boolean getBooleanProperty(String name, boolean defaultValue);

    Boolean getOptinalBooleanProperty(String name);

    Path getPathProperty(String name);

    Path getPathProperty(String name, Path defaultValue);

    List<Path> getPathListProperty(String name);

    <T> Class<? extends T> getClassProperty(String name, Class<T> subClass);

    <T> Class<? extends T> getClassProperty(String name, Class<T> subClass, Class<? extends T> defaultValue);

    DateTime getDateTimeProperty(String name);

    Interval getIntervalProperty(String name);
}
