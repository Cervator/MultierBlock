/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.multiBlock;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.Region3i;
import org.terasology.math.Vector3i;
import org.terasology.world.block.Block;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcin Sciesinski <marcins78@gmail.com>
 */
public class UniformBlockReplacementCallback<T> implements MultiBlockCallback<T> {
    private Block block;

    public UniformBlockReplacementCallback(Block block) {
        this.block = block;
    }

    @Override
    public Map<Vector3i, Block> getReplacementMap(Region3i region, T designDetails) {
        Map<Vector3i, Block> result = new HashMap<>();
        for (Vector3i location : region) {
            result.put(location, block);
        }

        return result;
    }

    @Override
    public void multiBlockFormed(Region3i region, EntityRef entity, T designDetails) {
    }
}
