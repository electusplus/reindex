package com.electusplus.reindex.app_settings;

public class AppSettingsPOJO {
    private final AppPOJO app;
    private final InternalSettings internals;

    public AppSettingsPOJO(AppPOJO app, final String homeFolder) {
        this.app = app;
        this.internals = new InternalSettings(homeFolder);
    }

    public AppPOJO getApp() {
        return app;
    }

    public InternalSettings getInternals() {
        return internals;
    }

}
