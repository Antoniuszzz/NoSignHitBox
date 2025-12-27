package pl.nosignhitbox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NoSignHitBox implements ClientModInitializer {

    public static boolean ENABLED = true;
    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nosignhitbox.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.nosignhitbox"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                ENABLED = !ENABLED;
                if (client.player != null) {
                    client.player.sendMessage(
                            net.minecraft.text.Text.literal(
                                    "NoSignHitBox: " + (ENABLED ? "ON" : "OFF")
                            ),
                            true
                    );
                }
            }
        });
    }
}
