package net.cathienova.havenalchemy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
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
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
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

    public static Map<String, Long> getMap()
    {
        return map;
    }

    public static void addEmc(Item item, long emc)
    {
        addEmc(itemToId(item), emc);
    }

    public static void addEmc(String item, long emc)
    {
        if (contains(item)) return;
        map.put(item, emc);
    }

    public static String itemToId(Item item)
    {
        return ItemUtil.toID(item).toString();
    }

    public static void remove(Item item)
    {
        map.remove(itemToId(item));
    }

    public static long GetEmc(Item item)
    {
        return contains(item) ? map.get(itemToId(item)) : 0;
    }

    public static long GetEmc(ItemStack stack)
    {
        return contains(stack.getItem()) ? GetEmc(stack.getItem()) * stack.getCount() : 0;
    }

    public static boolean contains(Item item)
    {
        return contains(itemToId(item));
    }

    public static boolean contains(String item)
    {
        return map.containsKey(item);
    }

    public static void addEmcTags(TagKey<Item> tagKey, long emc)
    {
        ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item.builtInRegistryHolder().tags().anyMatch(tag -> tag.equals(tagKey)))
                .forEach(item -> addEmc(item, emc));
    }

    public static void loadEmcValues()
    {
        try
        {
            HavenAlchemy.LOGGER.info("[HavenAlchemy]: Loading EMC values...");
            if (!java.nio.file.Files.exists(CONFIG_PATH))
            {
                java.nio.file.Files.createDirectories(CONFIG_PATH);
            }
            if (java.nio.file.Files.exists(EMC_FILE))
            {
                Map<String, Long> fileMap = gson.fromJson(new FileReader(EMC_FILE.toFile()), new TypeToken<Map<String, Long>>()
                {
                }.getType());
                for (Map.Entry<String, Long> entry : fileMap.entrySet())
                {
                    if (entry.getValue() > 0)
                        map.put(entry.getKey(), entry.getValue());
                }
                HavenAlchemy.LOGGER.info("[HavenAlchemy]: EMC file loaded successfully with " + map.size() + " entries.");
            }
            else
            {
                HavenAlchemy.LOGGER.info("[HavenAlchemy]: EMC file does not exist, creating with default values...");
                defaultMap();
                saveEmcValues();
            }

            HavenAlchemy.LOGGER.info("[HavenAlchemy]: Setting EMC values from recipes...");
            setEmcFromRecipes();
            saveEmcValues();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void saveEmcValues()
    {
        try (FileWriter writer = new FileWriter(EMC_FILE.toFile()))
        {
            gson.toJson(map, writer);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void writeEmcToPlayer(Player player, ItemStack stack)
    {
        IncrementEmc(player, EMCSystem.GetEmc(stack));
    }

    public static void decrementEmc(Player player, long amount)
    {
        CompoundTag playerNbt = new CompoundTag();
        player.deserializeNBT(playerNbt);
        long emc = 0;
        if (playerNbt.contains("havenalchemy"))
        {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("emc"))
            {
                emc += havenAlchemyTag.getLong("emc");
            }
        }
        emc -= amount;

        if (playerNbt.contains("havenalchemy"))
        {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.putLong("emc", emc);
        }
        else
        {
            CompoundTag havenAlchemyTag = new CompoundTag();
            havenAlchemyTag.putLong("emc", emc);
            playerNbt.put("havenalchemy", havenAlchemyTag);
        }
        player.serializeNBT();
    }

    public static void IncrementEmc(Player player, long amount)
    {
        CompoundTag playerNbt = new CompoundTag();
        player.deserializeNBT(playerNbt);
        long emc = 0;
        if (playerNbt.contains("havenalchemy"))
        {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            if (havenAlchemyTag.contains("emc"))
            {
                emc += havenAlchemyTag.getLong("emc");
            }
        }
        emc += amount;

        if (playerNbt.contains("havenalchemy"))
        {
            CompoundTag havenAlchemyTag = playerNbt.getCompound("havenalchemy");
            havenAlchemyTag.putLong("emc", emc);
        }
        else
        {
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

        int iterations = 5;

        for(int i = 0; i < iterations; i++)
        {
            for (Recipe<?> recipe : recipes)
            {
                ItemStack outputStack = recipe.getResultItem(registryAccess);
                addEmcFromRecipe(outputStack, recipe, unsetRecipes, false);
            }

            List<Recipe<?>> dummyList = new ArrayList<>();
            for (Recipe<?> recipe : unsetRecipes)
            {
                ItemStack outputStack = recipe.getResultItem(registryAccess);
                addEmcFromRecipe(outputStack, recipe, dummyList, true);
            }
        }
    }

    public static void addEmcFromRecipe(ItemStack outStack, Recipe<?> recipe, List<Recipe<?>> unsetRecipes, boolean last) {
        if (!contains(outStack.getItem())) {
            long totalEmc = 0;
            boolean allIngredientsResolved = true;

            for (Ingredient ingredient : recipe.getIngredients()) {
                ItemStack[] matchingStacks = ingredient.getItems();
                long ingredientLowestEmc = Long.MAX_VALUE;

                if (matchingStacks.length == 0) continue;

                for (ItemStack stack : matchingStacks) {
                    if (contains(stack.getItem())) {
                        long emcValue = GetEmc(stack.getItem());
                        if (emcValue < ingredientLowestEmc) {
                            ingredientLowestEmc = emcValue;
                        }
                    } else {
                        allIngredientsResolved = false;
                        break;
                    }
                }

                if (ingredientLowestEmc != Long.MAX_VALUE) {
                    totalEmc += ingredientLowestEmc;
                }
            }

            if (!allIngredientsResolved) {
                if (!last && !unsetRecipes.contains(recipe)) {
                    unsetRecipes.add(recipe);
                }
                return;
            }

            // Calculate the EMC value per item produced by the recipe.
            long emcPerItem = totalEmc / Math.max(1, outStack.getCount());

            // Assign the calculated EMC value if it's positive.
            if (emcPerItem > 0) {
                addEmc(outStack.getItem(), emcPerItem);
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
        addEmc(Items.BLACK_SHULKER_BOX, 4176);
        addEmc(Items.BLACKSTONE, 1);
        addEmc(Items.BLAZE_POWDER, 768);
        addEmc(Items.BLAZE_ROD, 1536);
        addEmc(Items.BLUE_CONCRETE, 4);
        addEmc(Items.BLUE_CONCRETE_POWDER, 4);
        addEmc(Items.BLUE_SHULKER_BOX, 4176);
        addEmc(Items.BONE, 144);
        addEmc(Items.BONE_MEAL, 48);
        addEmc(Items.BOWL, 6);
        addEmc(Items.BRAIN_CORAL, 16);
        addEmc(Items.BRAIN_CORAL_BLOCK, 64);
        addEmc(Items.BRAIN_CORAL_FAN, 16);
        addEmc(Items.BREAD, 72);
        addEmc(Items.BRICK, 16);
        addEmc(Items.BROWN_CONCRETE, 4);
        addEmc(Items.BROWN_CONCRETE_POWDER, 4);
        addEmc(Items.BROWN_MUSHROOM, 32);
        addEmc(Items.BROWN_SHULKER_BOX, 4176);
        addEmc(Items.BUBBLE_CORAL, 16);
        addEmc(Items.BUBBLE_CORAL_BLOCK, 64);
        addEmc(Items.BUBBLE_CORAL_FAN, 16);
        addEmc(Items.BUCKET, 768);
        addEmc(Items.BUDDING_AMETHYST, 512);
        addEmc(Items.CACTUS, 8);
        addEmc(Items.CALCITE, 32);
        addEmc(Items.CARROT, 64);
        addEmc(Items.CHARCOAL, 32);
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
        addEmc(Items.DIAMOND, 8192);
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
        addEmc(Items.GRAY_SHULKER_BOX, 4176);
        addEmc(Items.GREEN_CONCRETE, 4);
        addEmc(Items.GREEN_CONCRETE_POWDER, 4);
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
        addEmc(Items.LEVER, 5);
        addEmc(Items.LIGHT_BLUE_CONCRETE, 4);
        addEmc(Items.LIGHT_BLUE_CONCRETE_POWDER, 4);
        addEmc(Items.LIGHT_BLUE_SHULKER_BOX, 4176);
        addEmc(Items.LIGHT_GRAY_CONCRETE, 4);
        addEmc(Items.LIGHT_GRAY_CONCRETE_POWDER, 4);
        addEmc(Items.LIGHT_GRAY_SHULKER_BOX, 4176);
        addEmc(Items.LILY_PAD, 16);
        addEmc(Items.LIME_CONCRETE, 4);
        addEmc(Items.LIME_CONCRETE_POWDER, 4);
        addEmc(Items.MAGENTA_CONCRETE, 4);
        addEmc(Items.MAGENTA_CONCRETE_POWDER, 4);
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
        addEmc(Items.ORANGE_SHULKER_BOX, 4176);
        addEmc(Items.OXIDIZED_COPPER, 1152);
        addEmc(Items.PAINTING, 80);
        addEmc(Items.PAPER, 32);
        addEmc(Items.PETRIFIED_OAK_SLAB, 1);
        addEmc(Items.PHANTOM_MEMBRANE, 192);
        addEmc(Items.PINK_CONCRETE, 4);
        addEmc(Items.PINK_CONCRETE_POWDER, 4);
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
        addEmc(Items.WHITE_SHULKER_BOX, 4176);
        addEmc(Items.WRITABLE_BOOK, 224);
        addEmc(Items.YELLOW_CONCRETE, 4);
        addEmc(Items.YELLOW_CONCRETE_POWDER, 4);
        addEmc(Items.YELLOW_SHULKER_BOX, 4176);
        addEmc(Items.ZOMBIE_HEAD, 256);



        for (Item item : ModItems.ITEMS.getEntries().stream().map(RegistryObject::get).toList()) {
            if (item instanceof SpawnEggItem) {
                addEmc(item, 128);
            }
        }

        addEmc(Items.LIME_SHULKER_BOX, 4176);
        addEmc(ModBlocks.dirt_chest.get().asItem(), 40);
        addEmc(ModBlocks.stone_chest.get().asItem(), 40);
        addEmc(ModBlocks.copper_chest.get().asItem(), 576);
        addEmc(ModBlocks.iron_chest.get().asItem(), 2624);
        addEmc(ModBlocks.gold_chest.get().asItem(), 19008);
        addEmc(ModBlocks.diamond_chest.get().asItem(), 84544);
        addEmc(ModBlocks.alchemical_chest.get().asItem(), 91556);
        addEmc(ModBlocks.emerald_chest.get().asItem(), 150080);
        addEmc(ModBlocks.obsidian_chest.get().asItem(), 85056);
        addEmc(ModBlocks.netherite_chest.get().asItem(), 608832);
        addEmc(ModItems.alchemical_coal.get(), 512);
        addEmc(ModBlocks.alchemical_coal_block.get().asItem(), 4608);
        addEmc(ModItems.ethern_coal.get(), 2048);
        addEmc(ModBlocks.ethern_coal_block.get().asItem(), 18432);
        addEmc(ModItems.aether_fuel.get(), 8192);
        addEmc(ModBlocks.aether_fuel_block.get().asItem(), 73728);
        addEmc(ModBlocks.red_matter_block.get().asItem(), 4202496);
        addEmc(ModItems.red_matter_pickaxe.get(), 1548288);
        addEmc(ModItems.red_matter_axe.get(), 1548288);
        addEmc(ModItems.red_matter_shovel.get(), 614400);
        addEmc(ModItems.red_matter_hoe.get(), 1081344);
        addEmc(ModItems.red_matter_sword.get(), 1081344);
        addEmc(ModItems.red_matter_shears.get(), 933888);
        addEmc(ModItems.red_matter_helmet.get(), 2334720);
        addEmc(ModItems.red_matter_chestplate.get(), 3735552);
        addEmc(ModItems.red_matter_leggings.get(), 3268608);
        addEmc(ModItems.red_matter_boots.get(), 1867776);
        addEmc(ModBlocks.dark_matter_block.get().asItem(), 663552);
        addEmc(ModBlocks.cable_block.get().asItem(), 216);
        addEmc(ModItems.neosphore_ingot.get(), 114688);
        addEmc(ModItems.neosphore_nugget.get(), 12743);
        addEmc(ModBlocks.neosphore_block.get().asItem(), 917504);
        addEmc(ModBlocks.basphalt_cobblestone.get().asItem(), 1);
        addEmc(ModBlocks.basphalt_stone.get().asItem(), 1);
        addEmc(Items.SOUL_TORCH, 21);

        addEmc(ModItems.oak_bark.get(), 8);
        addEmc(ModItems.spruce_bark.get(), 8);
        addEmc(ModItems.birch_bark.get(), 8);
        addEmc(ModItems.jungle_bark.get(), 8);
        addEmc(ModItems.acacia_bark.get(), 8);
        addEmc(ModItems.dark_oak_bark.get(), 8);
        addEmc(ModItems.crimson_bark.get(), 8);
        addEmc(ModItems.warped_bark.get(), 8);
        addEmc(ModItems.cherry_bark.get(), 8);
        addEmc(ModItems.mangrove_bark.get(), 8);

        //addEmc(ModBlocks.charmel_leaves.get().asItem(), 1);
        addEmc(ModBlocks.basphalt_stone.get().asItem(), 1);
        addEmc(ModBlocks.basphalt_cobblestone.get().asItem(), 1);
        addEmc(ModBlocks.asphalt.get().asItem(), 40);
        addEmc(ModBlocks.asphalt_bricks.get().asItem(), 518);
        //addEmc(ModBlocks.charmel_sapling.get().asItem(), 32);
        addEmc(ModItems.copper_dust.get(), 64);
        addEmc(ModItems.tin_dust.get(), 128);
        addEmcTags(ModTags.Items.forgeTinIngot, 128);
        addEmc(ModItems.netherite_dust.get(), 57344);
        addEmc(ModItems.iron_dust.get(), 256);
        addEmc(ModItems.gold_dust.get(), 2048);
        addEmcTags(ModTags.Items.forgeLeadIngot, 256);
        addEmc(ModItems.lead_dust.get(), 256);
        addEmcTags(ModTags.Items.forgeSilverIngot, 512);
        addEmc(ModItems.silver_dust.get(), 512);
        addEmcTags(ModTags.Items.forgeNickelIngot, 256);
        addEmc(ModItems.nickel_dust.get(), 256);
        addEmcTags(ModTags.Items.forgeUraniumIngot, 512);
        addEmc(ModItems.uranium_dust.get(), 512);
        addEmcTags(ModTags.Items.forgeOsmiumIngot, 512);
        addEmc(ModItems.osmium_dust.get(), 512);
        addEmcTags(ModTags.Items.forgeZincIngot, 256);
        addEmc(ModItems.zinc_dust.get(), 256);
        addEmc(ModBlocks.alchemical_condenser.get().asItem(), 2162688);
        addEmc(Items.CAMPFIRE, 140);
        addEmc(Items.FURNACE, 8);
        addEmc(Items.CRAFTING_TABLE, 32);
        addEmc("industrialforegoing:pink_slime", 32);
        addEmc("create:andesite_casing", 76);
        addEmc("create:cogwheel", 19);
        addEmc("create:large_cogwheel", 27);

        addEmcTags(ModTags.Items.glass, 2);
        addEmcTags(ModTags.Items.bricks, 16);
        addEmcTags(ModTags.Items.leather, 64);
        addEmcTags(ItemTags.BUTTONS, 1);
        addEmcTags(ItemTags.FENCES, 12);
        addEmcTags(ItemTags.FISHES, 64);
        addEmcTags(ItemTags.FLOWERS, 16);
        addEmcTags(ItemTags.LEAVES, 1);
        addEmcTags(ItemTags.LOGS, 32);
        addEmcTags(ItemTags.DIRT, 1);
        addEmcTags(ItemTags.MUSIC_DISCS, 2048);
        addEmcTags(ItemTags.PLANKS, 8);
        addEmcTags(ItemTags.SAND, 1);
        addEmcTags(ItemTags.SAPLINGS, 32);
        addEmcTags(ItemTags.TRAPDOORS, 24);
        addEmcTags(ItemTags.WOODEN_PRESSURE_PLATES, 16);
        addEmcTags(ItemTags.WOODEN_SLABS, 8);
        addEmcTags(ItemTags.WOODEN_STAIRS, 12);
        addEmcTags(ItemTags.WOOL, 48);;
        addEmcTags(ModTags.Items.bark, 8);
        addEmcTags(ModTags.Items.dyes, 16);
        addEmcTags(ModTags.Items.chests, 64);
    }
}