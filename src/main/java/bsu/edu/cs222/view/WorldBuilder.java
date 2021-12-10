package bsu.edu.cs222.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.HashMap;

//suppressed unchecked warning because object property will always be a width/height which is what dimension2d contains
@SuppressWarnings("unchecked")
public class WorldBuilder {
    private final HashMap<String, Property<?>> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected WorldBuilder() {
    }


    // ******************** Methods *******************************************
    public static WorldBuilder create() {
        return new WorldBuilder();
    }

    public final WorldBuilder selectionEnabled(final boolean ENABLED) {
        properties.put("selectionEnabled", new SimpleBooleanProperty(ENABLED));
        return this;
    }

    public final WorldBuilder zoomEnabled(final boolean ENABLED) {
        properties.put("zoomEnabled", new SimpleBooleanProperty(ENABLED));
        return this;
    }

    public final World build() {
        final World CONTROL = new World();

        for (String key : properties.keySet()) {
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setPrefSize(dim.getWidth(), dim.getHeight());
            } else if ("minSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setMinSize(dim.getWidth(), dim.getHeight());
            } else if ("maxSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                CONTROL.setMaxSize(dim.getWidth(), dim.getHeight());
            } else if ("prefWidth".equals(key)) {
                CONTROL.setPrefWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("prefHeight".equals(key)) {
                CONTROL.setPrefHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("minWidth".equals(key)) {
                CONTROL.setMinWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("minHeight".equals(key)) {
                CONTROL.setMinHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("maxWidth".equals(key)) {
                CONTROL.setMaxWidth(((DoubleProperty) properties.get(key)).get());
            } else if ("maxHeight".equals(key)) {
                CONTROL.setMaxHeight(((DoubleProperty) properties.get(key)).get());
            } else if ("scaleX".equals(key)) {
                CONTROL.setScaleX(((DoubleProperty) properties.get(key)).get());
            } else if ("scaleY".equals(key)) {
                CONTROL.setScaleY(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutX".equals(key)) {
                CONTROL.setLayoutX(((DoubleProperty) properties.get(key)).get());
            } else if ("layoutY".equals(key)) {
                CONTROL.setLayoutY(((DoubleProperty) properties.get(key)).get());
            } else if ("translateX".equals(key)) {
                CONTROL.setTranslateX(((DoubleProperty) properties.get(key)).get());
            } else if ("translateY".equals(key)) {
                CONTROL.setTranslateY(((DoubleProperty) properties.get(key)).get());
            } else if ("padding".equals(key)) {
                CONTROL.setPadding((Insets) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("backgroundColor".equals(key)) {
                CONTROL.setBackgroundColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("fillColor".equals(key)) {
                CONTROL.setFillColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("strokeColor".equals(key)) {
                CONTROL.setStrokeColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("hoverColor".equals(key)) {
                CONTROL.setHoverColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("pressedColor".equals(key)) {
                CONTROL.setPressedColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("selectedColor".equals(key)) {
                CONTROL.setSelectedColor((Color) ((ObjectProperty<?>) properties.get(key)).get());
            } else if ("hoverEnabled".equals(key)) {
                CONTROL.setHoverEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("selectionEnabled".equals(key)) {
                CONTROL.setSelectionEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("zoomEnabled".equals(key)) {
                CONTROL.setZoomEnabled(((BooleanProperty) properties.get(key)).get());
            } else if ("mouseEnterHandler".equals(key)) {
                CONTROL.setMouseEnterHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mousePressHandler".equals(key)) {
                CONTROL.setMousePressHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mouseReleaseHandler".equals(key)) {
                CONTROL.setMouseReleaseHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            } else if ("mouseExitHandler".equals(key)) {
                CONTROL.setMouseExitHandler(((ObjectProperty<EventHandler<MouseEvent>>) properties.get(key)).get());
            }
        }
        return CONTROL;
    }
}
