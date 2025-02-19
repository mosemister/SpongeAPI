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
package org.spongepowered.api.item.inventory;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.data.DataHolderBuilder;
import org.spongepowered.api.data.SerializableDataHolder;
import org.spongepowered.api.data.persistence.DataView;
import org.spongepowered.api.entity.attribute.AttributeModifier;
import org.spongepowered.api.entity.attribute.type.AttributeType;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.registry.DefaultedRegistryReference;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Represents mutable {@link ItemStackLike}. Can be compared
 * using the comparators listed in {@link ItemStackComparators}.
 */
public interface ItemStack extends ItemStackLike, SerializableDataHolder.Mutable {

    /**
     * Returns an empty {@link ItemStack}.
     *
     * @return The empty ItemStack
     */
    static ItemStack empty() {
        return Sponge.game().factoryProvider().provide(Factory.class).empty();
    }

    /**
     * Creates a new {@link Builder} to build an {@link ItemStack}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.game().builderProvider().provide(Builder.class);
    }

    /**
     * Creates a new {@link ItemStack} of the provided {@link ItemType}
     * and quantity.
     *
     * @param itemType The item type
     * @param quantity The quantity
     * @return The new item stack
     */
    static ItemStack of(Supplier<? extends ItemType> itemType, int quantity) {
        return ItemStack.of(itemType.get(), quantity);
    }

    /**
     * Creates a new {@link ItemStack} of the provided {@link ItemType}
     * and quantity.
     *
     * @param itemType The item type
     * @param quantity The quantity
     * @return The new item stack
     */
    static ItemStack of(ItemType itemType, int quantity) {
        return ItemStack.builder().itemType(itemType).quantity(quantity).build();
    }

    /**
     * Creates a new {@link ItemStack} of the provided {@link ItemType} and quantity of 1
     *
     * @param itemType The item type
     * @return The new item stack
     */
    static ItemStack of(Supplier<? extends ItemType> itemType) {
        return ItemStack.of(itemType.get());
    }

    /**
     * Creates a new {@link ItemStack} of the provided {@link ItemType} and quantity of 1
     *
     * @param itemType The item type
     * @return The new item stack
     */
    static ItemStack of(ItemType itemType) {
        return ItemStack.of(itemType, 1);
    }

    /**
     * Sets the quantity in this stack.
     *
     * @param quantity Quantity
     * @throws IllegalArgumentException If quantity set exceeds the
     * {@link ItemStack#maxStackQuantity()}
     */
    void setQuantity(int quantity) throws IllegalArgumentException;

    /**
     * @deprecated Use {@link #asImmutable()} instead.
     */
    @Deprecated(forRemoval = true)
    default ItemStackSnapshot createSnapshot() {
        return this.asImmutable();
    }

    /**
     * Returns true if the specified {@link ItemStack} has the same stack
     * size, {@link ItemType}, and data. Note that this method is not an
     * overrider of {@link Object#equals(Object)} in order to maintain
     * compatibility with the base game. Therefore, ItemStacks may not behave
     * as expected when using them in equality based constructs such as
     * {@link Map}s or {@link Set}s.
     *
     * @param that ItemStack to compare
     * @return True if this equals the ItemStack
     */
    boolean equalTo(ItemStack that);

    /**
     * Adds an {@link AttributeModifier} to this item stack.
     *
     * @param attributeType The attribute type.
     * @param modifier The attribute modifier.
     * @param equipmentType The equipment type this modifier will apply under.
     */
    default void addAttributeModifier(Supplier<? extends AttributeType> attributeType, AttributeModifier modifier, EquipmentType equipmentType) {
        this.addAttributeModifier(attributeType.get(), modifier, equipmentType);
    }

    /**
     * Adds an {@link AttributeModifier} to this item stack.
     *
     * @param attributeType The attribute type.
     * @param modifier The attribute modifier.
     * @param equipmentType The equipment type this modifier will apply under.
     */
    default void addAttributeModifier(AttributeType attributeType, AttributeModifier modifier, DefaultedRegistryReference<? extends EquipmentType> equipmentType) {
        this.addAttributeModifier(attributeType, modifier, equipmentType.get());
    }

    /**
     * Adds an {@link AttributeModifier} to this item stack.
     *
     * @param attributeType The attribute type.
     * @param modifier The attribute modifier.
     * @param equipmentType The equipment type this modifier will apply under.
     */
    default void addAttributeModifier(Supplier<? extends AttributeType> attributeType, AttributeModifier modifier, DefaultedRegistryReference<? extends EquipmentType> equipmentType) {
        this.addAttributeModifier(attributeType.get(), modifier, equipmentType.get());
    }

    /**
     * Adds an {@link AttributeModifier} to this item stack.
     *
     * @param attributeType The attribute type.
     * @param modifier The attribute modifier.
     * @param equipmentType The equipment type this modifier will apply under.
     */
    void addAttributeModifier(AttributeType attributeType, AttributeModifier modifier, EquipmentType equipmentType);

    @Override
    ItemStack copy();

    interface Builder extends DataHolderBuilder.Mutable<ItemStack, Builder> {

        /**
         * Sets the {@link ItemType} of the item stack.
         *
         * @param itemType The type of item
         * @return This builder, for chaining
         */
        Builder itemType(ItemType itemType);

        /**
         * Sets the {@link ItemType} of the item stack.
         *
         * @param itemType The type of item
         * @return This builder, for chaining
         */
        default Builder itemType(final Supplier<? extends ItemType> itemType) {
            return this.itemType(itemType.get());
        }

        ItemType currentItem();

        /**
         * Sets the quantity of the item stack.
         *
         * @param quantity The quantity of the item stack
         * @return This builder, for chaining
         * @throws IllegalArgumentException If the quantity is outside the
         *      allowed bounds
         */
        Builder quantity(int quantity) throws IllegalArgumentException;

        /**
         * Sets all the settings in this builder from the item stack blueprint.
         *
         * @param itemStack The item stack to copy
         * @return This builder, for chaining
         */
        Builder fromItemStack(ItemStack itemStack);

        /**
         * Adds an {@link AttributeModifier} to this item stack.
         *
         * @param attributeType The Attribute type.
         * @param modifier The Attribute modifier.
         * @param equipmentType The equipment type this
         * {@link AttributeModifier} will apply to.
         * @return This builder, for chaining
         */
        default Builder attributeModifier(Supplier<? extends AttributeType> attributeType, AttributeModifier modifier, DefaultedRegistryReference<? extends EquipmentType> equipmentType) {
            return this.attributeModifier(attributeType.get(), modifier, equipmentType.get());
        }

        /**
         * Adds an {@link AttributeModifier} to this item stack.
         *
         * @param attributeType The Attribute type.
         * @param modifier The Attribute modifier.
         * @param equipmentType The equipment type this
         * {@link AttributeModifier} will apply to.
         * @return This builder, for chaining
         */
        Builder attributeModifier(AttributeType attributeType, AttributeModifier modifier, EquipmentType equipmentType);

        /**
         * Sets the data to recreate a {@link BlockState} in a held {@link ItemStack}
         * state.
         *
         * @param blockState The block state to use
         * @return This builder, for chaining
         */
        Builder fromBlockState(final BlockState blockState);

        /**
         * Sets the data to recreate a {@link BlockState} in a held {@link ItemStack}
         * state.
         *
         * @param blockState The block state to use
         * @return This builder, for chaining
         */
        default Builder fromBlockState(final Supplier<? extends BlockState> blockState) {
            Objects.requireNonNull(blockState, "blockState");
            return this.fromBlockState(blockState.get());
        }

        /**
         * Attempts to reconstruct the builder with all of the data from
         * {@link ItemStack#toContainer()} including all custom data.
         *
         * @param container The container to translate
         * @return This builder, for chaining
         */
        Builder fromContainer(DataView container);

        /**
         * Reconstructs this builder to use the {@link ItemStackSnapshot}
         * for all the values and data it may contain.
         *
         * @param snapshot The snapshot
         * @return This builder, for chaining
         */
        default Builder fromSnapshot(final ItemStackSnapshot snapshot) {
            return this.fromItemStack(snapshot.asMutable());
        }

        /**
         * Attempts to reconstruct a {@link BlockSnapshot} including all data
         * and {@link BlockEntity} related data if necessary for creating an
         * {@link ItemStack} representation.
         *
         * @param blockSnapshot The snapshot to use
         * @return This builder, for chaining
         */
        Builder fromBlockSnapshot(BlockSnapshot blockSnapshot);

        default Builder apply(Predicate<Builder> predicate, Consumer<Builder> consumer) {
            if (predicate.test(this)) {
                consumer.accept(this);
            }
            return this;
        }

        /**
         * Builds an instance of an ItemStack.
         *
         * @return A new instance of an ItemStack
         * @throws IllegalStateException If the item stack is not completed
         */
        @Override
        ItemStack build() throws IllegalStateException;
    }

    interface Factory {

        ItemStack empty();
    }
}
