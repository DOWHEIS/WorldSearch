package bsu.edu.cs222.view;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.css.StyleablePropertyFactory;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.event.WeakEventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

@DefaultProperty("children")
public class World extends Region {

    private static final StyleablePropertyFactory<World> FACTORY = new StyleablePropertyFactory<>(Region.getClassCssMetaData());
    private static final String HIRES_PROPERTIES = "bsu.edu.cs222/hires.properties";
    private static final double PREFERRED_WIDTH = 1009;
    private static final double PREFERRED_HEIGHT = 665;
    private static final double MINIMUM_WIDTH = 100;
    private static final double MINIMUM_HEIGHT = 66;
    private static final double MAXIMUM_WIDTH = 1009;
    private static final double MAXIMUM_HEIGHT = 665;
    private static final double ASPECT_RATIO = PREFERRED_HEIGHT / PREFERRED_WIDTH;
    private static final CssMetaData<World, Color> BACKGROUND_COLOR = FACTORY.createColorCssMetaData("-background-color", s -> s.backgroundColor, Color.web("#36434F"), false);
    private final StyleableProperty<Color> backgroundColor;
    private static final CssMetaData<World, Color> FILL_COLOR = FACTORY.createColorCssMetaData("-fill-color", s -> s.fillColor, Color.web("#d9d9dc"), false);
    private final StyleableProperty<Color> fillColor;
    private static final CssMetaData<World, Color> STROKE_COLOR = FACTORY.createColorCssMetaData("-stroke-color", s -> s.strokeColor, Color.BLACK, false);
    private final StyleableProperty<Color> strokeColor;
    private static final CssMetaData<World, Color> HOVER_COLOR = FACTORY.createColorCssMetaData("-hover-color", s -> s.hoverColor, Color.web("#32a852"), false);
    private final StyleableProperty<Color> hoverColor;
    private static final CssMetaData<World, Color> PRESSED_COLOR = FACTORY.createColorCssMetaData("-pressed-color", s -> s.pressedColor, Color.web("#789dff"), false);
    private final StyleableProperty<Color> pressedColor;
    private static final CssMetaData<World, Color> SELECTED_COLOR = FACTORY.createColorCssMetaData("-selected-color", s -> s.selectedColor, Color.web("#32a852"), false);
    private final StyleableProperty<Color> selectedColor;
    private final BooleanProperty hoverEnabled;
    private final BooleanProperty selectionEnabled;
    private final ObjectProperty<CountryFX> selectedCountry;
    private final BooleanProperty zoomEnabled;
    private final DoubleProperty scaleFactor;
    private final Properties resolutionProperties;
    private CountryFX formerSelectedCountry;
    private double zoomSceneX;
    private double zoomSceneY;
    private double width;
    private double height;
    protected Pane pane;
    protected Group group;
    protected Map<String, List<CountryPath>> countryPaths;

    // internal event handlers
    protected EventHandler<MouseEvent> _mouseEnterHandler;
    protected EventHandler<MouseEvent> _mousePressHandler;
    protected EventHandler<MouseEvent> _mouseReleaseHandler;
    protected EventHandler<MouseEvent> _mouseExitHandler;
    private final EventHandler<ScrollEvent> _scrollEventHandler;
    // exposed event handlers
    private EventHandler<MouseEvent> mouseEnterHandler;
    private EventHandler<MouseEvent> mousePressHandler;
    private EventHandler<MouseEvent> mouseReleaseHandler;
    private EventHandler<MouseEvent> mouseExitHandler;


    //    // ******************** Constructors **************************************
    public World() {
        resolutionProperties = readProperties();
        backgroundColor = new StyleableObjectProperty<>(BACKGROUND_COLOR.getInitialValue(World.this)) {
            @Override
            protected void invalidated() {
                setBackground(new Background(new BackgroundFill(get(), CornerRadii.EMPTY, Insets.EMPTY)));
            }

            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "backgroundColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return BACKGROUND_COLOR;
            }
        };
        fillColor = new StyleableObjectProperty<>(FILL_COLOR.getInitialValue(World.this)) {
            @Override
            protected void invalidated() {
                setFillAndStroke();
            }

            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "fillColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return FILL_COLOR;
            }
        };
        strokeColor = new StyleableObjectProperty<>(STROKE_COLOR.getInitialValue(World.this)) {
            @Override
            protected void invalidated() {
                setFillAndStroke();
            }

            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "strokeColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return STROKE_COLOR;
            }
        };
        hoverColor = new StyleableObjectProperty<>(HOVER_COLOR.getInitialValue(World.this)) {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "hoverColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return HOVER_COLOR;
            }
        };
        pressedColor = new StyleableObjectProperty<>(PRESSED_COLOR.getInitialValue(this)) {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "pressedColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return PRESSED_COLOR;
            }
        };
        selectedColor = new StyleableObjectProperty<>(SELECTED_COLOR.getInitialValue(this)) {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "selectedColor";
            }

            @Override
            public CssMetaData<? extends Styleable, Color> getCssMetaData() {
                return SELECTED_COLOR;
            }
        };
        hoverEnabled = new BooleanPropertyBase(true) {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "hoverEnabled";
            }
        };
        selectionEnabled = new BooleanPropertyBase(false) {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "selectionEnabled";
            }
        };
        selectedCountry = new ObjectPropertyBase<>() {
            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "selectedCountry";
            }
        };
        zoomEnabled = new BooleanPropertyBase(false) {
            @Override
            protected void invalidated() {
                if (null == getScene()) return;
                if (get()) {
                    getScene().addEventFilter(ScrollEvent.ANY, _scrollEventHandler);
                } else {
                    getScene().removeEventFilter(ScrollEvent.ANY, _scrollEventHandler);
                }
            }

            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "zoomEnabled";
            }
        };
        scaleFactor = new DoublePropertyBase(1.0) {
            @Override
            protected void invalidated() {
                if (isZoomEnabled()) {
                    setScaleX(get());
                    setScaleY(get());
                }
            }

            @Override
            public Object getBean() {
                return World.this;
            }

            @Override
            public String getName() {
                return "scaleFactor";
            }
        };
        countryPaths = createCountryPaths();

        pane = new Pane();
        group = new Group();

        _mouseEnterHandler = evt -> handleMouseEvent(evt, mouseEnterHandler);
        _mousePressHandler = evt -> handleMouseEvent(evt, mousePressHandler);
        _mouseReleaseHandler = evt -> handleMouseEvent(evt, mouseReleaseHandler);
        _mouseExitHandler = evt -> handleMouseEvent(evt, mouseExitHandler);
        _scrollEventHandler = evt -> {
            if (group.getTranslateX() != 0 || group.getTranslateY() != 0) {
                resetZoom();
            }
            double delta = 1.2;
            double scale = getScaleFactor();
            double oldScale = scale;
            scale = evt.getDeltaY() < 0 ? scale / delta : scale * delta;
            scale = clamp(1, 10, scale);
            double factor = (scale / oldScale) - 1;
            if (Double.compare(1, getScaleFactor()) == 0) {
                zoomSceneX = evt.getSceneX();
                zoomSceneY = evt.getSceneY();
                resetZoom();
            }
            double deltaX = (zoomSceneX - (getBoundsInParent().getWidth() / 2 + getBoundsInParent().getMinX()));
            double deltaY = (zoomSceneY - (getBoundsInParent().getHeight() / 2 + getBoundsInParent().getMinY()));
            setScaleFactor(scale);
            setPivot(deltaX * factor, deltaY * factor);

            evt.consume();
        };

        initGraphics();
        registerListeners();
    }


    // ******************** Initialization ************************************
    private void initGraphics() {
        if (Double.compare(getPrefWidth(), 0.0) <= 0 || Double.compare(getPrefHeight(), 0.0) <= 0 ||
                Double.compare(getWidth(), 0.0) <= 0 || Double.compare(getHeight(), 0.0) <= 0) {
            if (getPrefWidth() > 0 && getPrefHeight() > 0) {
                setPrefSize(getPrefWidth(), getPrefHeight());
            } else {
                setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
            }
        }

        Color fill = getFillColor();
        Color stroke = getStrokeColor();

        countryPaths.forEach((name, pathList) -> {
            CountryFX country = CountryFX.valueOf(name);
            pathList.forEach(path -> {
                path.setFill(null == country.getColor() ? fill : country.getColor());
                path.setStroke(stroke);
                path.setStrokeWidth(0.2);
                path.setOnMouseEntered(new WeakEventHandler<>(_mouseEnterHandler));
                path.setOnMousePressed(new WeakEventHandler<>(_mousePressHandler));
                path.setOnMouseReleased(new WeakEventHandler<>(_mouseReleaseHandler));
                path.setOnMouseExited(new WeakEventHandler<>(_mouseExitHandler));
            });
            pane.getChildren().addAll(pathList);
        });

        group.getChildren().add(pane);

        getChildren().setAll(group);

        setBackground(new Background(new BackgroundFill(getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void registerListeners() {
        widthProperty().addListener(o -> resize());
        heightProperty().addListener(o -> resize());
        sceneProperty().addListener(o -> {
            if (isZoomEnabled()) {
                getScene().addEventFilter(ScrollEvent.ANY, new WeakEventHandler<>(_scrollEventHandler));
            }

        });
    }


    // ******************** Methods *******************************************
    @Override
    protected double computeMinWidth(final double HEIGHT) {
        return MINIMUM_WIDTH;
    }

    @Override
    protected double computeMinHeight(final double WIDTH) {
        return MINIMUM_HEIGHT;
    }

    @Override
    protected double computePrefWidth(final double HEIGHT) {
        return super.computePrefWidth(HEIGHT);
    }

    @Override
    protected double computePrefHeight(final double WIDTH) {
        return super.computePrefHeight(WIDTH);
    }

    @Override
    protected double computeMaxWidth(final double HEIGHT) {
        return MAXIMUM_WIDTH;
    }

    @Override
    protected double computeMaxHeight(final double WIDTH) {
        return MAXIMUM_HEIGHT;
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }

    public void setMouseEnterHandler(final EventHandler<MouseEvent> HANDLER) {
        mouseEnterHandler = HANDLER;
    }

    public void setMousePressHandler(final EventHandler<MouseEvent> HANDLER) {
        mousePressHandler = HANDLER;
    }

    public void setMouseReleaseHandler(final EventHandler<MouseEvent> HANDLER) {
        mouseReleaseHandler = HANDLER;
    }

    public void setMouseExitHandler(final EventHandler<MouseEvent> HANDLER) {
        mouseExitHandler = HANDLER;
    }

    public Color getBackgroundColor() {
        return backgroundColor.getValue();
    }

    public void setBackgroundColor(final Color COLOR) {
        backgroundColor.setValue(COLOR);
    }

    public Color getFillColor() {
        return fillColor.getValue();
    }

    public void setFillColor(final Color COLOR) {
        fillColor.setValue(COLOR);
    }

    public Color getStrokeColor() {
        return strokeColor.getValue();
    }

    public void setStrokeColor(final Color COLOR) {
        strokeColor.setValue(COLOR);
    }

    public Color getHoverColor() {
        return hoverColor.getValue();
    }

    public void setHoverColor(final Color COLOR) {
        hoverColor.setValue(COLOR);
    }

    public Color getPressedColor() {
        return pressedColor.getValue();
    }

    public void setPressedColor(final Color COLOR) {
        pressedColor.setValue(COLOR);
    }

    public Color getSelectedColor() {
        return selectedColor.getValue();
    }

    public void setSelectedColor(final Color COLOR) {
        selectedColor.setValue(COLOR);
    }

    public boolean isHoverEnabled() {
        return hoverEnabled.get();
    }

    public void setHoverEnabled(final boolean ENABLED) {
        hoverEnabled.set(ENABLED);
    }

    public boolean isSelectionEnabled() {
        return selectionEnabled.get();
    }

    public void setSelectionEnabled(final boolean ENABLED) {
        selectionEnabled.set(ENABLED);
    }

    public CountryFX getSelectedCountry() {
        return selectedCountry.get();
    }

    public void setSelectedCountry(final CountryFX COUNTRYFX) {
        selectedCountry.set(COUNTRYFX);
    }

    public boolean isZoomEnabled() {
        return zoomEnabled.get();
    }

    public void setZoomEnabled(final boolean ENABLED) {
        zoomEnabled.set(ENABLED);
    }


    public double getScaleFactor() {
        return scaleFactor.get();
    }

    public void setScaleFactor(final double FACTOR) {
        scaleFactor.set(FACTOR);
    }

    public void resetZoom() {
        setScaleFactor(1.0);
        setTranslateX(0);
        setTranslateY(0);
        group.setTranslateX(0);
        group.setTranslateY(0);
    }

    public void zoomToCountry(final CountryFX COUNTRYFX) {
        if (!isZoomEnabled()) return;
        if (null != getSelectedCountry()) {
            setCountryFillAndStroke(getSelectedCountry(), getSelectedColor(), getStrokeColor());
        }
        zoomToArea(getBounds(COUNTRYFX));
    }

    private double[] getBounds(final CountryFX... COUNTRIES) {
        return getBounds(Arrays.asList(COUNTRIES));
    }

    private double[] getBounds(final List<CountryFX> COUNTRIES) {
        double upperLeftX = PREFERRED_WIDTH;
        double upperLeftY = PREFERRED_HEIGHT;
        double lowerRightX = 0;
        double lowerRightY = 0;
        for (CountryFX country : COUNTRIES) {
            List<CountryPath> paths = countryPaths.get(country.getName());
            for (CountryPath path : paths) {
                Bounds bounds = path.getLayoutBounds();
                upperLeftX = Math.min(bounds.getMinX(), upperLeftX);
                upperLeftY = Math.min(bounds.getMinY(), upperLeftY);
                lowerRightX = Math.max(bounds.getMaxX(), lowerRightX);
                lowerRightY = Math.max(bounds.getMaxY(), lowerRightY);
            }
        }
        return new double[]{upperLeftX, upperLeftY, lowerRightX, lowerRightY};
    }

    private void zoomToArea(final double[] BOUNDS) {
        group.setTranslateX(0);
        group.setTranslateY(0);
        double areaWidth = BOUNDS[2] - BOUNDS[0];
        double areaHeight = BOUNDS[3] - BOUNDS[1];
        double areaCenterX = BOUNDS[0] + areaWidth * 0.5;
        double areaCenterY = BOUNDS[1] + areaHeight * 0.5;
        Orientation orientation = areaWidth < areaHeight ? Orientation.VERTICAL : Orientation.HORIZONTAL;
        double sf = switch (orientation) {
            case VERTICAL -> clamp(1.0, 10.0, 1 / (areaHeight / height));
            case HORIZONTAL -> clamp(1.0, 10.0, 1 / (areaWidth / width));
        };

        setScaleFactor(sf);
        group.setTranslateX(width * 0.5 - (areaCenterX));
        group.setTranslateY(height * 0.5 - (areaCenterY));
    }

    private void setPivot(final double X, final double Y) {
        setTranslateX(getTranslateX() - X);
        setTranslateY(getTranslateY() - Y);
    }

    private void handleMouseEvent(final MouseEvent EVENT, final EventHandler<MouseEvent> HANDLER) {
        final CountryPath COUNTRY_PATH = (CountryPath) EVENT.getSource();
        final String COUNTRY_NAME = COUNTRY_PATH.getName();
        final CountryFX COUNTRYFX = CountryFX.valueOf(COUNTRY_NAME);
        final List<CountryPath> PATHS = countryPaths.get(COUNTRY_NAME);

        final EventType<? extends MouseEvent> TYPE = EVENT.getEventType();

        if (MOUSE_ENTERED == TYPE) {
            if (isHoverEnabled()) {
                Color color = isSelectionEnabled() && COUNTRYFX.equals(getSelectedCountry()) ? getSelectedColor() : getHoverColor();
                for (SVGPath path : PATHS) {
                    path.setFill(color);
                }
            }
        } else if (MOUSE_PRESSED == TYPE) {
            if (isSelectionEnabled()) {
                Color color;
                if (null == getSelectedCountry()) {
                    setSelectedCountry(COUNTRYFX);
                    color = getSelectedColor();
                } else {
                    color = null == getSelectedCountry().getColor() ? getFillColor() : getSelectedCountry().getColor();
                }
                for (SVGPath path : countryPaths.get(getSelectedCountry().getName())) {
                    path.setFill(color);
                }
            } else {
                if (isHoverEnabled()) {
                    for (SVGPath path : PATHS) {
                        path.setFill(getPressedColor());
                    }
                }
            }
        } else if (MOUSE_RELEASED == TYPE) {
            Color color;
            if (isSelectionEnabled()) {
                if (formerSelectedCountry == COUNTRYFX) {
                    setSelectedCountry(null);
                    color = null == COUNTRYFX.getColor() ? getFillColor() : COUNTRYFX.getColor();
                } else {
                    setSelectedCountry(COUNTRYFX);
                    color = getSelectedColor();
                }
                formerSelectedCountry = getSelectedCountry();
            } else {
                color = getHoverColor();
            }
            if (isHoverEnabled()) {
                for (SVGPath path : PATHS) {
                    path.setFill(color);
                }
            }
        } else if (MOUSE_EXITED == TYPE) {
            if (isHoverEnabled()) {
                Color color = isSelectionEnabled() && COUNTRYFX.equals(getSelectedCountry()) ? getSelectedColor() : getFillColor();
                for (SVGPath path : PATHS) {
                    path.setFill(null == COUNTRYFX.getColor() || COUNTRYFX == getSelectedCountry() ? color : COUNTRYFX.getColor());
                }
            }
        }

        if (null != HANDLER) HANDLER.handle(EVENT);
    }

    private void setFillAndStroke() {
        countryPaths.keySet().forEach(name -> {
            CountryFX country = CountryFX.valueOf(name);
            setCountryFillAndStroke(country, null == country.getColor() ? getFillColor() : country.getColor(), getStrokeColor());
        });
    }

    private void setCountryFillAndStroke(final CountryFX COUNTRYFX, final Color FILL, final Color STROKE) {
        List<CountryPath> paths = countryPaths.get(COUNTRYFX.getName());
        for (CountryPath path : paths) {
            path.setFill(FILL);
            path.setStroke(STROKE);
        }
    }

    private double clamp(final double MIN, final double MAX, final double VALUE) {
        if (VALUE < MIN) return MIN;
        return Math.min(VALUE, MAX);
    }

    private Properties readProperties() {
        final ClassLoader LOADER = Thread.currentThread().getContextClassLoader();
        final Properties PROPERTIES = new Properties();
        try (InputStream resourceStream = LOADER.getResourceAsStream(World.HIRES_PROPERTIES)) {
            PROPERTIES.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PROPERTIES;
    }

    private Map<String, List<CountryPath>> createCountryPaths() {
        Map<String, List<CountryPath>> countryPaths = new HashMap<>();
        resolutionProperties.forEach((key, value) -> {
            String name = key.toString();
            List<CountryPath> pathList = new ArrayList<>();
            for (String path : value.toString().split(";")) {
                pathList.add(new CountryPath(name, path));
            }
            countryPaths.put(name, pathList);
        });
        return countryPaths;
    }


    // ******************** Style related *************************************
    @Override
    public String getUserAgentStylesheet() {
        return Objects.requireNonNull(World.class.getClassLoader().getResource("bsu.edu.cs222/world.css")).toExternalForm();
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return FACTORY.getCssMetaData();
    }


    // ******************** Resizing ******************************************
    private void resize() {
        width = getWidth() - getInsets().getLeft() - getInsets().getRight();
        height = getHeight() - getInsets().getTop() - getInsets().getBottom();

        if (ASPECT_RATIO * width > height) {
            width = 1 / (ASPECT_RATIO / height);
        } else if (1 / (ASPECT_RATIO / height) > width) {
            height = ASPECT_RATIO * width;
        }

        if (width > 0 && height > 0) {
            if (isZoomEnabled()) resetZoom();

            pane.setCache(true);
            pane.setCacheHint(CacheHint.SCALE);

            pane.setScaleX(width / PREFERRED_WIDTH);
            pane.setScaleY(height / PREFERRED_HEIGHT);

            group.resize(width, height);
            group.relocate((getWidth() - width) * 0.5, (getHeight() - height) * 0.5);

            pane.setCache(false);
        }
    }
}
