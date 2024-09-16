package net.cathienova.havenalchemy.capabilities;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IEmcHandler {

    void storeEMC(long emc);
    void takeEMC(long emc);

    long getEMC();

    /**
     * Destroys the current item stack and adds the total EMC to the player's EMC. If the player doesn't already know the item, it will be learned
     * @param itemStack
     */
    void deconstructItem(ItemStack itemStack);

    /**
     * Uses the EMC from the player to create the amount of items requested
     * @param item The item to create
     * @param qty The amount of items to create
     * @return The item stack containing either the requested amount or the max amount that the player can afford
     */
    ItemStack constructItem(Item item, int qty);

    /**
     * Adds the item to the player's knowledge
     * @param item The item to learn
     * @return True if the item was learned, false if it was already known
     */
    boolean learnItem(Item item);

    /**
     * Removes the item from the player's knowledge
     * @param item The item to forget
     * @return True if the item was forgotten, false if the item wasn't known
     */
    boolean forgetItem(Item item);

    /**
     * Checks if the player has knowledge of the item
     * @param item The item to check
     * @return True if the player has the knowledge, false if they don't
     */
    boolean hasKnowledge(Item item);
}
