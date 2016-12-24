package talent.bearers.slimefusion.common.items

import com.teamwizardry.librarianlib.common.base.item.ItemMod
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import talent.bearers.slimefusion.common.core.EnumPolestone
import talent.bearers.slimefusion.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 10:23 PM on 12/23/16.
 */
class ItemJewel : ItemMod(LibNames.JEWEL, *EnumPolestone.getNamesFor(LibNames.JEWEL)) {
    override fun getSubItems(itemIn: Item, tab: CreativeTabs?, subItems: MutableList<ItemStack>) {
        variants.indices.forEach {
            subItems.add(ItemStack(this, 1, it))
        }
    }
}
