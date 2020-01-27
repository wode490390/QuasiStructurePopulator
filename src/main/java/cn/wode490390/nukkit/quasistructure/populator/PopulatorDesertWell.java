package cn.wode490390.nukkit.quasistructure.populator;

import cn.nukkit.block.BlockSlabStone;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.biome.EnumBiome;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitRandom;
import cn.wode490390.nukkit.quasistructure.QuasiStructurePopulator;

public class PopulatorDesertWell extends Populator {

    @Override
    public void populate(ChunkManager level, int chunkX, int chunkZ, NukkitRandom random, FullChunk chunk) {
        if (chunk.getBiomeId(7, 7) == EnumBiome.DESERT.id && random.nextBoundedInt(1000) == 0) {
            int x = (chunkX << 4) + random.nextBoundedInt(16);
            int z = (chunkZ << 4) + random.nextBoundedInt(16);
            int y = getHighestWorkableBlock(level, x, z, chunk);

            if (level.getBlockIdAt(x, y, z) != SAND) {
                return;
            }
            for (int dx = -2; dx <= 2; ++dx) {
                for (int dz = -2; dz <= 2; ++dz) {
                    if (level.getBlockIdAt(x + dx, y - 1, z + dz) == AIR && level.getBlockIdAt(x + dx, y - 2, z + dz) == AIR) {
                        return;
                    }
                }
            }

            for (int dy = -1; dy <= 0; ++dy) {
                for (int dx = -2; dx <= 2; ++dx) {
                    for (int dz = -2; dz <= 2; ++dz) {
                        level.setBlockAt(x + dx, y + dy, z + dz, SANDSTONE);
                    }
                }
            }
            level.setBlockAt(x, y, z, WATER);
            level.setBlockAt(x - 1, y, z, WATER);
            level.setBlockAt(x + 1, y, z, WATER);
            level.setBlockAt(x, y, z - 1, WATER);
            level.setBlockAt(x, y, z + 1, WATER);
            for (int dx = -2; dx <= 2; ++dx) {
                for (int dz = -2; dz <= 2; ++dz) {
                    if (dx == -2 || dx == 2 || dz == -2 || dz == 2) {
                        level.setBlockAt(x + dx, y + 1, z + dz, SANDSTONE);
                    }
                }
            }
            level.setBlockAt(x + 2, y + 1, z, STONE_SLAB, BlockSlabStone.SANDSTONE);
            level.setBlockAt(x - 2, y + 1, z, STONE_SLAB, BlockSlabStone.SANDSTONE);
            level.setBlockAt(x, y + 1, z + 2, STONE_SLAB, BlockSlabStone.SANDSTONE);
            level.setBlockAt(x, y + 1, z - 2, STONE_SLAB, BlockSlabStone.SANDSTONE);
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    if (dx == 0 && dz == 0) {
                        level.setBlockAt(x + dx, y + 4, z + dz, SANDSTONE);
                    } else {
                        level.setBlockAt(x + dx, y + 4, z + dz, STONE_SLAB, BlockSlabStone.SANDSTONE);
                    }
                }
            }
            for (int dy = 1; dy <= 3; ++dy) {
                level.setBlockAt(x - 1, y + dy, z - 1, SANDSTONE);
                level.setBlockAt(x - 1, y + dy, z + 1, SANDSTONE);
                level.setBlockAt(x + 1, y + dy, z - 1, SANDSTONE);
                level.setBlockAt(x + 1, y + dy, z + 1, SANDSTONE);
            }

            QuasiStructurePopulator.debug(getClass().getSimpleName(), x, y, z);
        }
    }
}
