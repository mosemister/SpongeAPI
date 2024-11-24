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
package org.spongepowered.api.block.transaction;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.world.LocatableBlock;
import org.spongepowered.math.vector.Vector3i;

/**
 * Represents a notification that is being proposed to the engine.
 */
public interface NotificationTicket {

    /**
     * Gets the notifier block that scheduled this notification.
     *
     * @return The notifier block
     */
    LocatableBlock notifier();

    /**
     * Gets the notifier position that scheduled this notification.
     *
     * @return The notifier position
     */
    default Vector3i notifierPosition() {
        return this.notifier().blockPosition();
    }

    /**
     * Gets the target block of this notification.
     *
     * @return The target block
     */
    BlockSnapshot target();

    /**
     * Gets the target position of this notification.
     *
     * @return The target position
     */
    default Vector3i targetPosition() {
        return this.target().position();
    }

    /**
     * Gets whether this ticket is marked as valid.
     *
     * @return The valid state of this ticket
     */
    boolean valid();

    /**
     * Sets whether this ticket is valid or not.
     *
     * @param valid The valid state of this ticket
     */
    void setValid(boolean valid);

    /**
     * Invalidates this ticket.
     */
    default void invalidate() {
        this.setValid(false);
    }

}
