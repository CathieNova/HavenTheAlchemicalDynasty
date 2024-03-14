package net.cathienova.havenalchemy.recipe;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HavenAlchemy.MOD_ID);

    public static final RegistryObject<RecipeSerializer<AlchemicalChamberRecipe>> ALCHEMICAL_CHAMBER_SERIALIZER =
            SERIALIZERS.register("alchemical_chamber", () -> AlchemicalChamberRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
