package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.item.ModArmorMaterials;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HavenAlchemy.MOD_ID, existingFileHelper);
    }
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.alchemy_stone);
        simpleItem(ModItems.alchemy_stone_fractured);
        simpleItem(ModItems.alchemy_dust);
        simpleItem(ModItems.alchemy_dust_low);
        simpleItem(ModItems.alchemy_dust_medium);
        simpleItem(ModItems.alchemy_dust_high);
        simpleItem(ModItems.alchemical_coal);
        simpleItem(ModItems.aether_fuel);
        simpleItem(ModItems.ethern_coal);
        simpleItem(ModItems.essence_shard);
        simpleItem(ModItems.dark_matter);
        simpleItem(ModItems.red_matter);
        simpleItem(ModItems.void_matter);
        simpleItem(ModItems.essence_apple);
        simpleItem(ModItems.dark_matter_apple);
        simpleItem(ModItems.red_matter_apple);
        handHeldItem(ModItems.trowel);
        simpleItem(ModItems.experience_orb);

        trimmedArmorItem(ModItems.dark_matter_helmet);
        trimmedArmorItem(ModItems.dark_matter_chestplate);
        trimmedArmorItem(ModItems.dark_matter_leggings);
        trimmedArmorItem(ModItems.dark_matter_boots);
        trimmedArmorItem(ModItems.red_matter_helmet);
        trimmedArmorItem(ModItems.red_matter_chestplate);
        trimmedArmorItem(ModItems.red_matter_leggings);
        trimmedArmorItem(ModItems.red_matter_boots);
        trimmedArmorItem(ModItems.neosphore_helmet);
        trimmedArmorItem(ModItems.neosphore_chestplate);
        trimmedArmorItem(ModItems.neosphore_leggings);
        trimmedArmorItem(ModItems.neosphore_boots);

        handHeldItem(ModItems.dark_matter_hoe);
        handHeldItem(ModItems.dark_matter_pickaxe);
        handHeldItem(ModItems.dark_matter_axe);
        handHeldItem(ModItems.dark_matter_shovel);
        handHeldItem(ModItems.dark_matter_sword);
        handHeldItem(ModItems.dark_matter_shears);
        handHeldItem(ModItems.red_matter_hoe);
        handHeldItem(ModItems.red_matter_pickaxe);
        handHeldItem(ModItems.red_matter_axe);
        handHeldItem(ModItems.red_matter_shovel);
        handHeldItem(ModItems.red_matter_sword);
        handHeldItem(ModItems.red_matter_shears);

        simpleItem(ModItems.neosphore_ingot);
        simpleItem(ModItems.neosphore_nugget);
        simpleItem(ModItems.raw_neosphore);
        handHeldItem(ModItems.neosphore_axe);
        handHeldItem(ModItems.neosphore_pickaxe);
        handHeldItem(ModItems.neosphore_sword);
        handHeldItem(ModItems.neosphore_hoe);
        handHeldItem(ModItems.neosphore_shovel);
        ShearsItem(ModItems.neosphore_shears);

        simpleItem(ModItems.copper_dust);
        simpleItem(ModItems.tin_dust);
        simpleItem(ModItems.tin_ingot);
        simpleItem(ModItems.netherite_dust);
        simpleItem(ModItems.iron_dust);
        simpleItem(ModItems.gold_dust);
        simpleItem(ModItems.lead_dust);
        simpleItem(ModItems.lead_ingot);
        simpleItem(ModItems.silver_dust);
        simpleItem(ModItems.silver_ingot);
        simpleItem(ModItems.nickel_dust);
        simpleItem(ModItems.nickel_ingot);
        simpleItem(ModItems.uranium_dust);
        simpleItem(ModItems.uranium_ingot);
        simpleItem(ModItems.osmium_dust);
        simpleItem(ModItems.osmium_ingot);
        simpleItem(ModItems.zinc_dust);
        simpleItem(ModItems.zinc_ingot);

        handHeldItem(ModItems.copper_ore_hammer);
        handHeldItem(ModItems.iron_ore_hammer);
        handHeldItem(ModItems.gold_ore_hammer);
        handHeldItem(ModItems.diamond_ore_hammer);
        handHeldItem(ModItems.neosphore_ore_hammer);

        simpleItem(ModItems.essentia_seeds);
        simpleItem(ModItems.coal_seeds);
        simpleItem(ModItems.coral_seeds);
        simpleItem(ModItems.deepslate_seeds);
        simpleItem(ModItems.diamond_seeds);
        simpleItem(ModItems.dirt_seeds);
        simpleItem(ModItems.dye_seeds);
        simpleItem(ModItems.emerald_seeds);
        simpleItem(ModItems.end_seeds);
        simpleItem(ModItems.experience_seeds);
        simpleItem(ModItems.fire_seeds);
        simpleItem(ModItems.glowstone_seeds);
        simpleItem(ModItems.gold_seeds);
        simpleItem(ModItems.ice_seeds);
        simpleItem(ModItems.iron_seeds);
        simpleItem(ModItems.lapis_seeds);
        simpleItem(ModItems.nature_seeds);
        simpleItem(ModItems.nether_seeds);
        simpleItem(ModItems.nether_quartz_seeds);
        simpleItem(ModItems.obsidian_seeds);
        simpleItem(ModItems.redstone_seeds);
        simpleItem(ModItems.stone_seeds);
        simpleItem(ModItems.water_seeds);
        simpleItem(ModItems.wood_seeds);
        simpleItem(ModItems.amethyst_seeds);
        simpleItem(ModItems.copper_seeds);
        simpleItem(ModItems.honey_seeds);
        simpleItem(ModItems.prismarine_seeds);
        simpleItem(ModItems.netherite_seeds);
        simpleItem(ModItems.air_seeds);
        simpleItem(ModItems.earth_seeds);

        simpleItem(ModItems.essentia_spirit);
        simpleItem(ModItems.mysterium_spirit);
        simpleItem(ModItems.vitalium_spirit);
        simpleItem(ModItems.celestium_spirit);
        simpleItem(ModItems.eternium_spirit);
        simpleItem(ModItems.coal_spirit);
        simpleItem(ModItems.coral_spirit);
        simpleItem(ModItems.deepslate_spirit);
        simpleItem(ModItems.diamond_spirit);
        simpleItem(ModItems.dirt_spirit);
        simpleItem(ModItems.dye_spirit);
        simpleItem(ModItems.emerald_spirit);
        simpleItem(ModItems.end_spirit);
        simpleItem(ModItems.experience_spirit);
        simpleItem(ModItems.fire_spirit);
        simpleItem(ModItems.glowstone_spirit);
        simpleItem(ModItems.gold_spirit);
        simpleItem(ModItems.ice_spirit);
        simpleItem(ModItems.iron_spirit);
        simpleItem(ModItems.lapis_spirit);
        simpleItem(ModItems.nature_spirit);
        simpleItem(ModItems.nether_spirit);
        simpleItem(ModItems.nether_quartz_spirit);
        simpleItem(ModItems.obsidian_spirit);
        simpleItem(ModItems.redstone_spirit);
        simpleItem(ModItems.stone_spirit);
        simpleItem(ModItems.water_spirit);
        simpleItem(ModItems.wood_spirit);
        simpleItem(ModItems.amethyst_spirit);
        simpleItem(ModItems.copper_spirit);
        simpleItem(ModItems.honey_spirit);
        simpleItem(ModItems.prismarine_spirit);
        simpleItem(ModItems.netherite_spirit);
        simpleItem(ModItems.air_spirit);
        simpleItem(ModItems.earth_spirit);

        evenSimplerBlockItem(ModBlocks.basphalt_stone_fence_gate);
        evenSimplerBlockItem(ModBlocks.basphalt_cobblestone_fence_gate);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_bricks_fence_gate);
        evenSimplerBlockItem(ModBlocks.charmel_fence_gate);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_slabs);
        evenSimplerBlockItem(ModBlocks.basphalt_cobblestone_slabs);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_bricks_slabs);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_stairs);
        evenSimplerBlockItem(ModBlocks.basphalt_cobblestone_stairs);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_bricks_stairs);
        wallItem(ModBlocks.basphalt_stone_wall, ModBlocks.basphalt_stone);
        wallItem(ModBlocks.basphalt_cobblestone_wall, ModBlocks.basphalt_cobblestone);
        wallItem(ModBlocks.basphalt_stone_bricks_wall, ModBlocks.basphalt_stone_bricks);
        fenceItem(ModBlocks.basphalt_stone_fence, ModBlocks.basphalt_stone);
        fenceItem(ModBlocks.basphalt_cobblestone_fence, ModBlocks.basphalt_cobblestone);
        fenceItem(ModBlocks.basphalt_stone_bricks_fence, ModBlocks.basphalt_stone_bricks);

        buttonItem(ModBlocks.basphalt_stone_button, ModBlocks.basphalt_stone);
        buttonItem(ModBlocks.basphalt_cobblestone_button, ModBlocks.basphalt_cobblestone);
        buttonItem(ModBlocks.basphalt_stone_bricks_button, ModBlocks.basphalt_stone_bricks);
        buttonItem(ModBlocks.charmel_button, ModBlocks.charmel_planks);
        fenceItem(ModBlocks.charmel_fence, ModBlocks.charmel_planks);

        evenSimplerBlockItem(ModBlocks.charmel_pressure_plate);

        evenSimplerBlockItem(ModBlocks.basphalt_stone_fence_gate);
        evenSimplerBlockItem(ModBlocks.basphalt_cobblestone_fence_gate);
        evenSimplerBlockItem(ModBlocks.basphalt_stone_bricks_fence_gate);
        evenSimplerBlockItem(ModBlocks.charmel_fence_gate);

        evenSimplerBlockItem(ModBlocks.charmel_slab);
        evenSimplerBlockItem(ModBlocks.charmel_stairs);
        saplingItem(ModBlocks.charmel_sapling);
        simpleItem(ModItems.neosphore_smithing_template);

        //Artifacts
        simpleItem(ModItems.magnet);
        simpleItem(ModItems.mending_necklace);
        simpleItem(ModItems.boots_of_meow);
        simpleItem(ModItems.gloves_of_rawr);
        simpleItem(ModItems.ring_of_haste);
        simpleItem(ModItems.ring_of_love);
        simpleItem(ModItems.regeneration_pendant);
        simpleItem(ModItems.fire_pendant);
        simpleItem(ModItems.water_pendant);
        simpleItem(ModItems.nightvision_goggles);

        simpleItem(ModItems.oak_bark);
        simpleItem(ModItems.spruce_bark);
        simpleItem(ModItems.birch_bark);
        simpleItem(ModItems.jungle_bark);
        simpleItem(ModItems.acacia_bark);
        simpleItem(ModItems.dark_oak_bark);
        simpleItem(ModItems.crimson_bark);
        simpleItem(ModItems.warped_bark);
        simpleItem(ModItems.cherry_bark);
        simpleItem(ModItems.mangrove_bark);
        simpleItem(ModItems.charmel_bark);

        withExistingParent(ModBlocks.generator_block.getId().getPath(), modLoc("block/generator_block_off"));
        withExistingParent(ModBlocks.charger_block.getId().getPath(), modLoc("block/charger_block_on"));
        withExistingParent(ModBlocks.cable_block.getId().getPath(), modLoc("block/cable"));
        withExistingParent(ModBlocks.facade_block.getId().getPath(), modLoc("block/facade"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HavenAlchemy.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(HavenAlchemy.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block)
    {
        this.withExistingParent(HavenAlchemy.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(HavenAlchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(HavenAlchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(HavenAlchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder ShearsItem(RegistryObject<ShearsItem> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HavenAlchemy.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(HavenAlchemy.MOD_ID,"block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject)
    {
        final String MOD_ID = HavenAlchemy.MOD_ID; // Change this to your mod id

        if (itemRegistryObject.get() instanceof ArmorItem armorItem)
        {
            trimMaterials.entrySet().forEach(entry ->
            {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot())
                        {
                            case HEAD -> "helmet";
                            case CHEST -> "chestplate";
                            case LEGS -> "leggings";
                            case FEET -> "boots";
                            default -> "";
                        };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}