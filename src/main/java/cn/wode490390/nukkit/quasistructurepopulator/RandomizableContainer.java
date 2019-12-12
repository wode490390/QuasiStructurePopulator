package cn.wode490390.nukkit.quasistructurepopulator;

import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import cn.nukkit.math.NukkitRandom;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

public abstract class RandomizableContainer {

    protected final Inventory inventory;
    protected final NukkitRandom random;

    public RandomizableContainer(Inventory inventory, NukkitRandom random) {
        this.inventory = inventory;
        this.random = random;
    }

    public void create() {
        this.getPools().forEach((pool, roll) -> {
            for (int i = roll.getMin() == -1 ? roll.getMax() : this.random.nextRange(roll.getMin(), roll.getMax()); i > 0; --i) {
                int result = this.random.nextBoundedInt(roll.getTotalWeight());
                for (ItemEntry entry : pool) {
                    result -=  entry.getWeight();
                    if (result < 0) {
                        this.inventory.setItem(this.random.nextBoundedInt(this.inventory.getSize()), Item.get(entry.getId(), entry.getMeta(), this.random.nextRange(entry.getMinCount(), entry.getMaxCount())), false);
                        break;
                    }
                }
            }
        });
    }

    public abstract Map<List<ItemEntry>, RollEntry> getPools();

    protected static class RollEntry {

        private int max;
        private int min;
        private int totalWeight;

        public RollEntry(int max, int totalWeight) {
            this(max, -1, totalWeight);
        }

        public RollEntry(int max, int min, int totalWeight) {
            this.max = max;
            this.min = min;
            this.totalWeight = totalWeight;
        }

        public int getMax() {
            return this.max;
        }

        public int getMin() {
            return this.min;
        }

        public int getTotalWeight() {
            return this.totalWeight;
        }
    }

    protected static class ItemEntry {

        private int id;
        private int meta;
        private int maxCount;
        private int minCount;
        private int weight;

        public ItemEntry(int id, int weight) {
            this(id, 0, weight);
        }

        public ItemEntry(int id, int meta, int weight) {
            this(id, meta, 1, weight);
        }

        public ItemEntry(int id, int meta, int maxCount, int weight) {
            this(id, meta, maxCount, 0, weight);
        }

        public ItemEntry(int id, int meta, int maxCount, int minCount, int weight) {
            this.id = id;
            this.meta = meta;
            this.maxCount = maxCount;
            this.minCount = minCount;
            this.weight = weight;
        }

        public int getId() {
            return this.id;
        }

        public int getMeta() {
            return this.meta;
        }

        public int getMaxCount() {
            return this.maxCount;
        }

        public int getMinCount() {
            return this.minCount;
        }

        public int getWeight() {
            return this.weight;
        }
    }

    protected static class PoolBuilder {

        private List<ItemEntry> pool = Lists.newArrayList();
        private int totalWeight = 0;

        public PoolBuilder register(ItemEntry entry) {
            this.pool.add(entry);
            this.totalWeight += entry.getWeight();
            return this;
        }

        public List<ItemEntry> build() {
            return this.pool;
        }

        public int getTotalWeight() {
            return this.totalWeight;
        }
    }
}
