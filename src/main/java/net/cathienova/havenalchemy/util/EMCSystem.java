package net.cathienova.havenalchemy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.*;

@Mod.EventBusSubscriber
public class EMCSystem
{
    private static final Map<String, Long> map = new LinkedHashMap<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("havenalchemy");
    private static final Path EMC_FILE = CONFIG_PATH.resolve("emc_list.json");
    public static Map<String, Long> getMap() {
        return map;
    }

    public static void addEmc(Item item, long emc) {
        addEmc(itemToId(item), emc);
    }

    public static void addEmc(String item, long emc) {
        if (contains(item)) return;
        map.put(item, emc);
    }

    public static String itemToId(Item item) {
        return ItemUtil.toID(item).toString();
    }

    public static void remove(Item item) {
        map.remove(itemToId(item));
    }

    public static long getEmc(Item item) {
        return contains(item) ? map.get(itemToId(item)) : 0;
    }

    public static long getEmc(ItemStack stack) {
        return contains(stack.getItem()) ? getEmc(stack.getItem()) * stack.getCount() : 0;
    }

    public static boolean contains(Item item) {
        return contains(itemToId(item));
    }

    public static boolean contains(String item) {
        return map.containsKey(item);
    }

    public static void addEmcTags(TagKey<Item> tagKey, long emc) {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.builtInRegistryHolder().is(tagKey)) {
                addEmc(item, emc);
            }
        }
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        loadEmcValues();
    }

    private static void loadEmcValues() {
        try {
            HavenAlchemy.LOGGER.info("[HavenAlchemy]: Loading EMC values...");
            if (!java.nio.file.Files.exists(CONFIG_PATH)) {
                java.nio.file.Files.createDirectories(CONFIG_PATH);
            }
            if (java.nio.file.Files.exists(EMC_FILE)) {
                Map<String, Long> fileMap = gson.fromJson(new FileReader(EMC_FILE.toFile()), new TypeToken<Map<String, Long>>() {}.getType());
                map.clear();
                map.putAll(fileMap);
                HavenAlchemy.LOGGER.info("[HavenAlchemy]: EMC file loaded successfully with " + map.size() + " entries.");
            } else {
                HavenAlchemy.LOGGER.info("[HavenAlchemy]: EMC file does not exist, creating with default values...");
                defaultMap();
                saveEmcValues();
            }

            HavenAlchemy.LOGGER.info("[HavenAlchemy]: Setting EMC values from recipes...");
            setEmcFromRecipes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveEmcValues() {
        try (FileWriter writer = new FileWriter(EMC_FILE.toFile())) {
            gson.toJson(map, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeEmcToPlayer(Player player, ItemStack stack) {
        IncrementEmc(player, EMCSystem.getEmc(stack));
    }

    public static void decrementEmc(Player player, long amount) {
        CompoundTag playerNbt = new CompoundTag();
        player.deserializeNBT(playerNbt);
        long emc = 0;
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("emc")) {
                emc += havenAlchemyTag.getLong("emc");
            }
        }
        emc -= amount;

        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.putLong("emc", emc);
        } else {
            CompoundTag havenAlchemyTag = new CompoundTag();
            havenAlchemyTag.putLong("emc", emc);
            playerNbt.put("havenalchemy", havenAlchemyTag);
        }
        player.serializeNBT();
    }

    public static void IncrementEmc(Player player, long amount) {
        CompoundTag playerNbt = new CompoundTag();
        player.deserializeNBT(playerNbt);
        long emc = 0;
        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("emc")) {
                emc += havenAlchemyTag.getLong("emc");
            }
        }
        emc += amount;

        if (playerNbt.contains("havenalchemy")) {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.putLong("emc", emc);
        } else {
            CompoundTag havenAlchemyTag = new CompoundTag();
            havenAlchemyTag.putLong("emc", emc);
            playerNbt.put("havenalchemy", havenAlchemyTag);
        }
        player.serializeNBT();
    }

    public static void setEmcFromRecipes() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        RegistryAccess registryAccess = server.registryAccess();
        RecipeManager recipeManager = server.getRecipeManager();
        List<Recipe<?>> unsetRecipes = new ArrayList<>();
        Collection<Recipe<?>> recipes = recipeManager.getRecipes();

        for (Recipe<?> recipe : recipes) {
            ItemStack outputStack = recipe.getResultItem(registryAccess);
            addEmcFromRecipe(outputStack, recipe, unsetRecipes, false);
        }

        List<Recipe<?>> dummyList = new ArrayList<>();
        for (Recipe<?> recipe : unsetRecipes) {
            ItemStack outputStack = recipe.getResultItem(registryAccess);
            addEmcFromRecipe(outputStack, recipe, dummyList, true);
        }
    }

    public static void addEmcFromRecipe(ItemStack outStack, Recipe<?> recipe, List<Recipe<?>> unsetRecipes, boolean last)
    {
        if (!contains(outStack.getItem())) {
            long totalEmc = 0;
            for (Ingredient ingredient : recipe.getIngredients()) {
                ItemStack[] matchingStacks = ingredient.getItems();
                if (matchingStacks.length > 0) {
                    ItemStack stack = matchingStacks[0];
                    if (contains(stack.getItem())) {
                        long emcValue = getEmc(stack.getItem());
                        if (outStack.getCount() > 0) {
                            totalEmc += emcValue / outStack.getCount();
                        } else {
                            totalEmc += emcValue;
                        }
                    } else if (!last && !unsetRecipes.contains(recipe)) {
                        unsetRecipes.add(recipe);
                        return;
                    }
                }
            }
            if (totalEmc > 0) {
                addEmc(outStack.getItem(), totalEmc);
            }
        }
    }

    public static void defaultMap() {
        addEmc(Items.AMETHYST_SHARD, 32);
        addEmc(Items.AMETHYST_BLOCK, 288);
        addEmc(Items.ANCIENT_DEBRIS, 12288);
        addEmc(Items.ANDESITE, 16);
        addEmc(Items.APPLE, 128);
        addEmc(Items.ARROW, 14);
        addEmc(Items.AXOLOTL_BUCKET, 832);
        addEmc(Items.BAKED_POTATO, 64);
        addEmc(Items.BAMBOO, 32);
        addEmc(Items.BASALT, 1);
        addEmc(Items.BEEF, 64);
        addEmc(Items.BEETROOT, 64);
        addEmc(Items.BEETROOT_SEEDS, 16);
        addEmc(Items.BLACK_CONCRETE, 4);
        addEmc(Items.BLACK_CONCRETE_POWDER, 4);
        addEmc(Items.BLACK_DYE, 16);
        addEmc(Items.BLACK_SHULKER_BOX, 4176);
        addEmc(Items.BLACKSTONE, 1);
        addEmc(Items.BLAZE_POWDER, 768);
        addEmc(Items.BLAZE_ROD, 1536);
        addEmc(Items.BLUE_CONCRETE, 4);
        addEmc(Items.BLUE_CONCRETE_POWDER, 4);
        addEmc(Items.BLUE_DYE, 16);
        addEmc(Items.BLUE_SHULKER_BOX, 4176);
        addEmc(Items.BONE, 144);
        addEmc(Items.BONE_MEAL, 48);
        addEmc(Items.BRAIN_CORAL, 16);
        addEmc(Items.BRAIN_CORAL_BLOCK, 64);
        addEmc(Items.BRAIN_CORAL_FAN, 16);
        addEmc(Items.BREAD, 72);
        addEmc(Items.BRICK, 16);
        addEmc(Items.BROWN_CONCRETE, 4);
        addEmc(Items.BROWN_CONCRETE_POWDER, 4);
        addEmc(Items.BROWN_DYE, 16);
        addEmc(Items.BROWN_MUSHROOM, 32);
        addEmc(Items.BROWN_SHULKER_BOX, 4176);
        addEmc(Items.BUBBLE_CORAL, 16);
        addEmc(Items.BUBBLE_CORAL_BLOCK, 64);
        addEmc(Items.BUBBLE_CORAL_FAN, 16);
        addEmc(Items.BUDDING_AMETHYST, 512);
        addEmc(Items.CACTUS, 8);
        addEmc(Items.CALCITE, 32);
        addEmc(Items.CARROT, 64);
        addEmc(Items.CHARCOAL, 32);
        addEmc(Items.CHEST, 832);
        addEmc(Items.CHERRY_LOG, 32);
        addEmc(Items.CHICKEN, 64);
        addEmc(Items.CHORUS_FLOWER, 96);
        addEmc(Items.CHORUS_FRUIT, 192);
        addEmc(Items.CHORUS_PLANT, 64);
        addEmc(Items.CLAY_BALL, 16);
        addEmc(Items.CLAY, 64);
        addEmc(Items.COAL, 128);
        addEmc(Items.COAL_BLOCK, 1152);
        addEmc(Items.COARSE_DIRT, 2);
        addEmc(Items.COBBLED_DEEPSLATE, 2);
        addEmc(Items.COBBLESTONE, 1);
        addEmc(Items.COBBLESTONE_SLAB, 1);
        addEmc(Items.COBWEB, 12);
        addEmc(Items.COCOA_BEANS, 64);
        addEmc(Items.COD_BUCKET, 832);
        addEmc(Items.CONDUIT, 40960);
        addEmc(Items.COPPER_INGOT, 64);
        addEmc(Items.COPPER_BLOCK, 576);
        addEmc(Items.CREEPER_HEAD, 256);
        addEmc(Items.CRIMSON_FUNGUS, 32);
        addEmc(Items.CRIMSON_NYLIUM, 2);
        addEmc(Items.CRYING_OBSIDIAN, 768);
        addEmc(Items.CYAN_CONCRETE, 4);
        addEmc(Items.CYAN_CONCRETE_POWDER, 4);
        addEmc(Items.CYAN_DYE, 16);
        addEmc(Items.CYAN_SHULKER_BOX, 4176);
        addEmc(Items.DEAD_BRAIN_CORAL, 1);
        addEmc(Items.DEAD_BRAIN_CORAL_BLOCK, 4);
        addEmc(Items.DEAD_BRAIN_CORAL_FAN, 1);
        addEmc(Items.DEAD_BUBBLE_CORAL, 1);
        addEmc(Items.DEAD_BUBBLE_CORAL_BLOCK, 4);
        addEmc(Items.DEAD_BUBBLE_CORAL_FAN, 1);
        addEmc(Items.DEAD_BUSH, 1);
        addEmc(Items.DEAD_FIRE_CORAL, 1);
        addEmc(Items.DEAD_FIRE_CORAL_BLOCK, 4);
        addEmc(Items.DEAD_FIRE_CORAL_FAN, 1);
        addEmc(Items.DEAD_HORN_CORAL, 1);
        addEmc(Items.DEAD_HORN_CORAL_BLOCK, 4);
        addEmc(Items.DEAD_HORN_CORAL_FAN, 1);
        addEmc(Items.DEAD_TUBE_CORAL, 1);
        addEmc(Items.DEAD_TUBE_CORAL_BLOCK, 4);
        addEmc(Items.DEAD_TUBE_CORAL_FAN, 1);
        addEmc(Items.DEEPSLATE, 2);
        addEmc(Items.DIAMOND_HORSE_ARMOR, 2048);
        addEmc(Items.DIAMOND_ORE, 8192);
        addEmc(Items.DIORITE, 16);
        addEmc(Items.DIRT, 1);
        addEmc(Items.DRIPSTONE_BLOCK, 64);
        addEmc(Items.EGG, 32);
        addEmc(Items.EMERALD, 8192);
        addEmc(Items.EMERALD_BLOCK, 73728);
        addEmc(Items.ENCHANTED_BOOK, 160);
        addEmc(Items.ENCHANTED_GOLDEN_APPLE, 147585);
        addEmc(Items.ENDER_EYE, 1792);
        addEmc(Items.ENDER_PEARL, 1024);
        addEmc(Items.END_ROD, 432);
        addEmc(Items.END_STONE, 1);
        addEmc(Items.END_STONE_BRICKS, 1);
        addEmc(Items.END_STONE_BRICK_SLAB, 1);
        addEmc(Items.END_STONE_BRICK_STAIRS, 1);
        addEmc(Items.EXPOSED_COPPER, 64);
        addEmc(Items.FEATHER, 48);
        addEmc(Items.FERMENTED_SPIDER_EYE, 192);
        addEmc(Items.FERN, 1);
        addEmc(Items.FIRE_CORAL, 16);
        addEmc(Items.FIRE_CORAL_BLOCK, 64);
        addEmc(Items.FIRE_CORAL_FAN, 16);
        addEmc(Items.FISHING_ROD, 12);
        addEmc(Items.FISHING_ROD, 36);
        addEmc(Items.FLINT, 4);
        addEmc(Items.GHAST_TEAR, 4096);
        addEmc(Items.GLASS, 1);
        addEmc(Items.GLOWSTONE_DUST, 384);
        addEmc(Items.GLOW_BERRIES, 16);
        addEmc(Items.GLOW_INK_SAC, 400);
        addEmc(Items.GLOW_LICHEN, 8);
        addEmc(Items.GOLDEN_HORSE_ARMOR, 2048);
        addEmc(Items.GOLD_NUGGET, 227);
        addEmc(Items.GOLD_INGOT, 2048);
        addEmc(Items.GOLD_BLOCK, 18432);
        addEmc(Items.GRANITE, 16);
        addEmc(Items.GRASS, 1);
        addEmc(Items.GRASS_BLOCK, 1);
        addEmc(Items.GRAVEL, 4);
        addEmc(Items.GRAY_CONCRETE, 4);
        addEmc(Items.GRAY_CONCRETE_POWDER, 4);
        addEmc(Items.GRAY_DYE, 16);
        addEmc(Items.GRAY_SHULKER_BOX, 4176);
        addEmc(Items.GREEN_CONCRETE, 4);
        addEmc(Items.GREEN_CONCRETE_POWDER, 4);
        addEmc(Items.GREEN_DYE, 16);
        addEmc(Items.GREEN_SHULKER_BOX, 4176);
        addEmc(Items.GUNPOWDER, 192);
        addEmc(Items.HANGING_ROOTS, 4);
        addEmc(Items.HEART_OF_THE_SEA, 32768);
        addEmc(Items.HONEYCOMB, 16);
        addEmc(Items.HONEY_BOTTLE, 48);
        addEmc(Items.HORN_CORAL, 16);
        addEmc(Items.HORN_CORAL_BLOCK, 64);
        addEmc(Items.HORN_CORAL_FAN, 16);
        addEmc(Items.ICE, 1);
        addEmc(Items.INK_SAC, 16);
        addEmc(Items.INK_SAC, 16);
        addEmc(Items.IRON_HORSE_ARMOR, 2048);
        addEmc(Items.IRON_NUGGET, 28);
        addEmc(Items.IRON_INGOT, 256);
        addEmc(Items.IRON_BLOCK, 2304);
        addEmc(Items.KELP, 1);
        addEmc(Items.LADDER, 14);
        addEmc(Items.LAPIS_LAZULI, 256);
        addEmc(Items.LAPIS_BLOCK, 2304);
        addEmc(Items.LAVA_BUCKET, 832);
        addEmc(Items.LEATHER, 64);
        addEmc(Items.LEVER, 5);
        addEmc(Items.LIGHT_BLUE_CONCRETE, 4);
        addEmc(Items.LIGHT_BLUE_CONCRETE_POWDER, 4);
        addEmc(Items.LIGHT_BLUE_DYE, 16);
        addEmc(Items.LIGHT_BLUE_SHULKER_BOX, 4176);
        addEmc(Items.LIGHT_GRAY_CONCRETE, 4);
        addEmc(Items.LIGHT_GRAY_CONCRETE_POWDER, 4);
        addEmc(Items.LIGHT_GRAY_DYE, 16);
        addEmc(Items.LIGHT_GRAY_SHULKER_BOX, 4176);
        addEmc(Items.LILY_PAD, 16);
        addEmc(Items.LIME_CONCRETE, 4);
        addEmc(Items.LIME_CONCRETE_POWDER, 4);
        addEmc(Items.LIME_DYE, 16);
        addEmc(Items.MAGENTA_CONCRETE, 4);
        addEmc(Items.MAGENTA_CONCRETE_POWDER, 4);
        addEmc(Items.MAGENTA_DYE, 16);
        addEmc(Items.MAGENTA_SHULKER_BOX, 4176);
        addEmc(Items.MAGMA_BLOCK, 128);
        addEmc(Items.MANGROVE_ROOTS, 2);
        addEmc(Items.MAP, 1344);
        addEmc(Items.MELON_SEEDS, 16);
        addEmc(Items.MELON_SLICE, 16);
        addEmc(Items.MILK_BUCKET, 784);
        addEmc(Items.MOSS_BLOCK, 12);
        addEmc(Items.MOSS_CARPET, 8);
        addEmc(Items.MUD, 1);
        addEmc(Items.MUD_BRICK_STAIRS, 1);
        addEmc(Items.MYCELIUM, 2);
        addEmc(Items.NAME_TAG, 192);
        addEmc(Items.NAUTILUS_SHELL, 1024);
        addEmc(Items.NETHERITE_INGOT, 57344);
        addEmc(Items.NETHERITE_SCRAP, 12288);
        addEmc(Items.NETHERRACK, 1);
        addEmc(Items.NETHER_BRICK, 4);
        addEmc(Items.NETHER_STAR, 139264);
        addEmc(Items.NETHER_WART, 24);
        addEmc(Items.OBSIDIAN, 64);
        addEmc(Items.ORANGE_CONCRETE, 4);
        addEmc(Items.ORANGE_CONCRETE_POWDER, 4);
        addEmc(Items.ORANGE_DYE, 16);
        addEmc(Items.ORANGE_SHULKER_BOX, 4176);
        addEmc(Items.OXIDIZED_COPPER, 1152);
        addEmc(Items.PAINTING, 80);
        addEmc(Items.PAPER, 32);
        addEmc(Items.PETRIFIED_OAK_SLAB, 1);
        addEmc(Items.PHANTOM_MEMBRANE, 192);
        addEmc(Items.PINK_CONCRETE, 4);
        addEmc(Items.PINK_CONCRETE_POWDER, 4);
        addEmc(Items.PINK_DYE, 16);
        addEmc(Items.PINK_SHULKER_BOX, 4176);
        addEmc(Items.PODZOL, 2);
        addEmc(Items.POINTED_DRIPSTONE, 16);
        addEmc(Items.POLISHED_BASALT, 1);
        addEmc(Items.POPPED_CHORUS_FRUIT, 192);
        addEmc(Items.PORKCHOP, 64);
        addEmc(Items.POTATO, 64);
        addEmc(Items.POTION, 1);
        addEmc(Items.POWDER_SNOW_BUCKET, 784);
        addEmc(Items.PRISMARINE_CRYSTALS, 512);
        addEmc(Items.PRISMARINE_SHARD, 256);
        addEmc(Items.PUFFERFISH_BUCKET, 832);
        addEmc(Items.PUMPKIN, 144);
        addEmc(Items.CARVED_PUMPKIN, 108);
        addEmc(Items.PUMPKIN_SEEDS, 36);
        addEmc(Items.PURPLE_CONCRETE, 4);
        addEmc(Items.PURPLE_CONCRETE_POWDER, 4);
        addEmc(Items.PURPLE_DYE, 16);
        addEmc(Items.PURPLE_SHULKER_BOX, 4176);
        addEmc(Items.QUARTZ, 256);
        addEmc(Items.QUARTZ_BLOCK, 2304);
        addEmc(Items.RABBIT, 64);
        addEmc(Items.RABBIT_FOOT, 128);
        addEmc(Items.RABBIT_HIDE, 16);
        addEmc(Items.REDSTONE, 64);
        addEmc(Items.REDSTONE_BLOCK, 576);
        addEmc(Items.RED_CONCRETE, 4);
        addEmc(Items.RED_CONCRETE_POWDER, 4);
        addEmc(Items.RED_DYE, 16);
        addEmc(Items.RED_MUSHROOM, 32);
        addEmc(Items.RED_SHULKER_BOX, 4176);
        addEmc(Items.ROOTED_DIRT, 5);
        addEmc(Items.ROSE_BUSH, 16);
        addEmc(Items.ROTTEN_FLESH, 24);
        addEmc(Items.SADDLE, 192);
        addEmc(Items.SALMON_BUCKET, 832);
        addEmc(Items.SANDSTONE, 4);
        addEmc(Items.SANDSTONE_SLAB, 4);
        addEmc(Items.SCUTE, 96);
        addEmc(Items.SEA_PICKLE, 16);
        addEmc(Items.SHULKER_BOX, 4097);
        addEmc(Items.SHULKER_SHELL, 2048);
        addEmc(Items.SLIME_BALL, 32);
        addEmc(Items.SMOOTH_BASALT, 1);
        addEmc(Items.SMOOTH_STONE_SLAB, 1);
        addEmc(Items.SNOW, 1);
        addEmc(Items.SNOWBALL, 1);
        addEmc(Items.SOUL_SAND, 48);
        addEmc(Items.SOUL_SOIL, 48);
        addEmc(Items.SPIDER_EYE, 128);
        addEmc(Items.SPONGE, 144);
        addEmc(Items.STICK, 4);
        addEmc(Items.STONE, 1);
        addEmc(Items.STONE_BRICKS, 1);
        addEmc(Items.STONE_BRICK_SLAB, 1);
        addEmc(Items.STONE_BRICK_STAIRS, 1);
        addEmc(Items.STONE_PRESSURE_PLATE, 2);
        addEmc(Items.STONE_SLAB, 1);
        addEmc(Items.STONE_STAIRS, 1);
        addEmc(Items.STRING, 12);
        addEmc(Items.SUGAR, 15);
        addEmc(Items.SUGAR_CANE, 32);
        addEmc(Items.SWEET_BERRIES, 16);
        addEmc(Items.TALL_GRASS, 1);
        addEmc(Items.TORCH, 9);
        addEmc(Items.TRIDENT, 16464);
        addEmc(Items.TROPICAL_FISH_BUCKET, 832);
        addEmc(Items.TUBE_CORAL, 16);
        addEmc(Items.TUBE_CORAL_BLOCK, 64);
        addEmc(Items.TUBE_CORAL_FAN, 16);
        addEmc(Items.TUFF, 4);
        addEmc(Items.TURTLE_EGG, 192);
        addEmc(Items.VINE, 8);
        addEmc(Items.WARPED_FUNGUS, 32);
        addEmc(Items.WARPED_NYLIUM, 2);
        addEmc(Items.WATER_BUCKET, 768);
        addEmc(Items.WEATHERED_COPPER, 1152);
        addEmc(Items.WHEAT, 24);
        addEmc(Items.WHEAT_SEEDS, 16);
        addEmc(Items.WHITE_CONCRETE, 4);
        addEmc(Items.WHITE_CONCRETE_POWDER, 4);
        addEmc(Items.WHITE_DYE, 16);
        addEmc(Items.WHITE_SHULKER_BOX, 4176);
        addEmc(Items.WRITABLE_BOOK, 224);
        addEmc(Items.YELLOW_CONCRETE, 4);
        addEmc(Items.YELLOW_CONCRETE_POWDER, 4);
        addEmc(Items.YELLOW_DYE, 16);
        addEmc(Items.YELLOW_SHULKER_BOX, 4176);
        addEmc(Items.ZOMBIE_HEAD, 256);

        addEmcTags(ItemTags.BUTTONS, 2);
        addEmcTags(ItemTags.FENCES, 12);
        addEmcTags(ItemTags.FENCES, 32);
        addEmcTags(ItemTags.FISHES, 64);
        addEmcTags(ItemTags.FLOWERS, 16);
        addEmcTags(ItemTags.LEAVES, 1);
        addEmcTags(ItemTags.LOGS, 32);
        addEmcTags(ItemTags.MUSIC_DISCS, 2048);
        addEmcTags(ItemTags.PLANKS, 8);
        addEmcTags(ItemTags.SAND, 1);
        addEmcTags(ItemTags.SAPLINGS, 32);
        addEmcTags(ItemTags.TRAPDOORS, 24);
        addEmcTags(ItemTags.WOODEN_PRESSURE_PLATES, 16);
        addEmcTags(ItemTags.WOODEN_SLABS, 8);
        addEmcTags(ItemTags.WOODEN_STAIRS, 12);
        addEmcTags(ItemTags.WOOL, 48);
        addEmc(Items.LIME_SHULKER_BOX, 4176);

        addEmc(Items.SPIDER_SPAWN_EGG, 128);
        addEmc(Items.CREEPER_SPAWN_EGG, 128);
        addEmc(Items.SKELETON_SPAWN_EGG, 128);
        addEmc(Items.WITHER_SKELETON_SPAWN_EGG, 128);
        addEmc(Items.ZOMBIE_SPAWN_EGG, 128);
        addEmc(Items.SLIME_SPAWN_EGG, 128);
        addEmc(Items.GHAST_SPAWN_EGG, 128);
        addEmc(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG, 128);
        addEmc(Items.ENDERMAN_SPAWN_EGG, 128);
        addEmc(Items.CAVE_SPIDER_SPAWN_EGG, 128);
        addEmc(Items.SILVERFISH_SPAWN_EGG, 128);
        addEmc(Items.BLAZE_SPAWN_EGG, 128);
        addEmc(Items.MAGMA_CUBE_SPAWN_EGG, 128);
        addEmc(Items.BAT_SPAWN_EGG, 128);
        addEmc(Items.WITCH_SPAWN_EGG, 128);
        addEmc(Items.ENDERMITE_SPAWN_EGG, 128);
        addEmc(Items.GUARDIAN_SPAWN_EGG, 128);
        addEmc(Items.SHULKER_SPAWN_EGG, 128);
        addEmc(Items.PIG_SPAWN_EGG, 128);
        addEmc(Items.SHEEP_SPAWN_EGG, 128);
        addEmc(Items.COW_SPAWN_EGG, 128);
        addEmc(Items.CHICKEN_SPAWN_EGG, 128);
        addEmc(Items.SQUID_SPAWN_EGG, 128);
        addEmc(Items.WOLF_SPAWN_EGG, 128);
        addEmc(Items.MOOSHROOM_SPAWN_EGG, 128);
        addEmc(Items.OCELOT_SPAWN_EGG, 128);
        addEmc(Items.HORSE_SPAWN_EGG, 128);
        addEmc(Items.RABBIT_SPAWN_EGG, 128);
        addEmc(Items.POLAR_BEAR_SPAWN_EGG, 128);
        addEmc(Items.LLAMA_SPAWN_EGG, 128);
        addEmc(Items.PARROT_SPAWN_EGG, 128);
        addEmc(Items.VILLAGER_SPAWN_EGG, 128);
        addEmc(Items.TURTLE_SPAWN_EGG, 128);
        addEmc(Items.PUFFERFISH_SPAWN_EGG, 128);
        addEmc(Items.DOLPHIN_SPAWN_EGG, 128);
        addEmc(Items.CAT_SPAWN_EGG, 128);
        addEmc(Items.PANDA_SPAWN_EGG, 128);
        addEmc(Items.FOX_SPAWN_EGG, 128);
        addEmc(Items.BEE_SPAWN_EGG, 128);
        addEmc(Items.PIGLIN_SPAWN_EGG, 128);
        addEmc(Items.HOGLIN_SPAWN_EGG, 128);
        addEmc(Items.STRIDER_SPAWN_EGG, 128);
        addEmc(Items.ZOGLIN_SPAWN_EGG, 128);
        addEmc(Items.PIGLIN_BRUTE_SPAWN_EGG, 128);
        addEmc(Items.AXOLOTL_SPAWN_EGG, 128);
        addEmc(Items.GLOW_SQUID_SPAWN_EGG, 128);
        addEmc(Items.GOAT_SPAWN_EGG, 128);
        addEmc(Items.WANDERING_TRADER_SPAWN_EGG, 128);
        addEmc(Items.PHANTOM_SPAWN_EGG, 128);
        addEmc(Items.RAVAGER_SPAWN_EGG, 128);
        
        addEmc(ModItems.alchemical_coal.get(), 512);
        addEmc(ModItems.ethern_coal.get(), 2048);
        addEmc(ModItems.aether_fuel.get(), 8192);
    }


}