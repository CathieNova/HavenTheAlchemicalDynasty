package net.cathienova.havenalchemy.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.cathienova.havenalchemy.item.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class WardenLootModifier extends LootModifier {

    public static final Codec<WardenLootModifier> CODEC = RecordCodecBuilder.create(inst -> codecStart(inst)
            .apply(inst, WardenLootModifier::new));

    protected WardenLootModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        int x = 0;
        while (x < 12)
        {
            if (context.getRandom().nextFloat() > 0.5F)
            {
                generatedLoot.add(new ItemStack(ModItems.sculk_soul.get(), 1));
            }
            x += 1;
        }
        generatedLoot.add(new ItemStack(ModItems.sculk_soul.get(), 4));
        generatedLoot.add(new ItemStack(ModItems.warden_heart.get(), 1));
        return generatedLoot;
    }

    @Override
    public Codec<WardenLootModifier> codec() {
        return CODEC;
    }
}