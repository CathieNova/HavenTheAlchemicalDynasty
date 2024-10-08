package net.cathienova.havenalchemy.block;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.block.chests.*;
import net.cathienova.havenalchemy.block.custom.*;
import net.cathienova.havenalchemy.block.farmland.*;
import net.cathienova.havenalchemy.block.horticulture.*;
import net.cathienova.havenalchemy.cables.blocks.CableBlock;
import net.cathienova.havenalchemy.cables.blocks.FacadeBlock;
import net.cathienova.havenalchemy.cables.blocks.FacadeBlockItem;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.item.fuel.FuelBlockItem;
import net.cathienova.havenalchemy.worldgen.tree.CharmelTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HavenAlchemy.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> ENTITY = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, HavenAlchemy.MOD_ID);

    public static final RegistryObject<Block> dust = registerBlock("dust",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SAND)));

    public static final RegistryObject<Block> crushed_netherrack = registerBlock("crushed_netherrack",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));

    public static final RegistryObject<Block> crushed_end_stone = registerBlock("crushed_end_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRAVEL)));

    public static final RegistryObject<Block> alchemical_coal_block = registerFuelBlockBlock("alchemical_coal_block",
            () -> new FuelBlock(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ethern_coal_block = registerFuelBlockBlock("ethern_coal_block",
            () -> new FuelBlock(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> aether_fuel_block = registerFuelBlockBlock("aether_fuel_block",
            () -> new FuelBlock(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Item> alchemical_coal_block_item = ModItems.ITEMS.register("alchemical_coal_block",
            () -> new FuelBlockItem(ModBlocks.alchemical_coal_block.get(),
                    new Item.Properties(), 28800));

    public static final RegistryObject<Item> ethern_coal_block_item = ModItems.ITEMS.register("ethern_coal_block",
            () -> new FuelBlockItem(ethern_coal_block.get(),
                    new Item.Properties(), 57600));

    public static final RegistryObject<Item> aether_fuel_block_item = ModItems.ITEMS.register("aether_fuel_block",
            () -> new FuelBlockItem(aether_fuel_block.get(),
                    new Item.Properties(), 115200));

    public static final RegistryObject<Block> asphalt = registerBlock("asphalt",
            () -> new Asphalt(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> asphalt_bricks = registerBlock("asphalt_bricks",
            () -> new AsphaltBricks(BlockBehaviour.Properties.copy(Blocks.BLACK_CONCRETE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> dark_matter_block = registerBlock("dark_matter_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> red_matter_block = registerBlock("red_matter_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> essentia_spirit_block = registerBlock("essentia_spirit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> mysterium_spirit_block = registerBlock("mysterium_spirit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> vitalium_spirit_block = registerBlock("vitalium_spirit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> celestium_spirit_block = registerBlock("celestium_spirit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> eternium_spirit_block = registerBlock("eternium_spirit_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> essentia_farmland = registerBlock("essentia_farmland",
            () -> new EssentiaFarmland(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> mysterium_farmland = registerBlock("mysterium_farmland",
            () -> new MysteriumFarmland(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> vitalium_farmland = registerBlock("vitalium_farmland",
            () -> new VitaliumFarmland(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> celestium_farmland = registerBlock("celestium_farmland",
            () -> new CelestiumFarmland(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> eternium_farmland = registerBlock("eternium_farmland",
            () -> new EterniumFarmland(BlockBehaviour.Properties.copy(Blocks.FARMLAND)));

    public static final RegistryObject<Block> havenite_crop = registerBlock("havenite_crop",
            () -> new HaveniteCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> essentia_crop = registerBlock("essentia_crop",
            () -> new EssentiaCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> stone_crop = registerBlock("stone_crop",
            () -> new StoneCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> coal_crop = registerBlock("coal_crop",
            () -> new CoalCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> coral_crop = registerBlock("coral_crop",
            () -> new CoralCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> deepslate_crop = registerBlock("deepslate_crop",
            () -> new DeepslateCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> diamond_crop = registerBlock("diamond_crop",
            () -> new DiamondCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> dirt_crop = registerBlock("dirt_crop",
            () -> new DirtCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> dye_crop = registerBlock("dye_crop",
            () -> new DyeCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> emerald_crop = registerBlock("emerald_crop",
            () -> new EmeraldCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> end_crop = registerBlock("end_crop",
            () -> new EndCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> experience_crop = registerBlock("experience_crop",
            () -> new ExperienceCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> fire_crop = registerBlock("fire_crop",
            () -> new FireCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> glowstone_crop = registerBlock("glowstone_crop",
            () -> new GlowstoneCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> gold_crop = registerBlock("gold_crop",
            () -> new GoldCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> ice_crop = registerBlock("ice_crop",
            () -> new IceCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> iron_crop = registerBlock("iron_crop",
            () -> new IronCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> lapis_crop = registerBlock("lapis_crop",
            () -> new LapisCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> nature_crop = registerBlock("nature_crop",
            () -> new NatureCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> nether_crop = registerBlock("nether_crop",
            () -> new NetherCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> nether_quartz_crop = registerBlock("nether_quartz_crop",
            () -> new NetherQuartzCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> obsidian_crop = registerBlock("obsidian_crop",
            () -> new ObsidianCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> redstone_crop = registerBlock("redstone_crop",
            () -> new RedstoneCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> water_crop = registerBlock("water_crop",
            () -> new WaterCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> wood_crop = registerBlock("wood_crop",
            () -> new WoodCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> amethyst_crop = registerBlock("amethyst_crop",
            () -> new AmethystCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> copper_crop = registerBlock("copper_crop",
            () -> new CopperCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> honey_crop = registerBlock("honey_crop",
            () -> new HoneyCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> prismarine_crop = registerBlock("prismarine_crop",
            () -> new PrismarineCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> netherite_crop = registerBlock("netherite_crop",
            () -> new NetheriteCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> air_crop = registerBlock("air_crop",
            () -> new AirCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> earth_crop = registerBlock("earth_crop",
            () -> new EarthCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    public static final RegistryObject<Block> neosphore_block = registerBlock("neosphore_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> raw_neosphore_block = registerBlock("raw_neosphore_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> neosphore_ore = registerBlock("neosphore_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> havenite_ore = registerBlock("havenite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> andesite_havenite_ore = registerBlock("andesite_havenite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> diorite_havenite_ore = registerBlock("diorite_havenite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIORITE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> granite_havenite_ore = registerBlock("granite_havenite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.GRANITE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> deepslate_havenite_ore = registerBlock("deepslate_havenite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> raw_havenite_block = registerBlock("raw_havenite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> havenite_block = registerBlock("havenite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final RegistryObject<Block> charmel_log = registerBlock("charmel_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> charmel_wood = registerBlock("charmel_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> stripped_charmel_wood = registerBlock("stripped_charmel_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> stripped_charmel_log = registerBlock("stripped_charmel_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> charmel_planks = registerBlock("charmel_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return 5;
                }
            });

    public static final RegistryObject<Block> charmel_slab = registerBlock("charmel_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> charmel_stairs = registerBlock("charmel_stairs",
            () -> new StairBlock(() -> ModBlocks.charmel_planks.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> charmel_button = registerBlock("charmel_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)
                    .sound(SoundType.WOOD), BlockSetType.OAK, 20,true));

    public static final RegistryObject<Block> charmel_pressure_plate = registerBlock("charmel_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)
                    .sound(SoundType.WOOD), BlockSetType.OAK));

    public static final RegistryObject<Block> charmel_fence = registerBlock("charmel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)
                    .sound(SoundType.WOOD)));

    public static final RegistryObject<Block> charmel_fence_gate = registerBlock("charmel_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)
                    .sound(SoundType.WOOD), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    public static final RegistryObject<Block> charmel_sapling = registerBlock("charmel_sapling",
            () -> new SaplingBlock(new CharmelTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


    public static final RegistryObject<Block> charmel_leaves = registerBlock("charmel_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.CHERRY_LEAVES))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
                {
                    return 5;
                }
            });

    // BASPHALT STONE AND MORE
    public static final RegistryObject<Block> basphalt_stone = registerBlock("basphalt_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .isValidSpawn((state, world, pos, type) -> false)
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_stairs = registerBlock("basphalt_stone_stairs",
            () -> new StairBlock(() -> ModBlocks.basphalt_stone.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_slabs = registerBlock("basphalt_stone_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_button = registerBlock("basphalt_stone_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), BlockSetType.STONE, 20,true));

    public static final RegistryObject<Block> basphalt_stone_fence = registerBlock("basphalt_stone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_fence_gate = registerBlock("basphalt_stone_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    public static final RegistryObject<Block> basphalt_stone_wall = registerBlock("basphalt_stone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));


    // BASPHALT COBBLESTONE AND MORE
    public static final RegistryObject<Block> basphalt_cobblestone = registerBlock("basphalt_cobblestone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_cobblestone_stairs = registerBlock("basphalt_cobblestone_stairs",
            () -> new StairBlock(() -> ModBlocks.basphalt_cobblestone.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_cobblestone_slabs = registerBlock("basphalt_cobblestone_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_cobblestone_fence = registerBlock("basphalt_cobblestone_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_cobblestone_fence_gate = registerBlock("basphalt_cobblestone_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> basphalt_cobblestone_wall = registerBlock("basphalt_cobblestone_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_cobblestone_button = registerBlock("basphalt_cobblestone_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), BlockSetType.STONE, 20,true));


    // BASPHALT STONE BRICKS AND MORE
    public static final RegistryObject<Block> basphalt_stone_bricks_stairs = registerBlock("basphalt_stone_bricks_stairs",
            () -> new StairBlock(() -> ModBlocks.basphalt_stone_bricks.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_slabs = registerBlock("basphalt_stone_bricks_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_button = registerBlock("basphalt_stone_bricks_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), BlockSetType.STONE, 20,true));

    public static final RegistryObject<Block> basphalt_stone_bricks_fence = registerBlock("basphalt_stone_bricks_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_fence_gate = registerBlock("basphalt_stone_bricks_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    public static final RegistryObject<Block> basphalt_stone_bricks_wall = registerBlock("basphalt_stone_bricks_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks = registerBlock("basphalt_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_1 = registerBlock("basphalt_stone_bricks_1",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_2 = registerBlock("basphalt_stone_bricks_2",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_3 = registerBlock("basphalt_stone_bricks_3",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_4 = registerBlock("basphalt_stone_bricks_4",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_5 = registerBlock("basphalt_stone_bricks_5",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_6 = registerBlock("basphalt_stone_bricks_6",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_7 = registerBlock("basphalt_stone_bricks_7",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_8 = registerBlock("basphalt_stone_bricks_8",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_9 = registerBlock("basphalt_stone_bricks_9",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_10 = registerBlock("basphalt_stone_bricks_10",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_11 = registerBlock("basphalt_stone_bricks_11",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_12 = registerBlock("basphalt_stone_bricks_12",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_13 = registerBlock("basphalt_stone_bricks_13",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_14 = registerBlock("basphalt_stone_bricks_14",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_15 = registerBlock("basphalt_stone_bricks_15",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_16 = registerBlock("basphalt_stone_bricks_16",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_17 = registerBlock("basphalt_stone_bricks_17",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_18 = registerBlock("basphalt_stone_bricks_18",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_19 = registerBlock("basphalt_stone_bricks_19",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_20 = registerBlock("basphalt_stone_bricks_20",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_21 = registerBlock("basphalt_stone_bricks_21",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_22 = registerBlock("basphalt_stone_bricks_22",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_23 = registerBlock("basphalt_stone_bricks_23",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_24 = registerBlock("basphalt_stone_bricks_24",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_25 = registerBlock("basphalt_stone_bricks_25",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_26 = registerBlock("basphalt_stone_bricks_26",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_27 = registerBlock("basphalt_stone_bricks_27",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> basphalt_stone_bricks_28 = registerBlock("basphalt_stone_bricks_28",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    /*public static final RegistryObject<Block> suspicious_basphalt = registerBlock("suspicious_basphalt",
            () -> new ModBrushableBlock(basphalt_stone.get(), BlockBehaviour.Properties.of().mapColor(MapColor.SAND)
                    .instrument(NoteBlockInstrument.SNARE).strength(0.25F).sound(SoundType.SUSPICIOUS_SAND)
                    .pushReaction(PushReaction.DESTROY), SoundEvents.BRUSH_SAND, SoundEvents.BRUSH_SAND_COMPLETED));*/

    /*public static final RegistryObject<BlockEntityType<ModBrushableBlockEntity>> BRUSHABLE_BLOCK = ENTITY.register("brushable_block", () ->
            BlockEntityType.Builder.of(ModBrushableBlockEntity::new,
                    suspicious_basphalt.get()
            ).build(null)
    );*/

    public static final RegistryObject<Block> alchemical_chamber = registerBlock("alchemical_chamber",
            () -> new AlchemicalChamberBlock(BlockBehaviour.Properties.copy(ModBlocks.basphalt_stone.get()).noOcclusion()));

    public static final RegistryObject<Block> alchemical_processor = registerBlock("alchemical_processor",
            () -> new AlchemicalProcessorBlock(BlockBehaviour.Properties.copy(ModBlocks.basphalt_stone.get()).noOcclusion()));

    public static final RegistryObject<Block> alchemical_condenser = registerBlock("alchemical_condenser",
            () -> new AlchemicalCondenserBlock(BlockBehaviour.Properties.copy(ModBlocks.basphalt_stone.get()).noOcclusion()));

    public static final RegistryObject<Block> alchemical_transmutation = registerBlock("alchemical_transmutation",
            () -> new AlchemicalTransmutationBlock(BlockBehaviour.Properties.copy(ModBlocks.basphalt_stone.get()).noOcclusion()));

    /*public static final RegistryObject<Block> dirt_chest = registerBlock("dirt_chest",
            () -> new DirtChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.DIRT));

    public static final RegistryObject<Block> stone_chest = registerBlock("stone_chest",
            () -> new StoneChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.STONE));

    public static final RegistryObject<Block> copper_chest = registerBlock("copper_chest",
            () -> new CopperChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.COPPER));

    public static final RegistryObject<Block> iron_chest = registerBlock("iron_chest",
            () -> new IronChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.IRON));

    public static final RegistryObject<Block> gold_chest = registerBlock("gold_chest",
            () -> new GoldChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.GOLD));

    public static final RegistryObject<Block> diamond_chest = registerBlock("diamond_chest",
            () -> new DiamondChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.DIAMOND));

    public static final RegistryObject<Block> obsidian_chest = registerBlock("obsidian_chest",
            () -> new ObsidianChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.OBSIDIAN));

    public static final RegistryObject<Block> emerald_chest = registerBlock("emerald_chest",
            () -> new EmeraldChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.EMERALD));

    public static final RegistryObject<Block> netherite_chest = registerBlock("netherite_chest",
            () -> new NetheriteChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.NETHERITE));*/

    public static final RegistryObject<Block> alchemical_chest = registerBlock("alchemical_chest",
            () -> new AlchemicalChestBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).noOcclusion(), ChestTypes.ALCHEMICAL));

    public static final RegistryObject<Block> generator_block = registerBlock("generator_block",
            GeneratorBlock::new);

    public static final RegistryObject<Block> charger_block = registerBlock("charger_block",
            ChargerBlock::new);

    public static final RegistryObject<Block> cable_block = registerBlock("cable",
            CableBlock::new);

    public static final RegistryObject<FacadeBlock> facade_block = registerFacade("facade",
            FacadeBlock::new);

    public static final RegistryObject<Block> speed_plate_i = registerBlock("speed_plate_i",
            () -> new SpeedPlate1(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).noOcclusion().noCollission().strength(0.25f)));

    public static final RegistryObject<Block> speed_plate_ii = registerBlock("speed_plate_ii",
            () -> new SpeedPlate2(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).noOcclusion().noCollission().strength(0.25f)));

    public static final RegistryObject<Block> speed_plate_iii = registerBlock("speed_plate_iii",
            () -> new SpeedPlate3(BlockBehaviour.Properties.copy(Blocks.BLACK_WOOL).noOcclusion().noCollission().strength(0.25f)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerFuelBlockBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerFacade(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new FacadeBlockItem(facade_block.get(), new Item.Properties()));
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ENTITY.register(eventBus);
    }
}
