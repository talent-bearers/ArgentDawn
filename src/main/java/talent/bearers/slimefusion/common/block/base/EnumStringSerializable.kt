package talent.bearers.slimefusion.common.block.base

import net.minecraft.util.IStringSerializable

/**
 * @author WireSegal
 * Created at 10:03 AM on 12/25/16.
 */
interface EnumStringSerializable : IStringSerializable {
    override fun getName() = (this as Enum<*>).name.toLowerCase()
}
