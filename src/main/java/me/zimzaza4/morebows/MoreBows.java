package me.zimzaza4.morebows;

import cn.nukkit.Server;
import cn.nukkit.inventory.CraftingManager;
import cn.nukkit.inventory.ShapedRecipe;
import cn.nukkit.inventory.ShapelessRecipe;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.plugin.PluginBase;
import me.zimzaza4.morebows.item.arrows.*;
import me.zimzaza4.morebows.item.bows.*;
import me.zimzaza4.morebows.listeners.ProjectileListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreBows extends PluginBase {
    private static MoreBows instance;

    private static final String[] RECIPE = new String[] {
            "mmm",
            "mbm",
            "mmm"
    };
    @Override
    public void onEnable() {
        instance = this;
        Server.getInstance().getPluginManager().registerEvents(new ProjectileListener(), this);

        try {
            Item.registerCustomItem(List.of(IceArrow.class, TeleportArrow.class, TNTArrow.class, ThunderArrow.class, BlazeArrow.class, ShulkerArrow.class));
            Item.registerCustomItem(List.of(IcyBow.class, TeleportBow.class, TNTBow.class, AutomaticBow.class, ThunderBow.class, BlazeBow.class, ShulkerBow.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        CraftingManager craftingManager = Server.getInstance().getCraftingManager();


        //register bows
        craftingManager.registerRecipe(new ShapedRecipe(new AutomaticBow(), RECIPE, getBowRecipeMap(Item.get(Item.REDSTONE_BLOCK)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new BlazeBow(), RECIPE, getBowRecipeMap(Item.get(Item.BLAZE_ROD)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new IcyBow(), RECIPE, getBowRecipeMap(Item.get(Item.ICE)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new ShulkerBow(), RECIPE, getBowRecipeMap(Item.get(Item.SHULKER_SHELL)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new TeleportBow(), RECIPE, getBowRecipeMap(Item.get(Item.ENDER_PEARL)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new ThunderBow(), RECIPE, getBowRecipeMap(Item.get(Item.GOLD_BLOCK)), new ArrayList<>()));
        craftingManager.registerRecipe(new ShapedRecipe(new TNTBow(), RECIPE, getBowRecipeMap(Item.get(Item.TNT)), new ArrayList<>()));
        //register arrows
        craftingManager.registerRecipe(new ShapelessRecipe(new BlazeArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.BLAZE_POWDER))));
        craftingManager.registerRecipe(new ShapelessRecipe(new IceArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.SNOWBALL))));
        craftingManager.registerRecipe(new ShapelessRecipe(new ShulkerArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.FEATHER))));
        craftingManager.registerRecipe(new ShapelessRecipe(new TeleportArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.CHORUS_FRUIT))));
        craftingManager.registerRecipe(new ShapelessRecipe(new ThunderArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.GOLD_NUGGET))));
        craftingManager.registerRecipe(new ShapelessRecipe(new ThunderArrow(), List.of(Item.get(ItemID.ARROW), Item.get(Item.GUNPOWDER))));
        craftingManager.rebuildPacket();
    }



    public Map<Character, Item> getBowRecipeMap(Item material) {
        Map<Character, Item> map = new HashMap<>();
        map.put('b', Item.get(Item.BOW));
        map.put('m', material);
        return map;
    }

    @Override
    public void onDisable() {

    }

    public static MoreBows getInstance() {
        return instance;
    }
}
