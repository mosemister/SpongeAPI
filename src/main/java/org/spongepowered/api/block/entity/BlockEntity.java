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
package org.spongepowered.api.block.entity;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.SerializableDataHolder;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.util.annotation.DoNotStore;
import org.spongepowered.api.util.mirror.Mirror;
import org.spongepowered.api.util.rotation.Rotation;
import org.spongepowered.api.world.Locatable;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.api.world.schematic.Schematic;
import org.spongepowered.api.world.server.ServerLocation;

import java.util.function.Supplier;

/**
 * Represents a block entity. It is a functional block that is
 * continuously updated while residing in a world. It can perform specific
 * functions based on the data that it contains.
 *
 * <p>A {@link BlockEntity} is contained within a {@link ServerLocation} and will
 * continue to exists so long as the {@link ServerLocation} is of the correct
 * block type.</p>
 *
 * <p>Since a {@link BlockEntity} is performing various actions, all methods
 * that are purely functional methods reside in the {@link BlockEntity}, whereas
 * customizable data associated with a {@link BlockEntity} is represented by
 * {@link Value Values}.</p>
 */
@DoNotStore
public interface BlockEntity extends SerializableDataHolder.Mutable, Locatable {

    /**
     * Returns whether this block entity has been removed.
     *
     * @return True if this block entity has been removed
     */
    boolean isRemoved();

    /**
     * Removes this block entity from the world and its corresponding block.
     */
    void remove();

    /**
     * Returns whether this block entity can tick.
     *
     * @return True if this block entity can tick
     */
    boolean canTick();

    /**
     * Returns whether this block entity is ticking.
     *
     * @return True if this block entity is ticking
     */
    boolean isTicking();

    /**
     * Attempts to set if this block entity will naturally tick.
     *
     * <p>This will return <code>false</code>
     * if {@link #isRemoved()} returns <code>true</code>
     * or {@link #canTick()} returns <code>false</code></p>
     *
     * @param ticking The ticking state
     * @return True if ticking state was successfully set
     */
    boolean setTicking(boolean ticking);

    /**
     * Gets the type of {@link BlockEntity} this is.
     *
     * @return The type of block entity
     */
    BlockEntityType type();

    /**
     * Gets the {@link BlockState} that this {@link BlockEntity} represents.
     *
     * @return The blockstate
     */
    BlockState block();

    /**
     * Rotates this {@link BlockEntity} for the desired {@link Rotation}.
     *
     * @param rotation The rotation
     * @return The rotated state if not this state
     */
    BlockEntity rotate(Rotation rotation);

    /**
     * Rotates this {@link BlockEntity} for the desired {@link Rotation}.
     *
     * @param rotation The rotation
     * @return The rotated state if not this state
     */
    default BlockEntity rotate(final Supplier<? extends Rotation> rotation) {
        return this.rotate(rotation.get());
    }

    /**
     * Gets the appropriate {@link BlockEntity} for the desired {@link Mirror}.
     *
     * @param mirror The mirror
     * @return The mirrored BlockEntity
     */
    BlockEntity mirror(Mirror mirror);

    /**
     * Gets the appropriate {@link BlockEntity} for the desired {@link Mirror}.
     *
     * @param mirror The mirror
     * @return The mirrored BlockEntity
     */
    default BlockEntity mirror(final Supplier<? extends Mirror> mirror) {
        return this.mirror(mirror.get());
    }

    /**
     * Creates a new {@link BlockEntityArchetype} for use with {@link Schematic}s
     * and placing the archetype in multiple locations.
     *
     * @return The created archetype for re-creating this block entity
     */
    BlockEntityArchetype createArchetype();

    /**
     * Creates a {@link LocatableBlock} for this {@link BlockEntity}. Can be used
     * as a simpler method of making them. Since this does not persist the
     * data of this {@link BlockEntity}, it should not be used in place of a
     * {@link BlockSnapshot} where data is being safely cloned.
     *
     * @return The created locatable block, not as a block snapshot
     */
    LocatableBlock locatableBlock();

    /**
     * Creates a new {@link BlockEntityArchetype} for use with {@link Schematic}s
     * and placing the archetype in multiple locations.
     *
     * @return The created archetype for re-creating this block entity
     */
    @Override
    BlockEntity copy();
}
