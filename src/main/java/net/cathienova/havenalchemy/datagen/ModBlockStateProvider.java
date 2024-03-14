package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.horticulture.BaseCrop;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HavenAlchemy.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(ModBlocks.alchemical_coal_block);
        blockWithItem(ModBlocks.ethern_coal_block);
        blockWithItem(ModBlocks.aether_fuel_block);
        blockWithItem(ModBlocks.asphalt);
        blockWithItem(ModBlocks.asphalt_bricks);
        blockWithItem(ModBlocks.dark_matter_block);
        blockWithItem(ModBlocks.red_matter_block);
        blockWithItem(ModBlocks.essentia_spirit_block);
        simpleBlockWithItem(ModBlocks.alchemical_chamber.get(), new ModelFile.UncheckedModelFile(modLoc("block/alchemical_chamber")));

        CropDrops((CropBlock) ModBlocks.essentia_crop.get(), "essentia_crop", "essentia_crop");
        CropDrops((CropBlock) ModBlocks.coal_crop.get(), "coal_crop", "coal_crop");
        CropDrops((CropBlock) ModBlocks.coral_crop.get(), "coral_crop", "coral_crop");
        CropDrops((CropBlock) ModBlocks.deepslate_crop.get(), "deepslate_crop", "deepslate_crop");
        CropDrops((CropBlock) ModBlocks.diamond_crop.get(), "diamond_crop", "diamond_crop");
        CropDrops((CropBlock) ModBlocks.dirt_crop.get(), "dirt_crop", "dirt_crop");
        CropDrops((CropBlock) ModBlocks.dye_crop.get(), "dye_crop", "dye_crop");
        CropDrops((CropBlock) ModBlocks.emerald_crop.get(), "emerald_crop", "emerald_crop");
        CropDrops((CropBlock) ModBlocks.end_crop.get(), "end_crop", "end_crop");
        CropDrops((CropBlock) ModBlocks.experience_crop.get(), "experience_crop", "experience_crop");
        CropDrops((CropBlock) ModBlocks.fire_crop.get(), "fire_crop", "fire_crop");
        CropDrops((CropBlock) ModBlocks.glowstone_crop.get(), "glowstone_crop", "glowstone_crop");
        CropDrops((CropBlock) ModBlocks.gold_crop.get(), "gold_crop", "gold_crop");
        CropDrops((CropBlock) ModBlocks.ice_crop.get(), "ice_crop", "ice_crop");
        CropDrops((CropBlock) ModBlocks.iron_crop.get(), "iron_crop", "iron_crop");
        CropDrops((CropBlock) ModBlocks.lapis_crop.get(), "lapis_crop", "lapis_crop");
        CropDrops((CropBlock) ModBlocks.nature_crop.get(), "nature_crop", "nature_crop");
        CropDrops((CropBlock) ModBlocks.nether_crop.get(), "nether_crop", "nether_crop");
        CropDrops((CropBlock) ModBlocks.nether_quartz_crop.get(), "nether_quartz_crop", "nether_quartz_crop");
        CropDrops((CropBlock) ModBlocks.obsidian_crop.get(), "obsidian_crop", "obsidian_crop");
        CropDrops((CropBlock) ModBlocks.redstone_crop.get(), "redstone_crop", "redstone_crop");
        CropDrops((CropBlock) ModBlocks.stone_crop.get(), "stone_crop", "stone_crop");
        CropDrops((CropBlock) ModBlocks.water_crop.get(), "water_crop", "water_crop");
        CropDrops((CropBlock) ModBlocks.amethyst_crop.get(), "amethyst_crop", "amethyst_crop");
        CropDrops((CropBlock) ModBlocks.copper_crop.get(), "copper_crop", "copper_crop");
        CropDrops((CropBlock) ModBlocks.honey_crop.get(), "honey_crop", "honey_crop");
        CropDrops((CropBlock) ModBlocks.prismarine_crop.get(), "prismarine_crop", "prismarine_crop");
        CropDrops((CropBlock) ModBlocks.netherite_crop.get(), "netherite_crop", "netherite_crop");
        CropDrops((CropBlock) ModBlocks.air_crop.get(), "air_crop", "air_crop");
        CropDrops((CropBlock) ModBlocks.earth_crop.get(), "earth_crop", "earth_crop");

        blockWithItem(ModBlocks.neosphore_block);
        blockWithItem(ModBlocks.neosphore_ore);
        blockWithItem(ModBlocks.raw_neosphore_block);

        logBlock(((RotatedPillarBlock) ModBlocks.charmel_log.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.charmel_wood.get()), blockTexture(ModBlocks.charmel_log.get()), blockTexture(ModBlocks.charmel_log.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.stripped_charmel_log.get()), blockTexture(ModBlocks.stripped_charmel_log.get()),
                new ResourceLocation(HavenAlchemy.MOD_ID, "block/stripped_charmel_log_top"));

        axisBlock(((RotatedPillarBlock) ModBlocks.stripped_charmel_wood.get()), blockTexture(ModBlocks.stripped_charmel_log.get()),
                blockTexture(ModBlocks.stripped_charmel_log.get()));

        blockItem(ModBlocks.charmel_log);
        blockItem(ModBlocks.charmel_wood);
        blockItem(ModBlocks.stripped_charmel_log);
        blockItem(ModBlocks.stripped_charmel_wood);

        blockWithItem(ModBlocks.charmel_planks);

        leavesBlock(ModBlocks.charmel_leaves);
        saplingBlock(ModBlocks.charmel_sapling);

        blockWithItem(ModBlocks.catacombs_portal);

        blockWithItem(ModBlocks.basphalt_cobblestone);
        blockWithItem(ModBlocks.basphalt_stone);
        blockWithItem(ModBlocks.basphalt_stone_bricks);
        stairsBlock(((StairBlock) ModBlocks.basphalt_stone_stairs.get()), blockTexture(ModBlocks.basphalt_stone.get()));
        stairsBlock(((StairBlock) ModBlocks.basphalt_cobblestone_stairs.get()), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        stairsBlock(((StairBlock) ModBlocks.basphalt_stone_bricks_stairs.get()), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
        stairsBlock(((StairBlock) ModBlocks.charmel_stairs.get()), blockTexture(ModBlocks.charmel_planks.get()));
        slabBlock(((SlabBlock) ModBlocks.basphalt_stone_slabs.get()), blockTexture(ModBlocks.basphalt_stone.get()), blockTexture(ModBlocks.basphalt_stone.get()));
        slabBlock(((SlabBlock) ModBlocks.basphalt_cobblestone_slabs.get()), blockTexture(ModBlocks.basphalt_cobblestone.get()), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        slabBlock(((SlabBlock) ModBlocks.basphalt_stone_bricks_slabs.get()), blockTexture(ModBlocks.basphalt_stone_bricks.get()), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
        slabBlock(((SlabBlock) ModBlocks.charmel_slab.get()), blockTexture(ModBlocks.charmel_planks.get()), blockTexture(ModBlocks.charmel_planks.get()));
        buttonBlock((ButtonBlock) ModBlocks.basphalt_stone_button.get(), blockTexture(ModBlocks.basphalt_stone.get()));
        buttonBlock((ButtonBlock) ModBlocks.basphalt_cobblestone_button.get(), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        buttonBlock((ButtonBlock) ModBlocks.basphalt_stone_bricks_button.get(), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
        buttonBlock((ButtonBlock) ModBlocks.charmel_button.get(), blockTexture(ModBlocks.charmel_planks.get()));

        pressurePlateBlock((PressurePlateBlock) ModBlocks.charmel_pressure_plate.get(), blockTexture(ModBlocks.charmel_planks.get()));
        fenceBlock((FenceBlock) ModBlocks.basphalt_stone_fence.get(), blockTexture(ModBlocks.basphalt_stone.get()));
        fenceBlock((FenceBlock) ModBlocks.basphalt_cobblestone_fence.get(), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        fenceBlock((FenceBlock) ModBlocks.basphalt_stone_bricks_fence.get(), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
        fenceBlock((FenceBlock) ModBlocks.charmel_fence.get(), blockTexture(ModBlocks.charmel_planks.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.basphalt_stone_fence_gate.get(), blockTexture(ModBlocks.basphalt_stone.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.basphalt_cobblestone_fence_gate.get(), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.basphalt_stone_bricks_fence_gate.get(), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.charmel_fence_gate.get(), blockTexture(ModBlocks.charmel_planks.get()));
        wallBlock((WallBlock) ModBlocks.basphalt_stone_wall.get(), blockTexture(ModBlocks.basphalt_stone.get()));
        wallBlock((WallBlock) ModBlocks.basphalt_cobblestone_wall.get(), blockTexture(ModBlocks.basphalt_cobblestone.get()));
        wallBlock((WallBlock) ModBlocks.basphalt_stone_bricks_wall.get(), blockTexture(ModBlocks.basphalt_stone_bricks.get()));
    }

    public void CropDrops(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> cropStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] cropStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];

        if (state.getValue(((BaseCrop) block).getAgeProperty()) >= 0 && state.getValue(((BaseCrop) block).getAgeProperty()) < 7)
        {
            models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BaseCrop) block).getAgeProperty()),
                    new ResourceLocation(HavenAlchemy.MOD_ID, "block/crop_stage_" + state.getValue(((BaseCrop) block).getAgeProperty()))).renderType("cutout"));
        }
        else if (state.getValue(((BaseCrop) block).getAgeProperty()) == 7)
        {
            models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BaseCrop) block).getAgeProperty()),
                    new ResourceLocation(HavenAlchemy.MOD_ID, "block/" + textureName + "_stage_7")).renderType("cutout"));
        }
        else
        {
            models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BaseCrop) block).getAgeProperty()),
                    new ResourceLocation(HavenAlchemy.MOD_ID, "block/" + textureName + "_stage_0")).renderType("cutout"));
        }
        return models;
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(HavenAlchemy.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
