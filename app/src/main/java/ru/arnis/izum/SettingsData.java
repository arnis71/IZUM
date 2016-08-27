package ru.arnis.izum;

/**
 * Created by arnis on 24/08/16.
 */

//Класс для хранения настроек
public class SettingsData {
    private boolean autoScroll;
    private int autoScrollInterval;
    private String animation;
    private boolean showRandom;
    private boolean showFavourites;

    public SettingsData() {
    }

    public boolean isAutoScroll() {
        return autoScroll;
    }

    public void setAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
    }

    public int getAutoScrollInterval() {
        return autoScrollInterval;
    }

    public void setAutoScrollInterval(int autoScrollInterval) {
        this.autoScrollInterval = autoScrollInterval;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public boolean isShowRandom() {
        return showRandom;
    }

    public void setShowRandom(boolean showRandom) {
        this.showRandom = showRandom;
    }

    public boolean isShowFavourites() {
        return showFavourites;
    }

    public void setShowFavourites(boolean showFavourites) {
        this.showFavourites = showFavourites;
    }
}
