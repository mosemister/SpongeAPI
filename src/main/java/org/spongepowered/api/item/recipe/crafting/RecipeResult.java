/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.item.recipe.crafting;

import org.spongepowered.api.item.inventory.ItemStackLike;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * The result of fulfilling a {@link CraftingRecipe}.
 */
public final class RecipeResult {

    private final ItemStackSnapshot result;
    private final List<ItemStackSnapshot> remainingItems;

    /**
     * @deprecated Use {@link #RecipeResult(ItemStackLike, List)} instead.
     */
    @Deprecated(forRemoval = true)
    public RecipeResult(ItemStackSnapshot result, List<ItemStackSnapshot> remainingItems) {
        this((ItemStackLike) result, (List<? extends ItemStackLike>) remainingItems);
    }

    /**
     * Creates a new {@link RecipeResult}.
     *
     * <p>Note that this may be replaced with a static of method in the
     * future.</p>
     *
     * @param result The result of the crafting recipe
     * @param remainingItems The remaining items to leave in the
     *     crafting window
     */
    public RecipeResult(ItemStackLike result, List<? extends ItemStackLike> remainingItems) {
        Objects.requireNonNull(result, "result");
        if (result.isEmpty()) {
            throw new IllegalArgumentException("The result must not be empty!");
        }
        Objects.requireNonNull(remainingItems, "remainingItems");
        if (remainingItems.isEmpty()) {
            throw new IllegalArgumentException("The remainingItems list must not be empty."
                + " It should contain empty ItemStackSnapshot values for slots which should be cleared.");
        }

        this.result = result.asImmutable();
        this.remainingItems = remainingItems.stream().map(ItemStackLike::asImmutable).toList();
    }

    /**
     * This method should be used instead of the
     * {@link CraftingRecipe#exemplaryResult()} method, as it customizes the
     * result further depending on the specified ingredient
     * {@link ItemStackSnapshot}. It is advised to use the output of
     * {@link CraftingRecipe#exemplaryResult()}, modify it accordingly, and
     * {@code return} it.
     *
     * @return The result of fulfilling the requirements of a
     *         {@link CraftingRecipe}
     */
    public ItemStackSnapshot result() {
        return this.result;
    }

    /**
     * Returns a list of {@link ItemStackSnapshot} to be set in the input
     * {@link CraftingGridInventory}, contains {@link ItemStackSnapshot#empty()} for
     * slots which should be cleared.
     *
     * @return A list of {@link ItemStackSnapshot}s to be set in the input
     *         {@link CraftingGridInventory}
     */
    public List<ItemStackSnapshot> remainingItems() {
        return this.remainingItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final RecipeResult that = (RecipeResult) o;

        return this.result.equals(that.result) && this.remainingItems.equals(that.remainingItems);
    }

    @Override
    public int hashCode() {
        int result1 = this.result.hashCode();
        result1 = 31 * result1 + this.remainingItems.hashCode();

        return result1;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecipeResult.class.getSimpleName() + "[", "]")
            .add("result=" + this.result)
            .add("remainingItems=" + this.remainingItems)
            .toString();
    }
}
