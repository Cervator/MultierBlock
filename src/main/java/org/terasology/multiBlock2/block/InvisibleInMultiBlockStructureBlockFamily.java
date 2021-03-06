/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.multiBlock2.block;

import org.terasology.math.Side;
import org.terasology.math.geom.Vector3i;
import org.terasology.naming.Name;
import org.terasology.world.BlockEntityRegistry;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.AbstractBlockFamily;

import java.util.Arrays;

public class InvisibleInMultiBlockStructureBlockFamily extends AbstractBlockFamily implements VisibilityEnabledBlockFamily {
    private static final Name VISIBLE_NAME = new Name("visible");
    private static final Name INVISIBLE_NAME = new Name("invisible");
    private Block visibleBlock;
    private Block invisibleBlock;

    public InvisibleInMultiBlockStructureBlockFamily(BlockUri uri, Iterable<String> categories,
                                                     Block visibleBlock, Block invisibleBlock) {
        super(uri, categories);
        this.visibleBlock = visibleBlock;
        this.invisibleBlock = invisibleBlock;

        visibleBlock.setUri(new BlockUri(uri, VISIBLE_NAME));
        visibleBlock.setBlockFamily(this);
        invisibleBlock.setUri(new BlockUri(uri, INVISIBLE_NAME));
        invisibleBlock.setBlockFamily(this);
    }

    @Override
    public Block getBlockForPlacement(WorldProvider worldProvider, BlockEntityRegistry blockEntityRegistry, Vector3i location, Side attachmentSide, Side direction) {
        return visibleBlock;
    }

    @Override
    public Block getArchetypeBlock() {
        return visibleBlock;
    }

    @Override
    public Block getBlockFor(BlockUri blockUri) {
        if (blockUri.getIdentifier().equals(VISIBLE_NAME)) {
            return visibleBlock;
        } else {
            return invisibleBlock;
        }
    }

    @Override
    public Block getInvisibleBlock(Block currentBlock) {
        return invisibleBlock;
    }

    @Override
    public Block getVisibleBlock(Block currentBlock) {
        return visibleBlock;
    }

    @Override
    public Iterable<Block> getBlocks() {
        return Arrays.asList(visibleBlock, invisibleBlock);
    }
}
