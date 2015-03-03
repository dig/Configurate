/**
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ninja.leaping.configurate;

import com.google.common.base.Supplier;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.util.MapFactories;

import java.util.concurrent.ConcurrentMap;

/**
 * This object is a holder for general configuration options. This is meant to hold options
 * that are used in configuring how the configuration data structures are handled, rather than the serialization configuration that is located in {@link ConfigurationLoader}s
 */
public class ConfigurationOptions {
    private final Supplier<ConcurrentMap<Object, SimpleConfigurationNode>> mapSupplier;
    private final String header;

    private ConfigurationOptions(Supplier<ConcurrentMap<Object, SimpleConfigurationNode>> mapSupplier, String header) {
        this.mapSupplier = mapSupplier;
        this.header = header;
    }

    /**
     * Create a new options object with defaults set
     *
     * @return A new default options object
     */
    public static ConfigurationOptions defaults() {
        return new ConfigurationOptions(MapFactories.<SimpleConfigurationNode>insertionOrdered(), null);
    }

    /**
     * Get the key comparator currently being used for this configuration
     *
     * @return The active key comparator
     */
    @SuppressWarnings("unchecked")
    public Supplier<ConcurrentMap<Object, ? extends ConfigurationNode>> getMapFactory() {
        return (Supplier) mapSupplier;
    }

    /**
     * Return a new options object with the provided option set.
     *
     * @param factory The new factory to use to create a map
     * @return The new options object
     */
    @SuppressWarnings("unchecked")
    public ConfigurationOptions setMapFactory(Supplier<ConcurrentMap<Object, ConfigurationNode>> factory) {
        return new ConfigurationOptions((Supplier) factory, header);
    }

    /**
     * Get the header used for this configuration
     *
     * @return The current header. Lines are split by \n,
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * Set the header that will be written to a file if
     * @param header The new header to use for the configuration
     * @return The map's header
     */
    public ConfigurationOptions setHeader(String header) {
        return new ConfigurationOptions(mapSupplier, header);
    }
}
