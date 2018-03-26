/*
 *
 *   Copyright 2015-2017 Vladimir Bukhtoyarov
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.bucket4j;


/**
 * A builder for buckets. Builder can be reused, i.e. one builder can create multiple buckets with similar configuration.
 *
 */
public class AbstractBucketBuilder<T extends AbstractBucketBuilder> {

    private final ConfigurationBuilder configurationBuilder;
    private BucketListener listener;

    protected AbstractBucketBuilder() {
        configurationBuilder = new ConfigurationBuilder();
        listener = BucketListener.NOPE;
    }

    /**
     * Adds limited bandwidth for all buckets which will be constructed by this builder instance.
     *
     * @param bandwidth limitation
     * @return this builder instance
     */
    public T addLimit(Bandwidth bandwidth) {
        configurationBuilder.addLimit(bandwidth);
        return (T) this;
    }

    // TODO javadocs
    public T withListener(BucketListener listener) {
        if (listener == null) {
            // TODO add test
            throw BucketExceptions.nullListener();
        }
        this.listener = listener;
        return (T) this;
    }

    public BucketConfiguration buildConfiguration() {
        return configurationBuilder.build();
    }

    public BucketListener getListener() {
        return listener;
    }

}