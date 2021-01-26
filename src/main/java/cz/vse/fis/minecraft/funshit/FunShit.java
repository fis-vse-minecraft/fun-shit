package cz.vse.fis.minecraft.funshit;

import org.bukkit.plugin.java.JavaPlugin;

public final class FunShit extends JavaPlugin {

    @Override
    public void onEnable() {
        registerEventListeners();
    }

    @Override
    public void onDisable() {
    }

    /**
     * Register all plugin event listeners under this plugin
     * Potentially this method can be later an IOC dependency injection entrypoint?
     */
    private void registerEventListeners() {
        getServer()
                .getPluginManager()
                .registerEvents(new EggImplosionGrenadesListener(this), this);
    }
}
