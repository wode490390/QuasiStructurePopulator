package cn.wode490390.nukkit.quasistructure.loot;

import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import cn.nukkit.math.NukkitRandom;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class DungeonChest extends RandomizableContainer {

    private static final Map<List<ItemEntry>, RollEntry> pools = Maps.newHashMap();

    static {
        PoolBuilder pool1 = new PoolBuilder()
                .register(new ItemEntry(Item.SADDLE, 20))
                .register(new ItemEntry(Item.GOLDEN_APPLE, 15))
                .register(new ItemEntry(Item.GOLDEN_APPLE, 1, 2))
                .register(new ItemEntry(Item.RECORD_13, 15))
                .register(new ItemEntry(Item.RECORD_CAT, 15))
                .register(new ItemEntry(Item.NAME_TAG, 20))
                .register(new ItemEntry(Item.GOLD_HORSE_ARMOR, 10))
                .register(new ItemEntry(Item.IRON_HORSE_ARMOR, 15))
                .register(new ItemEntry(Item.DIAMOND_HORSE_ARMOR, 5))
                .register(new ItemEntry(Item.ENCHANTED_BOOK, 10)); //TODO: ench nbt
        pools.put(pool1.build(), new RollEntry(3, 1, pool1.getTotalWeight()));

        PoolBuilder pool2 = new PoolBuilder()
                .register(new ItemEntry(Item.IRON_INGOT, 0, 4, 1, 10))
                .register(new ItemEntry(Item.GOLD_INGOT, 0, 4, 1, 5))
                .register(new ItemEntry(Item.BREAD, 20))
                .register(new ItemEntry(Item.WHEAT, 0, 4, 1, 20))
                .register(new ItemEntry(Item.BUCKET, 10))
                .register(new ItemEntry(Item.REDSTONE, 0, 4, 1, 15))
                .register(new ItemEntry(Item.COAL, 0, 4, 1, 15))
                .register(new ItemEntry(Item.MELON_SEEDS, 0, 4, 2, 10))
                .register(new ItemEntry(Item.PUMPKIN_SEEDS, 0, 4, 2, 10))
                .register(new ItemEntry(Item.BEETROOT_SEEDS, 0, 4, 2, 10));
        pools.put(pool2.build(), new RollEntry(4, 1, pool2.getTotalWeight()));

        PoolBuilder pool3 = new PoolBuilder()
                .register(new ItemEntry(Item.BONE, 0, 8, 1, 10))
                .register(new ItemEntry(Item.GUNPOWDER, 0, 8, 1, 10))
                .register(new ItemEntry(Item.ROTTEN_FLESH, 0, 8, 1, 10))
                .register(new ItemEntry(Item.STRING, 0, 8, 1, 10));
        pools.put(pool3.build(), new RollEntry(3, pool3.getTotalWeight()));
    }

    public DungeonChest(Inventory inventory, NukkitRandom random) {
        super(inventory, random);
    }

    @Override
    public Map<List<ItemEntry>, RollEntry> getPools() {
        return pools;
    }
}
