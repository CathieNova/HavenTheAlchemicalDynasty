package net.cathienova.havenalchemy.events;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit
{
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, HavenAlchemy.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, HavenAlchemy.MOD_ID);

    public static final FluidRegistryContainer acid_fluid = new FluidRegistryContainer("acid_fluid", FluidType.Properties.create()
            .canDrown(true)
            .canConvertToSource(false)
            .canPushEntity(true)
            .supportsBoating(true)
            .canExtinguish(true),
            () -> FluidRegistryContainer.createExtension(new FluidRegistryContainer.ClientExtensions(HavenAlchemy.MOD_ID, "acid_fluid")
            .tint(0x1A21961a)
            .fogColor(0.15f, 0.5f, 0.05f)),
            BlockBehaviour.Properties.of(),
            new Item.Properties()
                    .stacksTo(1));

    /*public static final FluidRegistryContainer havenite_fluid = new FluidRegistryContainer("havenite_fluid", FluidType.Properties.create()
            .canDrown(true)
            .canConvertToSource(false)
            .canPushEntity(true)
            .supportsBoating(true)
            .canExtinguish(true),
            () -> FluidRegistryContainer.createExtension(new FluidRegistryContainer.ClientExtensions(HavenAlchemy.MOD_ID, "havenite_fluid")
            .tint(0x1Aff007f)
            .fogColor(1f, 0f, 0.498f)),
            BlockBehaviour.Properties.of(),
            new Item.Properties()
                    .stacksTo(1));*/
}
