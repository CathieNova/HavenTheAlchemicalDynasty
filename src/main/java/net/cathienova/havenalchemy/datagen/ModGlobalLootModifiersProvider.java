package net.cathienova.havenalchemy.datagen;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.ModItems;
import net.cathienova.havenalchemy.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider
{
    public ModGlobalLootModifiersProvider(PackOutput output)
    {
        super(output, HavenAlchemy.MOD_ID);
    }

    @Override
    protected void start()
    {
        add("smithing_template_from_ancient_chest", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/ancient_city"))
                        .build()}, ModItems.neosphore_smithing_template.get(), 0.2f));

        add("boots_of_meow_from_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/simple_dungeon"))
                        .build()}, ModItems.boots_of_meow.get(), 0.2f));

        add("mending_necklace_from_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/simple_dungeon"))
                        .build()}, ModItems.mending_necklace.get(), 0.2f));

        add("mending_necklace_from_buried_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/buried_treasure"))
                        .build()}, ModItems.mending_necklace.get(), 0.2f));

        add("boots_of_meow_from_buried_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/buried_treasure"))
                        .build()}, ModItems.boots_of_meow.get(), 0.2f));

        add("gloves_of_rawr_from_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/simple_dungeon"))
                        .build()}, ModItems.gloves_of_rawr.get(), 0.2f));

        add("gloves_of_rawr_from_buried_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/buried_treasure"))
                        .build()}, ModItems.gloves_of_rawr.get(), 0.2f));

        add("fire_pendant_from_bastion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_bridge"))
                        .build()}, ModItems.fire_pendant.get(), 0.2f));

        add("water_pendant_from_underwater_ruin_big", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/underwater_ruin_big"))
                        .build()}, ModItems.water_pendant.get(), 0.2f));

        add("magnet_from_jungle_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/jungle_temple"))
                        .build()}, ModItems.magnet.get(), 0.2f));

        add("nightvision_goggles_from_abandoned_mineshaft", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/abandoned_mineshaft"))
                        .build()}, ModItems.nightvision_goggles.get(), 0.2f));

        add("nightvision_goggles_from_pillage_outpost", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/pillager_outpost"))
                        .build()}, ModItems.nightvision_goggles.get(), 0.2f));

        add("regeneration_pendant_from_end_city", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/end_city_treasure"))
                        .build()}, ModItems.regeneration_pendant.get(), 0.2f));

        add("regeneration_pendant_from_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/woodland_mansion"))
                        .build()}, ModItems.regeneration_pendant.get(), 0.2f));

        add("regeneration_pendant_from_bastion_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/bastion_treasure"))
                        .build()}, ModItems.regeneration_pendant.get(), 0.2f));

        add("ring_of_haste_from_village", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_weaponsmith"))
                        .build()}, ModItems.ring_of_haste.get(), 0.2f));

        add("ring_of_haste_from_village_toolsmith", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_toolsmith"))
                        .build()}, ModItems.ring_of_haste.get(), 0.2f));

        add("ring_of_haste_from_village_mason", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_mason"))
                        .build()}, ModItems.ring_of_haste.get(), 0.2f));

        add("ring_of_love_from_village_shepherd", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_shepherd"))
                        .build()}, ModItems.ring_of_love.get(), 0.2f));

        add("ring_of_love_from_village_fisher", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft", "chests/village/village_fisher"))
                        .build()}, ModItems.ring_of_love.get(), 0.2f));
    }
}
