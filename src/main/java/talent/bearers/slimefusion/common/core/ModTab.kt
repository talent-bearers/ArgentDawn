package talent.bearers.slimefusion.common.core

import com.teamwizardry.librarianlib.common.base.ModCreativeTab
import net.minecraft.item.ItemStack
import talent.bearers.slimefusion.common.items.ModItems

/**
 * @author WireSegal
 * Created at 10:37 PM on 12/23/16.
 */
object ModTab : ModCreativeTab() {
    init {
        registerDefaultTab()
    }

    override val iconStack = ItemStack(ModItems.GEM, 1, EnumPolestone.ZIRCON.ordinal)
}
