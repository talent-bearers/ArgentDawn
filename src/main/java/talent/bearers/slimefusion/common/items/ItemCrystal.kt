package talent.bearers.slimefusion.common.items

import com.teamwizardry.librarianlib.common.base.item.ItemMod
import talent.bearers.slimefusion.common.core.EnumMetalType
import talent.bearers.slimefusion.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 4:51 PM on 12/29/16.
 */
class ItemCrystal : ItemMod(LibNames.CRYSTAL, *EnumMetalType.getNamesFor(LibNames.CRYSTAL))
