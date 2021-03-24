package hu.bendi.betterchat;

import hu.bendi.betterchat.command.BetterChatCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.kyori.adventure.platform.fabric.FabricServerAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterChat implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Better Chat");
    public static BetterChat INSTANCE;

    public static final MiniMessage miniMessage = MiniMessage.get();
    private FabricServerAudiences audiences;
    public static String VERSION = FabricLoader.getInstance().getModContainer("betterchat").get().getMetadata().getVersion().getFriendlyString();

    public BetterChat() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Allready Initialized");
        }
        INSTANCE = this;
    }

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> this.audiences = FabricServerAudiences.of(server));
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> this.audiences = null);

        BetterChatCommands.register();
    }

    public static FabricServerAudiences getAudiences() {
        return INSTANCE.audiences;
    }
}
