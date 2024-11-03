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
package org.spongepowered.api.item.recipe.smithing;

import org.spongepowered.api.Sponge;

import java.util.function.Supplier;

public interface ArmorTrim {

    static ArmorTrim of(TrimMaterial material, TrimPattern pattern) {
        return Sponge.game().factoryProvider().provide(Factory.class).create(material, pattern);
    }

    static ArmorTrim of(Supplier<? extends TrimMaterial> material, Supplier<? extends TrimPattern> pattern) {
        return Sponge.game().factoryProvider().provide(Factory.class).create(material, pattern);
    }
    static ArmorTrim of(TrimMaterial material, Supplier<? extends TrimPattern> pattern) {
        return Sponge.game().factoryProvider().provide(Factory.class).create(material, pattern);
    }

    static ArmorTrim of(Supplier<? extends TrimMaterial> material, TrimPattern pattern) {
        return Sponge.game().factoryProvider().provide(Factory.class).create(material, pattern);
    }

    TrimMaterial material();

    TrimPattern pattern();

    interface Factory {

        ArmorTrim create(TrimMaterial material, TrimPattern pattern);

        default ArmorTrim create(Supplier<? extends TrimMaterial> material, Supplier<? extends TrimPattern> pattern) {
            return create(material.get(), pattern.get());
        }

        default ArmorTrim create(Supplier<? extends TrimMaterial> material, TrimPattern pattern) {
            return create(material.get(), pattern);
        }

        default ArmorTrim create(TrimMaterial material, Supplier<? extends TrimPattern> pattern) {
            return create(material, pattern.get());
        }

    }
}
