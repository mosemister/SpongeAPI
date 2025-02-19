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
package org.spongepowered.api.event.block.entity;

import org.spongepowered.api.block.entity.carrier.BrewingStand;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.item.inventory.AffectSlotEvent;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;


/**
 * Fires during the brewing process where {@link ItemStack}s are brewed into different {@link ItemStack}s
 * based on an ingredient which is also an {@link ItemStack} within a {@link BrewingStand}.
 */
public interface BrewingEvent extends Event {

    /**
     * Gets the {@link BrewingStand}.
     *
     * @return The brewing stand
     */
    BrewingStand brewingStand();

    /**
     * Gets the {@link ItemStackSnapshot} as the ingredient used.
     *
     * @return The ingredient
     */
    ItemStackSnapshot ingredient();

    /**
     * Fired when the brewing process starts.
     */
    interface Start extends BrewingEvent, Cancellable {}

    /**
     * Fired when a fuel item is consumed to refill the fuel bar.
     */
    interface ConsumeFuel extends BrewingEvent, AffectSlotEvent, Cancellable {
    }

    /**
     * Fired every tick while brewing.
     */
    interface Tick extends BrewingEvent, Cancellable {}

    /**
     * Fires when brewing is interrupted.
     */
    interface Interrupt extends BrewingEvent {}

    /**
     * Fires when brewing finished.
     */
    interface Finish extends BrewingEvent, AffectSlotEvent {
    }
}
