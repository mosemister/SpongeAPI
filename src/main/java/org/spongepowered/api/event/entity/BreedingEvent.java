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
package org.spongepowered.api.event.entity;

import org.spongepowered.api.entity.Ageable;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.animal.Animal;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.TristateResult;

public interface BreedingEvent extends Event, Cancellable {

    /**
     * Called when an {@link Animal} has made it known it is ready to breed.
     */
    interface ReadyToMate extends BreedingEvent {

        Animal entity();
    }

    /**
     * Called when an {@link Animal} finds an {@link Animal} to mate with.
     */
    interface FindMate extends BreedingEvent, TristateResult {

        /**
         * Returns the {@link Animal} this entity will mate with.
         *
         * @return the mate
         */
        Animal matingEntity();
    }

    /**
     * Called when an {@link Animal} begins to breed with an {@link Animal}.
     */
    interface Breed extends BreedingEvent {

        /**
         * Gets the offspring {@link Entity}.
         *
         * @return the offspring
         */
        Ageable offspringEntity();
    }
}
